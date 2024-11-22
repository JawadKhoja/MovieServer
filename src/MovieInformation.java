import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieInformation extends UnicastRemoteObject implements MovieInterface {

    // stores the movie tiles as a hashmap
    private Map<String, Movie> movieCatalog = new HashMap<>();
    // Tracks rental history as a list of array
    private List<String> rentalHistory = new ArrayList<>();

    public MovieInformation() throws RemoteException {
        super();

        movieCatalog.put("The Matrix", new Movie("The Matrix", "The Wachowskis", 3, 1999));
        movieCatalog.put("Inception", new Movie("Inception", "Christopher Nolan", 2, 2010));
        movieCatalog.put("Interstellar", new Movie("Interstellar", "Christopher Nolan", 1, 2014));
        movieCatalog.put("The Dark Knight", new Movie("The Dark Knight", "Christopher Nolan", 4, 2008));
        movieCatalog.put("Pulp Fiction", new Movie("Pulp Fiction", "Quentin Tarantino", 5, 1994));
        movieCatalog.put("The Silence of the Lambs", new Movie("The Silence of the Lambs", "Jonathan Demme", 3, 1991));
        movieCatalog.put("Gladiator", new Movie("Gladiator", "Ridley Scott", 2, 2000));
        movieCatalog.put("The Social Network", new Movie("The Social Network", "David Fincher", 5, 2010));
        movieCatalog.put("Fight Club", new Movie("Fight Club", "David Fincher", 4, 1999));
    }

    public List<String> searchMovie(String query, String type) throws RemoteException {
        List<String> results = new ArrayList<>(); // Array that has movies stored
        String queryLower = query.toLowerCase(); // Allow lowercase search

        for (Movie movie : movieCatalog.values()) {
            // Search by title
            if ("title".equalsIgnoreCase(type) && movie.getTitle().toLowerCase().contains(queryLower)) {
                results.add(movie.getTitle());
            }
            // Search by director
            else if ("director".equalsIgnoreCase(type) && movie.getDirector().toLowerCase().contains(queryLower)) {
                results.add(movie.getTitle());
            }
            // Search by release year
            else if ("year".equalsIgnoreCase(type) && Integer.toString(movie.getReleaseYear()).contains(queryLower)) {
                results.add(movie.getTitle());
            }
        }

        return results;
    }


    public boolean rentMovie(String movieTitle) throws RemoteException {
        System.out.println("Attempting to rent movie: " + movieTitle);
        Movie movie = movieCatalog.get(movieTitle);
        // if movie exist showfound movie
        if (movie != null) {
            System.out.println("Found movie: " + movie.getTitle() + " with " + movie.getAvailableCopies() + " available copies.");
           // shows the avalaible copy
            if (movie.getAvailableCopies() > 0) {
                // reduces the copy from array when users rents it
                movie.setAvailableCopies(movie.getAvailableCopies() - 1);
                // Add rented movie to rental history
                rentalHistory.add(movie.getTitle());
                System.out.println("Rented the movie: " + movie.getTitle());
                return true;
            } else {
                 // if no more copies to rent
                System.out.println("No available copies left to rent for" + movie.getTitle());
                return false;
            }
        } else {
            // Movies doesn't exist in the system
            System.out.println("Movie not found: " + movieTitle);
            return false;
        }
    }


    @Override
    public boolean returnMovie(String movieTitle) throws RemoteException {
        // Normalize the movie title to lowercase
        movieTitle = movieTitle.toLowerCase();
        // check the movies array
        for (Movie movie : movieCatalog.values()) {
            // Add the copy back to the array
            if (movie.getTitle().toLowerCase().equals(movieTitle)) {
                movie.setAvailableCopies(movie.getAvailableCopies() + 1);
                return true;
            }
        }
        return false; // Movie not found
    }

    @Override
    public void addReview(String movieTitle, String review) throws RemoteException {
        // check the movie array
        Movie movie = movieCatalog.get(movieTitle);
       // if exist add review
        if (movie != null) {
            movie.addReview(review);
        }else
            // Can't review since movie isn't in the system
            System.out.println("This movie isn't in the system to review" + movieTitle);
    }


    @Override
    public List<String> getAvailableMovies() throws RemoteException {
        // Intializes to store the values of avalaible movies
        List<String> availableMovies = new ArrayList<>();
        // iterate though the movies
        for (Movie movie : movieCatalog.values()) {
            // checks the copies for each movie
            if (movie.getAvailableCopies() > 0) {
                // adds movie tile and avalaible copy
                availableMovies.add(movie.getTitle() + " - Available Copies: " + movie.getAvailableCopies());
            }
        }
        // return avalaible copy
        return availableMovies;
    }


    @Override
    public List<String> getRentalHistory() throws RemoteException {
        return rentalHistory; // Return the list of rented movies
    }

}
