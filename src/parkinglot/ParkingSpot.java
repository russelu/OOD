package parkinglot;

import parkinglot.vehicle.Bus;
import parkinglot.vehicle.Type;
import parkinglot.vehicle.Vehicle;

public class ParkingSpot {
    private int id;
    private Vehicle vehicle;
    private final Type type;

    public ParkingSpot(Type t, int id) {
        type = t;
        this.id = id;
        vehicle = null;
    }

    public Type getType() {
        return type;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean canPark(Vehicle v) {
        return vehicle == null && v.getType() == type;
    }

    public synchronized boolean park(Vehicle v) {
        if (!canPark(v)) return false;
        vehicle = v;
        return true;
    }

    public Vehicle leavePark() {
        Vehicle leavingVehicle = vehicle;
        vehicle = null;
        return leavingVehicle;
    }

    public String toString() {
        return "Spot " + id + " is " + (vehicle == null ? "empty." : vehicle.toString());
    }

    public static void main(String[] args) {
        ParkingSpot ps = new ParkingSpot(Type.BUS, 1);
        Bus bus1 = new Bus();
        System.out.println(ps.getType());
        System.out.println(ps.canPark(bus1));
        ps.park(bus1);
        System.out.println(ps.toString());
    }
}
