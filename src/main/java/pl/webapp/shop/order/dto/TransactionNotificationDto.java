package pl.webapp.shop.order.dto;

import pl.webapp.shop.common.model.ProductCurrency;

public record TransactionNotificationDto(

        Integer merchantId,
        Integer posId,
        String sessionId,
        Integer amount,
        Integer originAmount,
        ProductCurrency currency,
        Long orderId,
        Integer methodId,
        String statement,
        String sign) {
}
