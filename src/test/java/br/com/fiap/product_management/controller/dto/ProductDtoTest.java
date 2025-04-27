package br.com.fiap.product_management.controller.dto;

import br.com.fiap.product_management.domain.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductDtoTest {

    private final Long defaultId = 1L;
    private final String defaultName = "Caneca";
    private final String defaultDescription = "Caneca Stan Branca";
    private final String defaultSku = "250417-0001";
    private final Double defaultPrice = 199.99;
    private final Product product = new Product(defaultId, defaultName, defaultDescription, defaultSku, defaultPrice);

    @Test
    void createsProductEntity() {
        ProductDto productDto = new ProductDto(product);

        assertEquals(product.getId(), productDto.id());
        assertEquals(product.getName(), productDto.name());
        assertEquals(product.getDescription(), productDto.description());
        assertEquals(product.getSKU(), productDto.sku());
        assertEquals(product.getPrice(), productDto.price());
    }


}
