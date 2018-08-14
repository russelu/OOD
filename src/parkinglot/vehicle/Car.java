package parkinglot.vehicle;

public class Car extends Vehicle {
    public Car() {
        super(Type.CAR);
    }

    public String toString() {
        return String.valueOf(super.type);
    }
}
