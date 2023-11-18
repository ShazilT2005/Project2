import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int currentFloor;
    private boolean movingUp;
    private final List<Passenger> passengers; // Renamed from 'passenger' to avoid naming conflict
    private final int capacity;
    private final List<Integer> destinationFloors;

    public Elevator(int capacity) {
        this.currentFloor = 1;
        this.movingUp = true;
        this.passengers = new ArrayList<>();
        this.capacity = capacity;
        this.destinationFloors = new ArrayList<>();
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
        }
    }

    public void boardPassenger(Passenger p) { // Renamed parameter to avoid naming conflict
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

    public void releasePassenger(Passenger p) { // Renamed parameter to avoid naming conflict
        this.passengers.remove(p);
    }

    private void checkDestinationFloor() {
        if (destinationFloors.contains(currentFloor)) {
            unloadPassenger();
            destinationFloors.remove(Integer.valueOf(currentFloor));
        }
    }

    private void unloadPassenger() {
        List<Passenger> passengersToUnload = new ArrayList<>(this.passengers);
        for (Passenger passenger : passengersToUnload) {
            if (passenger.getDestinationFloor() == currentFloor) {
                passenger.exitElevator();
                releasePassenger(passenger);
            }
        }
    }

    // Getters and Setters
    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Passenger> getPassengers() { // Renamed getter to match the field name
        return passengers;
    }

    public List<Integer> getDestinationFloors() {
        return destinationFloors;
    }
    public void update() {
        // Move the elevator
        move();

        // Check if the elevator has reached a destination floor
        checkDestinationFloor();


    }
}
