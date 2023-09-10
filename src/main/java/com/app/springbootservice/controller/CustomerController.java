package com.app.springbootservice.controller;

import com.app.springbootservice.entity.Customer;
import com.app.springbootservice.repository.CustomerRepository;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/greet")
    public GreetResponse greet() {
        return new GreetResponse(
                "Hello!",
                List.of("Java", "Go", "JavaScript"),
                new Person("Sukhdev", 40)
        );
    }

    @RequestMapping("/all")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @PostMapping("/add")
    public void addCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setName(customerRequest.name);
        customer.setEmail(customerRequest.email);
        customer.setAge(customerRequest.age);

        customerRepository.save(customer);

    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("/update/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") Integer id, @RequestBody CustomerRequest customerRequest) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        Customer updatedCustomer = null;
        if(existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(customerRequest.name);
            customer.setEmail(customerRequest.email);
            customer.setAge(customerRequest.age);

            updatedCustomer = customerRepository.save(customer);
        }

        return updatedCustomer;
    }

    record CustomerRequest(String name, String email, Integer age) {};

    record GreetResponse(String greet, List<String> favProgramLanguages, Person person) {};

    record Person(String name, int age){};
}
