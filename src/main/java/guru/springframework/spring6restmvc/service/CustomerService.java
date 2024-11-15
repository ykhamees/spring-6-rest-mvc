package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> getCustomers();

    Optional<CustomerDTO> getCustomerById(UUID customerId);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    void updateCustomer(UUID customerId, CustomerDTO customer);

    void deleteById(UUID customerId);

    void patchCustomer(UUID customerId, CustomerDTO customer);
}
