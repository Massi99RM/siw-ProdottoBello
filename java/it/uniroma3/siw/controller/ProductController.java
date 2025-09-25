package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Comment;
import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.model.ProductType;
import it.uniroma3.siw.repository.ProductRepository;
import it.uniroma3.siw.service.CommentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final CommentService commentService;

    public ProductController(ProductRepository productRepository, CommentService commentService) {
        this.productRepository = productRepository;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/product/{id}")
    @Transactional
    public String productDetail(@PathVariable Long id, Model model) {
        Optional<Product> productOpt = productRepository.findById(id);
        
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.getSimilarProducts().size(); // Forza caricamento prodotti simili
            
            // AGGIUNGI QUESTO: Carica i commenti del prodotto
            List<Comment> comments = commentService.getCommentsByProduct(id);
            model.addAttribute("comments", comments);
            model.addAttribute("product", product);
        } else {
            model.addAttribute("product", null);
        }
        
        return "product";
    }

    @GetMapping("/catalog")
    public String catalog(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort,
            Model model) {
        
        List<Product> products;
        
        // Applica filtri
        if (category != null && !category.isEmpty()) {
            try {
                ProductType productType = ProductType.valueOf(category.toUpperCase());
                products = productRepository.findByType(productType);
            } catch (IllegalArgumentException e) {
                products = productRepository.findAll();
            }
        } else {
            products = productRepository.findAll();
        }
        
        // Applica ricerca per nome (usa il repository per efficienza)
        if (search != null && !search.trim().isEmpty()) {
            if (category != null && !category.isEmpty()) {
                // Ricerca filtrata per categoria giÃ  applicata
                products = products.stream()
                    .filter(p -> p.getName().toLowerCase().contains(search.toLowerCase().trim()))
                    .collect(Collectors.toList());
            } else {
                // Ricerca globale usando il repository
                products = productRepository.findByNameContainingIgnoreCase(search.trim());
            }
        }
        
        // Applica ordinamento
        if (sort != null) {
            switch (sort) {
                case "name":
                    products = products.stream()
                        .sorted(Comparator.comparing(Product::getName))
                        .collect(Collectors.toList());
                    break;
                case "price_asc":
                    products = products.stream()
                        .sorted(Comparator.comparing(Product::getPrice))
                        .collect(Collectors.toList());
                    break;
                case "price_desc":
                    products = products.stream()
                        .sorted(Comparator.comparing(Product::getPrice).reversed())
                        .collect(Collectors.toList());
                    break;
                default:
                    // Nessun ordinamento specifico
                    break;
            }
        }
        
        // Aggiungi attributi per il template
        model.addAttribute("products", products);
        model.addAttribute("searchTerm", search);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("sortBy", sort);
        
        return "catalog";
    }
}