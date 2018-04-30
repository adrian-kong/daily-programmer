package C4.intermediate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Scanner;

/**
 * @author Adrian
 * @since 30 Apr 18
 */
public class Intermediate {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        /**
         * Taken from https://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
         * Because manually typing it up is too much work.
         */
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        while (scanner.hasNext()) {
            String inp = scanner.nextLine().replaceAll(" ", "");
            if (inp.contains("(") && inp.contains(")")) {
                if (inp.indexOf('(') != 0) {
                    inp = inp.substring(0, inp.indexOf('(')) + "*" + inp.substring(inp.indexOf('('), inp.length());
                }
            }

            System.out.println(engine.eval(inp));
        }
    }
}
