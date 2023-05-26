package pl.webapp.shop.order.service.payment.p24;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import pl.webapp.shop.common.model.ProductCurrency;
import pl.webapp.shop.order.model.Order;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentMethodP24 {

    private final PaymentMethodP24Config p24Config;

    public String initPayment(Order order) {
        log.info("Payment initialization");
        WebClient webClient = WebClient.builder()
                .filter(ExchangeFilterFunctions.basicAuthentication(p24Config.getPosId().toString(),
                        p24Config.isTestMode() ? p24Config.getTestSecretId() : p24Config.getSecretId()))
                .baseUrl(p24Config.isTestMode() ? p24Config.getTestApiUrl() : p24Config.getApiUrl())
                .build();

        ResponseEntity<TransactionRegisterResponse> response = webClient.post().uri("/transaction/register")
                .bodyValue(TransactionRegisterRequest.builder()
                        .merchantId(p24Config.getMerchantId())
                        .posId(p24Config.getPosId())
                        .sessionId(createSessionId(order))
                        .amount(createAmount(order))
                        .currency(ProductCurrency.PLN.name())
                        .description("Zamówienie o numerze " + order.getId())
                        .email(order.getEmail())
                        .client(order.getFirstName() + " " + order.getLastName())
                        .country("PL")
                        .language("pl")
                        .urlReturn(p24Config.isTestMode() ? p24Config.getTestUrlReturn() : p24Config.getUrlReturn())
                        .sign(createSign(order))
                        .encoding("UTF-8")
                        .build())
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

    private String createSessionId(Order order) {
        return "order_uuid_" + order.getUuid();
    }

    private Integer createAmount(Order order) {
        return order.getTotalValue().movePointRight(2).intValue();
    }

    private String createSign(Order order) {
        String json = "{\"sessionId\":\"" + createSessionId(order) +
                "\",\"merchantId\":" + p24Config.getMerchantId() +
                ",\"amount\":" + createAmount(order) +
                ",\"currency\":\"" + ProductCurrency.PLN.name() +
                "\",\"crc\":\"" + (p24Config.isTestMode() ? p24Config.getTestCrc() : p24Config.getCrc()) + "\"}";

        return DigestUtils.sha384Hex(json);
    }

    private String createLink(String token) {
        return (p24Config.isTestMode() ? p24Config.getTestUrl() : p24Config.getUrl()) + "/trnRequest/" + token;
    }
}
