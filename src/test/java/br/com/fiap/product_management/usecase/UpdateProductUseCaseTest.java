package br.com.fiap.product_management.usecase;

import br.com.fiap.product_management.domain.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateProductUseCaseTest {
    
    private final String defaultSku = "250417-0001";
    private final Double defaultPrice = 199.99;
    private final Product defaultOldProduct = new Product("Caneca", "Caneca Stan Branca", defaultSku, defaultPrice);
    private final Product defaultNewProduct = new Product("Caderno", "Caderno Tilib Azul", defaultSku, defaultPrice);

    @Test
    void throwsExceptionWhenOldProductIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateProductUseCase.update(null, this.defaultNewProduct),
                "Old product is required"
        );
    }

    @Test
    void throwsExceptionWhenNewProductIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateProductUseCase.update(this.defaultOldProduct, null),
                "New product is required"
        );
    }

    @Test
    void throwsExceptionWhenChangeSKU() {
        Product newProduct = new Product("Caderno", "Caderno Tilib Azul", "250417-0002", defaultPrice);
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateProductUseCase.update(this.defaultOldProduct, newProduct),
                "SKU cannot be changed"
        );
    }

    @Test
    void throwsExceptionWhenChangeId() {
        Product newProduct = new Product(2L, "Caderno", "Caderno Tilib Azul", defaultSku, defaultPrice);
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateProductUseCase.update(this.defaultOldProduct, newProduct),
                "Id cannot be changed"
        );
    }

    @Test
    void throwsExceptionWhenOldProductIsEqualToNewProduct() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UpdateProductUseCase.update(this.defaultOldProduct, this.defaultOldProduct),
                "Old product is equal to new product"
        );
    }

    @Test
    void updatesProduct() {
        Product newProduct = new Product("Caderno", "Caderno Tilib Azul", null, defaultPrice);
        Product updatedProduct = UpdateProductUseCase.update(newProduct, this.defaultNewProduct);

        assertEquals(newProduct.getId(), updatedProduct.getId());
        assertEquals(newProduct.getName(), updatedProduct.getName());
        assertEquals(newProduct.getDescription(), updatedProduct.getDescription());
        assertEquals(newProduct.getSKU(), updatedProduct.getSKU());
        assertEquals(newProduct.getPrice(), updatedProduct.getPrice());
    }

    @Test
    void updatesProductWithSKU() {
        Product updatedProduct = UpdateProductUseCase.update(this.defaultOldProduct, this.defaultNewProduct);

        assertEquals(this.defaultOldProduct.getId(), updatedProduct.getId());
        assertEquals(this.defaultOldProduct.getName(), updatedProduct.getName());
        assertEquals(this.defaultOldProduct.getDescription(), updatedProduct.getDescription());
        assertEquals(this.defaultOldProduct.getSKU(), updatedProduct.getSKU());
        assertEquals(this.defaultOldProduct.getPrice(), updatedProduct.getPrice());
    }

}
