package C1.intermediate;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Adrian
 * @since 30 Apr 18
 */
public class Intermediate {

    /**
     * poorly coded. assumes the input is correct.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CopyOnWriteArrayList<Event> e = new CopyOnWriteArrayList();

        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.startsWith("add") && command.contains(" ") && command.split(" ").length >= 3) {
                String data = "";
                for (int i = 2; i < command.split(" ").length; i++) {
                    data += command.split(" ")[i] + " ";
                }
                e.add(new Event(Integer.parseInt(command.split(" ")[1]), data));
                e.sort(Comparator.comparingInt(Event::getTime));
            } else if (command.equals("list")) {
                e.forEach(event -> System.out.println(event.time + ":" + event.getEvents()));
            } else if (command.startsWith("remove") && command.contains(" ") && command.split(" ").length >= 2) {
                String data = "";
                for (int i = 1; i < command.split(" ").length; i++) {
                    data += command.split(" ")[i] + " ";
                }
                Event ev = find(e, data);
                if (ev != null) {
                    e.remove(ev);
                    System.out.println("removed " + ev.events);
                } else {
                    System.out.println("error removing " + data);
                }
            }
        }
    }

    static Event find(List<Event> list, String event) {
        for (Event e : list) {
            if (e.equals(event)) {
                return e;
            }
        }
        return null;
    }

    static class Event {

        private int time;
        private String events;

        public Event(int time, String events) {
            this.events = events;
            this.time = time;
        }

        public int getTime() {
            return time;
        }

        public String getEvents() {
            return events;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof String ? obj.equals(events) : super.equals(obj);
        }
    }
}
