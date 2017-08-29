# Testing
Dieses Dokument beschreibt unser vorgehen beim Test unserer Android App "Todo+", die im Rahmen eines ÜKs im ZLI entwickelt wird.
## Whitebox Testing
| Create Todo Test Cases                               | State |
|------------------------------------------------------|:-----:|
| Create Todo Button klicken                           |  :x:  |
| Todo Titel festlegen                                 |  :x:  |
| "Sport Todo" Toggle aktivieren                       |  :x:  |
| Anzahl Schritte kann festgelegt werden               |  :x:  |
| "Remind me" ist nicht klickbar wenn Sport Todo aktiv |  :x:  |
| "Sport Todo" Toggle deaktivieren                     |  :x:  |
| "Remind me" ist klickbar wenn Sport Todo deaktiviert |  :x:  |
| "Remind me" Toogle aktiveren                         |  :x:  |
| Zeitpunkt der Erinnerung festlegen                   |  :x:  |
| Priorität festlegen                                  |  :x:  |
| "Create"-Button klicken und so Todo erstellen        |  :x:  |
| Todo ist erstellt und in Liste verfügbar             |  :x:  |


| Close Todo Test Cases                                                       | :x: |
|-----------------------------------------------------------------------------|-----|
| Offene Todos werden in der Liste angezeigt                                  | :x: |
| Titel, Priorität und Fälligkeitszeitpunkt werden korrekt angezeigt          | :x: |
| Bei klicken der Checkbox verschwindet das Todo von der Liste                | :x: |
| Die Ansicht "Closed Todos" kann mit Swipe nach Rechts geöffnet werden       | :x: |
| In der Ansicht "Closed Todos" werden alle abgeschlossenen Todos aufgelistet | :x: |

| Sport Todo Test Cases                                                                       | :x: |
|---------------------------------------------------------------------------------------------|:---:|
| Sport Todo wird in der Liste mit Anzahl verbleibenden Schritten angezeigt                   | :x: |
| Schrittezähler kann über Button in der Liste gestartet werden                               | :x: |
| Todo+ zählt die Anzahl Schritte während der Schrittzähler des Todos läuft                   | :x: |
| Die Schritte werden korrekt gezählt und in der Liste Live angezeigt                         | :x: |
| Über den "Pause"-Button kann der Schrittzähler pausiert werden                              | :x: |
| Erneutes Klicken auf den "Start"-Button startet den Schrittzähler wieder                    | :x: |
| Die Zahl der verbleibenden Schritte ist entsprechend der bereits abgelaufenen               | :x: |
| Sind die Schritte abgelaufen, wird eine Benachrichtigung geschickt                          | :x: |
| In der Liste wird angezeigt, wie lange man gebraucht hat, um die Anzahl Schritte abzulaufen | :x: |
| Beim Klicken der Checkbox wird das Sport-Todo in die "Closed Todos"-Ansicht verschoben      | :x: |

## Blackbox Testing
Nachdem wir die Whitebox-Tests abgeschlossen und alle Fehler die wir finden konnten ausgemerzt haben, lassen wir unsere App "Todo+" von einem anderen Team testen. 

Dieses wird versuchen, eventuell noch vorhandene Fehler und Bugs aufzudecken. Auf diesem Wege können wir uns auch ein Feedback zur Bedienung einholen und so die Usability unserer Applikation verbessern.

Wir testen im Umkehrschluss die App des andereren Teams und geben ihnen entsprechend Feedback.