package mer.zipcode.passionproject2.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import mer.zipcode.passionproject2.domain.TripLocation;
import mer.zipcode.passionproject2.repository.TripLocationRepository;
import mer.zipcode.passionproject2.service.TripLocationService;
import mer.zipcode.passionproject2.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link mer.zipcode.passionproject2.domain.TripLocation}.
 */
@RestController
@RequestMapping("/api")
public class TripLocationResource {

    private final Logger log = LoggerFactory.getLogger(TripLocationResource.class);

    private static final String ENTITY_NAME = "tripLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TripLocationService tripLocationService;

    private final TripLocationRepository tripLocationRepository;

    public TripLocationResource(TripLocationService tripLocationService, TripLocationRepository tripLocationRepository) {
        this.tripLocationService = tripLocationService;
        this.tripLocationRepository = tripLocationRepository;
    }

    /**
     * {@code POST  /trip-locations} : Create a new tripLocation.
     *
     * @param tripLocation the tripLocation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tripLocation, or with status {@code 400 (Bad Request)} if the tripLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trip-locations")
    public ResponseEntity<TripLocation> createTripLocation(@RequestBody TripLocation tripLocation) throws URISyntaxException {
        log.debug("REST request to save TripLocation : {}", tripLocation);
        if (tripLocation.getId() != null) {
            throw new BadRequestAlertException("A new tripLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TripLocation result = tripLocationService.save(tripLocation);
        return ResponseEntity
            .created(new URI("/api/trip-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trip-locations/:id} : Updates an existing tripLocation.
     *
     * @param id the id of the tripLocation to save.
     * @param tripLocation the tripLocation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tripLocation,
     * or with status {@code 400 (Bad Request)} if the tripLocation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tripLocation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trip-locations/{id}")
    public ResponseEntity<TripLocation> updateTripLocation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TripLocation tripLocation
    ) throws URISyntaxException {
        log.debug("REST request to update TripLocation : {}, {}", id, tripLocation);
        if (tripLocation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tripLocation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tripLocationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TripLocation result = tripLocationService.update(tripLocation);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tripLocation.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /trip-locations/:id} : Partial updates given fields of an existing tripLocation, field will ignore if it is null
     *
     * @param id the id of the tripLocation to save.
     * @param tripLocation the tripLocation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tripLocation,
     * or with status {@code 400 (Bad Request)} if the tripLocation is not valid,
     * or with status {@code 404 (Not Found)} if the tripLocation is not found,
     * or with status {@code 500 (Internal Server Error)} if the tripLocation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/trip-locations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TripLocation> partialUpdateTripLocation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TripLocation tripLocation
    ) throws URISyntaxException {
        log.debug("REST request to partial update TripLocation partially : {}, {}", id, tripLocation);
        if (tripLocation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tripLocation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tripLocationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TripLocation> result = tripLocationService.partialUpdate(tripLocation);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tripLocation.getId().toString())
        );
    }

    /**
     * {@code GET  /trip-locations} : get all the tripLocations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tripLocations in body.
     */
    @GetMapping("/trip-locations")
    public List<TripLocation> getAllTripLocations() {
        log.debug("REST request to get all TripLocations");
        return tripLocationService.findAll();
    }

    /**
     * {@code GET  /trip-locations/:id} : get the "id" tripLocation.
     *
     * @param id the id of the tripLocation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tripLocation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trip-locations/{id}")
    public ResponseEntity<TripLocation> getTripLocation(@PathVariable Long id) {
        log.debug("REST request to get TripLocation : {}", id);
        Optional<TripLocation> tripLocation = tripLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tripLocation);
    }

    /**
     * {@code DELETE  /trip-locations/:id} : delete the "id" tripLocation.
     *
     * @param id the id of the tripLocation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trip-locations/{id}")
    public ResponseEntity<Void> deleteTripLocation(@PathVariable Long id) {
        log.debug("REST request to delete TripLocation : {}", id);
        tripLocationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
