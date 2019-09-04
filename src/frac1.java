/*
ID: xiaoyun4
LANG: JAVA
TASK: frac1
*/

/**
 * How to solve it:
 * I have go through everything that the demoninator can be, and I have a class for fraction, so i have all the fractions
 * of that demoninator from 1 to n-1 and then i can put it into a hashset and then sort it
 *
 */


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class frac1{
    public static class fraction implements Comparable<fraction>{
        int n;
        int d;
        fraction(int i, int j){
            n= i;
            d = j;
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj){
                return true;
            }
            if(obj==null){
                return false;
            }
            if(getClass()!=obj.getClass()){
                return false;
            }
            fraction f = (fraction) obj;
            if(n*f.d==f.n*d){
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(fraction frac) {
            if(n*frac.d>frac.n*d){
                return 1;
            }
            return -1;
        }

        @Override
        public int hashCode() {
            int gcd = GCD(n,d,1);
            return  n/d;
        }
        public String getFrac(){
            return n+"/"+d;
        }
    }
    public static int GCD(int i, int j, int curr){
        if(i==0){
            return 1;
        }

        for(int m = 2; m<=i;m++){
            if(i%m==0&&j%m==0){
                curr = GCD(i/m,j/m,curr*m);
            }
        }
        return curr;

    }
    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

        int x = Integer.parseInt(f.readLine());
        HashSet<fraction> all = new HashSet<fraction>();
        for(int i = 1; i<=x;i++){
            for(int j = 0; j<=i;j++){
                all.add(new fraction(j,i));
            }
        }
        fraction test = new fraction(0,2);
        System.out.println(test.equals(new fraction(1,4)));
        ArrayList<fraction> stuff = new ArrayList<fraction>(all);
        Collections.sort(stuff);
        for(int i = 0; i< stuff.size();i++){
            out.println(stuff.get(i).getFrac());
        }
        System.out.println(GCD(3,6,1));

        out.close();
        System.exit(0);


    }
}
