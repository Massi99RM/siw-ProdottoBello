package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Metodi esistenti
    List<Product> findByType(ProductType type);
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Nuovi metodi per ricerca avanzata
    List<Product> findByTypeAndNameContainingIgnoreCase(ProductType type, String name);
    
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    List<Product> findByTypeAndPriceBetween(ProductType type, BigDecimal minPrice, BigDecimal maxPrice);
    
    // Ordinamento con query personalizzate
    List<Product> findByTypeOrderByNameAsc(ProductType type);
    List<Product> findByTypeOrderByPriceAsc(ProductType type);
    List<Product> findByTypeOrderByPriceDesc(ProductType type);
    
    List<Product> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
    List<Product> findByNameContainingIgnoreCaseOrderByPriceAsc(String name);
    List<Product> findByNameContainingIgnoreCaseOrderByPriceDesc(String name);
    
    // Query JPQL personalizzata per ricerca combinata
    @Query("SELECT p FROM Product p WHERE " +
           "(:type IS NULL OR p.type = :type) AND " +
           "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) " +
           "ORDER BY " +
           "CASE WHEN :sort = 'name' THEN p.name END ASC, " +
           "CASE WHEN :sort = 'price_asc' THEN p.price END ASC, " +
           "CASE WHEN :sort = 'price_desc' THEN p.price END DESC, " +
           "p.id ASC")
    List<Product> findProductsWithFilters(
        @Param("type") ProductType type,
        @Param("name") String name,
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("sort") String sort
    );
    
    // Conteggio prodotti per categoria
    long countByType(ProductType type);
    
    // Trova prodotti simili escludendo il prodotto stesso
    @Query("SELECT p FROM Product p WHERE p.id IN " +
           "(SELECT sp.id FROM Product pr JOIN pr.similarProducts sp WHERE pr.id = :productId) " +
           "OR p.id IN " +
           "(SELECT pr.id FROM Product pr JOIN pr.similarProducts sp WHERE sp.id = :productId)")
    List<Product> findAllSimilarProducts(@Param("productId") Long productId);
    
    /**
     * Trova un prodotto con i suoi prodotti simili caricati
     */
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.similarProducts WHERE p.id = :id")
    Optional<Product> findByIdWithSimilarProducts(@Param("id") Long id);
    
    /**
     * Trova tutti i prodotti con i loro prodotti simili caricati
     */
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.similarProducts")
    List<Product> findAllWithSimilarProducts();
}
