package indi.kwanho.util;

public class NumberUtils {
    public static String getRandomInRange(int low, int height) {
        int random = (int) (Math.random() * (height - low + 1) + low);
        return random < 10 ? "0" + random : String.valueOf(random);
    }
}
