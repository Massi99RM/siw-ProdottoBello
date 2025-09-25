package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.model.ProductType;
import it.uniroma3.siw.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Raggruppa i prodotti per categoria
     */
    public Map<ProductType, List<Product>> getProductsByCategory() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .collect(Collectors.groupingBy(Product::getType));
    }

    /**
     * Raggruppa i prodotti per categoria, escludendo un prodotto specifico
     */
    public Map<ProductType, List<Product>> getProductsByCategoryExcluding(Long excludeId) {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .filter(product -> !product.getId().equals(excludeId))
                .collect(Collectors.groupingBy(Product::getType));
    }

    /**
     * Salva un prodotto e gestisce i prodotti simili in modo bidirezionale
     */
    @Transactional
    public Product saveProductWithSimilarProducts(Product product, List<Long> similarProductIds) {
        // Salva prima il prodotto
        Product savedProduct = productRepository.save(product);
        
        // Gestisci prodotti simili se specificati
        if (similarProductIds != null && !similarProductIds.isEmpty()) {
            updateSimilarProductsBidirectional(savedProduct, similarProductIds);
        }
        
        return savedProduct;
    }

    /**
     * Aggiorna un prodotto esistente e gestisce i prodotti simili
     */
    @Transactional
    public Product updateProductWithSimilarProducts(Long productId, Product updatedProduct, List<Long> similarProductIds) {
        Optional<Product> existingProductOpt = productRepository.findById(productId);
        if (existingProductOpt.isEmpty()) {
            throw new RuntimeException("Prodotto non trovato con ID: " + productId);
        }
        
        Product existingProduct = existingProductOpt.get();
        
        // Rimuovi prima tutte le relazioni esistenti
        removeSimilarProductsBidirectional(existingProduct);
        
        // Aggiorna i campi
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setType(updatedProduct.getType());
        
        // Salva le modifiche
        existingProduct = productRepository.save(existingProduct);
        
        // Aggiungi le nuove relazioni
        if (similarProductIds != null && !similarProductIds.isEmpty()) {
            updateSimilarProductsBidirectional(existingProduct, similarProductIds);
        }
        
        return existingProduct;
    }

    /**
     * Elimina un prodotto e tutte le sue relazioni
     */
    @Transactional
    public void deleteProduct(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            removeSimilarProductsBidirectional(product);
            productRepository.deleteById(productId);
        }
    }

    /**
     * Aggiorna i prodotti simili in modo bidirezionale
     */
    private void updateSimilarProductsBidirectional(Product product, List<Long> similarProductIds) {
        List<Product> similarProducts = productRepository.findAllById(similarProductIds);
        
        for (Product similarProduct : similarProducts) {
            // Aggiungi la relazione in entrambe le direzioni
            product.getSimilarProducts().add(similarProduct);
            similarProduct.getSimilarProducts().add(product);
            
            // Salva il prodotto correlato
            productRepository.save(similarProduct);
        }
        
        // Salva il prodotto principale
        productRepository.save(product);
    }
    
    /**
     * Rimuove tutte le relazioni di prodotti simili in modo bidirezionale
     */
    private void removeSimilarProductsBidirectional(Product product) {
        // Crea una copia della lista per evitare ConcurrentModificationException
        List<Product> currentSimilarProducts = new ArrayList<>(product.getSimilarProducts());
        
        for (Product similarProduct : currentSimilarProducts) {
            // Rimuovi la relazione in entrambe le direzioni
            product.getSimilarProducts().remove(similarProduct);
            similarProduct.getSimilarProducts().remove(product);
            
            // Salva il prodotto correlato
            productRepository.save(similarProduct);
        }
        
        // Salva il prodotto principale
        productRepository.save(product);
    }
}