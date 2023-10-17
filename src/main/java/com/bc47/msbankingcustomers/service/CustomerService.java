package com.bc47.msbankingcustomers.service;

import com.bc47.msbankingcustomers.api.CustomersApiDelegate;
import com.bc47.msbankingcustomers.entity.Customer;
import com.bc47.msbankingcustomers.model.CustomerDTO;
import com.bc47.msbankingcustomers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements CustomersApiDelegate {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity<List<CustomerDTO>> retrieveAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOList =
                customers
                        .stream()
                        .map(this::createDTO)
                        .collect(Collectors.toList());
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDTO> retrieveCustomer(String id) {
        List<Customer> customers = customerRepository.findAll();
        Optional<CustomerDTO> customerFound =
                customers
                        .stream()
                        .filter(c -> Objects.equals(c.getId(), id))
                        .map(this::createDTO)
                        .findFirst();
        return customerFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new CustomerDTO()));
    }

    @Override
    public ResponseEntity<CustomerDTO> saveCustomer(CustomerDTO customerDTO) {
        Customer customer = Customer
                .builder()
                .id(customerDTO.getId())
                .customerType(customerDTO.getCustomerType())
                .name(customerDTO.getName())
                .docType(customerDTO.getDocType())
                .docNumber(customerDTO.getDocNumber())
                .createdAt(new Date().toString())
                .address(customerDTO.getAddress())
                .phoneNumber(customerDTO.getPhoneNumber())
                .status(customerDTO.getStatus())
                .email(customerDTO.getEmail())
                .mobilePhoneImeiNumber(customerDTO.getMobilePhoneImeiNumber())
                .ownedPasiveProductsQty(customerDTO.getOwnedPasiveProductsQty())
                .ownedActiveProductsQty(customerDTO.getOwnedActiveProductsQty())
                .build();
        customerRepository.save(customer);
        customerDTO.setCreatedAt(new Date().toString());
        return ResponseEntity.ok(customerDTO);
    }

    @Override
    public ResponseEntity<CustomerDTO> updateCustomer(CustomerDTO customerDTO) {
        return saveCustomer(customerDTO);
    }

    @Override
    public ResponseEntity<CustomerDTO> deleteCustomer(String id) {
        List<Customer> customers = customerRepository.findAll();
        Optional<CustomerDTO> customerFound =
                customers
                        .stream()
                        .filter(c -> Objects.equals(c.getId(), id))
                        .map(customer -> {
                            customerRepository.deleteById(customer.getId());
                            return createDTO(customer);
                        })
                        .findFirst();
        return customerFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new CustomerDTO()));
    }

    private CustomerDTO createDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setCustomerType(customer.getCustomerType());
        customerDTO.setName(customer.getName());
        customerDTO.setDocType(customer.getDocType());
        customerDTO.setDocNumber(customer.getDocNumber());
        customerDTO.setCreatedAt(customer.getCreatedAt());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setStatus(customer.getStatus());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobilePhoneImeiNumber(customer.getMobilePhoneImeiNumber());
        customerDTO.setOwnedPasiveProductsQty(customer.getOwnedPasiveProductsQty());
        customerDTO.setOwnedActiveProductsQty(customer.getOwnedActiveProductsQty());
        return customerDTO;
    }
}
