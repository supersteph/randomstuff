/*
ID: xiaoyun4
LANG: JAVA
TASK: sort3
*/

/**
 * How to solve it:
 *I start at the first number, and I check to see if there are any numbers that less than it, if not, then i switch to
 * the second number, and if in all the numbers, there is nothing less than it than i am good
 *
 */


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class sort3 {

    static class Ary {
        public int[] payloads;
        public int[] counts;

        public Ary(int[] p) {
            payloads = p;
            counts = getBorders(p);
            //print(counts);
        }

        public int GetIndexOfExpectedValueFromDomain(int domain, int value) {
            for (int idx = counts[domain-1]; idx < counts[domain]; ++idx) {
                if (payloads[idx] == value) return idx;
            }
            return -1;
        }

        public int GetIndexOfMisfitFromDomain(int domain) {
            for (int idx = counts[domain-1]; idx < counts[domain]; ++idx) {
                if (payloads[idx] != domain) return idx;
            }
            return -1;
        }

        public int domainForIndex(int idx){
            for(int i = 0; i<3;i++){
                if(counts[i] <= idx && counts[i+1] > idx){
                    return i+1;
                }
            }
            return -1;
        }
    }


    public static void print(int[]x){
        for(int j:x){
            System.out.print(j+ " ");
        }
        System.out.println();
    }
    public static int[] getBorders(int[] x){
        int[] count = new int[4];
        for(int y:x){
            count[y]+=1;
        }
        count[2]+=count[1];
        count[3]+=count[2];
        return count;
    }



    public static int sort(int[] x){
        int sum  = 0;
        Ary ary = new Ary(x);

        int[] borders = getBorders(x);
        for (int cur = 0; cur !=x.length; ++cur){
            //print(ary.payloads);
            if(ary.domainForIndex(cur) == ary.payloads[cur]) {
                continue;
            }

            // now we have a misfit.
            // first test whether there is direct swap.
            int idx = ary.GetIndexOfExpectedValueFromDomain(ary.payloads[cur], ary.domainForIndex(cur));
            if (idx > 0) {
                int tmp = ary.payloads[cur];
                ary.payloads[cur] = ary.payloads[idx];
                ary.payloads[idx] = tmp;
                sum += 1;
            } else {
                int msftidx = ary.GetIndexOfMisfitFromDomain(ary.payloads[cur]);
                int idx2 = ary.GetIndexOfExpectedValueFromDomain(ary.payloads[msftidx], ary.domainForIndex(cur));
                int tmp = ary.payloads[idx2];
                ary.payloads[idx2] = ary.payloads[msftidx];
                ary.payloads[msftidx] = ary.payloads[cur];
                ary.payloads[cur] = tmp;
                sum += 2;
            }

        }

        return sum;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

        int k = Integer.parseInt(f.readLine());
        int[] m = new int[k];

        for(int i = 0; i < k;i++){
            m[i] = Integer.parseInt(f.readLine());

        }

        Ary ary = new Ary(m);
        //System.out.println(ary.domainForIndex(0));
        //System.out.println(ary.domainForIndex(1));
        //System.out.println(ary.domainForIndex(2));

        int l = sort(m);
        out.println(l);



        out.close();
        System.exit(0);


    }
}
