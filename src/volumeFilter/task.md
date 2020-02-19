# Tonfilter**

Ein Tonsignal wird manchmal als Liste von int-Werten gespeichert. Die Werte repräsentieren die Intensität des Signals in
aufeinanderfolgenden Zeitintervallen. Natürlich wird in einem Programm das Signal durch ein Array repräsentiert.  Oft 
ist in dem Signal ein kleiner Anteil von Störgeräuschen enthalten. Störgeräusche sind üblicherweise kleine, momentane 
Änderungen der Tonhöhe. Ein Beispiel ist das "Geräusch", das zusätzlich zum Ton eines Radios zu hören ist.  Das Glätten 
des Tons entfernt das Störgeräusch und verbessert die Wahrnehmung der Tonqualität. Diese Aufgabe besteht darin, die Werte
eines Integerarrays zu glätten. 

Angenommen, dass die ursprünglichen Werte in dem Array `values` gespeichert sind. Berechne das geglättete Array , indem 
du folgendes tust: 
- Jeder Wert geglaettet[N] ist der Durchschnitt von drei Werten: signal[N-1], signal[N] und signal[N+1]. 
- Berechne für das erste Element von geglaettet den Durchschnitt der ersten zwei Elemente von signal. 
- Berechne für das letzte Element von geglaettet den Durchschnitt der letzten zwei Elemente von signal. 

Verwende dafür Integerarithmetik , so dass die Werte in geglaettet vom Typ Integer sind. 

Die Zahlen im Array signal sollen 5, 5, 4, 5, 6, 6, 7, 6, 5, 4, 1, 4 sein. Gib das geglättete Array auf der Konsole aus.
###Aufgaben

1.	Implementiere in der Klasse `VolumeFilter` die Methode `public int[] smoothValues(int[] values)`. Sie führt die oben 
beschriebene Glättung mit den Werten in dem ihr übergebenen Array `values` durch und gibt sie in einem Array zurück.