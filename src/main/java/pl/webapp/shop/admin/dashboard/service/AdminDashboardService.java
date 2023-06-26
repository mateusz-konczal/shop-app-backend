package pl.webapp.shop.admin.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.webapp.shop.admin.common.model.AdminProduct;
import pl.webapp.shop.admin.common.repository.AdminProductRepository;

@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final AdminProductRepository productRepository;

    public Page<AdminProduct> getSaleProducts(Pageable pageable) {
        return productRepository.findAllBySalePriceIsNotNull(pageable);
    }
}
