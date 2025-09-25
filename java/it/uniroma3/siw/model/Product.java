package it.uniroma3.siw.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private BigDecimal price;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "product_similar",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "similar_id")
    )
    private Set<Product> similarProducts = new HashSet<>();

    // Costruttori
    public Product() {}
    public Product(String name, BigDecimal price, String description, ProductType type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.type = type;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ProductType getType() { return type; }
    public void setType(ProductType type) { this.type = type; }

    public Set<Product> getSimilarProducts() { return similarProducts; }
    public void setSimilarProducts(Set<Product> similarProducts) { this.similarProducts = similarProducts; }
}