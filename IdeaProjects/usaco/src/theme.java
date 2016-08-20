/*
ID: xiaoyun4
LANG: JAVA
TASK: theme
*/
//import com.sun.org.apache.xerces.internal.util.SymbolHash;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class theme
{
    static class config{
        int index1,index2;
        config(int a, int b){
            index1 = a;
            index2 = b;
        }

        @Override
        public String toString() {
          return index1+" "+index2;
        }
    }
    static void dp(config b, int[][]tofill, int[] k, int index){
        if(tofill[b.index1][b.index2] !=0){
            return;
        }


        if(b.index2-b.index1<index+1) {

            tofill[b.index1][b.index2] = 2;
            return;
        }
        else if(b.index1>=k.length-1||b.index2>=k.length-1) {

            tofill[b.index1][b.index2] = 2;
        }else if(k[b.index1+1]==k[b.index2+1]){

            b.index1 ++;
            b.index2 ++;
            dp(b,tofill,k,index+1);

            tofill[b.index1-1][b.index2-1]=tofill[b.index1][b.index2]+1;
            b.index1--;
            b.index2--;


        }else {
            tofill[b.index1][b.index2] = 2;
        }


    }
    //given differences
    static int dostuff(int[] k){
        int  lmao=0;

        ArrayList<config> configs = new ArrayList<config>();

        for(int i = 1; i<k.length;i++){
            for(int j = i+5;j<k.length;j++){
                if(k[i]==k[j]||i==0){
                    configs.add(new config(i,j));
                }
            }
        }


        if(configs.size()==0){
            return 0;
        }
        int[][] tofill = new int[k.length][k.length];
        int max = 0;
        for(config config:configs){
            dp(config,tofill,k,2);
            if(tofill[config.index1][config.index2]>max){
                max = tofill[config.index1][config.index2];
            }
        }
        
        return max;


    }

    public static void main (String[] args) throws IOException
    {

        BufferedReader f = new BufferedReader(new FileReader("theme.in"));
        PrintWriter out = new PrintWriter(new FileWriter("theme.out"));
        int n = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] bob = new int[n];
        int prev = Integer.parseInt(st.nextToken());
        for(int i = 1; i<n;i++){
            //System.out.println("yo");
            if((i)%20==0&&i!=1){
                st = new StringTokenizer(f.readLine());
            }
            int k = Integer.parseInt(st.nextToken());
            bob[i]=prev-k;
            prev = k;


        }
        int boo = dostuff(bob);
        if(boo<5){
            out.println(0);
        }else {
            out.println(boo);
        }

        out.close();
        System.exit(0);
    }
}