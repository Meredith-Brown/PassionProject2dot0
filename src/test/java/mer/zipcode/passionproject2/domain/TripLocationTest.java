package mer.zipcode.passionproject2.domain;

import static org.assertj.core.api.Assertions.assertThat;

import mer.zipcode.passionproject2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TripLocationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TripLocation.class);
        TripLocation tripLocation1 = new TripLocation();
        tripLocation1.setId(1L);
        TripLocation tripLocation2 = new TripLocation();
        tripLocation2.setId(tripLocation1.getId());
        assertThat(tripLocation1).isEqualTo(tripLocation2);
        tripLocation2.setId(2L);
        assertThat(tripLocation1).isNotEqualTo(tripLocation2);
        tripLocation1.setId(null);
        assertThat(tripLocation1).isNotEqualTo(tripLocation2);
    }
}
