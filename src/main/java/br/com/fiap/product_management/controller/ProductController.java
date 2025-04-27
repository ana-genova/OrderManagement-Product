package br.com.fiap.product_management.controller;

import br.com.fiap.product_management.controller.dto.ProductDto;
import br.com.fiap.product_management.domain.Product;
import br.com.fiap.product_management.gateway.database.jpa.ProductJpaGateway;
import br.com.fiap.product_management.usecase.CreateProductUseCase;
import br.com.fiap.product_management.usecase.UpdateProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductJpaGateway productJpaGateway;

    public ProductController(ProductJpaGateway productJpaGateway) {
        this.productJpaGateway = productJpaGateway;
    }

    @Operation(
            summary = "Buscar todos produtos",
            description = """
                    A chamada deste endpoint lista todos os produtos cadastrados
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem com todos os produtos cadastrados"),
    })
    @GetMapping
    public List<ProductDto> getProducts() {
        List<Product> products = productJpaGateway.findAll();
        return products.stream().map(ProductDto::new).toList();
    }

    @Operation(
            summary = "Busca produtos por código",
            description = """
                    A chamada deste endpoint lista um produto específico a partir do ID
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de um produto a partir do ID"),
            @ApiResponse(responseCode = "500", description = "Erro nas informações inseridas")
    })
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id) {
        return new ProductDto(productJpaGateway.findById(id));
    }

    @Operation(
            summary = "Busca produtos por código SKU",
            description = """
                    A chamada deste endpoint lista um produto específico a partir do SKU
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de um produto a partir do SKU"),
            @ApiResponse(responseCode = "500", description = "Erro nas informações inseridas")
    })
    @GetMapping("/sku/{sku}")
    public ProductDto getProductBySKU(@PathVariable("sku") String sku) {
        return new ProductDto(productJpaGateway.findBySKU(sku));
    }

    @Operation(
            summary = "Salva um produto",
            description = """
                    A chamada deste endpoint salva um produto
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salva um produto"),
            @ApiResponse(responseCode = "500", description = "Erro nas informações inseridas")
    })
    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto ProductDto) {
        Product product = CreateProductUseCase.create(ProductDto.name(), ProductDto.description(), ProductDto.sku(), ProductDto.price());

        return new ProductDto(productJpaGateway.create(product));
    }

    @Operation(
            summary = "Atualiza produto por código",
            description = """
                    A chamada deste endpoint edita um produto a partir de um id
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editar um produto a partir do ID"),
            @ApiResponse(responseCode = "500", description = "Erro nas informações inseridas")
    })
    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDto newProduct) {
        Product product = productJpaGateway.findById(productId);
        Product updatedProduct = UpdateProductUseCase.update(product, toDomain(newProduct, productId, product.getSKU()));

        return new ProductDto(productJpaGateway.update(updatedProduct));
    }

    @Operation(
            summary = "Exclusão permanente de produto por código",
            description = """
                    A chamada deste endpoint exclui permanentemente um produto a partir de um id
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exclui permanentemente um produto a partir do ID"),
            @ApiResponse(responseCode = "500", description = "Erro nas informações inseridas")
    })
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
                ProductDto.price()
        );
    }

}
