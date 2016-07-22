create extension postgis;

create table trucks(
    id text,
    nom text,
    lieu text,
    position geography(POINT, 4326),
    dateTruck text,
    heureDebut text,
    heureFin text,
    CONSTRAINT trucks_pk PRIMARY KEY ( id, dateTruck, heureDebut )
);

create table stations(
	nom text PRIMARY KEY,
	position geography(POINT, 4326),
	nbVelos int,
	disponibilite int
);