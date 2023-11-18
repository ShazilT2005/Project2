import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    private int totalTrips; // Total number of elevator trips
    private int totalStops; // Total number of stops made by all elevators
    private int totalPassengersTransported; // Total number of passengers transported
    private Configuration config;
    private ElevatorControlSystem controlSystem;
    private int ticks; // Duration of the simulation
    private Random random;
    private List<Passenger> allPassengers; // List to keep track of all passengers

    public Simulation(String configFile) {
        config = new Configuration(configFile);
        allPassengers = new ArrayList<>();
        initializeSimulation();
    }

    private void initializeSimulation() {
        ArrayList<Elevator> elevators = new ArrayList<>();
        for (int i = 0; i < config.getElevators(); i++) {
            elevators.add(new Elevator(config.getElevatorCapacity()));
        }
        controlSystem = new ElevatorControlSystem(elevators);
        ticks = config.getDuration();
        random = new Random();
    }

    public void runSimulation() {
        for (int tick = 0; tick < ticks; tick++) {
            generatePassengers();
            controlSystem.step();
            updatePassengerWaitTimes();
        }
        reportResults();
    }

    private void generatePassengers() {
        int floors = config.getFloors();
        double probability = config.getPassengerProbability();
        for (int floor = 1; floor <= floors; floor++) {
            if (random.nextDouble() < probability) {
                int destinationFloor;
                do {
                    destinationFloor = random.nextInt(floors) + 1;
                } while (destinationFloor == floor);
                Passenger passenger = new Passenger(floor, destinationFloor);
                controlSystem.addPassenger(passenger);
                allPassengers.add(passenger); // Add passenger to the list for tracking
            }
        }
    }

    private void updatePassengerWaitTimes() {
        for (Passenger passenger : allPassengers) {
            if (!passenger.isInElevator()) {
                passenger.incrementWaitTime(); // Ensure this method exists in Passenger
            }
        }
    }

    private void reportResults() {
        int totalWaitTime = 0;
        int longestWaitTime = 0;
        int shortestWaitTime = Integer.MAX_VALUE;
        for (Passenger passenger : allPassengers) {
            int waitTime = passenger.getWaitTime();
            totalWaitTime += waitTime;
            longestWaitTime = Math.max(longestWaitTime, waitTime);
            shortestWaitTime = Math.min(shortestWaitTime, waitTime);
        }
        double averageWaitTime = allPassengers.isEmpty() ? 0 : (double) totalWaitTime / allPassengers.size();
        System.out.println("Simulation Results:");
        System.out.println("Average Wait Time: " + averageWaitTime);
        System.out.println("Longest Wait Time: " + longestWaitTime);
        System.out.println("Shortest Wait Time: " + (shortestWaitTime == Integer.MAX_VALUE ? 0 : shortestWaitTime));
        System.out.println("Total Trips: " + totalTrips);
        System.out.println("Total Stops: " + totalStops);
        System.out.println("Total Passengers Transported: " + totalPassengersTransported);
    }
}
