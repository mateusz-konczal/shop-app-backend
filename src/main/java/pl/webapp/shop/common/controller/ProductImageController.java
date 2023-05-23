package pl.webapp.shop.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.common.service.ProductImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/products/productImage")
@RequiredArgsConstructor
class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping("/{filename}")
    ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {
        Resource file = productImageService.serveFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(filename)))
                .body(file);
    }
}
