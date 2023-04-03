package pl.webapp.shop.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.category.repository.CategoryRepository;
import pl.webapp.shop.category.service.dto.CategoryProductsDto;
import pl.webapp.shop.common.dto.ProductReadDto;
import pl.webapp.shop.common.model.Category;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.repository.ProductRepository;

import java.util.List;

import static pl.webapp.shop.common.mapper.ProductReadDtoMapper.mapToProductReadDtoList;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoryProductsDto getCategoryWithProducts(String slug, Pageable pageable) {
        Category category = categoryRepository.findBySlug(slug).orElseThrow();
        Page<Product> products = productRepository.findAllByCategoryId(category.getId(), pageable);
        List<ProductReadDto> productReadDtoList = mapToProductReadDtoList(products);

        return new CategoryProductsDto(category, new PageImpl<>(productReadDtoList, pageable, products.getTotalElements()));
    }
}
