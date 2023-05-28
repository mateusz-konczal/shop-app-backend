package pl.webapp.shop.order.service.payment.p24;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.webapp.shop.order.model.Order;
import reactor.core.publisher.Mono;

import static pl.webapp.shop.order.service.payment.p24.RequestUtil.createRegisterRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentMethodP24 {

    private final PaymentMethodP24Config p24Config;
    private final WebClient p24Client;

    public String initPayment(Order order) {
        log.info("Payment initialization");
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
            return createLink(response.getBody().data().token());
        }
        return null;
    }

    private String createLink(String token) {
        return (p24Config.isTestMode() ? p24Config.getTestUrl() : p24Config.getUrl()) + "/trnRequest/" + token;
    }
}
