package pl.webapp.shop.order.service.mapper;

import pl.webapp.shop.order.model.Order;

import java.time.format.DateTimeFormatter;

public class OrderMailContentMapper {

    private OrderMailContentMapper() {
    }

    public static String createMailContent(Order order) {
        return "Szanowny Kliencie," +
                "\nw naszym sklepie internetowym zarejestrowaliśmy Twoje zamówienie." +
                "\n\nNumer zamówienia: " + order.getId() +
                "\nData złożenia: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "\nKwota całkowita: " + order.getTotalValue() + " PLN" +
                "\n\nForma płatności: " + order.getPayment().getName() +
                (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\nDziękujemy za zakupy.";
    }
}
