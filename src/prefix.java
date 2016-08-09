import java.io.*;
import java.lang.Integer;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Exchanger;
/*
ID: xiaoyun4
LANG: JAVA
TASK: prefix
*/

public class prefix {

    public static boolean equals(String c, String p, int idx) {
        //go through all the chars, and if any of the chars aren't equal then you return false
        for (int i = 0; i < p.length(); ++i) {
            if (c.charAt(idx + i) != p.charAt(i)) return false;
        }
        return true;
    }

    static Comparator<String> lengthComp = new Comparator<String>() {
        public int compare(String s1, String s2) {
            return s2.length() - s1.length();
        }
    };


    public static int getPrefix(ArrayList<String> comp, String compare, int idx) {
        //System.out.println();

        boolean[] x = new boolean[compare.length() + 1];
        x[0] = true;
        // new boolean of all falses
        int max = 0;
        for (int i = 0; i < x.length; i++) {

            //print(x);
            // goes through all the integers in the array
            if (i > max) {
                // if you are past the max, then you know you went too far so you return max
                return max;
            }
            if (x[i] != true) {
                continue;
            }

            // other wise you go through all the primitives
            for (int j = idx; j < comp.size(); j++) {
                String p = comp.get(j);
                if (i + p.length() > compare.length()) {
                    continue;
                }

                //if you already explored this one, then you continue
                if (x[i + p.length()] == true) {
                    continue;
                }

                // if the primitive is equal to the current substring starting at index then you set x[i+p.length] as explored
                if (equals(compare, p, i)) {
                    x[i + p.length()] = true;
                    //if your past max, you make max bigger
                    if (i + p.length() > max) {
                        max = i + p.length();
                    }
                }
            }


        }
        //return the max

        return max;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("prefix.in"));

        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
        ArrayList<String> prim = new ArrayList<String>();
        String line = f.readLine();

        while (!line.equals(".")) {
            //System.out.println(line);
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                prim.add(st.nextToken());
            }
            line = f.readLine();
        }

        StringBuilder sb = new StringBuilder();
        String nextLine = f.readLine();
        while (nextLine != null) {
            sb.append(nextLine);
            nextLine = f.readLine();
        }
        String compare = sb.toString();

        Collections.sort(prim, lengthComp);
        ArrayList<String> nprim = new ArrayList<String>();


        for (int i = 0; i < prim.size() - 1; i++) {
            String m = prim.get(i);
            if (getPrefix(prim, m, i + 1) != m.length()) {
                nprim.add(m);
            }
        }

        nprim.add(prim.get(prim.size() - 1));
        System.out.println(nprim);

        int count = getPrefix(nprim, compare, 0);
        out.println(count);
        out.close();
        System.exit(0);
    }
}