var fetchAll = function() {
  fetch('/horaires-camions?du=2016-07-06&au=2016-07-07').then(function(resp){
    return resp.json()
  }).then(function(data) {
    console.table(data)
  })
}

document.addEventListener('DOMContentLoaded', fetchAll)
var bouton = document.getElementById("bouton")
var rep = document.getElementById("nbr")

      bouton.addEventListener("click", function(){
        var dateDebut = document.getElementById("dateDebut").value;
        var dateFin = document.getElementById("dateFin").value;
        var url = "http://localhost:8080/horaires-camions?du=" + dateDebut + "&au=" + dateFin;

        //alert(url);
        var xhttp = new XMLHttpRequest();
        
        xhttp.onreadystatechange = function() {
        	alert(xhttp.status);
          alert(xhttp.readyState);
          if (xhttp.readyState == 4 && xhttp.status == 200) {
            
            	//alert(xhttp.responseText);
              var json = JSON.parse(xhttp.responseText);
		          rep.innerHTML = json.length;    
          }
        };
        xhttp.open("GET", url, true);
        xhttp.send();
      });

      var div = document.getElementById("div-fact")
    var btn = document.getElementById("btn-fact")
    btn.addEventListener("click", function() {
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        alert(xhttp.status);
        if (xhttp.readyState == 4 && xhttp.status == 200) {
          var json = JSON.parse(xhttp.responseText);
          div.innerHTML = json.fact;
        }
      };
      xhttp.open("GET", "http://catfacts.moz-code.org", true);
      xhttp.send();
    });
