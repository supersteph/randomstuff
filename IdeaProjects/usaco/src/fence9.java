
/*
ID: xiaoyun4
LANG: JAVA
TASK: fence9
*/


import java.io.*;
import java.util.*;
import java.util.jar.Pack200;
//import java.lang.reflect.Array;
//import java.util.*;

public class fence9{

    static int gcd(int a, int b)
    {
        while(a!=0 && b!=0) // until either one of them is 0
        {
            int c = b;
            b = a%b;
            a = c;
        }
        return a+b; // either one is 0, so return the non-zero value
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new FileReader("fence9.in"));
        PrintWriter out = new PrintWriter(new FileWriter("fence9.out"));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int lol = gcd(b,a);

        //System.out.println(gcd(4,8));
        int count = ((a-1)*(b-1)-lol+1)/2;

        System.out.println(count);

        if(a==c){
        }
        else if(c>a) {
            int h = gcd(b,c-a);
            int othertrig = ((b - 1) * ((c - a) - 1)-h+1)/2;
            count += othertrig;

            count+=b-1;
        }
        else{
            int h = gcd(b,a-c);
            int othertrig = ((b-1)*((a-c)-1) + h - 1)/2;
            System.out.println(b+" "+ (a-c));
            System.out.println(othertrig);
            count-=othertrig;

        }


        System.out.println(count);

        out.println(count);
        in.close();
        out.close();

        System.exit(0);


    }
}