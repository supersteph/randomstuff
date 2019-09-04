/*
ID: xiaoyun4
LANG: JAVA
TASK: sprime
*/

/**
 * How to solve it:
 *So i get the first prime number, and then i add the second number to it and check to make sure that it is prime
 * if it is prime then i add it to the end of an arraylist of things to be explored if it isn't then i don't, when
 * i get to the first thing that shows that the entire thing is a super prime then i return
 *
 *
 */


import java.io.*;
import java.util.*;

public class sprime {
    public static boolean isPri(int x){
        for (int i = 3;i<=Math.sqrt(x)+1;i+=2) {
            if (x % i == 0) {

                return false;
            }

        }
        return true;
    }
    public static int getCount(int x){
        int m = 0;
        int k = x;
        while(k!=0){
            m++;
            k/=10;
        }
        return m;
    }
    public static void superpri(ArrayList<Integer> explore, int length){
        int m = length;
        while(getCount(explore.get(0))!=m){
            for(int i = 1;i<=9;i+=2){
                int k = explore.get(0)*10+i;
                if(isPri(k)){
                    explore.add(k);
                }
            }
            explore.remove(0);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
        int n = Integer.parseInt(f.readLine());
        ArrayList<Integer> stuff = new ArrayList<Integer>();
        stuff.add(2);
        stuff.add(3);
        stuff.add(5);
        stuff.add(7);
        superpri(stuff,n);
        for(Integer i: stuff){
            out.println(i);
        }


        out.close();
        System.exit(0);
    }
}
