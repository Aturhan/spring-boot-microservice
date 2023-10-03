package com.abdullahturhan.controller;

import com.abdullahturhan.dto.CustomerDto;
import com.abdullahturhan.dto.CustomerRegisterRequest;
import com.abdullahturhan.dto.UpdateCustomerRequest;
import com.abdullahturhan.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.findCustomer(id));
    }
    @GetMapping(path = "/email/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable("email") String email){
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.findCustomerByEmail(email));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> register(@Valid @RequestBody CustomerRegisterRequest request){
        customerService.registerCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Void> update(@PathVariable("id")Long id ,@Valid @RequestBody UpdateCustomerRequest request){
        customerService.updateCustomer(request,id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(204).build();
    }

}
