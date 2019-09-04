/*
ID: xiaoyun4
LANG: JAVA
TASK: nuggets
*/
import java.io.*;
import java.util.*;
public class nuggets{
    public static void main (String [] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("nuggets.in"));



        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));

        int n = Integer.parseInt(f.readLine());

        int[] s = new int[n];

        for(int i = 0; i<s.length;i++){
            s[i]=Integer.parseInt(f.readLine());
        }
        boolean poss = false;
        outerloop:
        for(int i = 0; i< s.length;i++){
            for(int j = i+1; j<s.length;j++){
                if(gcd(s[i],s[j])==1){
                    poss=true;
                    break outerloop;
                }
            }
        }


        if(!poss){
            out.println(0);
        }
        else{
            boolean[] stuff = new boolean[65537];
            int ans = 0;
            stuff[0]=true;
            for(int i = 0; i<stuff.length;i++){
                if(!stuff[i]){
                    ans = i;
                }
                else{
                    for(int num:s){
                        if(num+i<stuff.length){
                            stuff[num+i]=true;
                        }

                    }
                }

            }

            out.println(ans);

        }
        out.close();
    }

    static int gcd(int a, int b){
        if(b==0){
            return a;

        }
        return gcd(b,a%b);
    }
}