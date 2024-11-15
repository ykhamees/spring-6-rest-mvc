package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        CustomerDTO customer1 = CustomerDTO.builder().id(UUID.randomUUID()).customerName("John Doe").version("1")
                .createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build();
        CustomerDTO customer2 = CustomerDTO.builder().id(UUID.randomUUID()).customerName("Jane Doe").version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();
        CustomerDTO customer3 = CustomerDTO.builder().id(UUID.randomUUID()).customerName("Jack Doe").version("1")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now()).build();

        customerMap = Map.of(customer1.getId(), customer1, customer2.getId(), customer2, customer3.getId(), customer3);
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID customerId) {
        return Optional.of(customerMap.get(customerId));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder().id(UUID.randomUUID()).customerName(customer.getCustomerName())
                .version("1").createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return customerMap.get(savedCustomer.getId());
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDTO customer) {
        CustomerDTO savedCustomer = customerMap.get(customerId);

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
    public void patchCustomer(UUID customerId, CustomerDTO customer) {
        CustomerDTO savedCustomer = customerMap.get(customerId);

        if (customer.getCustomerName() != null) {
            savedCustomer.setCustomerName(customer.getCustomerName());
        }

        savedCustomer.setVersion(savedCustomer.getVersion() + 1);
        savedCustomer.setLastModifiedDate(LocalDateTime.now());

        customerMap.put(savedCustomer.getId(), savedCustomer);
    }
}
