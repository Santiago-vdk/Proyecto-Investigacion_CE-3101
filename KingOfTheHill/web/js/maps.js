//$(document).ready(function () {
/* global google, map, marketStore */
google.maps.visualRefresh = true;
function initialize() {
    var mapOptions = {
        center: {
            lat: 9.8560355,
            lng: -83.9120774
        },
        zoom: 18,
        mapTypeId: google.maps.MapTypeId.HYBRID
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

google.maps.event.addDomListener(window, 'load', initialize);




var INTERVALREC = 250;
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
                for (var i = 0; i < res.length; i++) {
                    if (zoneStore.hasOwnProperty(res[i].name)) {
                        if (zoneStore.hasOwnProperty(res[i].color) !== res[i].color) {
                            zoneStore[res[i].name].set('strokeColor', res[i].color);
                            zoneStore[res[i].name].set('fillColor', res[i].color);
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
            }
        },
        error: function () { 
            console.log("Error de solicitud al cargar zonas.");
        }
    });
}

//Used to remember markers
var markerStore = {};

//Time between marker refreshes
var INTERVAL = 250;

function getMarkers() {
    $.ajax({
        type: 'GET',
        url: 'webresources/mobile/retrieve-position',
        contentType: 'application/json',
        dataType: "json", //linea fragril
        beforeSend: function (xhr) {
            // Set the CSRF Token in the header for security
            var token = window.sessionStorage.accessToken;
              if (token) {
                xhr.setRequestHeader('userToken', token);
              }
        },
        success: function (res, textStatus, jqXHR) {
            if (jqXHR.status !== 204) {
                for (var i = 0; i < res.length; i++) {
                    if (markerStore.hasOwnProperty(res[i].username)) {
                        //Repinto para moverlo
                        if(res[i].alive){
                            //alert(markerStore[res[i].username].alive + " Suicide");
                            markerStore[res[i].username].setMap(null);
                            //delete markerStore[res[i].username];
                        }
                        else{
                            markerStore[res[i].username].setPosition(new google.maps.LatLng(res[i].lat, res[i].long));
                        }
                    } 
                    else {
                        //Si no existe lo creo
                        var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + res[i].color);
                        var marker = new google.maps.Marker({
                            position: new google.maps.LatLng(res[i].lat, res[i].long),
                            title: res[i].username,
                            icon: pinImage,
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
            
            console.log("Error de solicitud al cargar marcadores.");
        }
    });
}
