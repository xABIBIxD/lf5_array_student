# Drei Gewinnt ****

## Spielprinzip 
Das Spiel *Drei Gewinnt* ist ein Zwei-Personen-Spiel. Das Spielfeld besteht aus nebeneinander liegenden und gleichgroßen Röhren. In jede Röhre passen gleich viele Spielsteine. Wie groß das Spielfeld ist, ist frei wählbar.
 

## Spielregeln
- Jeder Spieler hat ausreichend viele Spielsteine. Auf dem Spielstein des ersten Spielers befindet sich ein großes `O`, auf dem des zweiten Spielers ein großes `X`.
- Die Spieler spielen abwechselnd.
- Der Spieler, der an der Reihe ist, wirft einen seiner Spielsteine in eine beliebige Röhre.
- Ist eine Röhre komplett gefüllt, so darf dort kein weiterer Spielstein eingeworfen werden.
- Das Spielt endet, wenn alle Röhren gefüllt sind.
- Für jede Reihe bestehend aus mindestens drei benachbarten Spielsteinen eines Spielers (waagrecht oder senkrecht) gibt es je einen Siegpunkt.
- Wer die meisten Siegpunkte hat, gewinnt.

## Aufgaben

#### 1) Spielfeld erstellen
Implementiere den Konstruktor der Klasse `ThreeWins`, dem die Höhe und Breite des Spielfelds übergeben wird. Dieser erstellt ein neues Spielfeld bestehend aus Leerzeichen.

Das Spielfeld ist von außerhalb der Klasse mit der Methode `getMatchfield` abrufbar.

Nutzt man den Standardkonstruktor, so kann das Spielfeld von außerhalb der Klasse mit der Methode `setMatchfield` vorgegeben werden.

Folgender Test validiert die Aufgabe:
- `givenNewThreeWins_whenConstructor_matchfieldIsInitialized`


#### 2) Spielstein einwerfen
Implementiere die Methode `placeGamePiece`, der die Spielernummer (eine *1* oder eine *2*) und die Röhre, in die der Spielstein eingeworfen werden soll, mitgegeben werden muss.
 
Folgende Ereignisse sind möglich:
- Der Spielstein konnte eingeworfen werden: Die Methode liefert `true` und setzt den Spielstein auf die richtige Position. 
- Die Röhre ist bereits voll: Die Methode liefert `false`. 
- Die Spieler- oder die Röhrennummer sind nicht korrekt: Eine `IllegalArgumentException` wird geworfen.
 
Folgende Tests validieren die Aufgabe:
- `givenValidParams_whenPlaceGamePiece_returnTrueAndMatchfieldCorrect`
- `givenValidParamsButChoosenColumnIsFull_whenPlaceGamePiece_returnFalse`
- `givenInvalidPlayer_whenPlaceGamePiece_throwException`
- `givenInvalidColumn_whenPlaceGamePiece_throwException`


#### 3) Spielende überprüfen
Erstelle die Methode `checkEndOfGame`, die überprüft, ob das Spiel beendet ist oder nicht. Entsprechend liefert sie `true` oder `false`. Beendet ist ein Spiel, wenn keine Möglichkeit mehr existiert einen weiteren Spielstein zu setzen.

Folgende Tests validieren die Aufgabe:
- `givenValidField_whenCheckEndOfGame_returnTrue`
- `givenValidField_whenCheckEndOfGame_returnFalse`


#### 4) Siegpunkte ermitteln
Die Methode `getFinalScore` ist die aufwendigste Methode der Klasse `ThreeWins`. Sie ermittelt für einen anzugebenen Spieler die Siegpunkte.

*Tipp:* Teile die Methode auf in zwei Methoden - eine Methode ermittelt zeilenweise die Siegpunkte, eine weitere spaltenweise.

Folgender Test validiert die Aufgabe:
- `givenEndField_whenGetFinalScore_returnPlayersPoints`


#### 5) UI programmieren
Entwickel für das Spiel eine geeignete Benutzeroberfläche, mit der man das Spiel tatsächlich spielen kann. Nenne die Klasse `ThreeWinsUI`.