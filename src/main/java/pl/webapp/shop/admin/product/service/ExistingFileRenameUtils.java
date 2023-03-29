package pl.webapp.shop.admin.product.service;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;

class ExistingFileRenameUtils {

    private ExistingFileRenameUtils() {
    }

    static String renameIfExists(Path uploadDir, String filename) {
        if (Files.exists(uploadDir.resolve(filename))) {
            return renameAndCheck(uploadDir, filename);
        }

        return filename;
    }

    private static String renameAndCheck(Path uploadDir, String filename) {
        String newFilename = renameFile(filename);
        if (Files.exists(uploadDir.resolve(newFilename))) {
            newFilename = renameAndCheck(uploadDir, newFilename);
        }

        return newFilename;
    }

    private static String renameFile(String filename) {
        String name = FilenameUtils.getBaseName(filename);
        String[] splitName = name.split("-(?=\\d+$)");
        int counter = splitName.length > 1 ? Integer.parseInt(splitName[1]) + 1 : 1;

        return splitName[0] + "-" + counter + "." + FilenameUtils.getExtension(filename);
    }
}
