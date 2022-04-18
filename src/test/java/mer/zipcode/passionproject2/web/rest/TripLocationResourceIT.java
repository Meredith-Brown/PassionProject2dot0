package mer.zipcode.passionproject2.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import mer.zipcode.passionproject2.IntegrationTest;
import mer.zipcode.passionproject2.domain.TripLocation;
import mer.zipcode.passionproject2.repository.TripLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TripLocationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TripLocationResourceIT {

    private static final String ENTITY_API_URL = "/api/trip-locations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TripLocationRepository tripLocationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTripLocationMockMvc;

    private TripLocation tripLocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TripLocation createEntity(EntityManager em) {
        TripLocation tripLocation = new TripLocation();
        return tripLocation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TripLocation createUpdatedEntity(EntityManager em) {
        TripLocation tripLocation = new TripLocation();
        return tripLocation;
    }

    @BeforeEach
    public void initTest() {
        tripLocation = createEntity(em);
    }

    @Test
    @Transactional
    void createTripLocation() throws Exception {
        int databaseSizeBeforeCreate = tripLocationRepository.findAll().size();
        // Create the TripLocation
        restTripLocationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tripLocation)))
            .andExpect(status().isCreated());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeCreate + 1);
        TripLocation testTripLocation = tripLocationList.get(tripLocationList.size() - 1);
    }

    @Test
    @Transactional
    void createTripLocationWithExistingId() throws Exception {
        // Create the TripLocation with an existing ID
        tripLocation.setId(1L);

        int databaseSizeBeforeCreate = tripLocationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTripLocationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tripLocation)))
            .andExpect(status().isBadRequest());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTripLocations() throws Exception {
        // Initialize the database
        tripLocationRepository.saveAndFlush(tripLocation);

        // Get all the tripLocationList
        restTripLocationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tripLocation.getId().intValue())));
    }

    @Test
    @Transactional
    void getTripLocation() throws Exception {
        // Initialize the database
        tripLocationRepository.saveAndFlush(tripLocation);

        // Get the tripLocation
        restTripLocationMockMvc
            .perform(get(ENTITY_API_URL_ID, tripLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tripLocation.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingTripLocation() throws Exception {
        // Get the tripLocation
        restTripLocationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTripLocation() throws Exception {
        // Initialize the database
        tripLocationRepository.saveAndFlush(tripLocation);

        int databaseSizeBeforeUpdate = tripLocationRepository.findAll().size();

        // Update the tripLocation
        TripLocation updatedTripLocation = tripLocationRepository.findById(tripLocation.getId()).get();
        // Disconnect from session so that the updates on updatedTripLocation are not directly saved in db
        em.detach(updatedTripLocation);

        restTripLocationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTripLocation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTripLocation))
            )
            .andExpect(status().isOk());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeUpdate);
        TripLocation testTripLocation = tripLocationList.get(tripLocationList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingTripLocation() throws Exception {
        int databaseSizeBeforeUpdate = tripLocationRepository.findAll().size();
        tripLocation.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTripLocationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tripLocation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tripLocation))
            )
            .andExpect(status().isBadRequest());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTripLocation() throws Exception {
        int databaseSizeBeforeUpdate = tripLocationRepository.findAll().size();
        tripLocation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTripLocationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tripLocation))
            )
            .andExpect(status().isBadRequest());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTripLocation() throws Exception {
        int databaseSizeBeforeUpdate = tripLocationRepository.findAll().size();
        tripLocation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTripLocationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tripLocation)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTripLocationWithPatch() throws Exception {
        // Initialize the database
        tripLocationRepository.saveAndFlush(tripLocation);

        int databaseSizeBeforeUpdate = tripLocationRepository.findAll().size();

        // Update the tripLocation using partial update
        TripLocation partialUpdatedTripLocation = new TripLocation();
        partialUpdatedTripLocation.setId(tripLocation.getId());

        restTripLocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTripLocation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTripLocation))
            )
            .andExpect(status().isOk());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeUpdate);
        TripLocation testTripLocation = tripLocationList.get(tripLocationList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateTripLocationWithPatch() throws Exception {
        // Initialize the database
        tripLocationRepository.saveAndFlush(tripLocation);

        int databaseSizeBeforeUpdate = tripLocationRepository.findAll().size();

        // Update the tripLocation using partial update
        TripLocation partialUpdatedTripLocation = new TripLocation();
        partialUpdatedTripLocation.setId(tripLocation.getId());

        restTripLocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTripLocation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTripLocation))
            )
            .andExpect(status().isOk());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeUpdate);
        TripLocation testTripLocation = tripLocationList.get(tripLocationList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingTripLocation() throws Exception {
        int databaseSizeBeforeUpdate = tripLocationRepository.findAll().size();
        tripLocation.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTripLocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tripLocation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tripLocation))
            )
            .andExpect(status().isBadRequest());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTripLocation() throws Exception {
        int databaseSizeBeforeUpdate = tripLocationRepository.findAll().size();
        tripLocation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTripLocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tripLocation))
            )
            .andExpect(status().isBadRequest());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTripLocation() throws Exception {
        int databaseSizeBeforeUpdate = tripLocationRepository.findAll().size();
        tripLocation.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTripLocationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tripLocation))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TripLocation in the database
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTripLocation() throws Exception {
        // Initialize the database
        tripLocationRepository.saveAndFlush(tripLocation);

        int databaseSizeBeforeDelete = tripLocationRepository.findAll().size();

        // Delete the tripLocation
        restTripLocationMockMvc
            .perform(delete(ENTITY_API_URL_ID, tripLocation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TripLocation> tripLocationList = tripLocationRepository.findAll();
        assertThat(tripLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
