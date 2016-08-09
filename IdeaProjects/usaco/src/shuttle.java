
/*
ID: xiaoyun4
LANG: JAVA
TASK: shuttle
*/


import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.jar.Pack200;

//import java.lang.reflect.Array;
//import java.util.*;

public class shuttle {



    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("shuttle.in"));
        int num = Integer.parseInt(f.readLine());
        int cur = 0;
        int s;
        int t = -1;
        int[] ans = new int[500];

        for(int i =2; i<=num+1;i++){
            if(i%2==1){
                t=-2;
                s=num+i;
            }else{
                t=2;
                s = num-i+2;

            }

            ans[cur++]=s;
            for (int j = 2;j<=i;j++){
                s+=t;
                ans[cur++]=s;
            }

        }

        for(int i=num;i>=2;i--)
        {
            if(i%2==1)
            {
                t=-2;
                s=num+i;
            }
            else
            {
                t=2;
                s=num-i+2;
            }
            ans[cur++]=s;
            for(int j=2;j<=i;j++)
            {
                s+=t;
                ans[cur++]=s;
            }
        }

        ans[cur++]=num+1;


        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));

        for(int i =0;i<cur;i++){
            out.print(ans[i]);
            if(i%20==19||i==cur-1){
                out.println();
            }else {
                out.print(" ");
            }
        }
        out.close();
        System.exit(0);


    }
}