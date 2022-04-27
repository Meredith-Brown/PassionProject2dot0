export const loadMapApi = () => {
    const mapsURL = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyC2oRf7LRHQLEwz7koCJvj7r3UcUbfcisE&libraries=places&language=en&v=3.48';
    const scripts = document.getElementsByTagName('script');

    for (let i = 0; i < scripts.length; i++) {
        if (scripts[i].src.indexOf(mapsURL) === 0) {
            return scripts[i];
        }
    }

//     if (typeof google === 'object' && typeof google.maps === 'object') {...}

    const googleMapScript = document.createElement('script');
    googleMapScript.src = mapsURL;
    googleMapScript.async = true;
    googleMapScript.defer = true;
    window.document.body.appendChild(googleMapScript);

    return googleMapScript;
};
