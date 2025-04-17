package br.com.fiap.product_management.gateway;

import br.com.fiap.product_management.domain.Product;

public interface ProductGateway {

    Product findBySKU(String sku);

    Product create(Product product);

    Product findById(Long id);

    Product update(Product product);

    void delete(Product product);
}
