import React, { useEffect } from 'react';
import { Link, RouteComponentProps, withRouter } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './location.reducer';

const LocationHomePageDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const locationEntity = useAppSelector(state => state.location.entity);
  return (
  <div className="location-homepage-container">
    <Row>
      <Col md="7">
        <h3 data-cy="locationDetailsHeading">
          <Translate contentKey="passionProject2App.location.detail.title">Location</Translate>
        </h3>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="passionProject2App.location.name">Name</Translate>
            </span>
          </dt>
          <dd>{locationEntity.name}</dd>
          <dt>
            <span id="visited">
              <Translate contentKey="passionProject2App.location.visited">Visited</Translate>
            </span>
          </dt>
          <dd>{locationEntity.visited ? 'true' : 'false'}</dd>
        </dl>
      </Col>
    </Row>
    </div>
  );
};

export default withRouter(LocationHomePageDetail);

// <FontAwesomeIcon icon="arrow-left" />{' '}
