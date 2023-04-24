package pl.webapp.shop.admin.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.webapp.shop.admin.product.model.AdminProduct;
import pl.webapp.shop.admin.shipment.model.AdminShipment;

import java.math.BigDecimal;

@Entity
@Table(name = "order_rows")
@Getter
@AllArgsConstructor
@NoArgsConstructor
class AdminOrderRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @OneToOne
    @JoinColumn(name = "productId")
    private AdminProduct product;
    private int quantity;
    private BigDecimal price;
    @OneToOne
    @JoinColumn(name = "shipmentId")
    private AdminShipment shipment;
}
