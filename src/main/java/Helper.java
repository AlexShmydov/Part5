import java.util.Random;

public class Helper {

    public static int getRandomValue(int max, int min) {
        return new Random().nextInt((max - min) + 1) + min;
    }

}
