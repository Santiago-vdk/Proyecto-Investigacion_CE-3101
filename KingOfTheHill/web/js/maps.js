$(document).ready(function () {
    /* global google */

    function initialize() {
        var mapOptions = {
            center: {
                lat: 9.8560355,
                lng: -83.9120774
            },
            zoom: 18
        };
        var map = new google.maps.Map(document.getElementById('map-canvas'),
                mapOptions);

        // Limit the zoom level
        google.maps.event.addListener(map, 'zoom_changed', function () {
            if (map.getZoom() < 18)
                map.setZoom(18);

        });

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
   
    
    
    function foo() {
        $.ajax({
            url: "http://localhost:8080/KingOfTheHill/webresources/markers",
            data: "", //ur data to be sent to server
            contentType: "application/json; charset=utf-8",
            type: "GET",
            success: function (data) {
                
                //alert("sup");
                
            },
            error: function (x, y, z) {
                alert(x.responseText + "  " + x.status);
            }
        });
    }
    
    
    
    
    
    
    
    
    
    
    
    //Time between marker refreshes
    var INTERVAL = 2000;

    function getMarkers() {
        alert("yup");
        $.get("http://localhost:8080/KingOfTheHill/webresources/markers", {}, function (res, resp) {

            for (var i = 0, len = res.length; i < len; i++) {

                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(res[i].position.lat, res[i].position.long),
                    title: res[i].name,
                    map: map
                });
            }
            window.setTimeout(getMarkers, INTERVAL);
        }, "json");
    }



});

