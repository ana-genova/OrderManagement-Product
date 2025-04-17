package br.com.fiap.product_management.usecase;

import br.com.fiap.product_management.domain.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class CreateProductUseCaseTest {

    private final String defaultName = "Caneca";
    private final String defaultDescription = "Caneca Stan Branca";
    private final String defaultSku = "250417-0001";
    private final Double defaultPrice = 199.99;
    private final int defaultQuantity = 5;

    @Test
    void shouldCreateClient() {
        var result = CreateProductUseCase.create(this.defaultName, this.defaultDescription, this.defaultSku, this.defaultPrice, this.defaultQuantity);

        assertInstanceOf(Product.class, result);
    }

}
