package C14.easy;

import java.util.Scanner;

/**
 * Created by Adrian on 1/05/2018.
 */
public class Easy {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.contains(",")) {
                String[] split = input.split(",");
                String newString = "";
                String temp = "";
                for (int i = 0; i < split.length; i++) {
                    if (!temp.isEmpty()) {
                        newString += split[i] + "," + temp + ",";
                        temp = "";
                    } else {
                        if (i % 2 == 0 && split.length - 1 == i) {
                            newString += split[i];
                        }
                        temp = split[i];
                    }
                }
                if (split.length % 2 == 0) {
                    newString = newString.substring(0, newString.length() - 1);
                }
                System.out.println(newString);
            }
        }
    }
}
