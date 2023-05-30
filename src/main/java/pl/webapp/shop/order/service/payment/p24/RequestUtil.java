package pl.webapp.shop.order.service.payment.p24;

import org.apache.commons.codec.digest.DigestUtils;
import pl.webapp.shop.common.model.ProductCurrency;
import pl.webapp.shop.order.dto.TransactionNotificationDto;
import pl.webapp.shop.order.model.Order;

import java.math.BigDecimal;

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
                .urlReturn(createReturnUrl(p24Config, order))
                .urlStatus(createStatusUrl(p24Config, order))
                .sign(createRegisterSign(p24Config, order))
                .encoding("UTF-8")
                .build();
    }

    static void validateTransactionResult(PaymentMethodP24Config p24Config, Order order,
                                          TransactionNotificationDto notificationDto) {
        validateField(p24Config.getMerchantId().equals(notificationDto.merchantId()));
        validateField(p24Config.getPosId().equals(notificationDto.posId()));
        validateField(createSessionId(order).equals(notificationDto.sessionId()));
        validateField(order.getTotalValue().compareTo(createTotalValue(notificationDto.amount())) == 0);
        validateField(order.getTotalValue().compareTo(createTotalValue(notificationDto.originAmount())) == 0);
        validateField(ProductCurrency.PLN == notificationDto.currency());
        validateField(createReceivedSign(p24Config, order, notificationDto).equals(notificationDto.sign()));
    }

    static TransactionVerifyRequest createVerifyRequest(PaymentMethodP24Config p24Config, Order order,
                                                        TransactionNotificationDto notificationDto) {
        return TransactionVerifyRequest.builder()
                .merchantId(p24Config.getMerchantId())
                .posId(p24Config.getPosId())
                .sessionId(createSessionId(order))
                .amount(createAmount(order))
                .currency(ProductCurrency.PLN.name())
                .orderId(notificationDto.orderId())
                .sign(createVerifySign(p24Config, order, notificationDto))
                .build();
    }

    private static String createSessionId(Order order) {
        return "order_uuid_" + order.getUuid();
    }

    private static Integer createAmount(Order order) {
        return order.getTotalValue().movePointRight(2).intValue();
    }

    private static String createReturnUrl(PaymentMethodP24Config p24Config, Order order) {
        return (p24Config.isTestMode() ? p24Config.getTestUrlReturn() : p24Config.getUrlReturn()) +
                "/order/notification/" + order.getOrderHash();
    }

    private static String createStatusUrl(PaymentMethodP24Config p24Config, Order order) {
        return (p24Config.isTestMode() ? p24Config.getTestUrlStatus() : p24Config.getUrlStatus()) +
                p24Config.getContextPath() + "/orders/notification/" + order.getOrderHash();
    }

    private static String createRegisterSign(PaymentMethodP24Config p24Config, Order order) {
        String json = "{\"sessionId\":\"" + createSessionId(order) +
                "\",\"merchantId\":" + p24Config.getMerchantId() +
                ",\"amount\":" + createAmount(order) +
                ",\"currency\":\"" + ProductCurrency.PLN.name() +
                "\",\"crc\":\"" + (p24Config.isTestMode() ? p24Config.getTestCrc() : p24Config.getCrc()) + "\"}";

        return DigestUtils.sha384Hex(json);
    }

    private static void validateField(boolean condition) {
        if (!condition) {
            throw new RuntimeException("Invalid validation");
        }
    }

    private static BigDecimal createTotalValue(Integer amount) {
        return BigDecimal.valueOf(amount).movePointLeft(2);
    }

    private static String createReceivedSign(PaymentMethodP24Config p24Config, Order order,
                                             TransactionNotificationDto notificationDto) {
        String json = "{\"merchantId\":" + p24Config.getMerchantId() +
                ",\"posId\":" + p24Config.getPosId() +
                ",\"sessionId\":\"" + createSessionId(order) +
                "\",\"amount\":" + createAmount(order) +
                ",\"originAmount\":" + createAmount(order) +
                ",\"currency\":\"" + ProductCurrency.PLN.name() +
                "\",\"orderId\":" + notificationDto.orderId() +
                ",\"methodId\":" + notificationDto.methodId() +
                ",\"statement\":\"" + notificationDto.statement() +
                "\",\"crc\":\"" + (p24Config.isTestMode() ? p24Config.getTestCrc() : p24Config.getCrc()) + "\"}";

        return DigestUtils.sha384Hex(json);
    }

    private static String createVerifySign(PaymentMethodP24Config p24Config, Order order,
                                           TransactionNotificationDto notificationDto) {
        String json = "{\"sessionId\":\"" + createSessionId(order) +
                "\",\"orderId\":" + notificationDto.orderId() +
                ",\"amount\":" + createAmount(order) +
                ",\"currency\":\"" + ProductCurrency.PLN.name() +
                "\",\"crc\":\"" + (p24Config.isTestMode() ? p24Config.getTestCrc() : p24Config.getCrc()) + "\"}";

        return DigestUtils.sha384Hex(json);
    }
}
