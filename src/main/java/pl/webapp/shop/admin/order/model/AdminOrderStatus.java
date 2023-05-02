package pl.webapp.shop.admin.order.model;

public enum AdminOrderStatus {

    NEW("Nowe"),
    PAID("Opłacone"),
    PROCESSING("Przetwarzane"),
    WAITING_FOR_DELIVERY("Oczekujące na dostawę"),
    COMPLETED("Zrealizowane"),
    CANCELED("Anulowane"),
    REFUND("Zwrócone");

    private final String value;

    AdminOrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
