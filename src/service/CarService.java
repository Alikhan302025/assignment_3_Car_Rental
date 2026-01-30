package service;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.Car;
import repository.CarRepository;
import repository.Repository;

import java.util.List;

public class CarService implements CarServiceInterface {

    private final Repository<Car> carRepository;
    public CarService(Repository<Car> carRepository){
        this.carRepository = carRepository;
    }

    public void create(Car car) {
        if (car == null) {
            throw new InvalidInputException("Car cannot be null");
        }
        carRepository.save(car);
    }

    public Car getById(int id) {
        Car car = carRepository.findById(id);
        if (car == null) {
            throw new ResourceNotFoundException("Car not found with id: " + id);
        }
        return car;
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }


    public void update(Car car) {
        if (car == null || car.getId() <= 0) {
            throw new InvalidInputException("Invalid car ID");
        }
        carRepository.update(car);
    }

    public void delete(int id) {
        if (carRepository.findById(id) == null) {
            throw new ResourceNotFoundException("Car not found with id: " + id);
        }
        carRepository.delete(id);
    }
}

