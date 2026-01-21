package service;

import model.Rental;
import repository.RentalRepository;

import java.util.List;

public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }


    public void createRental(Rental rental) {
        if (rental == null) {
            throw new IllegalArgumentException("Rental cannot be null");
        }
        rentalRepository.save(rental);
    }


    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }


    public Rental getRentalById(int id) {
        Rental rental = rentalRepository.findById(id);
        if (rental == null) {
            throw new IllegalArgumentException("Rental not found with id " + id);
        }
        return rental;
    }


    public void deleteRental(int id) {
        rentalRepository.delete(id);
    }
}


