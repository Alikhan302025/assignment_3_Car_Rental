package repository;

import model.BaseEntity;
import model.Customer;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.*;
public class CustomerRepository implements Repository<Customer> {


    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customers(first_name, last_name, email, phone, date_of_birth) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setDate(5, Date.valueOf(customer.getDateOfBirth()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error saving customer: " + e.getMessage(), e);
        }
    }


    @Override
    public Customer findById(int id) {
        String sql = "SELECT customer_id, first_name, last_name, email, phone, date_of_birth FROM customers WHERE customer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getInt("customer_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getDate("date_of_birth").toLocalDate()
                    );
                }
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error finding customer by ID: " + e.getMessage(), e);
        }

        return null;
    }


    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, email, phone, date_of_birth FROM customers";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("date_of_birth").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching all customers: " + e.getMessage(), e);
        }

        return customers;
    }






    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, email = ?, phone = ?, date_of_birth = ? WHERE customer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setDate(5, Date.valueOf(customer.getDateOfBirth()));
            stmt.setInt(6, customer.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error updating customer: " + e.getMessage(), e);
        }
    }


    @Override
    public void delete(int id) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting customer: " + e.getMessage(), e);
        }
    }
}
