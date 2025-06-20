########################################
# Projekt: Labyrinth-Person           #
# Abgabe für Abschlussprojekt OOP     #
########################################

Gruppe:
- Johannes R. und Katharina D.

Java-Version:
- Java 21

Entwicklungsumgebung:
- IntelliJ IDEA Ultimate Edition 2025.1

Projektstruktur:
- controller/
    - Labyrinth.java (Main-Klasse)
    - Controller.java (verarbeitet Eingaben)
    - GameState.java (Spielstatus)
- model/
    - World.java (Spiellogik)
    - FieldType.java (Feldarten)
    - Direction.java (Richtungen)
    - Enemy.java (Gegner-Logik)
    - MazeGenerator.java (Labyrinth-Erzeugung)
- view/
    - GamePanel.java (grafische Darstellung)
    - ControlPanel.java (GUI-Buttons)
    - View.java (Interface für alle Views)
    - ConsoleView.java (optionale Textansicht)

Spielstart:
1. Starte `Labyrinth.java` (im Paket `controller`)
2. GUI wird geöffnet
3. Pfeiltasten ← ↑ ↓ → zum Bewegen der Spielfigur
4. Gegnerzahl über Drop-Down-Menü wählbar
5. Button "Neustart" generiert neues Labyrinth

Spielziel:
- Der Spieler (gelber Kreis) startet auf dem grünen Feld (Start)
- Ziel ist es, das blaue Feld (Ziel) zu erreichen
- Gegner (rote Kreise) verfolgen den Spieler
- Wird der Spieler gefangen → Game Over
- Kommt er ins Ziel → Sieg

Features:
- Automatisch generiertes Labyrinth (rekursiver Backtracking-Algorithmus)
- Sichtbares Start- und Zielfeld
- Gegner mit Verfolgungslogik (Manhattan-Distanz)
- Schwierigkeitsgrad (Gegneranzahl) wählbar
- Rundenbasiertes Spiel (Spielerzug → Gegnerzug)
- Sieganzeige / Game-Over-Anzeige
- GUI-Elemente (Swing): Spielfeld, Steuerfeld, Buttons

Kapselung & Architektur:
- Saubere Trennung in Model, View, Controller (MVC)
- Alle Attribute sind `private` oder `final`
- Kommunikation erfolgt ausschließlich über Getter/Setter und `View.update(...)`



