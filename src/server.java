

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class server {
    public static void main(String[] args) {
        try {
            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Create an instance of the MovieInformation service
            MovieInformation movieService = new MovieInformation();

            // Register the service with the RMI registry
            Naming.rebind("rmi://localhost:1098/MovieInformation", movieService);

            System.out.println("Movie Information Service is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
