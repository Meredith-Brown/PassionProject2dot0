package mer.zipcode.passionproject2.service.impl;

import java.util.List;
import java.util.Optional;
import mer.zipcode.passionproject2.domain.TripLocation;
import mer.zipcode.passionproject2.repository.TripLocationRepository;
import mer.zipcode.passionproject2.service.TripLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TripLocation}.
 */
@Service
@Transactional
public class TripLocationServiceImpl implements TripLocationService {

    private final Logger log = LoggerFactory.getLogger(TripLocationServiceImpl.class);

    private final TripLocationRepository tripLocationRepository;

    public TripLocationServiceImpl(TripLocationRepository tripLocationRepository) {
        this.tripLocationRepository = tripLocationRepository;
    }

    @Override
    public TripLocation save(TripLocation tripLocation) {
        log.debug("Request to save TripLocation : {}", tripLocation);
        return tripLocationRepository.save(tripLocation);
    }

    @Override
    public TripLocation update(TripLocation tripLocation) {
        log.debug("Request to save TripLocation : {}", tripLocation);
        return tripLocationRepository.save(tripLocation);
    }

    @Override
    public Optional<TripLocation> partialUpdate(TripLocation tripLocation) {
        log.debug("Request to partially update TripLocation : {}", tripLocation);

        return tripLocationRepository
            .findById(tripLocation.getId())
            .map(existingTripLocation -> {
                return existingTripLocation;
            })
            .map(tripLocationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripLocation> findAll() {
        log.debug("Request to get all TripLocations");
        return tripLocationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TripLocation> findOne(Long id) {
        log.debug("Request to get TripLocation : {}", id);
        return tripLocationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TripLocation : {}", id);
        tripLocationRepository.deleteById(id);
    }
}
