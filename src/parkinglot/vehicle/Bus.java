package parkinglot.vehicle;

public class Bus extends Vehicle {
    // fields

    // methods
    public Bus() {
        super(Type.BUS);
    }

    public String toString() {
        return String.valueOf(super.type);
    }
}
