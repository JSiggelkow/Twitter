# Twitter

Hier kommt später eine tolle Beschreibung für meine Komponente hin :)

# Lokale Installation
## Projekt klonen
```shell
  git clone https://github.com/JSiggelkow/Twitter.git
```
## Docker
Um die Architektur des ACD möglichst genau nachzubilden, wird die Anwendung in Docker-Containern ausgeführt.\
Für die lokale Installation ist also eine funktionierende Docker-Installation erforderlich.\
[Docker Installation](https://docs.docker.com/engine/install/)

## Umgebungsvariablen
Da sensible Daten wie Passwörter nie in ein GitHub Repo dürfen, wird das Datenbank-Passwort mithilfe von Docker Secrets und dem entrypoint.sh Skript gesetzt.
Für die lokale Installation muss das Password also selbst erstellt werden:
```shell
  cd twitter/twitterBackend
```
```shell
  mkdir secrets
```
```shell
  echo "Password" > db_password.txt
```
> **_NOTE:_**  Hier sollte ein eigenes, starkes Password verwendet werden!

## Backend und Datenbank starten
Mit hilfe der compose.yaml können nun der Backend- und Datenbank-Container gestartet werden:
```shell
  docker compose up
```
> **_NOTE:_**  docker compose up -d um die Container im Hintergrund zu starten :)

# Datenbank
Obwohl aus dem ACD die Nutzung einer Oracle Database hervorgeht, wurde sich aus Kostengründen für die erstellung des Prototyps für eine PostgreSQL Datenbanklösung entschieden.\
Um Datenbankänderungen und Versionen transparent zu gestalten wurde außerdem Flyway für die Datenbankmigration genutzt.
Vor der Erstellung der Datenbank wurde eine Entity Relationship Modell erstellt um die Entitäten und Abhängigkeiten darzustellen (Shoutout an Prof. Kirchberg sein Skript hat hierbei toll geholfen)
## Entity Relationship Modell:
![alt Entity Relationship Modell](/assets/EntityRelationshipModell.png "Entity Relationship Modell")
## Relationenmodell:
Aus dem Entity Relationship Modell wurde anschließend ein Relationenmodell erstellt (auch hier danke an Herr. Kichberg) und normalisiert.
![alt Relationenmodell](/assets/Relationenmodell.png "Relationenmodell")
 
