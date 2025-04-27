package br.com.fiap.product_management.controller.dto;

import br.com.fiap.product_management.domain.Product;

import java.time.LocalDate;

public record ProductDto(Long id, String name, String description, String sku, Double price) {

    public ProductDto(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getSKU(), product.getPrice());
    }

}
