package pl.webapp.shop.admin.product.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class UploadedFilenameUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "test test.png, test-test.png",
            "hello world.jpg, hello-world.jpg",
            "ąęśćżźńłó.png, aesczznlo.png",
            "Produkt 1.jpg, produkt-1.jpg",
            "Produkt  1.jpg, produkt-1.jpg",
            "Produkt - 1.jpg, produkt-1.jpg",
            "Produkt__1.jpg, produkt-1.jpg",

    })
    void shouldSlugifyFilename(String in, String out) {
        // WHEN
        String filename = UploadedFilenameUtils.slugifyFilename(in);
        // THEN
        assertThat(filename).isEqualTo(out);
    }
}