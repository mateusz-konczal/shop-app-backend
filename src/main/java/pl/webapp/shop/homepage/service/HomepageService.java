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

    public List<Product> getSaleProducts() {
        return productRepository.findTop10BySalePriceIsNotNullAndEnabledIsTrue();
    }
}
