package C358.intermediate;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Adrian
 * @since 29 Apr 2018
 */
public class Intermediate {

    public static void main(String[] args) throws Exception {
        ArrayList<String> lines = new ArrayList();
        try (BufferedReader reader = Files.newBufferedReader(new File("src/C358/intermediate/NCAA.txt").toPath())) {
            reader.lines().forEach(lines::add);
        }
        CopyOnWriteArrayList<Match> matches = new CopyOnWriteArrayList();
        for (String str : lines) {
            matches.add(new Match(str.substring(12, 36), str.substring(41, 65)));
        }

        CopyOnWriteArrayList<String> transitiveWinners = new CopyOnWriteArrayList();

        for (Match match : matches) {
            /**
             * Apparently the output is supposed to be 1185. If I add code below will give output with Villanova included thus 1185.
             */
//            if (match.getWinner().contains(champion)) {
//                matches.remove(match);
//            }
            if (match.getLoser().contains("Villanova") && !transitiveWinners.contains(match.getWinner())) {
                transitiveWinners.add(match.getWinner());
                matches.remove(match);
            }
        }
        int count = 0;
        while (true) {
            for (Match match : matches) {
                for (String str : transitiveWinners) {
                    if (match.getLoser().equals(str) && !transitiveWinners.contains(match.getWinner())) {
                        transitiveWinners.add(match.getWinner());
                        matches.remove(match);
                    }
                }
            }
            if (count == matches.size()) {
                break;
            }
            count = matches.size();
        }
        transitiveWinners.forEach(System.out::println);
    }

    static class Match {

        String winner;
        String loser;

        public Match(String winningTeam, String losingTeam) {
            this.winner = winningTeam;
            this.loser = losingTeam;
        }

        public String getWinner() {
            return winner;
        }

        public String getLoser() {
            return loser;
        }
    }
}
