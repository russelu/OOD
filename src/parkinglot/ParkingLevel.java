package parkinglot;

import parkinglot.vehicle.Type;
import parkinglot.vehicle.Vehicle;

import java.util.Arrays;

public class ParkingLevel {
    private final ParkingSpot[][] spots;
    private static final int BUS_INDEX = 0, CAR_INDEX = 1, MOTO_INDEX = 2;
    private final int id;
    private final int[] numSpots;

    public ParkingLevel(int[] config) {
        numSpots = Arrays.copyOf(config, 3);
        spots = new ParkingSpot[3][];
        this.id = config[3];
        spots[BUS_INDEX] = new ParkingSpot[numSpots[BUS_INDEX]];
        createSpots(spots[BUS_INDEX], Type.BUS);
        spots[CAR_INDEX] = new ParkingSpot[numSpots[CAR_INDEX]];
        createSpots(spots[CAR_INDEX], Type.CAR);
        spots[MOTO_INDEX] = new ParkingSpot[numSpots[MOTO_INDEX]];
        createSpots(spots[MOTO_INDEX], Type.MOTORCYCLE);
    }

    private void createSpots(ParkingSpot[] array, Type t) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = new ParkingSpot(t, i);
        }
    }

    public boolean canPark(Vehicle v) {
        switch (v.getType()) {
            case BUS:
                for (int i = 0; i < numSpots[BUS_INDEX]; ++i) {
                    if (spots[BUS_INDEX][i].canPark(v))
                        return true;
                }
            case CAR:
                for (int i = 0; i <  numSpots[CAR_INDEX]; ++i) {
                    if (spots[CAR_INDEX][i].canPark(v))
                        return true;
                }
            case MOTORCYCLE:
                for (int i = 0; i <  numSpots[MOTO_INDEX]; ++i) {
                    if (spots[MOTO_INDEX][i].canPark(v))
                        return true;
                }
        }
        return false;
    }

    public synchronized boolean park(Vehicle v) {
        switch (v.getType()) {
            case BUS:
                for (int i = 0; i < numSpots[BUS_INDEX]; ++i) {
                    if (spots[BUS_INDEX][i].park(v))
                        return true;
                }
            case CAR:
                for (int i = 0; i < numSpots[CAR_INDEX]; ++i) {
                    if (spots[CAR_INDEX][i].park(v))
                        return true;
                }
            case MOTORCYCLE:
                for (int i = 0; i < numSpots[MOTO_INDEX]; ++i) {
                    if (spots[MOTO_INDEX][i].park(v))
                        return true;
                }
        }
        return false;
    }
}
