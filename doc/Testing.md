# <h1 align="center">-- User Storys --</h1>

<br>
<br>

## Testing – User Story 1

**Als Pfleger möchte ich eine Übersicht aller Pfleger, um Arbeitszeiten zu planen.**

| Test  | Beschreibung                   | Vorbedingung                                                                                            | Erwartetes Ergebnis                                                                                                                                                                                                                      | Tatsächliches Ergebnis      | Status |
|-------|--------------------------------|---------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------|--------|
| TF1_  | Alle Pfleger anzeigen          | Der Benutzer befindet sich auf dem Hauptbildschirm und kann über einen Button die Pflegeransicht öffnen | In der TableView werden alle Pfleger mit folgenden Daten angezeigt: `ID`, `Nachname`, `Vorname`, `Geburtsdatum`, `Telefonnummer`, `Aktiv-/Inaktivstatus`  (inkl. Datum bei Inaktivität)                                                  | Funktioniert wie erwartet   | ✅      |
| TF2_  | Pflegerdaten bearbeiten        | Der Benutzer hat das Pflegermodul über den Hauptbildschirm geöffnet                                     | 1. Benutzer wählt durch Doppelklick auf den Nachnamen einen Pfleger aus.<br>2. Benutzer ändert den Nachnamen.<br>3. Durch Drücken der Enter-Taste wird die Änderung gespeichert.<br>→ Der neue Nachname wird in der TableView angezeigt. | Funktioniert wie erwartet   | ✅      |
| TF3_  | Pflegertabelle in DB erstellen | Anwendung ist gestartet und die Datenbankverbindung ist erfolgreich hergestellt                         | Die Tabelle `pfleger` wird mit folgenden Spalten erstellt:<br>• `cid`<br>• `nachname`<br>• `vorname`<br> • `telefonnummer`<br>• `aktiv`<br>• `inaktivSeit`                                                                               | Funktioniert wie erwartet   | ✅      |
| TF4_  | Pfleger-Ansicht anzeigen       | Der Benutzer befindet sich auf dem Hauptbildschirm und öffnet die Ansicht "Alle Pfleger“                | In der TableView werden alle Pflegerdaten angezeigt. Zusätzlich sind Textfelder (z.B. zur Bearbeitung) und Buttons (z.B. Speichern, Löschen) sichtbar.                                                                                   | Funktioniert wie erwartet   | ✅      |
| TF5_  | Generische DAO-Implementierung | Die Verbindung zur Datenbank ist eingerichtet                                                           | Die generische DAO-Schicht ermöglicht CRUD-Operationen für Pfleger-Entitäten. Datenbankabfragen funktionieren korrekt und zuverlässig.                                                                                                   | Funktioniert wie erwartet   | ✅      |

<br>

## Testing – User Story 3

**Als Benutzer möchte ich beim Erstellen einer Behandlung den Pflegernamen und die Telefonnummer sehen, um eine eindeutige Zuordnung zu ermöglichen.**

| Test  | Beschreibung                                   | Vorbedingung                                                        | Auszuführende Schritte                                                                                                                | Erwartetes Ergebnis                                                           | Tatsächliches Ergebnis      | Status |
|-------|------------------------------------------------|---------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|-----------------------------|--------|
| TF1_  | Name des Pflegers im Behandlungsmodul          | Der Benutzer befindet sich auf der Seite mit der Behandlungsanzeige | 1. Benutzer wählt beim Erstellen einer Behandlung einen Pfleger über eine Combobox aus.<br>2. Klick auf "`neue Behandlung anlegen`".  | Der Name des Pflegers wird im Format **Nachname, Vorname** angezeigt.         | Funktioniert wie erwartet   | ✅      |
| TF2_  | Telefonnummer des Pflegers im Behandlungsmodul | Der Benutzer befindet sich auf der Seite mit der Behandlungsanzeige | 1. Benutzer wählt beim Erstellen einer Behandlung einen Pfleger über eine Combobox aus.<br>2. Klick auf "`neue Behandlung anlegen`".  | Die Telefonnummer des ausgewählten Pflegers wird korrekt angezeigt.           | Funktioniert wie erwartet   | ✅      |

<br>

## Tests zu Aufgabe 4

| Testfall                                             | Ergebnis      | Fehlerbeschreibung        |
|------------------------------------------------------|---------------|---------------------------|
| TF1: Patient wird ohne Vermögensstand angezeigt      | funktioniert  | –                         |
| TF2: Neuer Patient wird ohne Vermögensstand angelegt | funktioniert  | –                         |


<br>

## Tests zu Aufgabe 5

| Testfall                                                             | Ergebnis      | Fehlerbeschreibung        |
|----------------------------------------------------------------------|---------------|---------------------------|
| TF1: Erfolgreiche Anmeldung mit gültigem Benutzernamen und Passwort  | funktioniert  | –                         |
| TF2: Fehlerhafte Anmeldung                                           | funktioniert  | –                         |
| TF3: Passwortanzeige als Sternchen                                   | funktioniert  | –                         |