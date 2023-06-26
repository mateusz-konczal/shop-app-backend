package pl.webapp.shop.admin.dashboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.common.model.AdminProduct;
import pl.webapp.shop.admin.dashboard.service.AdminCachingService;
import pl.webapp.shop.admin.dashboard.service.AdminDashboardService;

@RestController
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
class AdminDashboardController {

    private final AdminDashboardService dashboardService;
    private final AdminCachingService cachingService;

    @GetMapping("/saleProducts")
    Page<AdminProduct> getSaleProducts(Pageable pageable) {
        return dashboardService.getSaleProducts(pageable);
    }

    @GetMapping("/caches/clearAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void clearAllCaches() {
        cachingService.clearAllCaches();
    }
}
