package br.com.fiap.product_management.gateway.database.jpa;

import br.com.fiap.product_management.config.ProductConfig;
import br.com.fiap.product_management.domain.Product;
import br.com.fiap.product_management.gateway.database.jpa.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ProductConfig.class)
public class ProductJpaGatewayTest {

    private final String defaultName = "Caneca";
    private final String defaultDescription = "Caneca Stan Branca";
    private final Double defaultPrice = 199.99;
    private final int defaultQuantity = 5;

    private ProductJpaGateway productJpaGateway;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productJpaGateway = new ProductJpaGateway(productRepository);
    }

    @Test
    void findProducts() {
        List<Product> productList = productJpaGateway.findAll();

        assertNotNull(productList);
    }

    @Test
    void createProductAndFindProductById() {
        Product product = new Product(defaultName, defaultDescription, null, defaultPrice, defaultQuantity);
        Product productSaved = productJpaGateway.create(product);

        Product productFind = productJpaGateway.findById(productSaved.getId());

        assertEquals(productSaved.getId(), productFind.getId());
    }

    @Test
    void createProductAndFindProductBySKU() {
        Product product = new Product(defaultName, defaultDescription, null, defaultPrice, defaultQuantity);
        Product productSaved = productJpaGateway.create(product);

        Product productFind = productJpaGateway.findBySKU(productSaved.getSKU());

        assertEquals(productSaved.getSKU(), productFind.getSKU());
    }

    @Test
    void createProductAndUpdate() {
        Product product = new Product(defaultName, defaultDescription, null, defaultPrice, defaultQuantity);
        Product productSaved = productJpaGateway.create(product);

        int newQuantity = 2;
        productSaved.setQuantity(newQuantity);
        Product productUpdated = productJpaGateway.update(productSaved);

        assertEquals(productUpdated.getQuantity(), newQuantity);
    }

    @Test
    void createProductAndDelete() {
        Product product = new Product(defaultName, defaultDescription, null, defaultPrice, defaultQuantity);
        Product productSaved = productJpaGateway.create(product);

        productJpaGateway.delete(productSaved);
        Product productFind = productJpaGateway.findById(productSaved.getId());

        assertEquals(productFind.getQuantity(), 0);
    }

    @Test
    void throwsExceptionWhenCreateProductAlreadyExists() {
        Product product = new Product(1L, defaultName, defaultDescription, null, defaultPrice, defaultQuantity);
        assertThrows(
                IllegalArgumentException.class,
                () -> productJpaGateway.create(product),
                "Product already exists"
        );
    }

    @Test
    void throwsExceptionWhenUpdateProductNotFound() {
        Product product = new Product(40000L, defaultName, defaultDescription, null, defaultPrice, defaultQuantity);
        assertThrows(
                IllegalArgumentException.class,
                () -> productJpaGateway.update(product),
                "Product not found"
        );
    }

    @Test
    void throwsExceptionWhenDeleteProductNotFound() {
        Product product = new Product(40000L, defaultName, defaultDescription, null, defaultPrice, defaultQuantity);
        assertThrows(
                IllegalArgumentException.class,
                () -> productJpaGateway.delete(product),
                "Product not found"
        );
    }
}

