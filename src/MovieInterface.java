import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MovieInterface extends Remote {
    List<String> searchMovie(String query, String type) throws RemoteException;
    boolean rentMovie(String movieTitle) throws RemoteException;
    boolean returnMovie(String movieTitle) throws RemoteException;
    void addReview(String movieTitle, String review) throws RemoteException;
    List<String> getAvailableMovies() throws RemoteException;
    List<String> getRentalHistory() throws RemoteException;
}
