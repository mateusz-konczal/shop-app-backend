package pl.webapp.shop.admin.shipment.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import pl.webapp.shop.admin.shipment.model.AdminShipmentType;

import java.math.BigDecimal;

public record AdminShipmentDto(

        @NotBlank
        @Length(min = 4)
        String name,

        @NotNull
        @Min(0)
        BigDecimal price,

        AdminShipmentType type,

        boolean defaultShipment) {
}
