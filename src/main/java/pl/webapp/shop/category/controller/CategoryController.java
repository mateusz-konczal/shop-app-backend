package pl.webapp.shop.category.controller;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.category.model.Category;
import pl.webapp.shop.category.model.dto.CategoryProductsDto;
import pl.webapp.shop.category.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Validated
class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{slug}/products")
    CategoryProductsDto getCategoryWithProducts(@PathVariable
                                                @Pattern(regexp = "[a-z0-9\\-]+")
                                                @Length(max = 255) String slug, Pageable pageable) {
        return categoryService.getCategoryWithProducts(slug, pageable);
    }
}
