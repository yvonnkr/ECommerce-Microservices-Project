package com.yvolabs.ecommerce.orderline;

import com.yvolabs.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 20/07/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer productId;
    private double quantity;
}
