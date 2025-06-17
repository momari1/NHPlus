# <h1 align="center">-- User Storys --</h1>

<br>
<br>

## Testing – User Story 1

**Als Pfleger möchte ich eine Übersicht aller Pfleger, um Arbeitszeiten zu planen.**

| Test | Beschreibung                   | Vorbedingung                                                                                            | Erwartetes Ergebnis                                                                                                                                                                                                                                                                                                 | Tatsächliches Ergebnis      | Status |
|------|--------------------------------|---------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------|--------|
| TF1_ | Alle Pfleger anzeigen          | Der Benutzer befindet sich auf dem Hauptbildschirm und kann über einen Button die Pflegeransicht öffnen | In der TableView werden alle Pfleger mit folgenden Daten angezeigt: `ID`, `Nachname`, `Vorname`, `Geburtsdatum`, `Telefonnummer`, `Aktiv-/Inaktivstatus`  (inkl. Datum bei Inaktivität)                                                                                                                             | Funktioniert wie erwartet   | ✅      |
| TF2_ | Pflegerdaten bearbeiten        | Der Benutzer hat das Pflegermodul über den Hauptbildschirm geöffnet                                     | 1. Benutzer wählt durch Doppelklick auf den Nachnamen einen Pfleger aus.<br>2. Benutzer ändert den Nachnamen.<br>3. Durch Drücken der Enter-Taste wird die Änderung gespeichert.<br>→ Der neue Nachname wird in der TableView angezeigt.                                                                            | Funktioniert wie erwartet   | ✅      |
| TF3_ | Pflegertabelle in DB erstellen | Anwendung ist gestartet und die Datenbankverbindung ist erfolgreich hergestellt                         | Die Tabelle `pfleger` wird mit folgenden Spalten erstellt:<br>• `cid`<br>• `nachname`<br>• `vorname`<br> • `telefonnummer`<br>• `aktiv`<br>• `inaktivSeit`                                                                                                                                                          | Funktioniert wie erwartet   | ✅      |
| TF4_ | Pfleger-Ansicht anzeigen       | Der Benutzer befindet sich auf dem Hauptbildschirm und öffnet die Ansicht "Alle Pfleger“                | In der TableView werden alle Pflegerdaten angezeigt. Zusätzlich sind Textfelder (z.B. zur Bearbeitung) und Buttons (z.B. Speichern, Löschen) sichtbar.                                                                                                                                                              | Funktioniert wie erwartet   | ✅      |
| TF5_ | Generische DAO-Implementierung | Die Verbindung zur Datenbank ist eingerichtet                                                           | Die generische DAO-Schicht ermöglicht CRUD-Operationen für Pfleger-Entitäten. Datenbankabfragen funktionieren korrekt und zuverlässig.                                                                                                                                                                              | Funktioniert wie erwartet   | ✅      |
| TF6_ | Aktiv/Inaktiv-Auswahl prüfen   | Benutzer befindet sich in der Pflegeransicht und möchte einen neuen Pfleger hinzufügen                  | 1. Benutzer gibt im Textfeld "`Aktiv`" entweder "`aktiv`" oder "`inaktiv`" ein (Groß und Kleinschreibung wird ignoriert).<br>2. Wenn "`Aktiv`" eingegeben wird, kann das Feld "`Inaktiv seit`" leer bleiben. Wenn "`Inaktiv`"  eingegeben wird, muss ein gültiges Datum im Format "`yyyy-MM-dd`" eingegeben werden. | Funktioniert wie erwartet   | ✅      |

<br>

## Testing - User Story 2

**Als Pflegekraft möchte ich beim Anlegen einer neuen Behandlung die zuständige Pflegekraft über eine Combobox auswählen können, damit die Behandlung eindeutig einer Pflegekraft zugewiesen ist und eine klare Verantwortlichkeit besteht.**

| Test | Beschreibung                                      | Vorbedingung                                                    | Erwartetes Ergebnis                                                                                    | Tatsächliches Ergebnis    | Status |
|------|---------------------------------------------------|-----------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|---------------------------|--------|
| TF1_ | ComboBox zeigt alle Pflegekräfte an               | Der Benutzer öffnet das Behandlungsformular                     | In der ComboBox werden alle verfügbaren Pflegekräfte mit Vor- und Nachnamen angezeigt                  | Funktioniert wie erwartet | ✅      |
| TF2_ | Pflegekraft wird ausgewählt und gespeichert       | Der Benutzer wählt eine Person aus und speichert die Behandlung | Die ausgewählte Pflegekraft wird korrekt in der Datenbank als zuständig für die Behandlung gespeichert | Funktioniert wie erwartet | ✅      |
| TF3_ | Pflegekraft wird im Bearbeitungsdialog angezeigt  | Behandlung mit gespeicherter Pflegekraft wird bearbeitet        | Die richtige Pflegekraft wird im Label oder Feld angezeigt, z.B. „Müller, Anna“                        | Funktioniert wie erwartet | ✅      |



<br>

## Testing – User Story 3

**Als Benutzer möchte ich beim Erstellen einer Behandlung den Pflegernamen und die Telefonnummer sehen, um eine eindeutige Zuordnung zu ermöglichen.**

| Test  | Beschreibung                                   | Vorbedingung                                                        | Auszuführende Schritte                                                                                                                | Erwartetes Ergebnis                                                           | Tatsächliches Ergebnis      | Status |
|-------|------------------------------------------------|---------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|-----------------------------|--------|
| TF1_  | Name des Pflegers im Behandlungsmodul          | Der Benutzer befindet sich auf der Seite mit der Behandlungsanzeige | 1. Benutzer wählt beim Erstellen einer Behandlung einen Pfleger über eine Combobox aus.<br>2. Klick auf "`neue Behandlung anlegen`".  | Der Name des Pflegers wird im Format **Nachname, Vorname** angezeigt.         | Funktioniert wie erwartet   | ✅      |
| TF2_  | Telefonnummer des Pflegers im Behandlungsmodul | Der Benutzer befindet sich auf der Seite mit der Behandlungsanzeige | 1. Benutzer wählt beim Erstellen einer Behandlung einen Pfleger über eine Combobox aus.<br>2. Klick auf "`neue Behandlung anlegen`".  | Die Telefonnummer des ausgewählten Pflegers wird korrekt angezeigt.           | Funktioniert wie erwartet   | ✅      |

<br>

## Testing – User Story 4

**Als Produktverantwortlicher, möchte ich, dass alle Daten zum Vermögensstand aus der Anwendung entfernt werden, damit keine sensiblen Finanzdaten mehr verarbeitet oder angezeigt werden und datenschutzrechtliche Vorgaben eingehalten werden.**

| Test  | Beschreibung                                    | Vorbedingung                                             | Auszuführende Schritte                                                          | Erwartetes Ergebnis                                                                                           | Tatsächliches Ergebnis      | Status  |
|-------|-------------------------------------------------|----------------------------------------------------------|---------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------|-----------------------------|---------|
| TF1_  | Patient wird ohne Vermögensstand angezeigt      | Datenbank enthält Patienten mit vollständigen Daten      | Der Benutzer startet die Anwendung und öffnet den Menüpunkt "Patienten"         | Der Patient wird korrekt ohne Vermögensstand angezeigt.                                                       | Funktioniert wie erwartet   | ✅       |
| TF2_  | Neuer Patient wird ohne Vermögensstand angelegt | Keine Vermögensstand-Felder in der Oberfläche vorhanden  | Der Benutzer füllt die Pflichtfelder aus und drückt auf den Button "Hinzufügen" | Der Patient wird korrekt in der Tabelle angezeigt, der Eintrag wird in die Datenbank ohne Fehler übernommen   | Funktioniert wie erwartet   | ✅       |

<br>

## Testing – User Story 5

**Als Mitarbeiter möchte ich mich über ein Login-Fenster mit Benutzernamen und Passwort anmelden, damit ich Zugriff auf die Anwendung erhalte. Wenn die Anmeldedaten fehlerhaft sind, möchte ich eine entsprechende Fehlermeldung bekommen.**

| Test  | Beschreibung                                                   | Vorbedingung                                   | Auszuführende Schritte                                                                      | Erwartetes Ergebnis                                                                                 | Tatsächliches Ergebnis    | Status  |
|-------|----------------------------------------------------------------|------------------------------------------------|---------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|---------------------------|---------|
| TF1_  | Erfolgreiche Anmeldung mit gültigem Benutzernamen und Passwort | Benutzername und Passwort sind gültig          | 1. Anwendung öffnen<br>2. Benutzernamen und Passwort eingeben<br>3. Auf “Anmelden” klicken  | Benutzer wird erfolgreich eingeloggt und zur Hauptanwendung weitergeleitet                          | Funktioniert wie erwartet | ✅       |
| TF2_  | Fehlerhafte Anmeldung                                          | Benutzername oder Passwort ist ungültig        | 1. Anwendung öffnen<br>2. Falsche Daten eingeben<br>3. Auf “Anmelden” klicken               | Fehlermeldung erscheint, Benutzer bleibt im Login-Fenster                                           | Funktioniert wie erwartet | ✅       |
| TF3_  | Passwortanzeige als Sternchen                                  | Benutzer gibt ein Passwort im Passwortfeld ein | 1. Anwendung öffnen<br>2. Passwort in das Passwortfeld eingeben<br/>                        | Passwort wird im Eingabefeld als Sternchen (*) angezeigt                                            | Funktioniert wie erwartet | ✅       |
| TF4_  | Login-Fenster enthält alle nötigen Felder                      | Anwendung wird geöffnet                        | 1. Anwendung starten<br>2. Login-Fenster betrachten                                         | Login-Fenster enthält zwei Eingabefelder (Benutzername und Passwort) und einen Anmelde-Button<br/>  | Funktioniert wie erwartet | ✅       |