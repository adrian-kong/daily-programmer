package C1.hard;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Adrian
 * @since 30 Apr 18
 */
public class Hard {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CopyOnWriteArrayList<Integer> ints = new CopyOnWriteArrayList();
        for (int i = 1; i <= 100; i++) {
            ints.add(i);
        }
        int guess = ints.get(new Random().nextInt(ints.size()));
        System.out.println("Is your number " + guess + "?");

        while (scanner.hasNext() && !ints.isEmpty()) {
            String input = scanner.nextLine();
            if (input.equals("=")) {
                System.out.println("Your number was " + guess);
                break;
            }
            if (input.equals("+")) {
                if (ints.get(ints.size() - 1) == guess) {
                    System.out.println("You cheated");
                    break;
                }
                for (Integer integer : ints) {
                    if (integer <= guess) {
                        ints.remove(integer);
                    }
                }
            }
            if (input.equals("-")) {
                if (ints.get(0) == guess) {
                    System.out.println("You cheated");
                    break;
                }
                for (Integer integer : ints) {
                    if (integer >= guess) {
                        ints.remove(integer);
                    }
                }
            }
            guess = ints.get(new Random().nextInt(ints.size()));
            System.out.println("Is your number " + guess + "?");
        }
    }
}
