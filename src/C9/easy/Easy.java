package C9.easy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Adrian on 1/05/2018.
 */
public class Easy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            System.out.println(String.valueOf(chars));
        }
    }
}
