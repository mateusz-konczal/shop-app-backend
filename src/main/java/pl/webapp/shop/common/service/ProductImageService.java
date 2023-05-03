package pl.webapp.shop.common.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {

    @Value("${app.uploadDir}")
    private String uploadDir;

    public Resource serveFile(String filename) {
        return new FileSystemResourceLoader().getResource(uploadDir + filename);
    }
}
