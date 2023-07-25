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
        clearCacheContainingProducts();
        return productRepository.save(product);
    }

    public AdminProduct updateProduct(AdminProduct product) {
        clearCacheContainingProducts();
        return productRepository.save(product);
    }

    @Transactional
    public void enableProduct(Long id) {
        clearProductCache(id);
        productRepository.enableProductById(id);
    }

    @Transactional
    public void disableProduct(Long id) {
        clearProductCache(id);
        productRepository.disableProductById(id);
    }

    @Transactional
    public void deleteProduct(Long id) {
        clearProductCache(id);
        reviewRepository.deleteAllByProductId(id);
        productRepository.deleteById(id);
    }

    private void clearProductCache(Long id) {
        clearCacheContainingProducts();
        productCachingService.clearProductDetailsCache(productRepository.findById(id).orElseThrow());
    }

    private void clearCacheContainingProducts() {
        productCachingService.clearCacheOfCategoryWithProducts();
        productCachingService.clearProductsCache();
        productCachingService.clearHomepageCache();
    }
}
