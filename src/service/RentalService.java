package service;

import model.Rental;
import repository.Repository;
import exception.*;
import java.util.List;

public class RentalService implements RentalServiceInterface {

    private final Repository<Rental> rentalRepository;

    public RentalService(Repository<Rental> rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    private void validateRental(Rental rental) {
        if (rental == null) {
            throw new InvalidInputException("Rental cannot be null");
        }
        rental.validate();
    }

    @Override
    public void createRental(Rental rental) {
        validateRental(rental);
        rentalRepository.save(rental);
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(int id) {
        Rental rental = rentalRepository.findById(id);
        if (rental == null) {
            throw new ResourceNotFoundException("Rental not found with id " + id);
        }
        return rental;
    }

    @Override
    public void updateRental(Rental rental) {
        validateRental(rental);
        if (rental.getId() <= 0) {
            throw new InvalidInputException("Invalid rental ID");
        }
        rentalRepository.update(rental);
    }

    @Override
    public void deleteRental(int id) {
        rentalRepository.delete(id);
    }
}
