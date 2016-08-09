
/*
ID: xiaoyun4
LANG: JAVA
TASK: race3
*/


import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.jar.Pack200;

//import java.lang.reflect.Array;
//import java.util.*;

public class race3 {

    public static void mustgo(int cur, ArrayList<ArrayList<Integer>> ref, boolean[] beenhere, HashSet<Integer>[] tofill, ArrayList<Integer>[] b) {

        beenhere[cur] = true;
        ArrayList<Integer> af=null;
        tofill[cur] = new HashSet<Integer>();

        for (int i : ref.get(cur)) {

            if (i == cur) {
                continue;
            }


            tofill[cur].add(i);

            if (i == beenhere.length - 1) {

                af=new ArrayList<Integer>();

                continue;

            } else if (b[i] != null) {
                tofill[cur].addAll(tofill[i]);

                if (af ==null) {
                    af=(ArrayList<Integer>) b[i].clone();
                } else {
                    for (int j = 0; j < af.size(); j++) {
                        if (!b[i].contains(af.get(j))) {
                            af.remove(j);
                            j--;
                        }
                    }
                }
            } else if (!beenhere[i]) {
                mustgo(i, ref, beenhere, tofill, b);
                if (b[i] == null) {
                    continue;
                }
                tofill[cur].addAll(tofill[i]);

                if (af ==null) {
                    af= (ArrayList<Integer>) b[i].clone();
                } else {
                    for (int j = 0; j < af.size(); j++) {
                        if (!b[i].contains(af.get(j))) {

                            af.remove(j);
                            j--;
                        }
                    }
                }

            }
            if(cur==6){
                //System.out.println(af);
            }

        }
        beenhere[cur] = false;
        if (af != null) {
            af.add(cur);
            b[cur]=af;
        }

    }
    //System.out.println(cur+" has been not set to null");
    //beenhere[cur]=false;



    public static boolean gothrough(HashSet<Integer> s, ArrayList<ArrayList<Integer>> ref, int cur){


        if(s.size()==0){
            return false;
        }
        if(s.contains(0)){
            return false;
        }



        for(int i = 0; i<ref.size();i++){
            if(!s.contains(i)&&i!=cur){
                for(int j : ref.get(i)){

                    if(s.contains(j)&&j!=cur){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("race3.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("race3.out")));

        String s = f.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int index = 0;
        ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
        while(!s.equals("-1")){
            int k = Integer.parseInt(st.nextToken());
            map.add(new ArrayList<Integer>());
            while(k!=-2){
                map.get(index).add(k);
                k = Integer.parseInt(st.nextToken());
            }
            s = f.readLine();
            st = new StringTokenizer(s);
            index++;
        }


        //System.out.println("MUSTGO START");

        boolean[] ha = new boolean[index];
        HashSet<Integer>[] my = new HashSet[index];
        ArrayList<Integer>[] m = new ArrayList[index];
        mustgo(0,map,ha,my,m);
        for(ArrayList<Integer> kaka:m){
            System.out.println(kaka);
        }
        ArrayList<Integer> hope = m[0];


        if(hope==null){
            out.println(0);
        }else {

            out.print(hope.size() - 1);
            for (int i = hope.size()-2; i >= 0; i--) {
                out.print(" " + hope.get(i));
            }
            out.println();
        }

        ArrayList<Integer> otherhope = new ArrayList<Integer>();
        for(int i = 1; i<map.size()-1;i++){
            if(gothrough(my[i],map,i)){
                //System.out.println("yo");
                otherhope.add(i);
            }

        }

        if(otherhope.size()==0){
            out.println(0);
        }
        else{
            out.print(otherhope.size()+" ");
            for(int i = 0; i<otherhope.size()-1;i++){
                out.print(otherhope.get(i)+" ");
            }
            out.println(otherhope.get(otherhope.size()-1));
        }



        out.close();
        System.exit(0);


    }
}