package randomNumbers;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Random;

public class RandomNumbersTest {

    private RandomNumbers rn;

    @BeforeEach
    public void setUp(){
        Random gen = new Random();
        rn = new RandomNumbers(gen);
    }

    public void test(){

    }




}
