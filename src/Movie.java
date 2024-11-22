import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String director;
    private int availableCopies;
    private int releaseYear;
    private List<String> reviews;  // List to store reviews

    // Constructor that includes the release year and initializes the reviews list
    public Movie(String title, String director, int availableCopies, int releaseYear) {
        this.title = title;
        this.director = director;
        this.availableCopies = availableCopies;
        this.releaseYear = releaseYear;
        this.reviews = new ArrayList<>();  // Initialize the reviews list
    }

    // Getter and setter methods
    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getReleaseYear() {
        return releaseYear;
    }


    public void addReview(String review) {
        reviews.add(review);
    }
}
