#eindimensionale Arrays

Bei vielen Aufgabenstellungen muss eine große Anzahl von Daten, die den gleichen Datentyp besitzen, verwaltet werden.
Ein Beispiel dafür wäre die Erfassung aller Mitglieder eines Vereins. Wolltest du dieses Problem mit deinem bisherigen 
Wissen lösen, hättest du mit einigen Umständlichkeiten zu kämpfen: Hätte der Verein 100 Mitglieder, müsstest du 100 
Variablen deklarieren, um alle Mitglieder erfassen zu können. Das Einlesen der Daten in die Variablen sowie das 
Ausgeben der Daten ist ebenso umständlich. Hierfür benötigst du 100 Zeilen mit jeweils einer Anweisung zum Einlesen, 
um dieselben Daten wieder auszugeben 100 Zeilen mit System.out.print(ln)-Anweisungen. Deswegen kommen bei solchen 
Aufgabenstellungen Arrays, auch Tabellen oder Felder genannt, zum Einsatz. In einem Array werden mehrere Elemente 
(z.B. die Namen der Vereinsmitglieder) des gleichen Datentyps zusammengefasst. Ein Array ist nichts anderes als eine 
Variable, die mehrere Variablen speichern kann. Hört sich komisch an? Zur Verdeutlichung ein Bild:
Die bisherigen Variablen waren wie Schränke, in die man nur eine Sache hineinlegen konnte. Immer, wenn etwas Neues 
abgelegt werden sollte, musste ein neuer Schrank angelegt werden. Arrays sind wie Schränke mit Schubladen. Um viele 
gleichartige Sachen abzulegen, muss nun nicht mehr für jede Sache ein Schrank angelegt werden. Stattdessen wird nur 
noch ein Schrank mit vielen Schubladen angelegt, wobei jede Schublade eine Sache aufnehmen kann. 

###Deklaration von Arrays

```
Datentyp des Arrays[] bezeichner = new Datentyp des Arrays[<Arraylänge>];

oder, wenn das Array gleich mit einer bekannten Anzahl an Elementen belegt werden soll:

Datentyp des Arrays[] bezeichner = {Argument1, Argument2, Argument3…};

Beispiel:  

String [] member = new String [100];
String [] member = {“Meier”, “Müller”, “Schulze”, “Hansen”, “Klaus”};

```

Im Beispiel oben wird zunächst die Variable member vom Typ String deklariert. Das geschieht wie bei jeder Variablen, die
einen einfachen Datentyp besitzt. Die eckigen Klammern hinter dem Datentyp signalisieren dem Compiler, dass der Bezeichner 
member ein Array referenziert. Alternativ können die eckigen Klammern auch hinter dem Bezeichner stehen. Hinter dem 
Gleichheitszeichen wird mithilfe des Operators new ein Array erzeugt und dessen Speicherplatz der Variablen member 
zugewiesen. In den eckigen Klammern muss die Länge, also die Anzahl der Felder des Arrays, angegeben werden. Die Länge des 
Arrays ist nach der Deklaration nicht mehr änderbar. Sind die Werte, die ein Array speichert, bei der Deklaration bekannt,
kann anstelle der Feldgrößenangabe direkt die Initialisierung erfolgen. Dazu werden die einzelnen Werte in einer durch 
geschweifte Klammern eingeschlossene Initialisierungsliste angegeben.

###Zugriff auf die Felder eines Arrays

----
Meyer|Jansen|Wirth|Müller 
---- | ---- | ----|----      
0|1|2|3
Wie oben in der Abbildung zu sehen ist, werden die einzelnen Elemente eines Arrays sequentiell im Speicher angeordnet. 
Jedes Element erhält dabei einen Index, über den es angesprochen werden kann. Das erste Element eines Arrays hat immer 
den Index 0. Um den Inhalt eines Elements zu verarbeiten, muss der Bezeichner des Arrays zusammen mit dem Indexwert 
dahinte rin eckigen Klammern angegeben werden.

```
//gibt den Wert von member an der Stelle 0 auf der Konsole aus
System.out.println(member[0]);
//weist dem Array member an der Stelle 1 den Wert Schmidt zu
member[0]= “Schmidt“; 
//weist dem Array member an der Stelle 5 einen über die Konsole eingelesenen Wert zu
vereinsmitglied[5]=sc.next(); 
```

Um alle Elemente eines Arrays zu verarbeiten, werden sie mithilfe von zählergesteuerten Schleifen. Dabei wird die 
Zählvariable als Index verwendet:
```
String [] member = new String [100];
for (int i=0; i<100; i++){
   System.out.print(“Name des Vereinsmitglieds: “);
   member[i]=sc.next();
} 
```
Der Zähler i zeigt immer die Position im Array an, so dass nacheinander alle Elemente gefüllt werden. Zu beachten ist 
hier, dass das Array zwar 100 Elemente besitzt, die Indizes dieser Elemente aber im Intervall von 0 bis 99 liegen. Daher 
lautet die Abbruchbedingung i<100.
Es ist allerdings keine gute Idee, die Obergrenze für den Index eines Arrays in einer Schleife als Literal anzugeben (i<=99 oder i<100).
Muss die Größe des Arrays später geändert werden, zieht das eine Änderung der Literale nach sich. Um den Code flexibler 
zu halten, besitzt jedes Array die Eigenschaft length, das die Anzahl der Elemente des Arrays speichert. 
Der nullbasierte Index läuft also bis zum Wert von length – 1:
```
String [] member = new String [100];
for (int i=0; i<member.length; i++){
   System.out.print(“Name des Vereinsmitglieds: “);
   member[i]=sc.next();
} 
```

Beim Zugriff auf Arrayelemente stellt die foreach-Schleife eine elegante Vereinfachung dar. Dabei wird hinter dem 
Schlüsselwort for in Klammern eine Variable deklariert, in der nacheinander alle Elemente des Arrays gespeichert und 
im Schleifenrumpf verarbeitet werden. Das Array, dem die einzelnen Elemente entnommen werden sollen, muss dahinter nach 
einem Doppelpunkt angegeben werden. Die unten abgebildete for-each-Schleife kann man lesen als: „Für jedes Element im Array
member führe die Anweisungen im Schleifenrumpf aus.“ dadurch, dass die for-each-Schleife keinen Index verwendet, ist sie 
kürzer und vermeidet Fehler durch falsche Indizierungen. Zu beachten ist, dass nur mit Kopien der Elemente und nicht mit
den Werten selbst gearbeitet wird. Wenn a in der Schleife ein neuer Wert zugewiesen wird, wird er nur in a, aber nicht 
in das Array geschrieben. Die foreach-Schleife ist kein eigenes Sprachkonstrukt in Java, sondern wird vom Compiler in die 
oben beschriebene for-Schleife umgewandelt. 
```
for(String a: member){
      System.out.println(a);
} 
```

###Zugriffsverletzungen

Ein häufiger Fehler beim Umgang mit Arrays ist die Verletzung der Grenzen eines Arrays. Ist ein Array 100 Felder groß, 
hat also die Indizes 0 bis 99, so wird das Programm beim Befehl 
```
member[100] ="Hoppe";
```
abstürzen, da auf ein Arrayelement zugegriffen werden soll, das es nicht gibt. Java wirft dann die Exception 
java.lang.ArrayIndexOutOfBoundsException.