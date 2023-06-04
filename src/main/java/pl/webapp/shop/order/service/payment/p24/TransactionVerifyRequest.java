package pl.webapp.shop.order.service.payment.p24;

import lombok.Builder;

@Builder
record TransactionVerifyRequest(

        Integer merchantId,
        Integer posId,
        String sessionId,
        Integer amount,
        String currency,
        Long orderId,
        String sign) {
}
