package pl.webapp.shop.admin.order.service.mapper;

import pl.webapp.shop.common.model.OrderStatus;

public class AdminOrderMailContentMapper {

    private AdminOrderMailContentMapper() {
    }

    public static String createProcessingMailContent(Long orderId, OrderStatus newStatus) {
        return "Szanowny Kliencie," +
                "\nwłaśnie rozpoczęliśmy realizację Twojego zamówienia o numerze " + orderId + "." +
                "\n\nStatus zamówienia został zmieniony na: " + newStatus.getValue() + "." +
                "\nPo skompletowaniu zamówienia niezwłocznie przekażemy je do wysyłki." +
                "\n\nPozdrawiamy" +
                "\nSklep Shop";
    }

    public static String createCompletedMailContent(Long orderId, OrderStatus newStatus) {
        return "Szanowny Kliencie," +
                "\nTwoje zamówienie o numerze " + orderId + " zostało zrealizowane." +
                "\n\nStatus zamówienia został zmieniony na: " + newStatus.getValue() + "." +
                "\nDziękujemy Ci za wybór naszego sklepu i zapraszamy ponownie." +
                "\n\nPozdrawiamy" +
                "\nSklep Shop";
    }

    public static String createRefundMailContent(Long orderId, OrderStatus newStatus) {
        return "Szanowny Kliencie," +
                "\nTwoje zamówienie o numerze " + orderId + " zostało zwrócone." +
                "\n\nStatus zamówienia został zmieniony na: " + newStatus.getValue() + "." +
                "\n\nPozdrawiamy" +
                "\nSklep Shop";
    }
}
