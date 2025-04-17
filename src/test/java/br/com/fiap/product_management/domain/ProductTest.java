package br.com.fiap.product_management.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    private final String defaultName = "Caneca";
    private final String defaultDescription = "Caneca Stan Branca";
    private final String defaultSku = "250417-0001";
    private final Double defaultPrice = 199.99;
    private final int defaultQuantity = 5;

    @Test
    void throwsExceptionWhenIdIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product(null, this.defaultName, this.defaultDescription, this.defaultSku, this.defaultPrice, this.defaultQuantity),
                "Id is required"
        );
    }

    @Test
    void throwsExceptionWhenIdIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product(-9L, this.defaultName, this.defaultDescription, this.defaultSku, this.defaultPrice, this.defaultQuantity),
                "Id is required"
        );
    }

    @Test
    void throwsExceptionWhenNameIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product(null, this.defaultDescription, this.defaultSku, this.defaultPrice, this.defaultQuantity),
                "Name is required"
        );
    }

    @Test
    void throwsExceptionWhenNameIsEmpty() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product("", this.defaultDescription, this.defaultSku, this.defaultPrice, this.defaultQuantity),
                "Name is required"
        );
    }

    @Test
    void throwsExceptionWhenPriceIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product(this.defaultName, this.defaultDescription, this.defaultSku, null, this.defaultQuantity),
                "Price is required"
        );
    }

    @Test
    void throwsExceptionWhenPriceIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product(this.defaultName, this.defaultDescription, this.defaultSku, -5.0, this.defaultQuantity),
                "Price cannot be negative"
        );
    }

    @Test
    void throwsExceptionWhenQuantityIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product(this.defaultName, this.defaultDescription, this.defaultSku, this.defaultPrice, -4),
                "Quantity cannot be negative"
        );
    }

}
