package pl.webapp.shop.order.service.payment.p24;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.webapp.shop.order.dto.TransactionNotificationDto;
import pl.webapp.shop.order.model.Order;
import reactor.core.publisher.Mono;

import static pl.webapp.shop.order.service.payment.p24.RequestUtil.createRegisterRequest;
import static pl.webapp.shop.order.service.payment.p24.RequestUtil.createVerifyRequest;
import static pl.webapp.shop.order.service.payment.p24.RequestUtil.filterIpAddress;
import static pl.webapp.shop.order.service.payment.p24.RequestUtil.validateTransactionResult;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentMethodP24 {

    private final PaymentMethodP24Config p24Config;
    private final WebClient p24Client;

    public String registerPayment(Order order) {
        log.info("Payment registration");
        ResponseEntity<TransactionRegisterResponse> response = p24Client.post().uri("/transaction/register")
                .bodyValue(createRegisterRequest(p24Config, order))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> {
                            log.error("Something went wrong: " + clientResponse.statusCode().value());
                            return Mono.empty();
                        })
                .toEntity(TransactionRegisterResponse.class)
                .block();

        if (response != null && response.getBody() != null && response.getBody().data() != null) {
            return createRedirectUrl(response.getBody().data().token());
        }
        return null;
    }

    public String receiveNotification(Order order, TransactionNotificationDto notificationDto, String serverAddr) {
        log.info(notificationDto.toString());
        filterIpAddress(p24Config, serverAddr);
        validateTransactionResult(p24Config, order, notificationDto);

        return verifyPayment(order, notificationDto);
    }

    private String createRedirectUrl(String token) {
        return (p24Config.isTestMode() ? p24Config.getTestUrl() : p24Config.getUrl()) + "/trnRequest/" + token;
    }

    private String verifyPayment(Order order, TransactionNotificationDto notificationDto) {
        log.info("Payment verification");
        ResponseEntity<TransactionVerifyResponse> response = p24Client.put().uri("/transaction/verify")
                .bodyValue(createVerifyRequest(p24Config, order, notificationDto))
                .retrieve()
                .toEntity(TransactionVerifyResponse.class)
                .block();

        if (response != null && response.getBody() != null && response.getBody().data() != null) {
            log.info("Transaction verification status: " + response.getBody().data().status());
            return response.getBody().data().status();
        }
        return null;
    }
}
