package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Sedan;
import ru.job4j.ood.lsp.parking.model.Truck;

import java.util.*;

import static java.lang.Math.abs;

public class Parking implements ParkingPlace {
    private int id = 0;
    private int limitSedan;
    private int limitTruck;
    private int placesTakenByTruckFromSedan = 0;
    private Map<Integer, Car> parkingArea = new HashMap<>();

    public Parking(int limitSedan, int limitTruck) {
        this.limitSedan = limitSedan;
        this.limitTruck = limitTruck;
    }

     @Override
        public Car parkCar(Car car) {
            id++;
            car.setId(id);
            if (car.getClass().equals(Sedan.class)) {
                if (limitSedan - car.getSize()  > 0) {
                    limitSedan -= car.getSize();
                } else {
                    throw new IllegalArgumentException("No free space");
                }
            }
            if (car.getClass().equals(Truck.class)) {
                if (limitTruck - car.getSize()  > 0) {
                    limitTruck -= car.getSize();
                } else if (limitSedan + limitTruck - car.getSize() > 0) {
                    placesTakenByTruckFromSedan = abs(limitTruck - car.getSize());
                    limitTruck = 0;
                    limitSedan -= placesTakenByTruckFromSedan;
                } else {
                    throw new IllegalArgumentException("No free space");
                }
            }
           return parkingArea.putIfAbsent(car.getId(), car);
        }

    @Override
    public Car removeCar(Car car) {
        if (car.getClass().equals(Sedan.class)) {
            limitSedan += car.getSize();
        }
        if (car.getClass().equals(Truck.class)) {
            if (placesTakenByTruckFromSedan > 0 && car.getSize() - placesTakenByTruckFromSedan < 0) {
                limitSedan += car.getSize();
                placesTakenByTruckFromSedan -= car.getSize();
            } else if (placesTakenByTruckFromSedan > 0 && car.getSize() - placesTakenByTruckFromSedan > 0) {
                limitTruck += car.getSize() - placesTakenByTruckFromSedan;
                limitSedan += placesTakenByTruckFromSedan;
                placesTakenByTruckFromSedan = 0;
            } else {
                limitTruck += car.getSize();
            }
        }
        return parkingArea.remove(car.getId());
    }

    @Override
    public Optional<Car> findById(int id) {
        return Optional.ofNullable(parkingArea.get(id));
    }

    @Override
    public Collection<Car> findAll() {
        return parkingArea.values();
    }

    public Map<Integer, Car> getParkingArea() {
        return parkingArea;
    }

    public int getLimitSedan() {
        return limitSedan;
    }

    public int getLimitTruck() {
        return limitTruck;
    }
}
