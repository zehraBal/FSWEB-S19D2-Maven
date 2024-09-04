package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer find(long id) {
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException());
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update (long id, Customer customer) {
        Customer oldCustomer=find(id);
        oldCustomer.setSalary(customer.getSalary());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setAccounts(customer.getAccounts());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setFirstName(customer.getFirstName());
        oldCustomer.setLastName(customer.getLastName());
        return customerRepository.save(oldCustomer);
    }

    @Override
    public Customer delete(long id) {
        Customer customer=find(id);
        customerRepository.deleteById(id);
        return customer;
    }
}
