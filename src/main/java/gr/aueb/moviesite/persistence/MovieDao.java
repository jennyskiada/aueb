package gr.aueb.moviesite.persistence;

/**
 * Movie DAO Interface
 * @author eskiada
 */
public interface MovieDao {

    /**
     * Perform A Query Against dual In Order To Check Database's Status
     * @return 1 If Everything OK
     */
    Integer probeDatabase();
}
