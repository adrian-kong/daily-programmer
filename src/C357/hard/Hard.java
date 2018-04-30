package C357.hard;

import java.util.ArrayList;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/8dqzyi/20180420_challenge_357_hard_continued_fractions/
 * <p>
 * In mathematics, a continued fraction is an expression obtained through an iterative process of representing a number as the sum of its integer part and the reciprocal of another number, then writing this other number as the sum of its integer part and another reciprocal, and so on.
 * A continued fraction is an expression of the form
 * Express fractions in Gauss notation
 *
 * @author Adrian
 * @since 29 Apr 18
 */
public class Hard {

    public static void main(String[] args) {
        ArrayList<String> inputs = new ArrayList();
        inputs.add("16\n--\n45");
        inputs.add("[2;1,7]");
        for (String string : inputs) {
            /**
             * TODO: to be finished
             * calculating from gauss notation to improper fraction
             */
            if (string.contains("[")) {
                String newStr = string.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(";", " ").replaceAll(",", " ");
                String[] ints = newStr.split(" ");
                int prev = 0;
                for (int i = ints.length; i > 0; i--) {
                    prev = Integer.parseInt(ints[i]);
                    
                    
                }
            } else {
                int num = Integer.parseInt(string.split("\n")[0]);
                int denom = Integer.parseInt(string.split("\n")[2]);
                String stuff = "[";
                while (num != 1) {
                    int temp = num;
                    num = denom;
                    denom = temp;
                    int add = num / denom;
                    stuff += add + " ";
                    num = num % denom;
                }
                stuff += "]";
                System.out.println(stuff);
            }
        }
    }
}
