package pl.webapp.shop.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.category.model.Category;
import pl.webapp.shop.category.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryWithProducts(String slug) {
        return categoryRepository.findBySlug(slug).orElseThrow();
    }
}
