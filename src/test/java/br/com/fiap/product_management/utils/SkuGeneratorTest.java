package br.com.fiap.product_management.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SkuGeneratorTest {

    @Test
    void skuGenerateValidValue() {
        String value = SkuGenerator.generateSKU(40000L);

        assertNotNull(value);
        Assertions.assertThat(value.length()).isEqualTo(12);
    }
}
