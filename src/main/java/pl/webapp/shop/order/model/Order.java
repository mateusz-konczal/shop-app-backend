package pl.webapp.shop.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime placeDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal totalValue;
    private String firstName;
    private String lastName;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String zipCode;
    private String city;
    private String email;
    private String phone;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderRow> orderRows;
    @OneToOne
    private Payment payment;
}
