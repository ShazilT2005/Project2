import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private final Properties properties = new Properties();

    private static final String DEFAULT_STRUCTURE = "linked";
    private static final int DEFAULT_FLOORS = 22;
    private static final double DEFAULT_PASSENGERS = 123432;
    private static final int DEFAULT_ELEVATORS = 2;
    private static final int DEFAULT_ELEVATOR_CAPACITY = 10;
    private static final int DEFAULT_DURATION = 12232;

    private static final String KEY_STRUCTURE = "structure";
    private static final String KEY_FLOORS = "floors";
    private static final String KEY_PASSENGERS = "passengers";
    private static final String KEY_ELEVATORS = "elevators";
    private static final String KEY_ELEVATOR_CAPACITY = "elevatorCapacity";
    private static final String KEY_DURATION = "duration";

    public Configuration(String filename) {
        try {
            properties.load(new FileInputStream(filename));
        } catch (IOException e) {
            System.err.println("Warning: Configuration file " + filename + " not found. Using default values.");
            setDefaultProperties();
        }
    }

    private void setDefaultProperties() {
        properties.setProperty(KEY_STRUCTURE, DEFAULT_STRUCTURE);
        properties.setProperty(KEY_FLOORS, Integer.toString(DEFAULT_FLOORS));
        properties.setProperty(KEY_PASSENGERS, Double.toString(DEFAULT_PASSENGERS));
        properties.setProperty(KEY_ELEVATORS, Integer.toString(DEFAULT_ELEVATORS));
        properties.setProperty(KEY_ELEVATOR_CAPACITY, Integer.toString(DEFAULT_ELEVATOR_CAPACITY));
        properties.setProperty(KEY_DURATION, Integer.toString(DEFAULT_DURATION));
    }

    public String getStructure() {
        return properties.getProperty(KEY_STRUCTURE, DEFAULT_STRUCTURE);
    }

    public int getFloors() {
        return parseInteger(properties.getProperty(KEY_FLOORS, String.valueOf(DEFAULT_FLOORS)));
    }

    public double getPassengerProbability() {
        return parseDouble(properties.getProperty(KEY_PASSENGERS, String.valueOf(DEFAULT_PASSENGERS)));
    }

    public int getElevators() {
        return parseInteger(properties.getProperty(KEY_ELEVATORS, String.valueOf(DEFAULT_ELEVATORS)));
    }

    public int getElevatorCapacity() {
        return parseInteger(properties.getProperty(KEY_ELEVATOR_CAPACITY, String.valueOf(DEFAULT_ELEVATOR_CAPACITY)));
    }

    public int getDuration() {
        return parseInteger(properties.getProperty(KEY_DURATION, String.valueOf(DEFAULT_DURATION)));
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("Warning: Unable to parse integer value. Using default value.");
            return DEFAULT_ELEVATORS;
        }
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.err.println("Warning: Unable to parse double value. Using default value.");
            return DEFAULT_PASSENGERS;
        }
    }
}
