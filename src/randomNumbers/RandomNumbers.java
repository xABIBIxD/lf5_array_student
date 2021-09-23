package randomNumbers;

import java.util.Random;

public class RandomNumbers{
    public int[] getRandomNumbers(int number) {
        Random a = new Random();
        int[] andrew = new int[number];
        for (int i = 0;i<andrew.length;i++){
            andrew[i] = a.nextInt();
        }
        return andrew;
    }

    public int evaluateArray(int[] randomNumbers, int digit){
        int affe=0;
        for (int i =0;i<randomNumbers.length;i++){
            if (randomNumbers[i]==digit){
                affe +=1;
            }
        }
        return affe;
    }

    public String getRandomNumbersToString(int[] randomNumbers){
        String ausgabeArray = "";
        for (int i = 0; i<randomNumbers.length;i++){
            ausgabeArray = randomNumbers[i]+" ";
        }
        return ausgabeArray;
    }
}