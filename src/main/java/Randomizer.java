public class Randomizer {

    public static int randomNumber(int min, int max) {

        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;


    }

    public static int randomNumber(int max) {

        return (int)(Math.random() * max);


    }
}