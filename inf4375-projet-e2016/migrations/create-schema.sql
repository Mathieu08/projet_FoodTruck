create table trucks(
    id text,
    nom text,
    lieu text,
    dateTruck text,
    heureDebut text,
    heureFin text,
  CONSTRAINT trucks_pk PRIMARY KEY ( id, dateTruck, heureDebut )
);