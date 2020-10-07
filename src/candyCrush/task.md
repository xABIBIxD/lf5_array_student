# Candy Crush! *****
![](../../images/candyCrush/CandyTop.png)
##Spielprinzip 
Candy Crush Saga (oder kurz: Candy Crush) ist ein Puzzle-Computerspiel. Es ist online spielbar und als App erhältlich. Jedes Level besteht aus einem Spielbrett, das mit verschiedenfarbigen Süßigkeiten und Hindernissen gefüllt ist. Die Süßigkeiten beinhalten das „rote Geleebonbon“, das „orange Lutschbonbon“, den „gelben Zitronen-Drop“, das „grüne Kaubonbon“, das „blaue Lollipop-Stück“ und die „lilafarbene Traube“. Der Spieler kann Süßigkeitenpaare durch horizontales oder vertikales Bewegen einsammeln. 

## Hinweis
Sie können jederzeit die CandyrushUI starten und den aktuellen Stand Ihres Spiels in einer Java-Oberflächenapplikation spielen!

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
Nun kommt der wohl schwierigste Teil. Bei Candy-Crush verschwinden nach einem Spielzug Steine vom Spielbrett (hier, indem das Symbol durch ein Leerzeichen `' '` ersetzt wird). Steine werden entfernt, wenn sie Teil einer Combo sind. Eine Combo ist vorhanden, wenn 3 oder mehr gleiche Symbole in einer horizontalen oder vertikalen Reihe sind. Implementiere also die Methode `removeMatchingSymbols()`. Validiere deinen Code mit den Test:
- `givenFieldWitHorizontalMatches_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols`
- `givenFieldWithVerticalMatches_whenRemoveMatchingSymbols_returnFieldWithBlanksOnMatchingSymbols`

Nun können die Combos aber auch gemischt auftreten. D.h. zum Beispiel wäre folgendendes Muster denkbar:
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

Nun noch ein kleines Refactoring. Die Methode `removeMatchingSymbols()` soll einen boolean-Wert zurückliefern, der `true`ist, sofern mindestens eins Mach gefunden wurde. 
Validere mit: 
- `givenFieldWithMatch_whenRemoveMatchingSymbols_returnTrue`
- `givenFieldWithNoMatch_whenRemoveMatchingSymbols_returnFalse`

#### 4) 
Jetzt sollen die Steine fallen! Die Lücken werden bei CandyCrush aufgefüllt, indem die über der Lücke liegenden vorhandenen Steine "herunter fallen". Die dann entstandene Lücke über den heruntergefallenen Steinen wird mit neuen zufälligen Steinen aufgefüllt. Implementiere die Methode `fillField`. Validiere mit 
- `givenFieldWithBlanks_whenFillField_fieldIsFilledUp`

Tipp: Die Klassenvariable `ANIMATED_EXPLOSION` kann nun auf `true` gesetzt werden, dann würde im UI eine "animierte" Explosion anstatt eines statischen Bildes gezeigt werden.
#### Ergänzungen
Sie sind schon fertig und wollen noch Herausforderungen? Dann mal los:

* Ein Swap ist nur erlaubt, sofern einer der zu tauschenden Steine nach dem Tausch ein Match auslöst. 
* Eine Methode `isGameOver()` prüft, ob mittels eines `swap` noch ein Match ausgelöst werden kann. Falls dies nicht mehr möglich sein sollte, liefert die Methode `true`zurück, sonst `false`. Versuchen Sie diesen Check in der Klasse `Canvas` einzubauen und im Game-Over-Fall eine Nachricht auszugeben (z.B. mit `JOptionPane.showMessageDialog(null, "Game Over!"). Im Anschluss könnte auch ein neues Feld generiert werden....
* Punkte zählen...
* PowerUps! Matchen 4 Steine auf einmal könnte man z.B. ein Power-Up spawnen, welches eine ganze Reihe ausradiert. 
