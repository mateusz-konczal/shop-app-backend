package pl.webapp.shop.admin.order.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.order.model.AdminOrder;
import pl.webapp.shop.admin.order.service.AdminOrderExportService;
import pl.webapp.shop.common.model.OrderStatus;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin/orders/export")
@RequiredArgsConstructor
class AdminOrderExportController {

    private static final CSVFormat CSV_FORMAT = CSVFormat.Builder
            .create(CSVFormat.DEFAULT)
            .setHeader("Id", "PlaceDate", "OrderStatus", "TotalValue", "FirstName", "LastName", "Street",
                    "HouseNumber", "ApartmentNumber", "ZipCode", "City", "Email", "Phone", "PaymentName")
            .build();

    private final AdminOrderExportService orderExportService;

    @GetMapping
    ResponseEntity<Resource> exportOrders(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate to,
            @RequestParam OrderStatus orderStatus) {

        List<AdminOrder> orders = orderExportService.exportOrders(
                LocalDateTime.of(from, LocalTime.of(0, 0, 0)),
                LocalDateTime.of(to, LocalTime.of(23, 59, 59)),
                orderStatus);
        ByteArrayInputStream inputStream = transformToCsv(orders);
        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "orders.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }

    private ByteArrayInputStream transformToCsv(List<AdminOrder> orders) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             CSVPrinter printer = new CSVPrinter(new PrintWriter(outputStream), CSV_FORMAT)) {
            for (AdminOrder order : orders) {
                printer.printRecord(Arrays.asList(
                        order.getId(),
                        order.getPlaceDate(),
                        order.getOrderStatus(),
                        order.getTotalValue(),
                        order.getFirstName(),
                        order.getLastName(),
                        order.getStreet(),
                        order.getHouseNumber(),
                        order.getApartmentNumber(),
                        order.getZipCode(),
                        order.getCity(),
                        order.getEmail(),
                        order.getPhone(),
                        order.getPayment().getName()
                ));
            }
            printer.flush();
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("CSV parsing error: " + e.getMessage());
        }
    }
}
