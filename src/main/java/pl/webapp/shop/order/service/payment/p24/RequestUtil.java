package pl.webapp.shop.order.service.payment.p24;

import org.apache.commons.codec.digest.DigestUtils;
import pl.webapp.shop.common.model.ProductCurrency;
import pl.webapp.shop.order.model.Order;

class RequestUtil {

    private RequestUtil() {
    }

    static TransactionRegisterRequest createRegisterRequest(PaymentMethodP24Config p24Config, Order order) {
        return TransactionRegisterRequest.builder()
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
                .sign(createSign(order, p24Config))
                .encoding("UTF-8")
                .build();
    }

    private static String createSessionId(Order order) {
        return "order_uuid_" + order.getUuid();
    }

    private static Integer createAmount(Order order) {
        return order.getTotalValue().movePointRight(2).intValue();
    }

    private static String createSign(Order order, PaymentMethodP24Config p24Config) {
        String json = "{\"sessionId\":\"" + createSessionId(order) +
                "\",\"merchantId\":" + p24Config.getMerchantId() +
                ",\"amount\":" + createAmount(order) +
                ",\"currency\":\"" + ProductCurrency.PLN.name() +
                "\",\"crc\":\"" + (p24Config.isTestMode() ? p24Config.getTestCrc() : p24Config.getCrc()) + "\"}";

        return DigestUtils.sha384Hex(json);
    }
}
