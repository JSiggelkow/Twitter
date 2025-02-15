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
