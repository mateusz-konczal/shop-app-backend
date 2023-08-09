package pl.webapp.shop.homepage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
            case "name,asc" ->
                    productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrue(Sort.by("name").ascending());
            case "name,desc" ->
                    productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrue(Sort.by("name").descending());
            case "salePrice,asc" ->
                    productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrue(Sort.by("salePrice").ascending());
            case "salePrice,desc" ->
                    productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrue(Sort.by("salePrice").descending());
            default -> productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrue();
        };
    }
}
