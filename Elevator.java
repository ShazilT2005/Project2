import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int currentFloor;
    private boolean movingUp;
    private final List<Passenger> passengers;
    private final int capacity;
    private final List<Integer> destinationFloors;

    private int trips;
    private int stops;
    private int passengersTransported;

    public Elevator(int capacity) {
        this.currentFloor = 1;
        this.movingUp = true;
        this.passengers = new ArrayList<>();
        this.capacity = capacity;
        this.destinationFloors = new ArrayList<>();
        this.trips = 0;
        this.stops = 0;
        this.passengersTransported = 0;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void move() {
        if (!destinationFloors.isEmpty()) {
            if (movingUp) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            checkDestinationFloor();
            trips++;
        }
    }

    public void boardPassenger(Passenger p) {
        if (this.passengers.size() >= capacity) {
            throw new IllegalStateException("Elevator is full");
        }
        this.passengers.add(p);
        addDestinationFloor(p.getDestinationFloor());
    }

    private void addDestinationFloor(int destinationFloor) {
        if (!destinationFloors.contains(destinationFloor)) {
            destinationFloors.add(destinationFloor);
        }
    }

    public void releasePassenger(Passenger p) {
        this.passengers.remove(p);
    }

    private void checkDestinationFloor() {
        if (destinationFloors.contains(currentFloor)) {
            unloadPassenger();
            destinationFloors.remove(Integer.valueOf(currentFloor));
            stops++;
        }
    }

    private void unloadPassenger() {
        List<Passenger> passengersToUnload = new ArrayList<>(this.passengers);
        for (Passenger passenger : passengersToUnload) {
            if (passenger.getDestinationFloor() == currentFloor) {
                passenger.exitElevator();
                releasePassenger(passenger);
                passengersTransported++;
            }
        }
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Integer> getDestinationFloors() {
        return destinationFloors;
    }

    public void update() {
        move();
        checkDestinationFloor();
    }

    public int getTrips() {
        return trips;
    }
    
    public int getStops() {
        return stops;
    }
    
    public int getPassengersTransported() {
        return passengersTransported;
    }
}
