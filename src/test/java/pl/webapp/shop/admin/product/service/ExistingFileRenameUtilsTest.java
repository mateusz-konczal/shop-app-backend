package pl.webapp.shop.admin.product.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ExistingFileRenameUtilsTest {

    @Test
    void shouldNotRenameFile(@TempDir Path tempDir) {
        // WHEN
        String newName = ExistingFileRenameUtils.renameIfExists(tempDir, "test.png");
        // THEN
        assertThat(newName).isEqualTo("test.png");
    }

    @Test
    void shouldRenameIfFileAlreadyExists(@TempDir Path tempDir) throws IOException {
        // GIVEN
        Files.createFile(tempDir.resolve("test.png"));
        // WHEN
        String newName = ExistingFileRenameUtils.renameIfExists(tempDir, "test.png");
        // THEN
        assertThat(newName).isEqualTo("test-1.png");
    }

    @Test
    void shouldRenameIfMultipleFilesAlreadyExist(@TempDir Path tempDir) throws IOException {
        // GIVEN
        Files.createFile(tempDir.resolve("test.png"));
        Files.createFile(tempDir.resolve("test-1.png"));
        Files.createFile(tempDir.resolve("test-2.png"));
        Files.createFile(tempDir.resolve("test-3.png"));
        // WHEN
        String newName = ExistingFileRenameUtils.renameIfExists(tempDir, "test.png");
        // THEN
        assertThat(newName).isEqualTo("test-4.png");
    }

    @Test
    void shouldIncrementLastNumberIfFilenameContainsSeveralNumbers(@TempDir Path tempDir) throws IOException {
        // GIVEN
        Files.createFile(tempDir.resolve("test-test-3-1.jpg"));
        // WHEN
        String newName = ExistingFileRenameUtils.renameIfExists(tempDir, "test-test-3-1.jpg");
        // THEN
        assertThat(newName).isEqualTo("test-test-3-2.jpg");
    }

    @ParameterizedTest
    @CsvSource({
            "@TempDir Path tempDir, test-test-3-1-test.jpg, test-test-3-1-test.jpg",
            "@TempDir Path tempDir, test-test-3-1-2test.jpg, test-test-3-1-2test.jpg",
            "@TempDir Path tempDir, test-test-3-1-te2st.jpg, test-test-3-1-te2st.jpg",
            "@TempDir Path tempDir, test-test-3-1-test2.jpg, test-test-3-1-test2.jpg"
    })
    void shouldNotIncrementNumberIfFilenameDoesNotEndWithNumber(Path tempDir, String inputFilename, String outputFilename) {
        // WHEN
        String newName = ExistingFileRenameUtils.renameIfExists(tempDir, inputFilename);
        // THEN
        assertThat(newName).isEqualTo(outputFilename);
    }
}
