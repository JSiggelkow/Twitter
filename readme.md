# Twitter

Hier kommt später eine tolle Beschreibung für meine Komponente hin :)\

# Anforderungen
* Tweets liken
* Tweets kommentieren
* Tweets retweeten
* Tweets speichern

--> Anforderungen müssen noch ein bisschen mehr ausformuliert werden :(

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
Es gilt als Best Practice, keine Passwörter oder Secrets in Git zu versionieren.\
Für die lokale Installation müssen jedoch ein Datenbank-Passwort und ein Secret Key erstellt werden.<br/><br/>
Für die lokale Installation müssen diese Werte als Umgebungsvariablen gesetzt werden.

### Secrets Directory
```shell
  cd twitter/twitterBackend
```
```shell
  mkdir secrets
```

### Datenbank Passwort
```shell
  echo "Password" > db_password.txt
```
> **_NOTE:_**  Hier sollte ein eigenes, starkes Password verwendet werden!

### Security
Obwohl die Anforderungen an die Komponente lediglich darin bestehen, Tweets zu retweeten, zu kommentieren, zu liken und zu speichern, war die Implementierung eines grundlegenden Sicherheitslayers dennoch notwendig.<br/><br/>
Ein wichtiger Aspekt hierbei ist, dass Benutzeraktionen wie das Kommentieren nicht auf Basis eines User-Objekts durchgeführt werden dürfen, das direkt vom Frontend übergeben wird. Diese Vorgehensweise hätte ein erhebliches Sicherheitsrisiko zur Folge, da manipulierte User-Daten übermittelt werden könnten. Um solche Sicherheitslücken zu vermeiden, muss die Benutzerauthentifizierung serverseitig erfolgen.<br/><br/>
Hierfür wurde ein einfacher und unkomplizierter Security Layer mit **auth0** implementiert. Die Identität des jeweiligen Nutzers wird dabei aus einem JWT Token extrahiert, der vom Backend validiert wird. Dieses Vorgehen stellt sicher, dass nur autorisierte und korrekt authentifizierte Benutzer die Funktionen nutzen können.<br/><br/>
Damit der Securitylayer auch in der lokalen Entwicklungsumgebung funktioniert, muss ein entsprechender **Secret Key** als Umgebungsvariable gesetzt werden.


```shell
  echo "Secret Key" > db_password.txt
```
> **_NOTE:_**  Ein solcher Key lässt sich z.B. ganz einfach mit node erstellen:

```shell
  node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"
```


## Backend und Datenbank starten
Mit hilfe der compose.yaml können nun der Backend- und Datenbank-Container gestartet werden:
```shell
  docker compose up
```
> **_NOTE:_**  docker compose up -d um die Container im Hintergrund zu starten :)

# Datenbank
Obwohl aus dem ACD die Nutzung einer Oracle Database hervorgeht, wurde sich aus Kostengründen für die Erstellung des Prototyps für eine PostgreSQL Datenbanklösung entschieden.\
Um Datenbankänderungen und Versionen transparent zu gestalten wurde außerdem Flyway für die Datenbankmigration genutzt.
Vor der Erstellung der Datenbank wurde eine Entity Relationship Modell erstellt um die Entitäten und Abhängigkeiten darzustellen.\
Für die Erstellung des Entity Relationship Modells wurde als erstes Twitter analysiert. Hierbei ist aufgefallen, dass Twitter hauptsächliche zwei Entitäten hat: User und Post\
Alle anderen "Objekte" wie Retweet, Quote, Comment sind auch im Allgemeinen normale Posts.
## Entity Relationship Modell
![alt Entity Relationship Modell](/assets/EntityRelationshipModell.png "Entity Relationship Modell")
## Relationenmodell
Auf Grundlage des Entity Relationship Modells wurde anschließend ein Relationenmodell erstellt.\
Dieses wurde dann normalisiert, sodass fünf verschiedene Tabellen erstellt werden: Users, Post, Retweet, Save, PostLikes: \
![alt Relationenmodell](/assets/Relationenmodell.png "Relationenmodell")
 
5