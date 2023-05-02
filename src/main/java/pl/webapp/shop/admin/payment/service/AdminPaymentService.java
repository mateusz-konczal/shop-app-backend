package pl.webapp.shop.admin.payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.admin.common.model.AdminPayment;
import pl.webapp.shop.admin.payment.repository.AdminPaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPaymentService {

    private final AdminPaymentRepository paymentRepository;

    public List<AdminPayment> getPayments() {
        return paymentRepository.findAll();
    }

    public AdminPayment getPayment(Long id) {
        return paymentRepository.findById(id).orElseThrow();
    }

    public AdminPayment createPayment(AdminPayment payment) {
        return paymentRepository.save(payment);
    }

    public AdminPayment updatePayment(AdminPayment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
