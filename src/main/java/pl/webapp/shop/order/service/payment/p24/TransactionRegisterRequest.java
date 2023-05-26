package pl.webapp.shop.order.service.payment.p24;

import lombok.Builder;

@Builder
record TransactionRegisterRequest(

        Integer merchantId,
        Integer posId,
        String sessionId,
        Integer amount,
        String currency,
        String description,
        String email,
        String client,
        String country,
        String language,
        String urlReturn,
        String sign,
        String encoding) {
}
