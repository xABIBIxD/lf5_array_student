package randomNumbers;

import java.util.Random;

public class RandomNumbers{
    private Random generator;

    public RandomNumbers(Random gen){
        this.generator = gen;
    }

    public int[] getRandomNumbers(int number) {
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
                digit++;
            }
        }
        return result;
    }
}