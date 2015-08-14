//$(document).ready(function () {
/* global google, map */

function initialize() {
    var mapOptions = {
        center: {
            lat: 9.8560355,
            lng: -83.9120774
        },
        zoom: 18
    };
    map = new google.maps.Map(document.getElementById('map-canvas'),
            mapOptions);
    /*
     // Limit the zoom level
     google.maps.event.addListener(map, 'zoom_changed', function () {
     if (map.getZoom() < 18)
     map.setZoom(18);
     
     });*/

}

/*
 
 var rectangleMate = new google.maps.Rectangle({
 strokeColor: '#FF0000',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#FF0000',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.856168, -83.913308),
 new google.maps.LatLng(9.856340, -83.913042))
 });
 
 var rectangleFisica = new google.maps.Rectangle({
 strokeColor: '#0000FF',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#0000FF',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.856167, -83.913313),
 new google.maps.LatLng(9.855899, -83.913061))
 });
 
 var rectangleB3 = new google.maps.Rectangle({
 strokeColor: '#33CC33',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#33CC33',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.856421, -83.912764),
 new google.maps.LatLng(9.856276, -83.912418))
 });
 
 var rectangleB2 = new google.maps.Rectangle({
 strokeColor: '#FF9966',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#FF9966',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.856216, -83.912781),
 new google.maps.LatLng(9.856062, -83.912411))
 });
 
 var rectangleB1 = new google.maps.Rectangle({
 strokeColor: '#FF66CC',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#FF66CC',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.855999, -83.912795),
 new google.maps.LatLng(9.855856, -83.912442))
 });
 
 var rectangleCompu = new google.maps.Rectangle({
 strokeColor: '#FFFF66',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#FFFF66',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.856983, -83.912683),
 new google.maps.LatLng(9.856634, -83.912552))
 });
 
 var rectangle1 = new google.maps.Rectangle({
 strokeColor: '#929292',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#929292',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.855782, -83.913328),
 new google.maps.LatLng(9.855640, -83.912978))
 });
 
 var rectangle2 = new google.maps.Rectangle({
 strokeColor: '#929292',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#929292',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.855536, -83.912368),
 new google.maps.LatLng(9.855354, -83.912114))
 });
 
 var rectangle3 = new google.maps.Rectangle({
 strokeColor: '#929292',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#929292',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.855559, -83.913191),
 new google.maps.LatLng(9.855210, -83.912668))
 });
 
 var rectangle4 = new google.maps.Rectangle({
 strokeColor: '#929292',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#929292',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.856709, -83.912279),
 new google.maps.LatLng(9.856467, -83.911637))
 });
 
 var rectangle5 = new google.maps.Rectangle({
 strokeColor: '#929292',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#929292',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.856678, -83.913144),
 new google.maps.LatLng(9.856528, -83.912855))
 });
 
 var rectangle6 = new google.maps.Rectangle({
 strokeColor: '#929292',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#929292',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.856071, -83.912315),
 new google.maps.LatLng(9.855907, -83.911844))
 });
 
 var rectangle7 = new google.maps.Rectangle({
 strokeColor: '#929292',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#929292',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.856420, -83.909943),
 new google.maps.LatLng(9.855754, -83.908950))
 });
 
 var rectangle8 = new google.maps.Rectangle({
 strokeColor: '#929292',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#929292',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.855752, -83.912468),
 new google.maps.LatLng(9.855604, -83.912107))
 });
 var rectangle9 = new google.maps.Rectangle({
 strokeColor: '#929292',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#929292',
 fillOpacity: 0.35,
 map: map,
 bounds: new google.maps.LatLngBounds(
 new google.maps.LatLng(9.855699, -83.912021),
 new google.maps.LatLng(9.855555, -83.911671))
 });*/


/*
 google.maps.event.addListener(rectangleMate, 'click', function (event) {
 alert("Latitude: " + event.latLng.lat() + " " + ", longitude: " + event.latLng.lng());
 });
 */


google.maps.event.addDomListener(window, 'load', initialize);




var INTERVALREC = 10000;
var zoneStore = {};

function getRectangles() {
    $.ajax({
        type: 'GET',
        url: 'webresources/zones/retrieve-zones',
        contentType: 'application/json',
        dataType: "json", //linea fragril
        beforeSend: function (xhr) {
            // Set the CSRF Token in the header for security
            var token = window.sessionStorage.accessToken;
            if (token){
                xhr.setRequestHeader('userToken', token);
            } 
        },
        success: function (res, textStatus, jqXHR) {
            if (jqXHR.status !== 204) {
                alert("correct");
                for (var i = 0; i < res.length; i++) {
                    if (zoneStore.hasOwnProperty(res[i].name)) {
                        if (zoneStore.hasOwnProperty(res[i].color) !== res[i].color) {
                            //zoneStore[res[i].name].
                        }
                    } else {
                        var rectangle = new google.maps.Rectangle({
                            strokeColor: res[i].color,
                            strokeOpacity: 0.8,
                            strokeWeight: 2,
                            fillColor: res[i].color,
                            fillOpacity: 0.35,
                            map: map,
                            bounds: new google.maps.LatLngBounds(
                                    new google.maps.LatLng(res[i].lat1, res[i].long1),
                                    new google.maps.LatLng(res[i].lat2, res[i].long2))
                        });
                        zoneStore[res[i].name] = rectangle;
                    }
                }
                window.setTimeout(getRectangles, INTERVALREC);
            } else {
                alert("Error server");
            }
            
        },
        error: function () {
            alert('failure');
        }
    });
}



//Used to remember markers
var markerStore = {};

//Time between marker refreshes
var INTERVAL = 50;

function getMarkers() {
    $.ajax({
        type: 'GET',
        url: 'webresources/mobile/retrieve-position',
        contentType: 'application/json',
        dataType: "json", //linea fragril
        beforeSend: function (xhr) {
            // Set the CSRF Token in the header for security
            var token = window.sessionStorage.accessToken;
            if (token)
                xhr.setRequestHeader('userToken', token);
        },
        success: function (res, textStatus, jqXHR) {
            if (jqXHR.status !== 204) {
                for (var i = 0; i < res.length; i++) {
                    if (markerStore.hasOwnProperty(res[i].username)) {
                        markerStore[res[i].username].setPosition(new google.maps.LatLng(res[i].lat, res[i].long));
                    } else {
                        var marker = new google.maps.Marker({
                            position: new google.maps.LatLng(res[i].lat, res[i].long),
                            title: res[i].username,
                            map: map
                        });
                        markerStore[res[i].username] = marker;
                        console.log(marker.getTitle());
                    }
                }
                window.setTimeout(getMarkers, INTERVAL);
            }
        },
        error: function () {
            alert('failure');
        }
    });
}


//});

