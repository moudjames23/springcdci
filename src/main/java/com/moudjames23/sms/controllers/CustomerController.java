package com.moudjames23.sms.controllers;

import com.moudjames23.sms.dto.HttpResponse;
import com.moudjames23.sms.entities.Customer;
import com.moudjames23.sms.services.CustomerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.moudjames23.sms.dto.HttpResponse.generateResponse;

@RestController
@RequestMapping("api/v1")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public HttpEntity<Object> index()
    {
        List<Customer> customers = this.customerService.findAll();

        return generateResponse("Success", HttpStatus.OK, customers);
    }

    @PostMapping("/customers")
    public HttpEntity<Object> create(@Valid @RequestBody Customer request)
    {
        Customer customer = this.customerService.create(request);
        return generateResponse("success", HttpStatus.CREATED, customer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> show(@PathVariable int id)
    {
        Customer customer = this.customerService.show(id);
        return generateResponse("success", HttpStatus.OK, customer);
    }

    @PutMapping("customers/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @Valid @RequestBody Customer request)
    {
        System.out.println("Customer: " +request.toString());
        Customer customer = this.customerService.update(request, id);
        return  generateResponse("success", HttpStatus.OK, customer);
    }

    @DeleteMapping("customers/{id}/delete")
    public ResponseEntity<Object> delete(@PathVariable int id)
    {
        this.customerService.delete(id);

        return  generateResponse("success", HttpStatus.OK, null);
    }

}
