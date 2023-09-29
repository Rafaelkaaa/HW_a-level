package org.example.service;

public class StringService {
    public static String reverse(String src) {
        String[] stringsArray = src.split(" ");
        return reverseWord(stringsArray);
    }

    public static String reverse(String src, String dest) {
        String reversed = reverse(dest);
        return src.replaceAll(dest, reversed);
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        return reverse(src, src.substring(firstIndex, lastIndex));
    }

    private static String reverseWord(String[] stringsArray) {
        reverseArray(stringsArray);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stringsArray.length - 1; i++) {
            builder.append(stringsArray[i]).append(' ');
        }
        builder.append(stringsArray[stringsArray.length - 1]);
        return builder.toString();
    }

    private static String[] reverseArray(String[] stringsArray) {
        char[] revers;
        for (int i = 0; i < stringsArray.length; i++) {
            revers = new char[stringsArray[i].length()];
            for (int j = 0; j < revers.length; j++) {
                revers[(revers.length - 1) - j] = stringsArray[i].charAt(j);
            }
            stringsArray[i] = new String(revers);
        }
        return stringsArray;
    }
}
