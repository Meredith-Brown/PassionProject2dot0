import trip from 'app/entities/trip/trip.reducer';
import location from 'app/entities/location/location.reducer';
import tripLocation from 'app/entities/trip-location/trip-location.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  trip,
  location,
  tripLocation,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
