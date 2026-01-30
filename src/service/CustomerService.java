package service;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.Customer;
import repository.CustomerRepository;
import repository.Repository;

import java.util.List;

public class CustomerService implements CustomerServiceInterface {

    private final Repository<Customer> customerRepository;
    public CustomerService(Repository<Customer> customerRepository){
        this.customerRepository = customerRepository;
    }

    public void create(Customer customer) {
        if (customer == null) {
            throw new InvalidInputException("Customer cannot be null");
        }
        customerRepository.save(customer);
    }

    public Customer getById(int id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        return customer;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public void update(Customer customer) {
        if (customer == null || customer.getId() <= 0) {
            throw new InvalidInputException("Invalid customer ID");
        }
        customerRepository.update(customer);
    }

    public void delete(int id) {
        if (customerRepository.findById(id) == null) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.delete(id);
    }
}

