package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface ParkingPlace {

    int ID = 0;
    int LIMITSEDAN = 0;
    int LIMITTRUCK = 0;

    Map<Integer, Car> MAP = new HashMap<>();
    Car parkCar(Car car);

    Car removeCar(Car car);

    Optional<Car> findById(int id);

    Collection<Car> findAll();
}
