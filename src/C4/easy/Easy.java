package C4.easy;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Adrian
 * @since 30 Apr 18
 */
public class Easy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        while (scanner.hasNext()) {
            int length = Integer.parseInt(scanner.nextLine());
            String pickfrom = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            String password = "";
            for (int i = 0; i < length; i++) {
                password += pickfrom.charAt(random.nextInt(pickfrom.length()));
            }
            System.out.println(password);
        }
    }
}
