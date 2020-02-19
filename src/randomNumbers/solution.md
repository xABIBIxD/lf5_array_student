**Lösung RandomNumbers**

```
package randomNumbers;

import java.util.Random;

public class RandomNumbers{
    public int[] getRandomNumbers(int number) {
        Random generator = new Random();
        int[] randomNumbers = new int[number];
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = generator.nextInt(10) + 1;
        }
        return randomNumbers;
    }

    public int evaluateArray(int[] randomNumbers, int digit){
        int result=0;
        for(int i=0; i<randomNumbers.length; i++){
            if (randomNumbers[i]==digit){
                result++;
            }
        }
        return result;
    }

    public String getRandomNumbersToString(int[] randomNumbers){
        String result="";
        for(int i = 0; i<randomNumbers.length;i++){
            result = result + randomNumbers[i] + " ";
        }
        return result;
    }
}
```