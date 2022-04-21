package javier.academy.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_products")
@Data @AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    private String description;
    @Positive(message = "stock must be superior than zero")
    private Double stock;
    private Double price;
    private String status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_product_category", joinColumns = { @JoinColumn(name = "product_id")},
    inverseJoinColumns = { @JoinColumn(name = "category_id")
    })
    @NotEmpty(message = "categories cannot be empty")
    @NotNull(message = "categories cannot be null")
    private List<Category> categories = new ArrayList<>();

    public Product(String name, String description, Double stock, Double price, String status, LocalDate createdAt, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.categories = categories;
    }

}
