# Projet INF4375

## Prérequis

- Java 1.8
- Maven 3
- PostgreSQL 9.5.3 avec PostGIS

## Compilation et exécution
Avant d'exécuter l'application il faut créer la base de données à l'aide de la commande:

    $ psql -U postgres -f migrations/create-database.sql

Ensuite il faut ajouter les tables à la base de données à l'aide de la commande:

    $ psql -U postgres -f migrations/V1__init.sql projet

L'application peut ensuite être compilée et exécuter à l'aide de la commande:

    $ mvn spring-boot:run

Le projet est alors disponible à l'adresse [http://localhost:8080/](http://localhost:8080/)

## Routes disponibles

- [http://localhost:8080/](http://localhost:8080/)
- [http://localhost:8080/trucks](http://localhost:8080/trucks)
