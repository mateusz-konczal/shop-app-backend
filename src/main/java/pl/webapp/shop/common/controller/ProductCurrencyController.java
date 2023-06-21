package pl.webapp.shop.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.common.model.ProductCurrency;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/currencies")
class ProductCurrencyController {

    @GetMapping
    List<String> getProductCurrencies() {
        return createProductCurrenciesList();
    }

    private List<String> createProductCurrenciesList() {
        List<String> productCurrencies = new ArrayList<>();
        for (ProductCurrency currency : ProductCurrency.values()) {
            productCurrencies.add(currency.name());
        }

        return productCurrencies;
    }
}
