/*
 ID: xiaoyun4
 PROB: msquare
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class msquare {

    public static void main(String[] args) throws Exception {
        msquare main = new msquare();
        main.run();
        System.exit(0);
    }

    private void run() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("msquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                "msquare.out")));
        String[] strs = in.readLine().split("\\s");
        in.close();
        // int[] src = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int src = 12345678;
        int dst = 0;
        for (int i = 0; i < 8; i++)
            dst = dst * 10 + Integer.parseInt(strs[i]);

        Queue<State> queue = new LinkedList<State>();
        HashSet<Integer> set = new HashSet<Integer>();
        State state = new State(src, "");

        queue.add(state);
        set.add(state.number);
        // System.out.println(state.transformA().number);
        // System.out.println(state.transformC().number);
        while (queue.isEmpty() == false) {
            state = queue.poll();
            if (state.number == dst) {
                String tmp = state.ops;
                out.write(String.format("%d\n%s\n", tmp.length(), tmp));
                out.close();
                return;
            }
            State stateA = null;

            stateA = state.transformA();
            if (set.contains(stateA.number) == false) {
                queue.add(stateA);
                set.add(stateA.number);
            }

            stateA = state.transformB();
            if (set.contains(stateA.number) == false) {
                queue.add(stateA);
                set.add(stateA.number);
            }

            stateA = state.transformC();
            if (set.contains(stateA.number) == false) {
                queue.add(stateA);
                set.add(stateA.number);
            }
        }
        out.close();
    }
    private class State implements Comparable<State> {
        public int number;
        public String ops;

        public State(int a, String prev) {
            this.number = a;
            this.ops = prev;
        }

        public State transformA() {
            int ret = 0;
            int cp = number;
            for (; cp > 0;) {
                int a = cp % 10;
                cp /= 10;
                ret = ret * 10 + a;
            }

            String prev = this.ops + "A";
            State A = new State(ret, prev);
            return A;
        }

        public State transformB() {
            int n = (number / 100000 * 10000)
                    + (((number / 10000) % 10) * 10000000) + ((number % 1000) * 10)
                    + ((number / 1000) % 10);
            String prev = this.ops + "B";
            State B = new State(n, prev);
            return B;
        }

        public State transformC() {
            int result = 0;
            result = number - number % 10000000;
            result += (number % 100 - number % 10) * 100000;
            result += (number % 10000000 - number % 1000000) / 10;
            result += number % 100000 - number % 1000;
            result += (number % 1000000 - number % 100000) / 1000;
            result += (number % 1000 - number % 100) / 10;
            result += number % 10;
            String prev = this.ops + "C";
            State C = new State(result, prev);
            return C;
        }

        @Override
        public int compareTo(State o) {
            return this.number - o.number;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            State other = (State) obj;
            if (this.number == other.number)
                return true;
            return false;
        }

    }
}

