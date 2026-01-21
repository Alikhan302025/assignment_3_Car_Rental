import model.*;
import utils.DatabaseConnection;
import java.sql.Connection;
import java.time.LocalDate;
import repository.*;

import model.Customer;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        /*BaseEntity customer = new Customer(
                1,
                "Alikhan",
                "Zhambayev",
                "alikhan@mail.kz",
                "+77011111111",
                LocalDate.of(2007, 3, 30)
        );

        BaseEntity car = new Car(
                1,
                "Toyota",
                "Camry",
                2020,
                18000,
                1
        );

        System.out.println(customer.getEntityType());
        System.out.println(customer.getDisplayName());

        System.out.println(car.getEntityType());
        System.out.println(car.getDisplayName());

        Rental rental = new Rental(
                1,
                (Customer) customer,
                (Car) car,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 5),
                "completed"
        );

        System.out.println(
                rental.getCustomer().getDisplayName()
                        + " rented "
                        + rental.getCar().getDisplayName()
        );

        PricedItem pricedCar = (PricedItem) car;
        System.out.println("Daily price " + pricedCar.getDailyPrice());



        CustomerRepository customerRepo = new CustomerRepository();


        Customer customer = new Customer(11, "Raыаывbotaet", "vrodfdgdy", "ydfgraaa@mail.com", "+770865168877", LocalDate.of(1995, 10, 30));
        customerRepo.save(customer);

        System.out.println("Saved customer with ID: " + customer.getId());*/

        CustomerRepository customerRepo = new CustomerRepository();
        CarRepository carRepo = new CarRepository();
        RentalRepository rentalRepo = new RentalRepository();

        /*for (Customer c : customerRepo.findAll()) {
            System.out.println(c.getId() + " - " + c.getDisplayName());
        }*/
        //Customer customer2 = new Customer(15, "Alt", "Ber", "alt@mail.com", "+77166898877", LocalDate.of(1995,5,20));
        //customerRepo.save(customer2);
        //Customer customer2 = customerRepo.findById(12);
        //customerRepo.delete(customer2.getId());

        //Customer customer3 = new Customer(-5, "Meha", "Haha", "alt@mail.ru", "+77756213006",LocalDate.of(1995,5,13));
        //customerRepo.save(customer3);
        //customerRepo.delete(customer3.getId());
        Customer customer4 = customerRepo.findById(3);
        System.out.println(customer4.getPhone());







    }
}


