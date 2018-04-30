package C2.easy;

import java.util.Scanner;

/**
 * @author Adrian
 * @since 30 Apr 18
 */
public class Easy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String next = scanner.nextLine();
            if (next.contains(" ") && next.split(" ").length == 2) {
                if (next.startsWith("sin"))
                    System.out.println(Math.sin(translate(next)));
                if (next.startsWith("tan"))
                    System.out.println(Math.tan(translate(next)));
                if (next.startsWith("cos"))
                    System.out.println(Math.cos(translate(next)));
                if (next.startsWith("asin"))
                    System.out.println(Math.asin(Double.parseDouble(next.split(" ")[1])) + " rads");
                if (next.startsWith("atan"))
                    System.out.println(Math.atan(Double.parseDouble(next.split(" ")[1])) + " rads");
                if (next.startsWith("acos"))
                    System.out.println(Math.acos(Double.parseDouble(next.split(" ")[1])) + " rads");
                if (next.startsWith("todeg"))
                    System.out.println(Math.toDegrees(Double.parseDouble(next.split(" ")[1])) + " deg");
                if (next.startsWith("torad"))
                    System.out.println(Math.toRadians(Double.parseDouble(next.split(" ")[1])) + " rads");
            }
        }
    }

    static double translate(String next) {
        double val = 0;
        if (next.split(" ")[1].endsWith("deg")) {
            val = Math.toRadians(Double.parseDouble(next.split(" ")[1].replaceAll("deg", "")));
        } else if (next.split(" ")[1].endsWith("rad")) {
            val = Double.parseDouble(next.split(" ")[1].replaceAll("rad", ""));
        }
        return val;
    }
}
