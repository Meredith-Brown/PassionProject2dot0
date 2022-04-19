export const loadMapApi = () => {
    const mapsURL =
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
