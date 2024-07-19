package com.yvolabs.ecommerce.category;

import com.yvolabs.ecommerce.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 18/07/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;

}