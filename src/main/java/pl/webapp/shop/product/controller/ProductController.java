package pl.webapp.shop.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.product.model.Product;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
class ProductController {

    @GetMapping
    List<Product> getProducts() {
        return List.of(
                new Product("Nowy produkt 1", "Nowa kategoria 1", "Nowy opis 1", new BigDecimal("8.99"), "PLN"),
                new Product("Nowy produkt 2", "Nowa kategoria 2", "Nowy opis 2", new BigDecimal("9.99"), "PLN"),
                new Product("Nowy produkt 3", "Nowa kategoria 3", "Nowy opis 3", new BigDecimal("10.99"), "PLN"),
                new Product("Nowy produkt 4", "Nowa kategoria 4", "Nowy opis 4", new BigDecimal("11.99"), "PLN")
        );
    }
}
