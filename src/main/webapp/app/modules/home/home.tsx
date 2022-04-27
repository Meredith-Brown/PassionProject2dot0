import './home.scss';

import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { Row, Col, Alert } from 'reactstrap';

import { useAppSelector } from 'app/config/store';

import Map from "./Map";
import { loadMapApi } from './GoogleMapsUtils'; // re-add .ts?

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import LocationHomePageDetail from "app/entities/location/location-homepage-detail";
import LocationHomePage from "app/entities/location/location-homepage";

// export const Home = () => {
function Home() {
  const account = useAppSelector(state => state.authentication.account);
  const [scriptLoaded, setScriptLoaded] = useState(false);

  useEffect( () => {
    const googleMapScript = loadMapApi();
    googleMapScript.addEventListener('load', function () {
      setScriptLoaded(true);
    })
  }, []);

  return (
    <Row>
      <Col md="9" className="pad">
        <span className="hipster rounded" />
        <div className="map-container">
           {scriptLoaded && (
              <Map mapType={google.maps.MapTypeId.ROADMAP} mapTypeControl={true} />
           )}
        </div>
      </Col>
      <Col md="3">
      <div className="trip-location-list">
          <LocationHomePage />
      </div>
      <Link to="/location/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
                  <FontAwesomeIcon icon="plus" />
                  &nbsp;
                  <Translate contentKey="passionProject2App.location.home.createLabel">Add a New Location</Translate>
                </Link>
      <p>My Trips</p>
{/*         <h2> */}
{/*           <Translate contentKey="home.title">Welcome, Java Hipster!</Translate> */}
{/*         </h2> */}
{/*         <p className="lead"> */}
{/*           <Translate contentKey="home.subtitle">This is your homepage</Translate> */}
{/*         </p> */}
        {account?.login ? (
          <div>
{/*             <Alert color="success"> */}
{/*               <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}> */}
{/*                 You are logged in as user {account.login}. */}
{/*               </Translate> */}
{/*             </Alert> */}
          </div>
        ) : (
          <div>
            <Alert color="warning">
              <Translate contentKey="global.messages.info.authenticated.prefix">If you want to </Translate>

              <Link to="/login" className="alert-link">
                <Translate contentKey="global.messages.info.authenticated.link"> sign in</Translate>
              </Link>
              <Translate contentKey="global.messages.info.authenticated.suffix">
                , you can try the default accounts:
                <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
                <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
              </Translate>
            </Alert>

            <Alert color="warning">
              <Translate contentKey="global.messages.info.register.noaccount">You do not have an account yet?</Translate>&nbsp;
              <Link to="/account/register" className="alert-link">
                <Translate contentKey="global.messages.info.register.link">Register a new account</Translate>
              </Link>
            </Alert>
          </div>
        )}
      </Col>
    </Row>
  );
};

export default Home;
