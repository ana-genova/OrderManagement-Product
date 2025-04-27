package br.com.fiap.product_management.gateway.database.jpa;

import br.com.fiap.product_management.domain.Product;
import br.com.fiap.product_management.gateway.ProductGateway;
import br.com.fiap.product_management.gateway.database.jpa.entity.ProductEntity;
import br.com.fiap.product_management.gateway.database.jpa.repository.ProductRepository;
import br.com.fiap.product_management.utils.SkuGenerator;

import java.util.List;
import java.util.Optional;

public class ProductJpaGateway implements ProductGateway {

    private final ProductRepository productRepository;

    public ProductJpaGateway(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        List<ProductEntity> productsEntity = productRepository.findAll();

        return productsEntity.stream().map(product -> convertOptionalToDomain(Optional.of(product))).toList();
    }

    @Override
    public Product findBySKU(String sku) {
        Optional<ProductEntity> productEntity = productRepository.findBySKU(sku);

        return convertOptionalToDomain(productEntity);
    }

    private Product convertOptionalToDomain(Optional<ProductEntity> productEntity) {
        if (productEntity.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        return productEntity.get().toDomain();
    }

    @Override
    public Product create(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Product already exists");
        }

        ProductEntity productSaved = productRepository.save(new ProductEntity(product));
        productSaved.setSKU(SkuGenerator.generateSKU(productSaved.getId()));

        ProductEntity productEntity = productRepository.save(productSaved);
        return productEntity.toDomain();
    }

    @Override
    public Product findById(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        return convertOptionalToDomain(productEntity);
    }

    @Override
    public Product update(Product product) {
        Optional<ProductEntity> productEntity = productRepository.findById(product.getId());

        if (productEntity.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        ProductEntity updatedProductEntity = productRepository.save(new ProductEntity(product));

        return updatedProductEntity.toDomain();
    }

    @Override
    public void delete(Product product) {
        Optional<ProductEntity> productEntity = productRepository.findById(product.getId());

        if (productEntity.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        productRepository.delete(productEntity.get());
    }

}
