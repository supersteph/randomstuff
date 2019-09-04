
/*
ID: xiaoyun4
LANG: JAVA
TASK: buylow
*/


import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.jar.Pack200;

//import java.lang.reflect.Array;
//import java.util.*;

public class buylow{

    static int maxlen = 1;

    public static void dostuff( int cur, int[] toput, int[] ref, BigInteger[] counts){
        if(toput[cur]!=0){
            return;
        }


        int len = 1;

        counts[cur]=BigInteger.ONE;
        HashSet<Integer> mama = new HashSet<Integer>();

        for(int i = cur+1;i<toput.length;i++){
            if(ref[i]<ref[cur]&&!mama.contains(ref[i])) {

                mama.add(ref[i]);
                //System.out.println("yooooo");
                dostuff(i, toput, ref, counts);
                int k = toput[i];


                if (k + 1 > len) {

                    len = k+1;
                    counts[cur]=counts[i];
                }
                else if(k+1==len){
                    counts[cur]=counts[cur].add(counts[i]);

                }

            }
        }

        toput[cur] = len;

    }
    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("buylow.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));

        int k = Integer.parseInt(f.readLine());

        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] s = new int[k];
        int ind =0;
        while(st!=null){
            while (st.hasMoreTokens()){
                s[ind]=Integer.parseInt(st.nextToken());
                ind++;
            }
            try {
                st = new StringTokenizer(f.readLine());
            }
            catch (NullPointerException e){
                break;
            }

        }




        int[] lol = new int[k];

        BigInteger[] counts = new BigInteger[k];
        int maxlen = 0;
        BigInteger count = BigInteger.ZERO;
        HashSet<Integer> lala = new HashSet<Integer>();
        for(int i = 0; i<lol.length;i++) {


            if(lala.contains(s[i])){
                continue;
            }
            lala.add(s[i]);

            dostuff(i, lol, s, counts);


            if (maxlen < lol[i]) {

                maxlen = lol[i];

                count=counts[i];



            } else if (maxlen == lol[i]) {
                //System.out.println(count+" "+ counts[i]);
                count = count.add(counts[i]);
                //System.out.println(count);


            }
        }
        out.println(maxlen+" "+count);




        out.close();
        System.exit(0);


    }
}