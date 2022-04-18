package mer.zipcode.passionproject2.service.impl;

import java.util.List;
import java.util.Optional;
import mer.zipcode.passionproject2.domain.Trip;
import mer.zipcode.passionproject2.repository.TripRepository;
import mer.zipcode.passionproject2.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Trip}.
 */
@Service
@Transactional
public class TripServiceImpl implements TripService {

    private final Logger log = LoggerFactory.getLogger(TripServiceImpl.class);

    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Trip save(Trip trip) {
        log.debug("Request to save Trip : {}", trip);
        return tripRepository.save(trip);
    }

    @Override
    public Trip update(Trip trip) {
        log.debug("Request to save Trip : {}", trip);
        return tripRepository.save(trip);
    }

    @Override
    public Optional<Trip> partialUpdate(Trip trip) {
        log.debug("Request to partially update Trip : {}", trip);

        return tripRepository
            .findById(trip.getId())
            .map(existingTrip -> {
                if (trip.getStartDate() != null) {
                    existingTrip.setStartDate(trip.getStartDate());
                }
                if (trip.getEndDate() != null) {
                    existingTrip.setEndDate(trip.getEndDate());
                }

                return existingTrip;
            })
            .map(tripRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trip> findAll() {
        log.debug("Request to get all Trips");
        return tripRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Trip> findOne(Long id) {
        log.debug("Request to get Trip : {}", id);
        return tripRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Trip : {}", id);
        tripRepository.deleteById(id);
    }
}
