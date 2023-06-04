package pl.webapp.shop.order.service.payment.p24;

record TransactionVerifyResponse(

        Data data,
        Integer responseCode) {

    record Data(String status) {
    }
}
