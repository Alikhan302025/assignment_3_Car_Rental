package repository;

import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;
import model.Car;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements Repository<Car> {

    @Override
    public void save(Car car) {
        String sql = "INSERT INTO cars (brand, model, year, daily_price, branch_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setInt(4, car.getDailyPrice());
            stmt.setInt(5, car.getBranchId());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                car.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error saving car", e);
        }
    }

    @Override
    public Car findById(int id) {
        String sql = "SELECT * FROM cars WHERE car_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Car(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getInt("daily_price"),
                        rs.getInt("branch_id")
                );
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error finding car", e);
        }

        return null;
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            //stmt.setInt(1, price);

            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("car_id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getInt("daily_price"),
                        rs.getInt("branch_id")
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching cars", e);
        }
        return cars;
    }



    @Override
    public void update(Car car) {
        String sql = "UPDATE cars SET brand=?, model=?, year=?, daily_price=?, branch_id=? WHERE car_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setInt(4, car.getDailyPrice());
            stmt.setInt(5, car.getBranchId());
            stmt.setInt(6, car.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error updating car", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cars WHERE car_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting car", e);
        }
    }
}
