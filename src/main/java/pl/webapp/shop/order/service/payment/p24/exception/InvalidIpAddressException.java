package pl.webapp.shop.order.service.payment.p24.exception;

public class InvalidIpAddressException extends RuntimeException {

    public InvalidIpAddressException(String serverAddr) {
        super("Invalid IP address for transaction notification: " + serverAddr);
    }
}
