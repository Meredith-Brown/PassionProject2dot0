import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TripLocation from './trip-location';
import TripLocationDetail from './trip-location-detail';
import TripLocationUpdate from './trip-location-update';
import TripLocationDeleteDialog from './trip-location-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TripLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TripLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TripLocationDetail} />
      <ErrorBoundaryRoute path={match.url} component={TripLocation} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TripLocationDeleteDialog} />
  </>
);

export default Routes;
