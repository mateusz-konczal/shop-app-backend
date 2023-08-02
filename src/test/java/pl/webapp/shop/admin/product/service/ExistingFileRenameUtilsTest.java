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
    void should_not_rename_file(@TempDir Path tempDir) {
        // WHEN
        String newName = ExistingFileRenameUtils.renameIfExists(tempDir, "test.png");
        // THEN
        assertThat(newName).isEqualTo("test.png");
    }

    @Test
    void should_rename_when_file_already_exists(@TempDir Path tempDir) throws IOException {
        // GIVEN
        Files.createFile(tempDir.resolve("test.png"));
        // WHEN
        String newName = ExistingFileRenameUtils.renameIfExists(tempDir, "test.png");
        // THEN
        assertThat(newName).isEqualTo("test-1.png");
    }

    @Test
    void should_rename_when_multiple_files_already_exist(@TempDir Path tempDir) throws IOException {
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
    void should_increment_last_number_when_filename_contains_several_numbers(@TempDir Path tempDir) throws IOException {
        // GIVEN
        Files.createFile(tempDir.resolve("test-test-3-1.jpg"));
        // WHEN
        String newName = ExistingFileRenameUtils.renameIfExists(tempDir, "test-test-3-1.jpg");
        // THEN
        assertThat(newName).isEqualTo("test-test-3-2.jpg");
    }

    @ParameterizedTest
    @CsvSource({
            "test-test-3-1-test.jpg, test-test-3-1-test.jpg",
            "test-test-3-1-2test.jpg, test-test-3-1-2test.jpg",
            "test-test-3-1-te2st.jpg, test-test-3-1-te2st.jpg",
            "test-test-3-1-test2.jpg, test-test-3-1-test2.jpg"
    })
    void should_not_increment_number_when_filename_does_not_end_with_number(String inputFilename, String outputFilename,
                                                                            @TempDir Path tempDir) {
        // WHEN
        String newName = ExistingFileRenameUtils.renameIfExists(tempDir, inputFilename);
        // THEN
        assertThat(newName).isEqualTo(outputFilename);
    }
}
