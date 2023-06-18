package pl.webapp.shop.admin.payment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.common.model.AdminPayment;
import pl.webapp.shop.admin.payment.controller.dto.AdminPaymentDto;
import pl.webapp.shop.admin.payment.service.AdminPaymentService;
import pl.webapp.shop.common.model.PaymentType;

import java.util.ArrayList;
import java.util.List;

import static pl.webapp.shop.admin.payment.controller.mapper.AdminPaymentMapper.mapToAdminPayment;

@RestController
@RequestMapping("/admin/payments")
@RequiredArgsConstructor
class AdminPaymentController {

    private static final Long EMPTY_ID = null;
    private final AdminPaymentService paymentService;

    @GetMapping
    List<AdminPayment> getPayments() {
        return paymentService.getPayments();
    }

    @GetMapping("/{id}")
    AdminPayment getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AdminPayment createPayment(@RequestBody @Valid AdminPaymentDto adminPaymentDto) {
        return paymentService.createPayment(mapToAdminPayment(adminPaymentDto, EMPTY_ID));
    }

    @PutMapping("/{id}")
    AdminPayment updatePayment(@RequestBody @Valid AdminPaymentDto adminPaymentDto, @PathVariable Long id) {
        return paymentService.updatePayment(mapToAdminPayment(adminPaymentDto, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }

    @GetMapping("/initTypes")
    List<String> getPaymentTypes() {
        return createPaymentTypesList();
    }

    private List<String> createPaymentTypesList() {
        List<String> paymentTypes = new ArrayList<>();
        for (PaymentType type : PaymentType.values()) {
            paymentTypes.add(type.name());
        }

        return paymentTypes;
    }
}
