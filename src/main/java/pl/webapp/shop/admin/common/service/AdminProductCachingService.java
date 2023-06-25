package pl.webapp.shop.admin.common.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.webapp.shop.admin.common.model.AdminProduct;

@Service
public class AdminProductCachingService {

    @CacheEvict(value = "categoryWithProducts", allEntries = true)
    public void clearCacheOfCategoryWithProducts() {
        // This method clears the category cache with paged products
    }

    @CacheEvict(value = "products", allEntries = true)
    public void clearProductsCache() {
        // This method clears the cache of paged products
    }

    @CacheEvict(value = "homepage", allEntries = true)
    public void clearHomepageCache() {
        // This method clears the homepage cache when a product contains a sale price
    }

    @CacheEvict(value = "productDetails", key = "#product.slug")
    public void clearProductDetailsCache(AdminProduct product) {
        // This method clears the details cache of the selected product
    }
}
