package pl.webapp.shop.admin.product.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

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
}