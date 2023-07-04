package pl.webapp.shop.admin.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.admin.common.model.AdminProduct;
import pl.webapp.shop.admin.common.repository.AdminProductRepository;
import pl.webapp.shop.admin.common.repository.AdminReviewRepository;
import pl.webapp.shop.admin.common.service.AdminProductCachingService;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductRepository productRepository;
    private final AdminReviewRepository reviewRepository;
    private final AdminProductCachingService productCachingService;

    public Page<AdminProduct> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public AdminProduct getProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public AdminProduct createProduct(AdminProduct product) {
        clearProductCache(product);
        return productRepository.save(product);
    }

    public AdminProduct updateProduct(AdminProduct product) {
        clearProductCache(productRepository.findById(product.getId()).orElseThrow());
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        clearCacheBeforeDeletingProduct(id);
        reviewRepository.deleteAllByProductId(id);
        productRepository.deleteById(id);
    }

    private void clearCacheBeforeDeletingProduct(Long id) {
        AdminProduct product = productRepository.findById(id).orElseThrow();
        clearProductCache(product);
        productCachingService.clearProductDetailsCache(product);
    }

    private void clearProductCache(AdminProduct product) {
        productCachingService.clearCacheOfCategoryWithProducts();
        productCachingService.clearProductsCache();

        if (product.getSalePrice() != null) {
            productCachingService.clearHomepageCache();
        }
    }
}
