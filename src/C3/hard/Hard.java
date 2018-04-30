package C3.hard;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Adrian
 * @since 30 Apr 18
 */
public class Hard {

    public static void main(String[] args) throws Exception {
        /**
         * probably should have downloaded the text doc in case it goes down.
         */
        List<String> strings = new ArrayList();
        URLConnection connection = new URL("https://pastebin.com/raw/jSD873gL").openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            reader.lines().forEach(strings::add);
        }

        List<String> descramble = new ArrayList();
        try (BufferedReader reader = Files.newBufferedReader(new File("src/C3/hard/descramble.txt").toPath())) {
            reader.lines().forEach(descramble::add);
        }

        List<String> sort = new ArrayList();
        descramble.forEach(a -> strings.stream().filter(b -> a.length() == b.length()).forEach(b -> {
            char[] d = a.toCharArray();
            char[] e = b.toCharArray();
            Arrays.sort(d);
            Arrays.sort(e);
            if (Arrays.equals(d, e)) {
                sort.add(b);
            }
        }));
        sort.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
    }
}
