package pl.webapp.shop.admin.common.utils;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;

public final class SlugifyUtils {

    private SlugifyUtils() {
    }

    public static String slugifyFilename(String filename) {
        String name = FilenameUtils.getBaseName(filename);
        Slugify slg = Slugify.builder()
                .customReplacement("_", "-")
                .build();
        String changedName = slg.slugify(name);

        return changedName + "." + FilenameUtils.getExtension(filename);
    }

    public static String slugifySlug(String slug) {
        Slugify slg = Slugify.builder()
                .customReplacement("_", "-")
                .build();

        return slg.slugify(slug);
    }
}
