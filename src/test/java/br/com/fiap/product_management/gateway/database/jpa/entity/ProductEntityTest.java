package br.com.fiap.product_management.gateway.database.jpa.entity;

import br.com.fiap.product_management.domain.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductEntityTest {

    private final Long defaultId = 1L;
    private final String defaultName = "Caneca";
    private final String defaultDescription = "Caneca Stan Branca";
    private final String defaultSku = "250417-0001";
    private final Double defaultPrice = 199.99;
    private final Product product = new Product(defaultId, defaultName, defaultDescription, defaultSku, defaultPrice);

    @Test
    void createsProductEntity() {
        ProductEntity productEntity = new ProductEntity(product);

        assertEquals(product.getId(), productEntity.getId());
        assertEquals(product.getName(), productEntity.getName());
        assertEquals(product.getDescription(), productEntity.getDescription());
        assertEquals(product.getSKU(), productEntity.getSKU());
        assertEquals(product.getPrice(), productEntity.getPrice());
    }

    @Test
    void updatesProductEntity() {
        ProductEntity productEntity = new ProductEntity(product);

        Long newId = 2L;
        String newName = "Caderno";
        String newDescription = "Caderno Tilib Azul";
        String newSku = "250417-0002";
        Double newPrice = 99.99;

        productEntity.setId(newId);
        productEntity.setName(newName);
        productEntity.setDescription(newDescription);
        productEntity.setSKU(newSku);
        productEntity.setPrice(newPrice);

        assertEquals(productEntity.getId(), newId);
        assertEquals(productEntity.getName(), newName);
        assertEquals(productEntity.getDescription(), newDescription);
        assertEquals(productEntity.getSKU(), newSku);
        assertEquals(productEntity.getPrice(), newPrice);
    }

}
