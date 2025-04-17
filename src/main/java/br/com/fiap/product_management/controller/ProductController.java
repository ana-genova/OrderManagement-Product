package br.com.fiap.product_management.controller;

import br.com.fiap.product_management.controller.dto.ProductDto;
import br.com.fiap.product_management.domain.Product;
import br.com.fiap.product_management.gateway.database.jpa.ProductJpaGateway;
import br.com.fiap.product_management.usecase.CreateProductUseCase;
import br.com.fiap.product_management.usecase.UpdateProductUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductJpaGateway productJpaGateway;

    public ProductController(ProductJpaGateway productJpaGateway) {
        this.productJpaGateway = productJpaGateway;
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        List<Product> products = productJpaGateway.findAll();
        return products.stream().map(ProductDto::new).toList() ;
    }

    @GetMapping("/{id}")
    public ProductDto getProductBySKU(@PathVariable("id") Long id) {
        return new ProductDto(productJpaGateway.findById(id));
    }

    @GetMapping("/sku/{sku}")
    public ProductDto getProductBySKU(@PathVariable("sku") String sku) {
        return new ProductDto(productJpaGateway.findBySKU(sku));
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto ProductDto) {
        Product product = CreateProductUseCase.create(ProductDto.name(), ProductDto.description(), ProductDto.sku(), ProductDto.price(), ProductDto.quantity());

        return new ProductDto(productJpaGateway.create(product));
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDto newProduct) {
        Product product = productJpaGateway.findById(productId);
        Product updatedProduct = UpdateProductUseCase.update(product, toDomain(newProduct, productId, product.getSKU()));

        return new ProductDto(productJpaGateway.update(updatedProduct));
    }

    @PutMapping("/{id}/stock/{quantity}")
    public ProductDto updateStockLow(@PathVariable("id") Long productId, @PathVariable("quantity") int quantity) {
        Product product = productJpaGateway.findById(productId);
        Product updatedProduct = UpdateProductUseCase.stock(product, quantity);

        return new ProductDto(productJpaGateway.update(updatedProduct));
    }

    @PutMapping("/{id}/stock/{quantity}/recover")
    public ProductDto updateStockRecover(@PathVariable("id") Long productId, @PathVariable("quantity") int quantity) {
        Product product = productJpaGateway.findById(productId);
        Product updatedProduct = UpdateProductUseCase.stockRecover(product,quantity);

        return new ProductDto(productJpaGateway.update(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) {
        Product product = productJpaGateway.findById(productId);
        productJpaGateway.delete(product);
    }

    private Product toDomain(ProductDto ProductDto, Long productId, String sku) {
        return new Product(
                productId,
                ProductDto.name(),
                ProductDto.description(),
                sku,
                ProductDto.price(),
                ProductDto.quantity()
        );
    }

}
