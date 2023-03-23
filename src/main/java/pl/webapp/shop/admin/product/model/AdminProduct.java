package pl.webapp.shop.admin.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
public class AdminProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String description;
    private BigDecimal price;
    private String currency;
}
