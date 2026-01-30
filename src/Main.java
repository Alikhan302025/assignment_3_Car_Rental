import model.*;
import utils.DatabaseConnection;

import java.io.PrintStream;
import java.sql.Connection;
import java.time.LocalDate;
import repository.*;
import service.*;
import controller.RentalController;
import service.RentalService;
import model.Customer;
import java.time.LocalDate;
import utils.SortingUtils;
import utils.ReflectionUtils;

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
        //Customer customer4 = customerRepo.findById(3);
        //System.out.println(customer4.getPhone());


        //бизнес логика
        //CustomerService customerService = new CustomerService();
        //CarService carService = new CarService();

        //RentalRepository rentalRepository = new RentalRepository();
        //CustomerRepository customerRepository = new CustomerRepository();
        //CarRepository carRepository = new CarRepository();

        //RentalService rentalService = new RentalService(rentalRepository);
        //RentalController rentalController = new RentalController(rentalService);

        //Customer customer3 = new Customer(15, "Medgha", "Hadfgha", "adfglt@mail.ru", "+777562556006",LocalDate.of(1995,5,23));
        //Car newCar = new Car(10, "Toyota", "Camry", 2020, 18000, 1);
        //System.out.println(customer3.getId());

        //customerService.create(customer3);

        //Customer cFromDb = customerService.getById(customer3.getId());
        //System.out.println(cFromDb.getDisplayName());

        //newCar.setDailyPrice(20000);
        //carService.update(newCar);


        /*for (Customer c : customerService.getAll()) {
            System.out.println(c.getId() + c.getDisplayName() + c.getEmail());
        }*/

        Repository<Rental> rentalRepo = new RentalRepository();
        RentalServiceInterface rentalService = new RentalService(rentalRepo);


        Repository<Customer> customerRepo = new CustomerRepository();
        Repository<Car> carRepo = new CarRepository();

        //CustomerService customerService = new CustomerService(customerRepo);
        //CarService carService = new CarService(carRepo);

        CarServiceInterface carService = new CarService(carRepo);
        CustomerServiceInterface customerService = new CustomerService(customerRepo);


        var cars = carService.getAll();

        var expensive = SortingUtils.filterByMinPrice(cars, 10000);
        System.out.println("cars with price >= 10000 " + expensive.size());

        var sorted = SortingUtils.sortByPriceDesc(cars);
        sorted.get(0).print();

        var mostExpensive = SortingUtils.getMostExpensive(cars);
        mostExpensive.print();


        System.out.println(" ");
        System.out.println("Reflection");
        if (!cars.isEmpty()) {
            ReflectionUtils.printClassInfo(cars.get(0));
        } else {
            System.out.println("No cars in database to show reflection.");
        }

        //System.out.println(Printable.separator());
        //cars.get(1).print();
        //System.out.println(Printable.separator());

        //System.out.println("Cars count: " + carService.getAll().size());
        //System.out.println("Customers count: " + customerService.getAll().size());





















    }
}


