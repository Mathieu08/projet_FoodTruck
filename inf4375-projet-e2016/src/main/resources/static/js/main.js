var mymap = L.map('mapid').setView([45.508, -73.587], 13);
tileLayer();

var bouton = document.getElementById("bouton")
var rep = document.getElementById("nbr")

bouton.addEventListener("click", function(){
  var dateDebut = document.getElementById("dateDebut").value;
  var dateFin = document.getElementById("dateFin").value;
  var url = "http://localhost:8080/horaires-camions?du=" + dateDebut + "&au=" + dateFin;

  var xhttp = new XMLHttpRequest();  
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      var json = JSON.parse(xhttp.responseText);
      rep.innerHTML = json.length + " résultats trouvés.";

      mymap.remove();
      mymap = L.map('mapid').setView([45.508, -73.587], 13);
      tileLayer();

      var markers = new L.markerClusterGroup({
        iconCreateFunction: function(cluster) {
          return L.divIcon({ html: '<font size="4" style="color:#FE642E">' + cluster.getChildCount() + '</font>', className: 'myCluster', iconSize: L.point(32,32) });
        }
      });

      var truckIcon = L.icon({
        iconUrl: '/truck.png',
        iconSize: [32,32]
      });

      for (var i = json.length - 1; i >= 0; i--) {
        var marker = L.marker([json[i].lat, json[i].lon], {icon: truckIcon});
        marker.bindPopup("<b>Food Truck</b><br><b>Nom: </b>" + json[i].nom + "<br><b>Lieu: </b>" + json[i].lieu + "<br><b>Jour: </b>" + json[i].dateTruck
                         + "<br><b>Heure d'arrivé: </b>" + json[i].heureDebut + "<br><b>Heure de départ: </b>" + json[i].heureFin);              
        marker.on("click",onClick);
        markers.addLayer(marker);
      }
      mymap.addLayer(markers); 
    }
  };

  xhttp.open("GET", url, true);
  xhttp.send();
        
});

function onClick(e) {
    var lat = e.latlng.lat;
    var lon = e.latlng.lng;
    var urlStation = "http://localhost:8080/stations?lon=" + lon + "&lat=" + lat;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
      if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        var station = JSON.parse(xmlHttp.responseText);
        for (var i=0; i < station.length; i++) {
          var stationMarker = L.marker([station[i].lat, station[i].lon]);
          stationMarker.bindPopup("<b>Station Bixi</b><br><b>Nom: </b>" + station[i].nom + "<br><b>Nombre de velos: </b>" 
                                  + station[i].nbVelos + "<br><b>Nombre de place disponible: </b>" + station[i].disponibilite);
          stationMarker.addTo(mymap);
        }
      }
    }
    xmlHttp.open("GET", urlStation, true);
    xmlHttp.send();
}

function tileLayer(){
    L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/streets-v9/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoiZ29yZWFmcm9tYW4iLCJhIjoiY2lxY2t0dzViMDJhamZ5bnBndDhyNm9xMyJ9.d5YvbUN58tpwR09z5Iet8g', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'your.mapbox.project.id',
    accessToken: 'your.mapbox.public.access.token'
    }).addTo(mymap);
}

