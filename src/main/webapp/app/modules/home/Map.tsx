// /// <reference types="@types/googlemaps" />
import React, {useEffect, useRef, useState} from 'react';
// import {} from '@types/googlemaps';
// import {} from 'node_modules/@types/google.maps';
// import {} from 'google.maps';

interface IMap {
    mapType: google.maps.MapTypeId,
    mapTypeControl?: boolean;
}

type GoogleLatLng = google.maps.LatLng;
type GoogleMap = google.maps.Map;

const Map: React.FC<IMap> = ({mapType, mapTypeControl = false}) => {

    const ref = useRef<HTMLDivElement>(null);
    const [map, setMap] = useState<GoogleMap>();

    const startMap = ():void => {
        if (!map) {
            defaultMapStart();
        }
    };
    useEffect(startMap, [map]);

    const defaultMapStart = ():void => {
      const defaultAddress = new google.maps.LatLng(39.746, -75.549);
      initMap(7, defaultAddress);
    };

    const initMap = (zoomLevel: number, address: GoogleLatLng): void => {
        if (ref.current) {
            setMap(
                new google.maps.Map(ref.current, {
                    zoom: zoomLevel,
                    center: address,
                    mapTypeControl,
                    streetViewControl: false,
                    zoomControl: true,
                    mapTypeId: mapType
                })
            )
        }
    };

    return (
        <div className="map-container">
            <div ref={ref} className="map-container"></div>
        </div>
    );
}

export default Map;
