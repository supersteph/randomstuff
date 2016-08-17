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

        int index = 1;
        while (configs.size()!=0){
            for(int i = 0; i<configs.size();i++){
                if(configs.get(i).index2-configs.get(i).index1==index+1) {
                    configs.remove(i);
                    i--;
                    continue;
                }
                if(configs.get(i).index1>=k.length-1||configs.get(i).index2>=k.length-1) {
                    configs.remove(i);
                    i--;
                }else if(k[configs.get(i).index1+1]==k[configs.get(i).index2+1]){
                    configs.get(i).index1 ++;
                    configs.get(i).index2 ++;
                }else {
                    configs.remove(i);
                    i--;
                }
            }
            index++;
        }
        return index;


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