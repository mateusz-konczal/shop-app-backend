package pl.webapp.shop.order.service.payment.p24;

record TransactionRegisterResponse(

        Data data,
        Integer responseCode) {

    record Data(String token) {
    }
}
