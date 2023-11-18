public class Passenger {
    private int currentFloor;
    private int destinationFloor;
    private boolean inElevator;
    private int waitTime;
    private Elevator requestedElevator;

    public Passenger(int currentFloor, int destinationFloor) {
        if (currentFloor < 1 || destinationFloor < 1 || currentFloor == destinationFloor) {
            throw new IllegalArgumentException("Invalid floor numbers");
        }
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.inElevator = false;
        this.waitTime = 0;
    }

    public void callElevator(ElevatorControlSystem controlSystem) {
        requestedElevator = controlSystem.requestElevator(this); // TODO: Add error handling if no elevator is available
    }

    public void enterElevator() {
        if (requestedElevator != null && requestedElevator.getCurrentFloor() == currentFloor) {
            inElevator = true;
            requestedElevator.boardPassenger(this);
            waitTime = 0;
        }
    }

    // TODO: Review for efficiency improvements
    public void updateWaitTime() {
        if (!inElevator) {
            waitTime++;
        }
    }

    // TODO: Review for efficiency improvements
    public void exitElevator() {
        if (inElevator && requestedElevator.getCurrentFloor() == destinationFloor) {
            inElevator = false;
            requestedElevator.releasePassenger(this);
            requestedElevator = null;
        }
    }

    // Getters
    public int getCurrentFloor() { return currentFloor; }
    public int getDestinationFloor() { return destinationFloor; }
    public boolean isInElevator() { return inElevator; }
    public int getWaitTime() { return waitTime; }
    public Elevator getRequestedElevator() { return requestedElevator; }

    // Setters
    public void setCurrentFloor(int currentFloor) { this.currentFloor = currentFloor; }
    public void setDestinationFloor(int destinationFloor) { this.destinationFloor = destinationFloor; }
    public void setInElevator(boolean inElevator) { this.inElevator = inElevator; }
    public void incrementWaitTime() {
        this.waitTime++;
    }
    public void setWaitTime(int waitTime) { this.waitTime = waitTime; }
    public void setRequestedElevator(Elevator requestedElevator) { this.requestedElevator = requestedElevator; }

    // ...
}
