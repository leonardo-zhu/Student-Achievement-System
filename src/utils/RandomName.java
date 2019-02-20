package utils;


public class RandomName {
    public static String random() {
        String random = "";
        for (int i = 0; i < 3; i++) {
            random += (int) (Math.random() * 10);
        }
        return random;
    }
}
