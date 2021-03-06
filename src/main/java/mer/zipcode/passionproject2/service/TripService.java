package mer.zipcode.passionproject2.service;

import java.util.List;
import java.util.Optional;
import mer.zipcode.passionproject2.domain.Trip;

/**
 * Service Interface for managing {@link Trip}.
 */
public interface TripService {
    /**
     * Save a trip.
     *
     * @param trip the entity to save.
     * @return the persisted entity.
     */
    Trip save(Trip trip);

    /**
     * Updates a trip.
     *
     * @param trip the entity to update.
     * @return the persisted entity.
     */
    Trip update(Trip trip);

    /**
     * Partially updates a trip.
     *
     * @param trip the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Trip> partialUpdate(Trip trip);

    /**
     * Get all the trips.
     *
     * @return the list of entities.
     */
    List<Trip> findAll();

    /**
     * Get the "id" trip.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Trip> findOne(Long id);

    /**
     * Delete the "id" trip.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
