package C1.easy;

import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * @author Adrian
 * @since 30 Apr 18
 */
public class Easy {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.println("your name is " + name + ", you are " + age + " years old, and your username is " + username);

        Files.write(new File("src/C1/easy/save.txt").toPath(), (name + ":" + age + ":" + username).getBytes());
    }
}
