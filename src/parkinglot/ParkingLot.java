package parkinglot;

import parkinglot.vehicle.Bus;
import parkinglot.vehicle.Car;
import parkinglot.vehicle.Motorcycle;
import parkinglot.vehicle.Vehicle;

public class ParkingLot {
   private final ParkingLevel[] levels;
   private final int size;

   public ParkingLot(int floors, int numBus, int numCar, int numMoto) {
       this.size = floors;
       levels = new ParkingLevel[floors];
       for (int i = 0; i < floors; ++i) {
           int bus = numBus / floors + (i < numBus % floors ? 1: 0);
           int car = numCar / floors + (i < numCar % floors ? 1: 0);
           int moto = numMoto / floors + (i < numMoto % floors ? 1: 0);
           levels[i] = new ParkingLevel(new int[]{bus, car, moto, i});
       }
   }

   public boolean canPark(Vehicle v) {
       for (int i = 0; i < size; ++i) {
           if (levels[i].canPark(v)) return true;
       }
       return false;
   }

   public synchronized boolean park(Vehicle v) {
       for (int i = 0; i < size; ++i) {
           if (levels[i].park(v)) return true;
       }
       return false;
   }

   public static void main(String[] args) {
       ParkingLot lot = new ParkingLot(8, 10, 50, 300);
       for (int i = 0; i < 379; ++i) {
           lot.park(new Car());
       }
       System.out.println(lot.canPark(new Bus()));
       System.out.println(lot.canPark(new Car()));
       System.out.println(lot.canPark(new Motorcycle()));
   }
}
