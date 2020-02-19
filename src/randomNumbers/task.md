# Zufallszahlen*

Es soll ein Programm geschrieben werden, das beliebig viele, ganzzahlige Zufallszahlen 
in einem Array abspeichert.
###Aufgaben

1.	Implementiere in der Klasse `RandomNumbers` die Methode `public int[] getRandomNumbers(int number)`, die in jedes 
Feld des Arrays eine Zufallszahl im Intervall von 1 bis 10 speichert und dieses zurückgibt. Der Parameter `number` gibt 
die Anzahl der Zufallszahlen, also die Größe des Arrays, an. 
2.	Implementiere in der Klasse `RandomNumbers` die Methode `public int evaluateArray(int[] randomNumbers, int digit)`.
Sie gibt zurück, wie häufig der Parameter `digit` im übergebenen Array vorkommt.
3.	Die Methode `public String getRandomNumbersToString(int[] randomNumbers)` hängt alle in randomNumbers gespeicherten 
Zahlen mit einem Leerzeichen getrennt der Reihe nach aneinander und gibt diesen String zurück.  

**Tipp: Erzeugen einer Zufallszahl in Java**
```
Damit Du Zufallszahlen erzeugen kannst, musst Du in der ersten Zeile 
Deines Quellcodes die Klasse java.util.Random importieren (1). Unter (2)
wird ein Objekt vom Typ Random zur Erzeugug von Zufallszahlen mit dem 
Bezeichner generator erzeugt. 
Unter (3) wird der Variablen zahl ein Zufallswert aus dem Intervall von 0 bis 10 
(aus den ersten elf Zahlen beginnend bei 0) zugewiesen.

import java.util.Random; (1)
public class Zufallszahlen {
   public static void main (String[] args){
	   int zahl;
	   Random generator = new Random(); (2)
	   zahl = generator.nextInt(11); (3)
    }
}
```