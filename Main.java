public class Main {
    public static void main(String[] args) {
        // Determine the configuration file to use
        String configFile = args.length > 0 ? args[0] : "default-config.properties";

        // Initialize and run the simulation with the specified config file
        Simulation simulation = new Simulation(configFile);
        simulation.runSimulation();
    }
}
