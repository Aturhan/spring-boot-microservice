package com.abdullahturhan.service;

import com.abdullahturhan.dto.CustomerDto;
import com.abdullahturhan.dto.CustomerRegisterRequest;
import com.abdullahturhan.dto.UpdateCustomerRequest;
import com.abdullahturhan.exception.CustomerNotFoundException;
import com.abdullahturhan.model.Customer;
import com.abdullahturhan.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto findCustomer(Long id){
        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id)));
        return CustomerDto.builder()
                .customerId(optionalCustomer.get().getId())
                .firstName(optionalCustomer.get().getFirstName())
                .lastName(optionalCustomer.get().getLastName())
                .email(optionalCustomer.get().getEmail())
                .build();
    }

    public CustomerDto findCustomerByEmail(String email){
        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + email)));
               return CustomerDto.builder()
                       .customerId(optionalCustomer.get().getId())
                       .firstName(optionalCustomer.get().getFirstName())
                       .lastName(optionalCustomer.get().getLastName())
                       .email(optionalCustomer.get().getEmail())
                       .build();
    }


    @Transactional
    public void registerCustomer(CustomerRegisterRequest request){
        final Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        customerRepository.save(customer);

        log.info("Customer registered: " + customer.toString());
    }
    @Transactional
    public void updateCustomer(UpdateCustomerRequest request,Long id){
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id)));
        customer.ifPresent(model ->{
            model.setFirstName(request.getFirstName());
            model.setLastName(request.getLastName());
            model.setEmail(request.getEmail());
            customerRepository.save(model);
        });


    }
    @Transactional
    public void deleteCustomer(Long id){
        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id " + id)));

        optionalCustomer.ifPresent(customerRepository::delete);
    }

    

}
