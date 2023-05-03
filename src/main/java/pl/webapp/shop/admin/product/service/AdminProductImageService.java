package pl.webapp.shop.admin.product.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.webapp.shop.admin.common.utils.SlugifyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AdminProductImageService {

    @Value("${app.uploadDir}")
    private String uploadDir;

    public String uploadImage(String filename, InputStream inputStream) {
        String newFilename = SlugifyUtils.slugifyFilename(filename);
        newFilename = ExistingFileRenameUtils.renameIfExists(Path.of(uploadDir), newFilename);
        Path filepath = Paths.get(uploadDir).resolve(newFilename);

        try (OutputStream outputStream = Files.newOutputStream(filepath)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException("The file cannot be saved: ", e);
        }

        return newFilename;
    }
}
