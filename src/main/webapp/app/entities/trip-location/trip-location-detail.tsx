import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './trip-location.reducer';

export const TripLocationDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const tripLocationEntity = useAppSelector(state => state.tripLocation.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tripLocationDetailsHeading">
          <Translate contentKey="passionProject2App.tripLocation.detail.title">TripLocation</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{tripLocationEntity.id}</dd>
          <dt>
            <Translate contentKey="passionProject2App.tripLocation.location">Location</Translate>
          </dt>
          <dd>{tripLocationEntity.location ? tripLocationEntity.location.id : ''}</dd>
          <dt>
            <Translate contentKey="passionProject2App.tripLocation.trip">Trip</Translate>
          </dt>
          <dd>{tripLocationEntity.trip ? tripLocationEntity.trip.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/trip-location" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/trip-location/${tripLocationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TripLocationDetail;
