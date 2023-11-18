public class Passenger {
    private int currentFloor;
    private int destinationFloor;
    private boolean inElevator;
    private int waitTime;
    private Elevator requestedElevator;

    public Passenger(int currentFloor, int destinationFloor) {
        validateFloors(currentFloor, destinationFloor);
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.inElevator = false;
        this.waitTime = 0;
    }

    private void validateFloors(int currentFloor, int destinationFloor) {
        if (currentFloor < 1 || destinationFloor < 1 || currentFloor == destinationFloor) {
            throw new IllegalArgumentException("Invalid floor numbers");
        }
    }

    public void callElevator(ElevatorControlSystem controlSystem) {
        requestedElevator = controlSystem.requestElevator(this);
        // TODO: Add error handling if no elevator is available
    }

    public void enterElevator() {
        if (requestedElevator != null && requestedElevator.getCurrentFloor() == currentFloor) {
            inElevator = true;
            requestedElevator.boardPassenger(this);
            waitTime = 0;
        }
    }

    public void updateWaitTime() {
        if (!inElevator) {
            waitTime++;
        }
    }

    public void exitElevator() {
        if (inElevator && requestedElevator.getCurrentFloor() == destinationFloor) {
            inElevator = false;
            requestedElevator.releasePassenger(this);
            requestedElevator = null;
        }
    }

    // Getters
    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public boolean isInElevator() {
        return inElevator;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public Elevator getRequestedElevator() {
        return requestedElevator;
    }

    // Setters
    // Use setter methods sparingly, only if they are necessary
    // ...

    // Increment wait time directly
    public void incrementWaitTime() {
        waitTime++;
    }
}
