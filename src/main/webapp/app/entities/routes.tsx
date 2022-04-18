import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Trip from './trip';
import Location from './location';
import TripLocation from './trip-location';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default ({ match }) => {
  return (
    <div>
      <Switch>
        {/* prettier-ignore */}
        <ErrorBoundaryRoute path={`${match.url}trip`} component={Trip} />
        <ErrorBoundaryRoute path={`${match.url}location`} component={Location} />
        <ErrorBoundaryRoute path={`${match.url}trip-location`} component={TripLocation} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </Switch>
    </div>
  );
};
