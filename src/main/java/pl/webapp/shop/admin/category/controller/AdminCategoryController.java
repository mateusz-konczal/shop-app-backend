package pl.webapp.shop.admin.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.category.controller.dto.AdminCategoryDto;
import pl.webapp.shop.admin.category.model.AdminCategory;
import pl.webapp.shop.admin.category.service.AdminCategoryService;

import java.util.List;

import static pl.webapp.shop.admin.category.controller.mapper.AdminCategoryMapper.mapToAdminCategory;

@RestController
@RequestMapping("/api/v1/admin/categories")
@RequiredArgsConstructor
class AdminCategoryController {

    private static final Long EMPTY_ID = null;
    private final AdminCategoryService categoryService;

    @GetMapping
    List<AdminCategory> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    AdminCategory getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    AdminCategory createCategory(@RequestBody @Valid AdminCategoryDto adminCategoryDto) {
        return categoryService.createCategory(mapToAdminCategory(adminCategoryDto, EMPTY_ID));
    }

    @PutMapping("/{id}")
    AdminCategory updateCategory(@RequestBody @Valid AdminCategoryDto adminCategoryDto, @PathVariable Long id) {
        return categoryService.updateCategory(mapToAdminCategory(adminCategoryDto, id));
    }

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
