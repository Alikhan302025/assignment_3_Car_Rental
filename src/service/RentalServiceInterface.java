package service;

import model.Rental;
import java.util.List;

public interface RentalServiceInterface {
    void createRental(Rental rental);
    List<Rental> getAllRentals();
    Rental getRentalById(int id);
    void updateRental(Rental rental);
    void deleteRental(int id);
}
