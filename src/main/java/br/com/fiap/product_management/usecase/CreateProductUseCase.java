package br.com.fiap.product_management.usecase;

import br.com.fiap.product_management.domain.Product;

import java.time.LocalDate;

public class CreateProductUseCase {

    public static Product create(String name, String description, String sku, Double price, int quantity) {

        return new Product(name, description, sku, price, quantity);
    }

}
