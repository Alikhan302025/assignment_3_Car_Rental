package controller;

import model.Rental;
import service.RentalService;

import java.util.List;

public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    public void createRental(Rental rental) {
        rentalService.createRental(rental);
        System.out.println("Rental created successfully");
    }


    public void getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        for (Rental r : rentals) {
            System.out.println(
                    r.getCustomer().getDisplayName() +
                            " rented " +
                            r.getCar().getDisplayName()
            );
        }
    }


    public void getRentalById(int id) {
        Rental rental = rentalService.getRentalById(id);
        System.out.println(
                rental.getCustomer().getDisplayName() +
                        " rented " +
                        rental.getCar().getDisplayName()
        );
    }


    public void deleteRental(int id) {
        rentalService.deleteRental(id);
        System.out.println("Rental deleted");
    }
}

