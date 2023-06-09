package pl.webapp.shop.category.controller;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.category.service.CategoryService;
import pl.webapp.shop.category.service.dto.CategoryProductsDto;
import pl.webapp.shop.common.model.Category;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Cacheable("categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{slug}/products")
    @Cacheable("categoryWithProducts")
    public CategoryProductsDto getCategoryWithProducts(@PathVariable
                                                       @Pattern(regexp = "[a-z0-9\\-]+")
                                                       @Length(max = 255) String slug, Pageable pageable) {
        return categoryService.getCategoryWithProducts(slug, pageable);
    }
}
