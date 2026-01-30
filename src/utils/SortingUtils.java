package utils;
import model.Car;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class SortingUtils {

    //все машины с ценой больше минпрайса
    public static List<Car> filterByMinPrice(List<Car> cars, int minPrice) {
        return cars.stream()
                .filter(c -> c.getDailyPrice() >= minPrice)
                .collect(Collectors.toList());
    }

    //сортировка по цене
    public static List<Car> sortByPriceDesc(List<Car> cars) {
        return cars.stream()
                .sorted((a, b) -> Integer.compare(b.getDailyPrice(), a.getDailyPrice())) // lambda comparator
                .collect(Collectors.toList());
    }

    //самая дорогая машина
    public static Car getMostExpensive(List<Car> cars) {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getDailyPrice)) // method reference тоже ок
                .orElse(null);
    }

}
