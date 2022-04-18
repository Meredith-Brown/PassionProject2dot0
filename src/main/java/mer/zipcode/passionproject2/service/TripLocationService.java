package mer.zipcode.passionproject2.service;

import java.util.List;
import java.util.Optional;
import mer.zipcode.passionproject2.domain.TripLocation;

/**
 * Service Interface for managing {@link TripLocation}.
 */
public interface TripLocationService {
    /**
     * Save a tripLocation.
     *
     * @param tripLocation the entity to save.
     * @return the persisted entity.
     */
    TripLocation save(TripLocation tripLocation);

    /**
     * Updates a tripLocation.
     *
     * @param tripLocation the entity to update.
     * @return the persisted entity.
     */
    TripLocation update(TripLocation tripLocation);

    /**
     * Partially updates a tripLocation.
     *
     * @param tripLocation the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TripLocation> partialUpdate(TripLocation tripLocation);

    /**
     * Get all the tripLocations.
     *
     * @return the list of entities.
     */
    List<TripLocation> findAll();

    /**
     * Get the "id" tripLocation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TripLocation> findOne(Long id);

    /**
     * Delete the "id" tripLocation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
