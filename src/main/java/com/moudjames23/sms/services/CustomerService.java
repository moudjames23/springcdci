package com.moudjames23.sms.services;

import com.moudjames23.sms.entities.Customer;
import com.moudjames23.sms.exceptions.ResourceAlreadyExistException;
import com.moudjames23.sms.exceptions.ResourceNotFoundException;
import com.moudjames23.sms.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CustomerService {

    CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAll()
    {
        return  this.repository.findAll();
    }

    public Customer create(Customer customer)
    {
        if (this.byPhone(customer.getPhone()) != null)
            throw new ResourceAlreadyExistException("This phone number " +customer.getPhone()+ " already exist");
        return this.repository.save(customer);
    }

    public void saveAll(List<Customer> customer)
    {
        this.repository.saveAll(customer);
    }

    public Customer show(int id)
    {
        return this.byId(id);
    }

    public Customer update(Customer data, int id)
    {
        this.byId(id);



        Customer customer = byPhone(data.getPhone());

        if (customer != null && customer.getId() == id)
        {
            data.setId(customer.getId());
            return this.repository.save(data);
        }

        throw new ResourceAlreadyExistException("This phone number " +data.getPhone()+ " already exist");
    }

    public void delete(int id)
    {
        Customer customer = byId(id);

        this.repository.delete(customer);


    }

    public Customer byId(int id)
    {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer doesn't exist"));
    }
    public Customer byPhone(String phone)
    {
        return  this.repository.findCustomerByPhone(phone);
    }
}
