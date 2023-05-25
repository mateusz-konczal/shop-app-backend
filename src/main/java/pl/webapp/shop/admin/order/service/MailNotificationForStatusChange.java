package pl.webapp.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.admin.order.model.AdminOrder;
import pl.webapp.shop.common.mail.MailClientService;
import pl.webapp.shop.common.model.OrderStatus;

import static pl.webapp.shop.admin.order.service.mapper.AdminOrderMailContentMapper.createCompletedMailContent;
import static pl.webapp.shop.admin.order.service.mapper.AdminOrderMailContentMapper.createProcessingMailContent;
import static pl.webapp.shop.admin.order.service.mapper.AdminOrderMailContentMapper.createRefundMailContent;

@Service
@RequiredArgsConstructor
class MailNotificationForStatusChange {

    private final MailClientService mailClientService;

    void sendMailNotification(AdminOrder order, OrderStatus newStatus) {
        if (newStatus == OrderStatus.PROCESSING) {
            sendMail(order.getEmail(),
                    "Twoje zamówienie nr " + order.getId() + " zmieniło status na: " + newStatus.getValue(),
                    createProcessingMailContent(order.getId(), newStatus));
        } else if (newStatus == OrderStatus.COMPLETED) {
            sendMail(order.getEmail(),
                    "Twoje zamówienie nr " + order.getId() + " zostało zrealizowane",
                    createCompletedMailContent(order.getId(), newStatus));
        } else if (newStatus == OrderStatus.REFUND) {
            sendMail(order.getEmail(),
                    "Twoje zamówienie nr " + order.getId() + " zostało zwrócone",
                    createRefundMailContent(order.getId(), newStatus));
        }
    }

    private void sendMail(String to, String subject, String content) {
        mailClientService.getInstance().send(to, subject, content);
    }
}
