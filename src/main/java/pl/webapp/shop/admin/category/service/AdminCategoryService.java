package pl.webapp.shop.admin.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.admin.category.model.AdminCategory;
import pl.webapp.shop.admin.category.repository.AdminCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final AdminCategoryRepository categoryRepository;

    public List<AdminCategory> getCategories() {
        return categoryRepository.findAll();
    }

    public AdminCategory getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public AdminCategory createCategory(AdminCategory category) {
        return categoryRepository.save(category);
    }

    public AdminCategory updateCategory(AdminCategory category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
