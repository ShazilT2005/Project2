import java.util.List;

public class Building {
    private int numberOfFloors;
    private ElevatorControlSystem elevatorControlSystem;

    public Building(int floors, List<Elevator> elevators) {
        this.numberOfFloors = floors;
        this.elevatorControlSystem = new ElevatorControlSystem(elevators);
    }

    public void simulateTick() {
        elevatorControlSystem.step();
    }

    public void addPassenger(Passenger passenger) {
        passenger.callElevator(elevatorControlSystem);
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public ElevatorControlSystem getElevatorControlSystem() {
        return elevatorControlSystem;
    }
}
