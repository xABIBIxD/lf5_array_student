#zweidimensionale Arrays

Zweidimensionale Arrays sind Tabellen mit mehreren Zeilen. In Java werden sie durch Arrays von Arrays realisiert, d.h.
jedes Element eines Arrays enthält wiederum ein Array.

### Deklaration von zweidimensionalen Arrays

```
Die allgemeine Form der Deklaration lautet
Datentyp [] [] bezeichner = new Datentyp[Feldlänge] [Feldlänge];

//Deklaration eines int-Arrays mit 8 Zellen, die in zwei Zeilen und 4 Spalten angeordnet sind
int[][] matrix = new int[2][4];
//alternativ:
int matrix[][] = new int[2][4]; 
int[] matrix[] = new int[2][4];
```

Wie eindimensionale Arrays können zweidimensionale Arrays bei der Deklaration initialisiert werden. Bei der zweiten 
Alternative ist zu erkennen, dass zweidimensionale Arrays nicht unbedingt rechteckig sein müssen, sondern die einzelnen
Arrays in den Arrays eine unterschiedliche Länge haben können:
```
int[][] matrixname = {{1, 2}, {2, 4}, {3, 5}};
int matrixname[][] = {{1, 2}, {2, 4, 3}, {1}}; 
```

###Zugriff
Zum Zugriff auf Komponenten eines zweidimensionalen Arrays bedient man sich zweier Indizes. Der erste gibt im graphischen 
Sinne die Zeile, der zweite die Spalte an. Die im Beispiel oben erzeugte Tabelle besteht aus 2 Zeilen und 4 Spalten. Soll 
der der Komponente mit dem Indexx 1, 3 die 4 zugewiesen werden,  geschieht das mit dem Befehl
```
matrix[1][3]=4;
```
<table>
  <tr>
    <td> </td>
    <td>0</td>
    <td>1</td>
    <td>2</td>
    <td>3</td>
  </tr>
  <tr>
    <td>0</td>
    <td> </td>
    <td> </td>
    <td> </td>
    <td> </td>
  </tr>
  <tr>
      <td>1</td>
      <td> </td>
      <td> </td>
      <td> </td>
      <td>4</td>
  </tr>
</table>

Die 1 in der ersten Klammer gibt die Zeile der Matrix, 3 die Spalte an. Wie beim eindimensionalen Array beginnt jeder 
Index mit 0.
Das Durchlaufen eines zweidimensionalen Arrays erfolgt im Allgemeinen mittels zweier ineinander geschachtelter 
for-Schleifen. Das unten abgebildete Codefragment bewirkt die Initialisierung einer Tabelle mit 3 Zeilen und 4 Spalten 
mit den Werten von 0 bis 27, das darunter abgebildete Codesnippet gibt die Matrix auf dem Bildschirm aus:

```
int[][] matrix=new int[3][4];
int value=1;
for (int row=0; row<3; row++){
     for(int column=0; column<4; column++){
          matrix[row][column]=value;
          value++;
     }
}

for (int row=0; row<matrix.length; row++){
     for(int column=0; column<matrix[row].length; column++){
          System.out.print(matrix[row][column] + "|");
     }
     System.out.println();
}
```
Die Ausgabe würde in etwa wie folgt aussehen:
<table>

  <tr>
    <td>1</td>
    <td>2</td>
    <td>3</td>
    <td>4</td>
  </tr>
  <tr>
    <td>5</td>
    <td>6</td>
    <td>7</td>
    <td>8</td>
  </tr>
  <tr>
    <td>9</td>
    <td>10</td>
    <td>11</td>
    <td>12</td>
    </tr>
</table>
