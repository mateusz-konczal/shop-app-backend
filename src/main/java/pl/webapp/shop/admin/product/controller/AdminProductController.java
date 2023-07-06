package pl.webapp.shop.admin.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.webapp.shop.admin.common.model.AdminProduct;
import pl.webapp.shop.admin.product.controller.dto.AdminProductDto;
import pl.webapp.shop.admin.product.controller.dto.UploadResponse;
import pl.webapp.shop.admin.product.exception.FileNotUploadedException;
import pl.webapp.shop.admin.product.service.AdminProductImageService;
import pl.webapp.shop.admin.product.service.AdminProductService;

import java.io.IOException;
import java.io.InputStream;

import static pl.webapp.shop.admin.product.controller.mapper.AdminProductMapper.mapToAdminProduct;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
class AdminProductController {

    private static final Long EMPTY_ID = null;
    private final AdminProductService productService;
    private final AdminProductImageService productImageService;

    @GetMapping
    Page<AdminProduct> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }

    @GetMapping("/{id}")
    AdminProduct getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AdminProduct createProduct(@RequestBody @Valid AdminProductDto adminProductDto) {
        return productService.createProduct(mapToAdminProduct(adminProductDto, EMPTY_ID));
    }

    @PutMapping("/{id}")
    @CacheEvict(cacheNames = "productDetails", key = "#adminProductDto.slug")
    public AdminProduct updateProduct(@RequestBody @Valid AdminProductDto adminProductDto, @PathVariable Long id) {
        return productService.updateProduct(mapToAdminProduct(adminProductDto, id));
    }

    @PutMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void enableProduct(@PathVariable Long id) {
        productService.enableProduct(id);
    }

    @PutMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void disableProduct(@PathVariable Long id) {
        productService.disableProduct(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/uploadImage")
    @ResponseStatus(HttpStatus.CREATED)
    UploadResponse uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            String savedFilename = productImageService.uploadImage(multipartFile.getOriginalFilename(), inputStream);
            return new UploadResponse(savedFilename);
        } catch (IOException e) {
            throw new FileNotUploadedException(e.getMessage());
        }
    }
}
