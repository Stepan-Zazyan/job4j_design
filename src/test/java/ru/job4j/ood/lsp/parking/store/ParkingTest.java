package ru.job4j.ood.lsp.parking.store;

import org.junit.Test;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.Sedan;
import ru.job4j.ood.lsp.parking.model.Truck;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingTest {

    @Test
    public void checkParkCar() {
        Parking parking = new Parking(5, 5);
        Car sedan = new Sedan(1);
        Car truck = new Truck(2, 3);
        parking.parkCar(sedan);
        parking.parkCar(truck);
        assertEquals(sedan, parking.getParkingArea().get(1));
        assertEquals(truck, parking.getParkingArea().get(2));
    }

    @Test
    public void checkRemove() {
        Parking parking = new Parking(5, 5);
        Car sedan = new Sedan(1);
        Car truck = new Truck(2, 3);
        parking.parkCar(sedan);
        parking.parkCar(truck);
        parking.removeCar(sedan);
        parking.removeCar(truck);
        assertNull(parking.getParkingArea().get(1));
        assertNull(parking.getParkingArea().get(2));
    }

    @Test
    public void checkLimitsWithNoTruckSpace() {
        Parking parking = new Parking(5, 5);
        Car sedan = new Sedan(1);
        Car truck = new Truck(2, 3);
        Car bigTruck = new Truck(2, 3);
        parking.parkCar(sedan);
        parking.parkCar(truck);
        parking.parkCar(bigTruck);
        int limitSedan  = parking.getLimitSedan();
        int limitTruck = parking.getLimitTruck();
        assertEquals(limitSedan, 3);
        assertEquals(limitTruck, 0);
    }

    @Test
    public void checkReturningLimits() {
        Parking parking = new Parking(5, 5);
        Car sedan = new Sedan(1);
        Car truck = new Truck(2, 3);
        Car bigTruck = new Truck(2, 3);
        parking.parkCar(sedan);
        parking.parkCar(truck);
        parking.parkCar(bigTruck);
        parking.removeCar(sedan);
        parking.removeCar(truck);
        parking.removeCar(bigTruck);
        int limitSedan  = parking.getLimitSedan();
        int limitTruck = parking.getLimitTruck();
        assertEquals(limitSedan, 5);
        assertEquals(limitTruck, 5);
    }

    @Test
    public void checkLimitsCrash() {
        Parking parking = new Parking(5, 5);
        Car bigTruck = new Truck(2, 11);
        assertThatThrownBy(() -> parking.parkCar(bigTruck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No free space");
    }
}