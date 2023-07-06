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
import pl.webapp.shop.common.model.ProductCurrency;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private String fullDescription;
    private BigDecimal price;
    private BigDecimal salePrice;
    @Enumerated(EnumType.STRING)
    private ProductCurrency currency;
    private String image;
    private String slug;
    private boolean enabled;
}
