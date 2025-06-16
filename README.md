# <h1 align="center">NHPlus</h1>

<br>

## Informationen zur Lernsituation:
Sie sind Mitarbeiter der HiTec GmbH, die seit über 15 Jahren IT-Dienstleister und seit einigen Jahren ISO/IEC 27001 zertifiziert ist. Die HiTec GmbH ist ein mittelgroßes IT-Systemhaus und ist auf dem IT-Markt mit folgenden Dienstleistungen und Produkten vetreten: 

## Entwicklung: 
Erstellung eigener Softwareprodukte.

## Consulting: 
Anwenderberatung und Schulungen zu neuen IT- und Kommunikationstechnologien , Applikationen und IT-Sicherheit.

## IT-Systembereich: 
Lieferung und Verkauf einzelner IT-Komponenten bis zur Planung und Installation komplexer Netzwerke und Dienste.

## Support und Wartung: 
Betreuung von einfachen und vernetzten IT-Systemen (Hard- und Software).

Für jede Dienstleistung gibt es Abteilungen mit spezialisierten Mitarbeitern. Jede Abteilung hat einen Abteilungs- bzw. Projektleiter, der wiederum eng mit den anderen Abteilungsleitern zusammenarbeitet.

## Projektumfeld und Projektdefinition:

Sie arbeiten als Softwareentwickler in der Entwicklungsabteilung. Aktuell sind sie dem Team zugeordnet, das das Projekt "NHPlus" betreut. Dessen Auftraggeber - das Betreuungs- und Pflegeheim "Curanum Schwachhausen" - ist ein Pflegeheim im Bremer Stadteil Schwachhausen - bietet seinen in eigenen Zimmern untergebrachten Bewohnern umfangreiche Therapie- und Serviceleistungen an, damit diese so lange wie möglich selbstbestimmt und unabhängig im Pflegeheim wohnen können. Curanum Schwachhausen hat bei der HiTec GmbH eine Individualsoftware zur Verwaltung der Patienten und den an ihnen durchgeführten Behandlungen in Auftrag gegeben. Aktuell werden die Behandlungen direkt nach ihrer Durchführung durch die entsprechende Pflegekraft handschriftlich auf einem Vordruck erfasst und in einem Monatsordner abgelegt. Diese Vorgehensweise führt dazu, dass Auswertungen wie z.B. welche Behandlungen ein Patient erhalten oder welche Pflegkraft eine bestimmte Behandlung durchgeführt hat, einen hohen Arbeitsaufwand nach sich ziehen. Durch NHPlus soll die Verwaltung der Patienten und ihrer Behandlungen elektronisch abgebildet und auf diese Weise vereinfacht werden.

Bei den bisher stattgefundenen Meetings mit dem Kunden konnten folgende Anforderungen an NHPlus identifiziert werden:

- alle Patienten sollen mit ihrem vollen Namen, Geburtstag, Pflegegrad, dem Raum, in dem sie im Heim untergebracht sind, sowie ihrem Vermögensstand erfasst werden.

- Die Pflegekräfte werden mit ihrem vollen Namen und ihrer Telefonnumer erfasst, um sie auf Station schnell erreichen zu können.

- jede Pflegekraft erfasst eine Behandlung elektronisch, indem sie den Patienten, das Datum, den Beginn, das Ende, die Behandlungsart sowie einen längeren Text zur Behandlung erfasst.

- Die Software muss den Anforderungen des Datenschutzes entsprechen. 

- Die Software ist zunächst als Desktopanwendung zu entwickeln, da die Pflegekräfte ihre Behandlungen an einem stationären Rechner in ihrem Aufenthaltsraum erfassen sollen.

 

Da in der Entwicklungsabteilung der HiTech GmbH agile Vorgehensweisen vorgeschrieben sind, wurde für NHPlus Scrum als Vorgehensweise gewählt.

---

<br>

# <h1 align="center"> - User-Stories, Akzeptanzkriterien, tasks, Testfälle - </h1>

<br>
<br>

##  User Story 1:
Als Pfleger/in möchte ich über die Navigationsleiste die Übersicht aller Pflegekräfte aufrufen können, um schnell auf deren Kontaktdaten zuzugreifen.

#### Akzeptanzkriterien

    A_1: Die persönlichen Daten eines Pflegers bestehen aus PflegerID, dem Nachname, Vorname,
         Telefonnummer und ob er aktiv ist bzw seit wann er inaktiv ist.
    
    A_2: Alle Felder sind verpflichtend.
    
    A_3: Alle Pfleger werden mit ihren vollständigen Daten in einer tabellarischen Übersicht dargestellt.
    
    A_4: Es können neue Pfleger hinzugefügt werden.
    
    A_5: Ein ausgewählter Pfleger kann gelöscht werden.
    
    A_6: Ein ausgewählter Pfleger kann geändert werden.
    
    A_7: Jede getätigte Änderung wird in der Datenbank abgebildet.


#### Tasks

    T_1:  Modellklasse Pfleger erstellen: abgeleitet von Person, Konstruktoren mit und ohne PflegerID.
    
    T_2:  allgemein gültiges DAO erstellen: Interface Dao, das die CRUD-Methoden (create, read, readAll, update und deleteByID) vorgibt, Datentyp ist der generische Typ T. 
          Implementierung der CRUD-Methoden durch die abstrakte Klasse DAOImp, die als abstrakte Methoden vorgibt, die von den erbenden Klassen die konkreten SQL-Strings für die CRUD-Methoden zurückgeben lassen; 
          DAO-Factory, die die benötigten DAO-Klassen instanziiert; 
          Erstellen der Klasse ConnectionBuilder, die für den Aufbau der Datenbankverbindung zuständig ist und von DAOImp gehalten wird; PflegerDAO erstellen.
    
    T_3: Datenbank mit einer Tabelle Pfleger anlegen, die die Stammdaten der Pfleger hält.

    T_4:  AllNursesView erstellen: TableView mit allen Pflegerdaten, darunter Textfelder zum Anlegen eines neuen Pflegers, Button zum Hinzufügen und Löschen.
    
    T_5:  Erstellen des NurseControllers: stellt die Methoden zum Anzeigen der Pfleger, Ändern, Löschen und Hinzufügen bereit.



####  Testfälle

| Testfall | Beschreibung                | Vorbedingung                                                                 | Auszuführende Testschritte                                                                                                   | Erwartetes Ergebnis                                                                                                                  |
|----------|-----------------------------|------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| TF1_     | Alle Pfleger anzeigen       | Der User hat im Hauptfenster die Möglichkeit, das Pflegermodul zu öffnen.   | –                                                                                                                           | Alle Pfleger aus der Datenbank werden angezeigt, inkl. PflegerID, Nachname, Vorname, Geburtsdatum, Telefonnummer, Aktivitätsstatus. |
| TF2_     | Pflegerdaten ändern         | Der User hat das Pflegermodul geöffnet.                                     | 1) Doppelklick auf Nachnamen eines Pflegers.<br>2) Nachnamen ändern.<br>3) Eingabe mit Entertaste abschließen.                | 1) Geänderter Nachname erscheint in TableView.<br>2) Geänderter Nachname wurde in der Datenbank gespeichert.                           |

<br>
<br>

##  User Story 2: 
Als Pflegekraft, möchte ich, beim Anlegen einer neuen Behandlung die zuständige Pflegekraft über eine Combobox auswählen können, damit die Behandlung eindeutig einer Pflegekraft zugewiesen ist und eine klare Verantwortlichkeit besteht.

#### Akzeptanzkriterien

    A_1: Beim Anlegen einer Behandlung muss ich einen zuständigen Pfleger über eine Combobox zuweisen.
    
    A_2: Die Combobox zeigt alle Pfleger an, welche im Betrieb arbeiten.
    
    A_3: Der zuständige Pfleger wird mittels der PflegerID in der Datenbanktabelle der Behandlungen gespeichert.


#### Tasks

    T_1: Datenbanktabelle der Behandlungen wird um das Feld PflegerID erweitert.
   
    T_2: Die TreatmentView wird um die Spalten Pflegername(Vorname und Nachname) und PflegerTel. Erweitert.


####  Testfall

| Testfall | Beschreibung                     | Vorbedingung                                                           | Auszuführende Testschritte                                                                                                 | Erwartetes Ergebnis                                                                                           |
|----------|----------------------------------|------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|
| TF1_     | Anlegen einer neuen Behandlung   | Der User befindet sich in der Behandlungsansicht und will einen neuen Patienten anlegen. | 1) User gibt alle Daten für die neue Behandlung ein.<br>2) User wählt einen Pfleger über eine Combobox aus.<br>3) User klickt auf "Hinzufügen"| 1) Zuständiger Pfleger wird in der Behandlungsübersicht angezeigt.<br>2) PflegerID wird korrekt in DB gespeichert |

<br>
<br>

## User Story 3:
Als Pflegekraft, möchte ich, dass bei der Anzeige einer einzelnen Behandlung auch der Name (im Format Nachname, Vorname) und die Telefonnummer der durchführenden Pflegekraft in einem eigenen Label angezeigt werden, 
damit klar ersichtlich ist, wer die Behandlung durchgeführt hat und wie diese Person bei Rückfragen kontaktiert werden kann.

#### Akzeptanzkriterien

    A_1: Die Anzeige der Behandlung soll, um ein Label für den Namen des Pflegers ergänz werden.
   
    A_2: Der Name des Pflegers soll im Format Nachname, Vorname angezeigt werden.
   
    A_3: Die Anzeige der Behandlung soll um ein Label für die Telefonnummer des Pflegers ergänzt werden.
   
    A_4: Die Daten des Pflegers sollen korrekt, aktuell und vollständig angezeigt werden.


#### Tasks

    T_1: Implementierung eines neuen Labels für den Namen des Pflegers.
    
    T_2: Implementierung eines neuen Labels für die Telefonnummer des Pflegers. 


####  Testfälle

| Testfall | Beschreibung                            | Vorbedingung                                               | Auszuführende Testschritte                                                                                                 | Erwartetes Ergebnis                                          |
|----------|-----------------------------------------|------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------|
| TF1_     | Name des Pflegers im Behandlungsmodul   | Der User befindet sich auf der Seite mit der Behandlungsanzeige. | 1) User wählt beim Erstellen einer Behandlung einen Pfleger über eine Combobox aus.<br>2) Klick auf "neue Behandlung anlegen" | Name des Pflegers wird im Format **Nachname, Vorname** angezeigt. |
| TF2_     | Telefonnummer des Pflegers anzeigen     | Der User befindet sich auf der Seite mit der Behandlungsanzeige. | 1) User wählt beim Erstellen einer Behandlung einen Pfleger über eine Combobox aus.<br>2) Klick auf "neue Behandlung anlegen" | Telefonnummer des Pflegers wird korrekt angezeigt.          |

<br>
<br>

## User Story 4:
Als Produktverantwortlicher, möchte ich, dass alle Daten zum Vermögensstand aus der Anwendung entfernt werden, damit keine sensiblen Finanzdaten mehr verarbeitet oder angezeigt werden und datenschutzrechtliche Vorgaben eingehalten werden.

#### Akzeptanzkriterien

    A_1: Die Modelklasse Patient enthält kein Attribut für den Vermögensstand mehr.
    
    A_2: Das Feld “Vermögensstand” wird in der grafischen Oberfläche (AllPatientView) nicht mehr angezeigt.
    
    A_3: Die Spalte “Vermögensstand” wird nicht mehr in der Datenbanktabelle Patient gespeichert.
    
    A_4: Der DAO-Layer verarbeitet keine Daten zum Vermögensstand mehr (keine Insert-, Update-, Read-Zugriffe darauf).
    
    A_5: Es dürfen keine versehentlichen Zugriffe auf nicht mehr vorhandene Datenbankspalten auftreten.
    
    A_6: Die Anwendung funktioniert weiterhin vollständig (Patienten können angelegt, angezeigt, geändert und gelöscht werden).


#### Tasks

    T_1: Entfernen des Felds assets aus der Patient-Modelklasse.
    
    T_2: Entfernen aller Zugriffe auf assets in PatientDao.
    
    T_3: Entfernen der Spalte vermögensstand aus der Datenbanktabelle patient.
    
    T_4: Entfernen der Textfelder und Labels zum Vermögensstand in der AllPatientView.fxml.


####  Testfälle

| Testfall | Beschreibung                             | Vorbedingung                                                            | Auszuführende Testschritte                                                                                           | Erwartetes Ergebnis                                                                                  |
|----------|------------------------------------------|-------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| TF1_     | Patient wird ohne Vermögensstand angezeigt | Datenbank enthält Patienten mit vollständigen Daten.                    | 1) Anwendung starten.<br>2) Menüpunkt "Patienten" öffnen                                                              | Patienten werden **ohne Spalte "Vermögensstand"** in der Tabelle angezeigt.                           |
| TF2_     | Neuer Patient anlegen ohne Vermögensstand | Keine Vermögensstand-Felder mehr in der Oberfläche vorhanden.           | 1) Pflichtfelder (Name, Vorname, Geburtsdatum, Pflegegrad, Zimmernummer) ausfüllen.<br>2) Button „Hinzufügen“ drücken | Patient wird korrekt in Tabelle angezeigt<br>Eintrag wird ohne Fehler in die Datenbank übernommen.    |


<br>
<br>

## User Story 5:
Als Mitarbeiter möchte ich mich über ein Login-Fenster mit Benutzernamen und Passwort anmelden, damit ich Zugriff auf die Anwendung erhalte. Wenn die Anmeldedaten fehlerhaft sind, möchte ich eine entsprechende Fehlermeldung bekommen.

#### Akzeptanzkriterien

    A_1: Das Login-Fenster enthält zwei Eingabefelder (Benutzername und Passwort) sowie einen Anmelde-Button.
    
    A_2: Der User kann auf den “Anmelden“-Button klicken, nachdem er seinen Benutzernamen und sein Passwort eingegeben hat.
    
    A_3: Die Anwendung überprüft, ob der eingegebene Benutzername und das Passwort mit den gespeicherten Daten übereinstimmen.
    
    A_4: Bei erfolgreicher Anmeldung wird der User zur Hauptanwendung weitergeleitet.
    
    A_5: Bei fehlerhaften Anmeldedaten (falscher Benutzername oder falsches Passwort) wird eine Fehlermeldung angezeigt.
    
    A_6: Das Passwort wird im Eingabefeld als Sternchen (*) angezeigt, um die Sicherheit zu gewährleisten.


#### Tasks

    T_1: Erstellen eines Login-Fensters.
    
    T_2: Implementierung einer Funktion zur Überprüfung des Benutzernamens und Passworts.
    
    T_3: Sicherstellen, dass das Passwort im Eingabefeld als * angezeigt wird.
    
    T_4: Implementierung von Fehlermeldungen mit einer Messagebox.


####  Testfälle

| Testfall | Beschreibung                            | Vorbedingung                                              | Auszuführende Testschritte                                                                                  | Erwartetes Ergebnis                                                                                   |
|----------|-----------------------------------------|-----------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------|
| TF1      | Erfolgreiche Anmeldung                  | Der User hat einen gültigen Benutzernamen und ein Passwort. | 1) Anwendung öffnen.<br>2) Gültigen Benutzernamen und Passwort eingeben.<br>3) Auf "Anmelden“ klicken.          | User wird erfolgreich eingeloggt und in die Hauptanwendung weitergeleitet.                             |
| TF2      | Fehlerhafte Anmeldung                   | Der User gibt einen falschen Benutzernamen oder Passwort ein. | 1) Anwendung öffnen.<br>2) Falschen Benutzernamen oder Passwort eingeben.<br>3) Auf "Anmelden“ klicken.         | Fehlermeldung erscheint, User bleibt im Login-Fenster.                                                 |
| TF3      | Passwortanzeige als Sternchen           | Der User gibt ein Passwort ein.                            | 1) Anwendung öffnen.<br>2) Passwort in das Passwortfeld eingeben.                                              | Passwort wird im Feld als Sternchen (*) angezeigt.                                                     |

# Hinweis: Beim einloggen bitte eingeben!.
### Benuzter name: admin
### Passwort: 1234
