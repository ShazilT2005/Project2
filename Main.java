import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Determine the configuration file to use
        String configFile = args.length > 0 ? args[0] : "default-config.properties";

        // Validate if the provided configuration file exists
        if (!isValidConfigFile(configFile)) {
            System.err.println("Error: The specified configuration file does not exist or is inaccessible.");
            return;
        }

        // Initialize and run the simulation with the specified config file
        Simulation simulation = new Simulation(configFile);
        simulation.runSimulation();
    }

    private static boolean isValidConfigFile(String configFile) {
        File file = new File(configFile);
        return file.exists() && file.isFile();
    }
}
