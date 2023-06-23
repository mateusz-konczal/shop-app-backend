package pl.webapp.shop.admin.common.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.webapp.shop.common.model.ShipmentType;

import java.math.BigDecimal;

@Entity
@Table(name = "shipments")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ShipmentType type;
    private boolean defaultShipment;
    private boolean enabled;
}
