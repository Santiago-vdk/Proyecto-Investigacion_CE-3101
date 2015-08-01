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

    // Limit the zoom level
    /* google.maps.event.addListener(map, 'zoom_changed', function () {
     if (map.getZoom() < 18)
     map.setZoom(18);
     
     });*/

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


}

google.maps.event.addDomListener(window, 'load', initialize);

//Used to remember markers
var markerStore = {};

//Time between marker refreshes
var INTERVAL = 2000;


function getMarkers() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/KingOfTheHill/webresources/markers/vehicles',
        contentType: 'application/json',
        dataType: "json", //linea fragril
        beforeSend: function (xhr) {
            // Set the CSRF Token in the header for security
            var token = window.sessionStorage.accessToken;
            if (token)
                xhr.setRequestHeader('userToken', token);
        },
        success: function (res, textStatus, jqXHR) {
            for (var i = 0, len = res.length; i < len; i++) {
                if (markerStore.hasOwnProperty(res[i].id)) {
                    markerStore[res[i].id].setPosition(new google.maps.LatLng(res[i].pos.lat, res[i].pos.long));
                } else {
                    var marker = new google.maps.Marker({
                        //position: new google.maps.LatLng(res[i].pos.lat, res[i].pos.long),
                        position: new google.maps.LatLng(res[i].pos.lat, res[i].pos.long),
                        title: res[i].name,
                        map: map
                    });
                    markerStore[res[i].id] = marker;
                    console.log(marker.getTitle());
                }
            }
            window.setTimeout(getMarkers, INTERVAL);
        },
        error: function () {
            alert('failure');
        }
    });
//        
//        $.get('http://localhost:8080/KingOfTheHill/webresources/markers/vehicles', {}, function (res, resp) {
//            for (var i = 0, len = res.length; i < len; i++) {
//                alert("here");
//                var marker = new google.maps.Marker({
//                    position: new google.maps.LatLng(res[i].position.lat, res[i].position.long),
//                    title: res[i].name,
//                    map: map
//                });
//            }
//            window.setTimeout(getMarkers, INTERVAL);
//        }, "json");
//        
//        


}


//});

