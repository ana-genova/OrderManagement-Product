package br.com.fiap.product_management.domain;

import io.micrometer.common.util.StringUtils;

import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private String description;
    private final String sku;
    private Double price;

    public Product(Long id, String name, String description, String sku, Double price) {
        validateProductId(id);
        validateProductName(name);
        validateProductPrice(price);

        this.id = id;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
    }

    public Product(String name, String description, String sku, Double price) {
        validateProductName(name);
        validateProductPrice(price);

        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
    }

    private void validateProductId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id is required");
        }
    }

    private void validateProductName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name is required");
        }
    }

    private void validateProductPrice(Double price) {
        if (price == null) {
            throw new IllegalArgumentException("Price is required");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSKU() {
        return sku;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product updateFields(Product newFields) {
        if (!Objects.equals(this.id, newFields.id)) {
            throw new IllegalArgumentException("Id cannot be changed");
        }

        if (this.sku != null && !this.sku.equals(newFields.sku)) {
            throw new IllegalArgumentException("SKU cannot be changed");
        }

        this.name = newFields.name;
        this.description = newFields.description;
        this.price = newFields.price;

        return this;
    }
}
