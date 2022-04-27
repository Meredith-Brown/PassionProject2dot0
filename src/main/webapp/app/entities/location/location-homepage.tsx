import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps, withRouter } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ILocation } from 'app/shared/model/location.model';
import { getEntities } from './location.reducer';

const LocationHomePage = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const locationList = useAppSelector(state => state.location.entities);
  const loading = useAppSelector(state => state.location.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="location-heading" data-cy="LocationHeading">
        <Translate contentKey="passionProject2App.location.home.title">My Locations</Translate>
      </h2>
      <div className="table-responsive">
        {locationList && locationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="passionProject2App.location.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="passionProject2App.location.city">City</Translate>
                </th>
                <th>
                  <Translate contentKey="passionProject2App.location.stateProvince">State Province</Translate>
                </th>
                <th>
                  <Translate contentKey="passionProject2App.location.country">Country</Translate>
                </th>
                <th>
                  <Translate contentKey="passionProject2App.location.visited">Visited</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {locationList.map((location, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/location/${location.id}`} color="link" size="sm">
                      {location.name}
                    </Button>
                  </td>
                  <td>{location.city}</td>
                  <td>{location.stateProvince}</td>
                  <td>{location.country}</td>
                  <td>{location.visited ? 'true' : 'false'}</td>
                  <td className="text-end">
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="passionProject2App.location.home.notFound">No Locations found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default withRouter(LocationHomePage);
