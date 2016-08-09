/*
 ID: xiaoyun4
 PROB: game1
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class game1 {
    private static class pair{
        int a;
        int b;
        pair(int one, int two){
            a = one;
            b = two;
        }
    }
    public static void good(boolean fir, int a, int b, pair[][] map, int[] s){
        //System.out.println(a+" "+ b);
        if(map[a][b]!=null){
            return;
        }
        if(b-a==1){
            System.out.println(a+" "+b);
            if(fir) {
                pair k= new pair(s[a],0);

                map[a][b]= k;
            }
            else{
                pair k= new pair(0,s[a]);

                map[a][b]= k;
            }
        }
        else{
            if(fir){
                good(!fir, a+1, b, map, s);
                good(!fir, a, b-1, map,s);
                //System.out.println(a+ " "+ b);
                pair ha = new pair(Math.max(map[a+1][b].a+s[a],map[a][b-1].a+s[b-1]),Math.min(map[a+1][b].b,map[a][b-1].b));
                map[a][b] = ha;

            }else{

                good(!fir, a+1, b, map, s);
                good(!fir, a, b-1, map,s);

                pair ha = new pair(Math.min(map[a+1][b].a,map[a][b-1].a),Math.max(map[a+1][b].b+s[a],map[a][b-1].b+s[b-1]));
                map[a][b] = ha;

            }
        }

    }


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("game1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
        int n = Integer.parseInt(in.readLine());
        int[] string = new int[n];

        String k = in.readLine();
        int i = 0;
        while (k!=null){
            StringTokenizer st = new StringTokenizer(k);
            while (st.hasMoreTokens()){
                string[i]=Integer.parseInt(st.nextToken());
                i++;
            }
            k=in.readLine();
        }

        pair[][] dp = new pair[n+1][n+1];

        good(true,0,n,dp,string);

        out.println(dp[0][n].a+" "+dp[0][n].b);
        out.close();
        System.exit(0);
    }



}