package br.com.fiap.product_management.gateway.database.jpa.repository;

import br.com.fiap.product_management.gateway.database.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "select c.* from product c where c.sku = :sku", nativeQuery = true)
    Optional<ProductEntity> findBySKU(String sku);

}
