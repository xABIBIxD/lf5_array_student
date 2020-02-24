# Candy Crush! *****

##Spielprinzip 
Candy Crush Saga (oder kurz: Candy Crush) ist ein Puzzle-Computerspiel. Es ist online spielbar und als App erhältlich. Jedes Level besteht aus einem Spielbrett, das mit verschiedenfarbigen Süßigkeiten und Hindernissen gefüllt ist. Die Süßigkeiten beinhalten das „rote Geleebonbon“, das „orange Lutschbonbon“, den „gelben Zitronen-Drop“, das „grüne Kaubonbon“, das „blaue Lollipop-Stück“ und die „lilafarbene Traube“. Der Spieler kann Süßigkeitenpaare durch horizontales oder vertikales Bewegen einsammeln. 

## Aufgaben

#### 1)
Erstellen Sie den Konstruktor der Klasse `CandyCrushLogic`. 
Dieser muss das Spielfeld, welches in der Instanzvariablen 
`field` gespeichert werden soll, generieren. Ein gültiges Spielfeld ist 
komplett mit Symbolen gefüllt (siehe Klassenvariable `SYMBOLS`) und hat die Größe `FIELD_SIZE x FIELD_SIZE` .
 Der Test `givenNewCandyCrushLogic_whenGetField_returnValidCandyCrushArray`
 validiert diese Aufgabe.
 
 #### 2)
 Implementiere Sie die Methode `swap` in der Klasse `CandyCrushLogic`. Hier werden zwei benachbarte Felder vertauscht. Die Parameter müssen valide sein! 
 Folgende Tests validieren diese Aufgabe:
 - `givenTwoValidPoints_whenSwap_swapTwoSymbols`
 - `givenTwoInvalidPoints_whenSwap_throwsException`

#### 3) 
Nun kommt der wohl schwierigste Teil. Bei Candy-Crush verschwinden nach einem Spielzug Steine vom Spielbrett. Steine werden entfernt, wenn sie Teil einer Combo sind. Eine Combo ist vorhanden, wenn 3 oder mehr gleiche Symbole in einer horizontalen oder vertikalen Reihe sind. Implementiere also die Methode `removeMatchingSymbols()`. Validiere deinen Code mit den Test:
- `givenFieldWitHorizontalMatches_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols`
- `givenFieldWithVerticalMatches_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols`


Nun können die Combos aber auch gemsicht auftreten. D.h. zum Beispiel wäre folgendendes Muster denkbar:
```   
   *
   *       *
   * * * * * * *
   *       *   *
               *
               *
   ```
Ergänze deinen Code, so dass er auch solche zugegeben eher seltenen Varianten abdeckt. Validiere mit:
- `givenCurrentFieldWithMixedColsAndRowMatching_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols`
- `givenCurrentFieldWithMultipleMixedColsAndRowMatching_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols`

#### 4)
