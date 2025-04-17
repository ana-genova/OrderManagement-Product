package br.com.fiap.product_management.usecase;

import br.com.fiap.product_management.domain.Product;

public class UpdateProductUseCase {

    public static Product update(Product oldProduct, Product newProduct) {
        validateOldProduct(oldProduct);
        validateNewProduct(newProduct);
        validateChanges(oldProduct, newProduct);

        return oldProduct.updateFields(newProduct);
    }

    private static void validateOldProduct(Product oldProduct) {
        if (oldProduct == null) {
            throw new IllegalArgumentException("Old product is required");
        }
    }

    private static void validateNewProduct(Product newProduct) {
        if (newProduct == null) {
            throw new IllegalArgumentException("New product is required");
        }
    }

    private static void validateChanges(Product oldProduct, Product newProduct) {
        if (oldProduct.equals(newProduct)) {
            throw new IllegalArgumentException("Old product is equal to new product");
        }
    }

}
