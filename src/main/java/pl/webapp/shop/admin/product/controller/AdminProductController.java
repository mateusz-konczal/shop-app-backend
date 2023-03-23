package pl.webapp.shop.admin.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.product.model.AdminProduct;
import pl.webapp.shop.admin.product.service.AdminProductService;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
class AdminProductController {

    private final AdminProductService productService;

    @GetMapping
    Page<AdminProduct> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }
}
