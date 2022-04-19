import './home.scss';

import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { Row, Col, Alert } from 'reactstrap';

import { useAppSelector } from 'app/config/store';

import Map from "./Map";
import { loadMapApi } from './GoogleMapsUtils'; // re-add .ts?

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
      <p>My Trips</p>
      <p>My Locations</p>
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

// // Initialize and add the map
// function initMap(): void {
//   // The location of Uluru
//   const uluru = { lat: -25.344, lng: 131.031 };
//   // The map, centered at Uluru
//   const map = new google.maps.Map(
//     document.getElementById("map") as HTMLElement,
//     {
//       zoom: 4,
//       center: uluru,
//     }
//   );
//
//   // The marker, positioned at Uluru
//   const marker = new google.maps.Marker({
//     position: uluru,
//     map: map,
//   });
// }
//
// declare global {
//   interface Window {
//     initMap: () => void;
//   }
// }
// window.initMap = initMap;
