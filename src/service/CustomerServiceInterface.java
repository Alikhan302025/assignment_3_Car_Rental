package service;

import model.Customer;
import java.util.List;

public interface CustomerServiceInterface {
    void create(Customer customer);
    Customer getById(int id);
    List<Customer> getAll();
    void update(Customer customer);
    void delete(int id);
}
