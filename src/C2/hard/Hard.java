package C2.hard;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Adrian
 * @since 30 Apr 18
 */
public class Hard {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        long ms = 0;
        long time = 0;
        ArrayList<Long> laps = new ArrayList();
        while (scanner.hasNext()) {
            String inp = scanner.nextLine();
            if (inp.equals("start") && ms == 0) {
                ms = System.currentTimeMillis();
                laps.add(0L);
            }
            if (inp.equals("lap") || inp.equals("stop") && ms != 0) {
                laps.add(System.currentTimeMillis() - ms);
                if (inp.equals("stop")) {
                    break;
                }
            }
        }
        ArrayList<String> write = new ArrayList();
        for (Long l : laps) {
            long hr = TimeUnit.MILLISECONDS.toHours(l);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(l);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(l);
            write.add(hr + "hrs:" + minutes + "mins:" + seconds + "secs");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(new File("src/C2/hard/stopwatch.txt").toPath())) {
            for (String str : write) {
                writer.write(str);
                writer.newLine();
            }
        }
    }
}
