package parkinglot.vehicle;

public abstract class Vehicle {
    // fields
    protected final Type type;

    // methods
    protected Vehicle(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public abstract String toString();
}
