
/*
ID: xiaoyun4
LANG: JAVA
TASK: spin
*/


import java.io.*;
import java.util.*;
import java.util.jar.Pack200;
//import java.lang.reflect.Array;
//import java.util.*;

public class spin{
    public static int getang(int s){
        return s%360;
    }

    public static void dostuff(BitSet toshift, int shiftamount){

        BitSet k = (BitSet) toshift.clone();
        for(int i = 0; i<360;i++){
            toshift.set((i+shiftamount)%360,k.get(i));
        }




    }
    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("spin.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));

        ArrayList<BitSet> wheels = new ArrayList<BitSet>();
        ArrayList<Integer> speed = new ArrayList<Integer>();
        for(int i = 0; i<5;i++) {
            StringTokenizer sb = new StringTokenizer(f.readLine());

            speed.add(Integer.parseInt(sb.nextToken())%360);
            int p = Integer.parseInt(sb.nextToken());
            BitSet bankai = new BitSet(360);
            for(int j=0;j<p;j++){
                int lol = Integer.parseInt(sb.nextToken())%360;
                int len =Integer.parseInt(sb.nextToken())%360;
                //System.out.println(lol+" "+len);

                if(lol+len<360) {

                    bankai.set(lol, lol + len+1);
                }
                else {
                    bankai.set(lol, 360);
                    bankai.set(0, len + lol - 360+1);
                }


            }
            //System.out.println(bankai);
            wheels.add(bankai);

        }





        int index = 0;
        for (int i = 0; i<700;i++){
            if(i==9){
                System.out.println(wheels.get(0));
                System.out.println(wheels.get(1));
                System.out.println(wheels.get(2));
                System.out.println(wheels.get(3));
                System.out.println(wheels.get(4));
            }
            BitSet k = (BitSet)wheels.get(0).clone();

            k.and(wheels.get(1));

            k.and(wheels.get(2));

            k.and(wheels.get(3));

            k.and(wheels.get(4));


//            System.out.println(k);

            if(k.nextSetBit(0)!=-1){
                System.out.println(index);
                out.println(index);
                out.close();
                System.exit(0);
                return;
            }
            index++;


            for(int j = 0; j<5;j++){
                dostuff(wheels.get(j),speed.get(j));
            }

        }
        out.println("none");

        out.close();
        System.exit(0);


    }
}