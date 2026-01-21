package repository;

import model.Rental;
import utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository implements Repository<Rental> {

    @Override
    public void save(Rental rental) {
        String sql = "INSERT INTO rentals (customer_id, car_id, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, rental.getCustomer().getId());
            stmt.setInt(2, rental.getCar().getId());
            stmt.setDate(3, Date.valueOf(rental.getStartDate()));
            stmt.setDate(4, rental.getEndDate() != null ? Date.valueOf(rental.getEndDate()) : null);
            stmt.setString(5, rental.getStatus());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                rental.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving rental", e);
        }
    }

    @Override
    public Rental findById(int id) {
        String sql = "SELECT * FROM rentals WHERE rental_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Rental(
                        rs.getInt("rental_id"),
                        null,
                        null,
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null,
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding rental", e);
        }

        return null;
    }

    @Override
    public List<Rental> findAll() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rentals.add(new Rental(
                        rs.getInt("rental_id"),
                        null,
                        null,
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date") != null ? rs.getDate("end_date").toLocalDate() : null,
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching rentals", e);
        }

        return rentals;
    }

    @Override
    public void update(Rental rental) {
        String sql = "UPDATE rentals SET end_date=?, status=? WHERE rental_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, rental.getEndDate() != null ? Date.valueOf(rental.getEndDate()) : null);
            stmt.setString(2, rental.getStatus());
            stmt.setInt(3, rental.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating rental", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM rentals WHERE rental_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting rental", e);
        }
    }
}
