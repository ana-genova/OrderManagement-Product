package br.com.fiap.product_management.config;

import br.com.fiap.product_management.gateway.database.jpa.ProductJpaGateway;
import br.com.fiap.product_management.gateway.database.jpa.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductJpaGateway productJpaGateway(ProductRepository productRepository) {
        return new ProductJpaGateway(productRepository);
    }

}
