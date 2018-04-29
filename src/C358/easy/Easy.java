package C358.easy;

import java.util.ArrayList;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/8eger3/20180423_challenge_358_easy_decipher_the_seven/
 *
 * For this challenge, you will receive 3 lines of input, with each line being 27 characters long (representing 9 total numbers), with the digits spread across the 3 lines. Your job is to return the represented digits. You don't need to account for odd spacing or missing segments.
 *
 * @author Adrian
 * @since 29 Apr 2018
 */
public class Easy {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList();
        strings.add("    _  _     _  _  _  _  _ ");
        strings.add("  | _| _||_||_ |_   ||_||_|");
        strings.add("  ||_  _|  | _||_|  ||_| _|");
        strings.add(" ");
        strings.add("    _  _  _  _  _  _  _  _ ");
        strings.add("|_| _| _||_|| ||_ |_| _||_ ");
        strings.add("  | _| _||_||_| _||_||_  _|");
        strings.add(" ");
        strings.add(" _  _  _  _  _  _  _  _  _ ");
        strings.add("|_  _||_ |_| _|  ||_ | ||_|");
        strings.add(" _||_ |_||_| _|  ||_||_||_|");
        strings.add(" ");
        strings.add(" _  _        _  _  _  _  _ ");
        strings.add("|_||_ |_|  || ||_ |_ |_| _|");
        strings.add(" _| _|  |  ||_| _| _| _||_ ");

        ArrayList<Letter> letters = new ArrayList();
        for (String str : strings) {
            if (str.equals(" ")) {
                letters.forEach(e -> System.out.print(e.getLetter()));
                System.out.println();
                letters.clear();
                continue;
            }
            if (letters.isEmpty()) {
                for (int i = 0; i < 9; i++) {
                    letters.add(new Letter(str.substring(i * 3, i * 3 + 3)));
                }
            } else {
                for (int i = 0; i < 9; i++) {
                    letters.get(i).add(str.substring(i * 3, i * 3 + 3));
                }
            }
        }
        letters.forEach(e -> System.out.print(e.getLetter()));
    }

    static class Letter {

        String letter = "";

        public Letter(String stuff) {
            add(stuff);
        }

        public void add(String stuff) {
            this.letter += stuff;
        }

        public int getLetter() {
            for (Number number : Number.values()) {
                if (letter.equals(number.data)) {
                    return number.number;
                }
            }
            return 0;
        }


    }

    public enum Number {
        ZERO("", 0),
        ONE("     |  |", 1),
        TWO(" _  _||_ ", 2),
        THREE(" _  _| _|", 3),
        FOUR("   |_|  |", 4),
        FIVE(" _ |_  _|", 5),
        SIX(" _ |_ |_|", 6),
        SEVEN(" _   |  |", 7),
        EIGHT(" _ |_||_|", 8),
        NINE(" _ |_| _|", 9);

        public int number;
        public String data;

        Number(String data, int number) {
            this.data = data;
            this.number = number;
        }
    }
}
