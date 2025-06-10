# Testing

Dokumentiert über diese Markdown-Datei eure Tests. Führt dazu in einer Tabelle alle Testfälle auf,
und gebt in einer zweiten Spalte das Ergebnis des jeweiligen Tests an. Über die Editor-Ansicht rechts oben
gelangst du in die Ansicht, in der du Inhalte hinzufügen kannst. Die Markdown-Syntax kannst du unter 
https://markdown.de/ einsehen.



-----------------------------------------------------------------------------------|Testing|----------------------------------------------------------------------------------------------------------




| User_Story | Description                            | Precondition                                                            | Expected Result                                                                                      | Actual Result     | Status |
|------------|----------------------------------------|-------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|-------------------|--------|
| TF1_       | Show all Caregivers                    | User is on the main screen and can open the caregiver view via a button | TableView Shows all caregivers with ID, surname, first name, phone number, active/inactive status    | Works as expected | Passed |
| TF2_       | Generic DAO Implementation             | Application has database connection set up                              |                                                                                                      |                   |        |
| TF3_       | Create caregiver table in the database | Application is started and database connection exists is established    | Table caregiver is created with columns: cid, surname, firstname, phoneNumber, active, inactiveSince | Works as expected | Passed |
| TF4_       |                                        |                                                                         |                                                                                                      |                   |        |
| TF5_       |                                        |                                                                         |                                                                                                      |                   |        |
| TF6_       |                                        |                                                                         |                                                                                                      |                   |        |