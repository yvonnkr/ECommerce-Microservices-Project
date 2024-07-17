package com.yvolabs.ecommerce.customer;

import java.util.List;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 17/07/2024
 */
public interface CustomerService {
    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    CustomerResponse findById(String customerId);

    Boolean existsById(String customerId);

    void deleteCustomer(String customerId);
}
