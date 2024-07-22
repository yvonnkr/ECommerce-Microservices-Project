package com.yvolabs.ecommerce.order;

import com.yvolabs.ecommerce.customer.CustomerClient;
import com.yvolabs.ecommerce.exception.BusinessException;
import com.yvolabs.ecommerce.kafka.OrderConfirmation;
import com.yvolabs.ecommerce.kafka.OrderProducer;
import com.yvolabs.ecommerce.orderline.OrderLineRequest;
import com.yvolabs.ecommerce.orderline.OrderLineService;
import com.yvolabs.ecommerce.payment.PaymentClient;
import com.yvolabs.ecommerce.payment.PaymentRequest;
import com.yvolabs.ecommerce.product.ProductClient;
import com.yvolabs.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 20/07/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final OrderMapper mapper;

    /**
     * @apiNote <h3>Logic to create an Order</h3>
     * <ol>
     *     <li>Check Customer exists --> customer-ms (using OpenFeign)</li>
     *     <li>Purchase the Products --> product-ms  (using RestTemplate)</li>
     *     <li>Persist Order</li>
     *     <li>Persist Order-Lines</li>
     *     <li>Start Payment process --> payment-ms (using OpenFeign)</li>
     *     <li>Send the order confirmation to --> notification-ms (using kafka)</li>
     * </ol>
     */
    @Override
    public Integer createOrder(OrderRequest request) {
        //check customer exists --> customer-ms (using OpenFeign)
        log.info("Fetching Customer...");
        var customer = this.customerClient
                .findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot place order:: No Customer exists with the provide ID: " + request.customerId()));

        //purchase the products --> product-ms (using RestTemplate)
        log.info("Purchasing Products...");
        var purchasedProducts = this.productClient
                .purchaseProducts(request.products());

        //persist order
        log.info("Persisting Order...");
        var order = this.orderRepository.save(mapper.toOrder(request));

        //persist order-lines
        log.info("Persisting Order-Lines...");
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //start payment process --> payment-ms (using OpenFeign)
        log.info("Processing Payment...");
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        this.paymentClient.requestOrderPayment(paymentRequest);

        //send the order confirmation to --> notification-ms (kafka)
        log.info("Sending OrderConfirmation...");
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        log.info("Create Order Complete...");
        return order.getId();
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .toList();
    }

    @Override
    public OrderResponse findById(int orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
    }
}
