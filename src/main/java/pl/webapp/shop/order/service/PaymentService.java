package pl.webapp.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.order.model.Payment;
import pl.webapp.shop.order.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> getPayments() {
        return paymentRepository.findAllByEnabledIsTrue();
    }
}
