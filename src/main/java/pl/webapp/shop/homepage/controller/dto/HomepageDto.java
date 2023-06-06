package pl.webapp.shop.homepage.controller.dto;

import pl.webapp.shop.common.model.Product;

import java.util.List;

public record HomepageDto(List<Product> saleProducts) {
}
