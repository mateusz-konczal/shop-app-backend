package pl.webapp.shop.admin.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.product.controller.dto.AdminProductDto;
import pl.webapp.shop.admin.product.model.AdminProduct;
import pl.webapp.shop.admin.product.service.AdminProductService;

import static pl.webapp.shop.admin.product.controller.mapper.AdminProductMapper.mapToAdminProduct;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
class AdminProductController {

    public static final Long EMPTY_ID = null;
    private final AdminProductService productService;

    @GetMapping
    Page<AdminProduct> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }

    @GetMapping("/{id}")
    AdminProduct getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    AdminProduct createProduct(@RequestBody @Valid AdminProductDto adminProductDto) {
        return productService.createProduct(mapToAdminProduct(adminProductDto, EMPTY_ID));
    }

    @PutMapping("/{id}")
    AdminProduct updateProduct(@RequestBody @Valid AdminProductDto adminProductDto, @PathVariable Long id) {
        return productService.updateProduct(mapToAdminProduct(adminProductDto, id));
    }

}
