package pl.webapp.shop.admin.dashboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.common.model.AdminProduct;
import pl.webapp.shop.admin.dashboard.service.AdminDashboardService;

@RestController
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
class AdminDashboardController {

    private final AdminDashboardService dashboardService;

    @GetMapping("/saleProducts")
    Page<AdminProduct> getSaleProducts(Pageable pageable) {
        return dashboardService.getSaleProducts(pageable);
    }

    @DeleteMapping("/saleProducts/{id}")
    void deleteSaleProduct(@PathVariable Long id) {
        dashboardService.deleteSaleProduct(id);
    }
}
