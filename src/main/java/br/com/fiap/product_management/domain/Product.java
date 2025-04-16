package br.com.fiap.product_management.domain;

import io.micrometer.common.util.StringUtils;

public class Product {

    private Long id;
    private String name;
    private String description;
    private String sku;
    private Double price;
    private int quantity;

    public Product(Long id, String name, String description, String sku, Double price, int quantity) {
        validateProductId(id);
        validateProductName(name);
        validateProductSKU(sku);
        validateProductPrice(price);
        validateProductQuantity(quantity);

        this.id = id;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, String description, String sku, Double price, int quantity) {
        validateProductName(name);
        validateProductSKU(sku);
        validateProductPrice(price);
        validateProductQuantity(quantity);

        this.name = name;
        this.description = description;
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
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

    private void validateProductSKU(String sku) {
        if (StringUtils.isBlank(sku)) {
            throw new IllegalArgumentException("SKU is required");
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

    private void validateProductQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
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

    public int getQuantity() {
        return quantity;
    }
}
