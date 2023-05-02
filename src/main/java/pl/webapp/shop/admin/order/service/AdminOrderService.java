package pl.webapp.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.admin.order.model.AdminOrder;
import pl.webapp.shop.admin.order.model.AdminOrderLog;
import pl.webapp.shop.admin.order.model.AdminOrderStatus;
import pl.webapp.shop.admin.order.repository.AdminOrderLogRepository;
import pl.webapp.shop.admin.order.repository.AdminOrderRepository;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final AdminOrderRepository orderRepository;
    private final AdminOrderLogRepository orderLogRepository;
    private final MailNotificationForStatusChange mailNotificationForStatusChange;

    public Page<AdminOrder> getOrders(Pageable pageable) {
        return orderRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("id").descending()));
    }

    public AdminOrder getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void patchOrder(Long id, Map<String, String> values) {
        AdminOrder order = orderRepository.findById(id).orElseThrow();
        patchValues(order, values);
    }

    private void patchValues(AdminOrder order, Map<String, String> values) {
        if (values.get("orderStatus") != null) {
            processOrderStatusChange(order, values);
        }
    }

    private void processOrderStatusChange(AdminOrder order, Map<String, String> values) {
        AdminOrderStatus oldStatus = order.getOrderStatus();
        AdminOrderStatus newStatus = AdminOrderStatus.valueOf(values.get("orderStatus"));
        if (newStatus != oldStatus) {
            order.setOrderStatus(newStatus);
            logOrderStatusChange(order.getId(), oldStatus, newStatus);
            mailNotificationForStatusChange.sendMailNotification(order, newStatus);
        }
    }

    private void logOrderStatusChange(Long orderId, AdminOrderStatus oldStatus, AdminOrderStatus newStatus) {
        orderLogRepository.save(AdminOrderLog.builder()
                .orderId(orderId)
                .created(LocalDateTime.now())
                .note("Zmiana statusu zamówienia z " + oldStatus.getValue() + " na " + newStatus.getValue())
                .build());
    }
}
