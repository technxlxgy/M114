package org.example;

import java.util.Scanner;

public class Emoji {

    public static void main(String[] args) {

//      User input \uD83C\uDF0D
        Scanner unicodeScanner = new Scanner(System.in);
        System.out.print("Enter Unicode: ");
        String unicodeInput = unicodeScanner.nextLine();

//      Remove backslashes and parse the Unicode
        String[] unicodePoints = unicodeInput.split("\\\\u");
        StringBuilder emojiBuilder = new StringBuilder();

        for (String point : unicodePoints) {
            if (!point.isEmpty()) {
                try {
                    int unicodePoint = Integer.parseInt(point, 16); // Convert hex to int
                    emojiBuilder.append(Character.toChars(unicodePoint)); // Convert int to char(s)
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Unicode input: " + point);
                }
            }
        }

//      Construct the final output
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_RESET = "\u001B[0m";
        String mysteryEmoji = emojiBuilder.toString();
        System.out.println(ANSI_BLUE +"The mystery emoji is: " + mysteryEmoji + ANSI_RESET);


//      Ask user to input emoji 🥐
        Scanner emojiScanner = new Scanner(System.in);
        System.out.print("Enter an Emoji: ");
        String emoji = emojiScanner.nextLine();

//      Print hex-value of emoji
        printHex(emoji);
    }

    private static void printHex(String emoji) {
        int emojiCodePoint = emoji.codePointAt(0);
        String emojiHex = Integer.toHexString(emojiCodePoint);

        String msg = String.format("Hexadecimal of the emoji %s is: %s", emoji, emojiHex.toUpperCase());
        String ANSI_YELLOW = "\u001B[33m";
        System.out.println(ANSI_YELLOW + msg);
    }
}
