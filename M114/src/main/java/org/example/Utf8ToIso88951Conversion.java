package org.example;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utf8ToIso88951Conversion {

    public static void main(String[] args) {

        Charset cs = Charset.defaultCharset();
        System.out.println("The default charset of the machine is :" + cs.displayName());

//      Input UTF-8 string
        String sourceString = "Grüezi!, здраво!, 你好!, 안녕하세요! \uD83D\uDE04";

//      Covert input-string to byte array
        String sourceStringToBinary = convertStringToBinary(sourceString);

//      Convert UTF-8 string to ISO-8859-1 (Latin-1)
        String iso8859String = new String(sourceString.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

//      Print the converted string
        System.out.println("UTF-8 String: " + sourceString);
        System.out.println("UTF-8 String in Binary: " + prettyBinary(sourceStringToBinary, 8, " | "));
        System.out.println("ISO-8859-1 String: " + iso8859String);

//      Convert ISO-8859-1 (Latin-1) string back to UTF-8 string
        String utf8String = new String(iso8859String.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        String utf8String = "test";

//      Print the converted string
        System.out.println("UTF-8 String: " + utf8String);

        if (utf8String.equals(sourceString)) {
            printSuccess("Decoding successful!");
        } else {
            printFailure("Decoding failed!");
        }
    }

    private static void printSuccess(final String msg) {
        String ANSI_GREEN = "\u001B[32m";
        System.out.println(ANSI_GREEN + msg);
    }

    private static void printFailure(final String msg) {
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED + msg);
    }

    private static String convertStringToBinary(String sourceString) {
        StringBuilder sourceStringToBinary = new StringBuilder();
        for (char aChar : sourceString.toCharArray()) {
            sourceStringToBinary.append(
                    String.format("%8s", Integer.toBinaryString(aChar))
                            .replace(' ', '0')
            );
        }
        return sourceStringToBinary.toString();
    }

    private static String prettyBinary(String binary, int blockSize, String seperator) {
        List<String> sourceStringToBinary = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            sourceStringToBinary.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }
        return sourceStringToBinary.stream().collect(Collectors.joining(seperator));
    }
}

