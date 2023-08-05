package pl.webapp.shop.homepage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomepageService {

    private final ProductRepository productRepository;

    public List<Product> getSaleProducts(String sort) {
        return switch (sort) {
            case "name,asc" -> productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrueOrderByNameAsc();
            case "name,desc" -> productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrueOrderByNameDesc();
            case "salePrice,asc" -> productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrueOrderBySalePriceAsc();
            case "salePrice,desc" -> productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrueOrderBySalePriceDesc();
            default -> productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrue();
        };
    }
}
