package pl.webapp.shop.admin.common.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SlugifyUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "test test.png, test-test.png",
            "hello world.jpg, hello-world.jpg",
            "ąęśćżźńłó.png, aesczznlo.png",
            "Produkt 1.jpg, produkt-1.jpg",
            "Produkt  1.jpg, produkt-1.jpg",
            "Produkt - 1.jpg, produkt-1.jpg",
            "Produkt__1.jpg, produkt-1.jpg"
    })
    void should_slugify_filename(String in, String out) {
        // WHEN
        String filename = SlugifyUtils.slugifyFilename(in);
        // THEN
        assertThat(filename).isEqualTo(out);
    }
}
