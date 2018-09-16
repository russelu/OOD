package parkinglot.vehicle;

public class Motorcycle extends Vehicle {
    public Motorcycle() {
        super(Type.MOTORCYCLE);
    }

    public String toString() {
        return String.valueOf(super.type);
    }
}
