#Exceptions
Leider treten auch in sehr sorgfältig programmierten Anwendungen Fehler in verschiedenen Formen auf, die das Programm 
während der Laufzeit zum Absturz bringen. Das kann einerseits daran liegen, dass im Code eine Fehlersituation übersehen 
und daher keine entsprechenden Vorkehrungen getroffen wurden. Andererseits kann der Benutzer durch fehlerhafte Eingaben 
Zustände im Programm erzeugen, die eine Weiterverarbeitung unmöglich machen. Solche Fehler können durch defensives 
Programmieren verhindert werden. Dabei wird versucht jede mögliche Fehlersituation durch Abfragen zu vermeiden. Eine 
mögliche Division durch null und damit ein Programmabsturz kann beispielsweise durch folgende Abfrage verhindert werden.
```
int zahl1 = sc.nextInt();
int zahl2 = sc.nextInt();
if(zahl2 != 0)
     int ergebnis = zahl1/zahl2;
else
     System.out.println(„Keine 0 eingeben!“);
```
##Exception Handling


In vielen Situationen ist eine solche Abfang-Abfrage aber nicht möglich, weil sie gerade den Fehler produziert, der 
vermieden werden soll. Um beispielsweise zu verhindern, dass eine Berechnung den Zahlenbereich der ganzen Zahlen 
überschreitet wäre eine Abfrage vom Typ if(zahl*zahl<=groessteDarstellbareZahl) nötig. Sie führt aber gerade die Berechnung 
aus, die zum Fehler führt. Außerdem haben Abfang-Abfragen den Nachteil, dass der Programmieraufwand steigt und der 
Quellcode sehr unübersichtlich wird, weil jedem möglichen Fehler eine Abfrage gewidmet wird. Java bietet deswegen ein 
eigenes Sprachkonstrukt an, das die Fehlerbehandlung erleichtert und damit das defensive Programmieren unterstützt.

Tritt in Java eine Situation ein, die zum Absturz der Anwendung führt, löst Java eine sogenannte Ausnahme (Exception) aus. 
Der Programmablauf verzweigt von der Stelle, an der das Ausnahmeereignis aufgetreten ist, zu im Code anzugebenden Stelle, 
in der die Ausnahme behandelt werden muss. Wird an einer bestimmten Stelle im Quellcode ein mögliche Fehler erwartet, 
wird dieser Teil in einen try-Block überwacht. Im anschließenden catch-Block wird dann angegeben, was passieren soll, wenn 
der Fehler tatsächlich auftritt. Diese try-catch Behandlung lässt sich als „Versuche dieses, wenn ein Fehler dabei 
auftritt, mache jenes.“ lesen. 

###Syntax

```
try{
    //Code
}
catch(Exceptiontyp Parametername){
     //Behandlung
}
finally{
   //Abschlussarbeiten
} 
```

- Die try-Anweisung leitet einen Exception-Block ein.
- Es folgt der Quellcode, der eine Exception auslösen kann.
- Über die catch-Anweisung kann eine ganz bestimmte Ausnahme, dies kann eine von Java vordefinierte oder eine selbst 
geschriebene sein, abgefangen werden. Wird als Typ Exception angegeben, werden alle möglichen Ausnahmen abgefangen. 
- Es folgt die Angabe eines Parameternamens, über den auf das Exceptionobjekt zugegriffen werden kann.
- Es können beliebig viele catch-Blöcke hintereinander formuliert werden. Das macht Sinn, wenn in dem zu behandelnden 
Quellcode unterschiedliche Fehler auftreten können. Es wird aber nur maximal einer davon ausgeführt. Wird ein bestimmter 
Fehlertyp in keinem catch-Block behandelt, stürzt das Programm weiterhin ab.
- Abschließend können in einem finally-Block Befehle formuliert werden, die in jedem Fall, also bei einem regulären 
Durchlaufen des Programms und bei einer Fehlerbehandlung, ausgeführt werden. Bevor ein Programm beispielsweise beendet 
werden kann, müssen alle geöffneten Dateien geschlossen werden. 

###Beispiel

Im Beispiel von oben wirft Java bei einer Division durch 0 eine ArithmeticException. Ist dies der Fall bekommt der 
Benutzer die Fehlermeldung „Nicht durch 0 teilen!“.

```
…
try{
  ergebnis = zahl1/zahl2;
}
catch(ArithmeticException e){
   System.out.println("Nicht durch 0 teilen!“);
}
```

##Exceptions an den Aufrufer weiterleiten

In vielen Fällen ist es nicht sinnvoll Fehler an der Stelle zu behandeln, an der sie aufgetreten sind. Oft kann eine 
aufgerufene Methode gar nicht wissen, was sie im Fehlerfall tun soll. Ist beispielsweise eine Fehlermeldung auf der Konsole 
oder in einer GUI gewünscht? Methoden müssen also in die Lage versetzt werden, Fehlerfälle an die aufrufende Methode 
weiterzugeben, damit diese die Fehlerbehandlung vornimmt. Eine Idee hierfür wäre, Fehler über den Rückgabewert der Methode 
anzuzeigen. Tritt in einer Methode ein Fehler auf, so könnte diese ein false oder eine -1 zurückgeben. Ein solches Verfahren 
ist allerdings nicht sinnvoll. Die aufrufende Methode muss so immerzu Rückgabewerte auf Fehlerfälle hin testen, wodurch 
ständig normaler Code mit Fehlerbehandlungscode gemischt und die Lesbarkeit des Quellcodes beeinträchtigt wird. Ist zweitens 
eine Fehlermeldung gewünscht, muss die aufgerufene Methode diesen ausgeben. Drittens sind häufig alle Parameterwerte gültige 
Rückgabewerte, so dass kein Wert als Fehlerwert übrig bleibt. 
Java bietet für Fälle, in denen Methoden Fehler an die aufrufende Methode zur Behandlung weiterreichen sollen eine 
throw-Klausel an. Dabei wird die Methodendeklaration um das Schlüsselwort throws und den möglicherweise auftretenden 
Exceptiontypen ergänzt. Im Beispiel wird in der Methode dividiere() eine Arithmetic-Exception ausgelöst, wenn versucht wird 
durch 0 zu teilen. Deswegen gibt dividiere() mithilfe der throws-Klausel die möglicherweise auftretende ArithmeticException 
an die aufrufende Methode, in diesem Fall die main-Methode, weiter. Kann die Methode mehrere Arten von Exceptions auslösen, 
werden diese hinter der Parameterliste mit Kommata getrennt aufgelistet. Ist die aufrufende  Methode nicht in der Lage, 
die weitergereichte Exception zu behandeln, muss sie diese ebenfalls durch eine throw-Klausel an ihre aufrufende Methode 
weiterreichen. Alternativ kann im Methodenrumpf explizit mit dem new-Operator eine Exception ausgelöst werden. Hier hat 
der Programmierer die Möglichkeit dem Exceptionobjekt eine Nachricht mitzugeben, auf die in einem catch-Block mithilfe 
der Methode getMessage() zugegriffen werden kann. Befindet sich in einer Methode eine throw-Klausel, wird hinter dieser 
Anweisung der Programmablauf abgebrochen und zur nächsten catch-Klausel verzweigt.

```
public static void main(String[] args) {
   int zahl1, zahl2;
   Scanner sc = new Scanner(System.in);
   zahl1 = sc.nextInt();
   zahl2 = sc.nextInt();
   try{
      System.out.println(dividiere(zahl1, zahl2));
   }
   catch(ArithmeticException e){
      System.out.println("Es darf nicht durch 0 geteilt werden!");
   }
}
	
public int dividiere(int zahl1, int zahl2) throws ArithmeticException{
    return zahl1/zahl2;
}    
```

```
public static void main(String[] args) {
   int zahl1, zahl2;
   Scanner sc = new Scanner(System.in);
   zahl1 = sc.nextInt();
   zahl2 = sc.nextInt();
   try{
      System.out.println(dividiere(zahl1, zahl2));
   }
   catch(ArithmeticException e){
      System.out.println(e.getMessage());
   }
}
	
public int dividiere(int zahl1, int zahl2) throws ArithmeticException{
   if(zahl2 == 0){
        throw new ArithmeticException("Nicht durch 0 teilen");
   }
   else{
        return zahl1/zahl2;  
   }
}    
```