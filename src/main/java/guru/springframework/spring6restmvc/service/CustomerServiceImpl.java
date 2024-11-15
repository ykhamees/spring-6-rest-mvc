package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        Customer customer1 = Customer.builder().id(UUID.randomUUID()).customerName("John Doe").version("1")
                .createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build();
        Customer customer2 = Customer.builder().id(UUID.randomUUID()).customerName("Jane Doe").version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();
        Customer customer3 = Customer.builder().id(UUID.randomUUID()).customerName("Jack Doe").version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();

        customerMap = Map.of(customer1.getId(), customer1, customer2.getId(), customer2, customer3.getId(), customer3);
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<Customer> getCustomerById(UUID customerId) {
        return Optional.of(customerMap.get(customerId));
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder().id(UUID.randomUUID()).customerName(customer.getCustomerName())
                .version("1").createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return customerMap.get(savedCustomer.getId());
    }

    @Override
    public void updateCustomer(UUID customerId, Customer customer) {
        Customer savedCustomer = customerMap.get(customerId);

        savedCustomer.setCustomerName(customer.getCustomerName());
        savedCustomer.setVersion(customer.getVersion() + 1);
        savedCustomer.setLastModifiedDate(LocalDateTime.now());

        customerMap.put(savedCustomer.getId(), savedCustomer);
    }

    @Override
    public void deleteById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomer(UUID customerId, Customer customer) {
        Customer savedCustomer = customerMap.get(customerId);

        if (customer.getCustomerName() != null) {
            savedCustomer.setCustomerName(customer.getCustomerName());
        }

        savedCustomer.setVersion(savedCustomer.getVersion() + 1);
        savedCustomer.setLastModifiedDate(LocalDateTime.now());

        customerMap.put(savedCustomer.getId(), savedCustomer);
    }
}
