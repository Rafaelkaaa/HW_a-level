package generator;

import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomNumber {
    public int randomNumberFrom1Till100() {
        return new RandomDataGenerator().nextInt(1,100);
    }
}
