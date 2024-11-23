import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class MoviesClient {
    public static void main(String[] args) {
        try {
            // Connect to the Server
            MovieInterface movieService = (MovieInterface) Naming.lookup("rmi://localhost:1099/MovieInformation");
            // User input from console
            Scanner scanner = new Scanner(System.in);
            // Client Connected to the Server
            System.out.println("Connected to Movie System!");

            while (true) {
                // All option to the Client
                System.out.println("\nChoose an option:");
                System.out.println("1. Show All Available Movies");
                System.out.println("2. Search Movie");
                System.out.println("3. Rent Movie");
                System.out.println("4. Return Movie");
                System.out.println("5. Add Review");
                System.out.println("6 Show Rental History");
                System.out.println("7. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        // Display avalaible movies
                        System.out.println("Available Movies:");
                        // reterive the avalaible movies from the remote server
                        List<String> availableMovies = movieService.getAvailableMovies();
                        // Check if the list is empty and show none to rent
                        if (availableMovies.isEmpty()) {
                            System.out.println("No movies available for rent.");
                        } else {
                            // shows the movies and avalaible copies in the system
                            availableMovies.forEach(System.out::println);
                        }
                    }
                    case 2 -> {
                        // Search the query for movie based on title and query with user input
                        System.out.println("Enter search query(do by title, director, year):");
                        String query = scanner.nextLine();
                        System.out.println("Search by (title/director/year):");
                        String type = scanner.nextLine();

                        // retrieve the query type
                        List<String> results = movieService.searchMovie(query, type);

                        // Result is empty display no movies found
                        if (results.isEmpty()) {
                            System.out.println("No movies found.");
                        } else {
                            // Search if movie exit
                            System.out.println("Search Results found: ");
                            results.forEach(System.out::println);
                        }
                    }
                    case 3 -> {
                        // display Avalaible movies
                        System.out.println("Available Movies:");
                        // fetch movies from array
                        List<String> availableMovies = movieService.getAvailableMovies();
                        // display no movies if none found
                        if (availableMovies.isEmpty()) {
                            System.out.println("No movies available for rent.");
                        } else {
                            // Rent the movie and display rented successfully
                            availableMovies.forEach(System.out::println);
                            System.out.println("\nEnter Movie Title to rent:");
                            String movieTitle = scanner.nextLine();
                            boolean success = movieService.rentMovie(movieTitle);
                            System.out.println(success ? "Movie rented successfully!" : "Failed to rent movie.");
                        }
                    }
                    case 4 -> {
                        // return the movie to the array
                        System.out.println("Enter Movie Title to return:");
                        String movieTitle = scanner.nextLine();
                        boolean success = movieService.returnMovie(movieTitle);
                        System.out.println(success ? "Movie returned successfully!" : "Failed to return movie.");
                    }
                    case 5 -> {
                        // enter movie title to review update the review
                        System.out.println("Enter Movie Title to review:");
                        String movieTitle = scanner.nextLine();
                        System.out.println("Enter your review:");
                        String review = scanner.nextLine();
                        movieService.addReview(movieTitle, review);
                        System.out.println("Review added successfully!");
                    }
                    case 6 -> {
                        // Fetch the rental history
                        List<String> rentalHistory = movieService.getRentalHistory();
                        if (rentalHistory.isEmpty()) {
                            System.out.println("No rental history available.");
                        } else {
                            System.out.println("Your Rental History:");
                            for (String movie : rentalHistory) {
                                System.out.println("- " + movie);
                            }
                        }
                    }
                    case 7 -> {
                        // Exit the system
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                    // handle invalid options
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
