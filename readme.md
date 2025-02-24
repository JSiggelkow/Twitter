# Twitter

Dieses Repository repräsentiert die Software-Komponente von Jonas Siggelkow für das Portfolio im Modul Web-Programmierung und verteilte Systeme.\
Die Komponente ist ein Teil des Projekts "Twitter". 

# Anforderungen
Für die Umsetzung der Komponente gab es vier Grundanforderungen.\
Um diese Grundanforderungen umzusetzen mussten weitere Anforderungen definiert werden, damit die Komponente alleinstehend funktioniert.

### Grundanforderungen
* Als User möchte ich Posts liken können, damit ich ausdrücken kann, ob mir ein Post gefällt oder nicht. 
* Als User möchte ich andere Posts kommentieren können, damit ich meine Meinung zu anderen Posts abgeben kann.
* Als User möchte ich andere Posts reposten können, damit ich Posts verbreiten kann.
* Als User möchte ich Posts speichern können, damit ich mir diese nochmal gesammelt anzeigen lassen kann.

### Weitere Anforderungen
* Als User möchte ich Posts senden können, damit anderen Usern meine Posts angezeigt werden.
* Als User möchte ich die Anzahl der Likes, Kommentare und Retweets eines Posts sehen können, damit ich einen Überblick habe wie der Post insgesamt bei andern Usern angekommen ist.
* Als User möchte ich Posts angezeigt bekommen, damit ich sehen kann, was andere User gepostet haben.
* Als User möchte ich Kommentare eines Posts sehen, damit ich lesen kann, was andere User zu einem Post kommentieren.
* Als User möchte ich mir einen Account erstellen, damit die Aktivitäten in der Komponente an meine Benutzer-Entität geknüpft sind.
* Als User möchte ich mich anmelden können, damit ich einen Zugang zum System habe.

### Out of Scope
* Es müssen keine Bilder, Videos o.ä hochgeladen werden können.
* Posts, Kommentare und Zitate müssen nicht gelöscht oder bearbeitet werden können.
* Es muss kein professioneller Login / Signup erstellt werden.
* Es müssen keine Benutzereinstellungen (z.B. Username oder Profilbild ändern) implementiert werden.
* Es muss keine Feed-Strategie (Algorithmus) entwickelt werden, die steuert wie und welche Posts angezeigt werden.
* Die Komponente muss weder deployed noch gehostet werden.

# Verwendete Frameworks und Bibliotheken
Für die Erstellung der Komponente wurde sich so weit es geht an die vorgegebenen Technologien, Frameworks und Bibliotheken aus dem ACD orientiert.\
Aus Kosten- und Komplexitätsgründen gibt es jedoch teilweise Abweichungen:

### Gleich wie ACD
* Spring Boot für das Backend
* Angular für das Frontend
* Die Anwendung läuft in Docker-Containern

### Abweichungen zum ACD
* PrimeNG als Component-Library → Weil Angular-Material schon zu oft benutzt und ich wollte mal eine andere Library ausprobieren
* PostgreSQL als Datenbank
* Tailwind als CSS Library → Weil so effektiver und schneller gearbeitet werden konnte

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
Obwohl die Anforderungen an die Komponente lediglich darin bestehen, Tweets zu retweeten, zu kommentieren, zu liken und zu speichern, war die Implementierung eines grundlegenden Sicherheitskonzepts dennoch notwendig.<br/><br/>
Ein wichtiger Aspekt hierbei ist, dass Benutzeraktionen wie das Kommentieren nicht auf Basis eines User-Objekts durchgeführt werden dürfen, das direkt vom Frontend übergeben wird. Diese Vorgehensweise hätte ein erhebliches Sicherheitsrisiko zur Folge, da manipulierte User-Daten übermittelt werden könnten. Um solche Sicherheitslücken zu vermeiden, muss die Benutzerauthentifizierung serverseitig erfolgen.<br/><br/>
Hierfür wurde ein einfacher und unkomplizierter Security Layer mit **auth0** implementiert. Die Identität des jeweiligen Nutzers wird dabei aus einem JWT Token extrahiert, der vom Backend validiert wird. Dieses Vorgehen stellt sicher, dass nur autorisierte und korrekt authentifizierte Benutzer die Funktionen nutzen können.<br/><br/>
Damit dieses Sicherheitskonzept auch in der lokalen Entwicklungsumgebung funktioniert, muss ein entsprechender **Secret Key** als Umgebungsvariable gesetzt werden.


```shell
  echo "Secret Key" > db_password.txt
```
> **_NOTE:_**  Ein solcher Key lässt sich z.B. ganz einfach mit node erstellen:

```shell
  node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"
```


## Backend, Frontend und Datenbank starten
Über die compose.yaml (im twitter directory) können nun der Backend-, Frontend- und Datenbank-Container gestartet werden:

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
Dieses wurde dann normalisiert, sodass fünf verschiedene Tabellen erstellt werden: Users, Post, Retweet, Save, PostLikes:\
![alt Relationenmodell](/assets/Relationenmodell.png "Relationenmodell")
 
5