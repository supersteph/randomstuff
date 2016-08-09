import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Exchanger;
/*
ID: xiaoyun4
LANG: JAVA
TASK: hamming
*/

/**
 * Created by tooawesome on 4/4/15.
 */
public class hamming {

    public static String toBoolean(int k, int toit){
        String todo = "";
        for(int i = toit-1; i>=0; i--){
            //System.out.println(k);
            if(k/Math.pow(2.0, (double) i)>=1){
                k=(int)k%(int)Math.pow(2.0, (double) i);
                todo+= "1";
            }
            else {
                todo+="0";
            }
        }
        return todo;
    }
    public static boolean getHamming(String first, String second, int desirednum){
        //System.out.println(first + " "+ second);

        int count = 0;
        for(int i = 0; i<first.length();i++){
            if(first.charAt(i)!=second.charAt(i)){
                count++;
                if(count==desirednum){
                    return true;
                }
            }
        }
        if(count>=desirednum){
            return true;
        }
        return false;
    }

    public static ArrayList<Integer> getAllcert(int n, int b, int d){
        ArrayList<Integer> x = new ArrayList<Integer>();
        outerloop:
        for(int i = 0; i<(int)Math.pow(2.0, (double)b);i++){
            //System.out.println("hi");

            if(x.size()==n){
                return x;
            }
            for(int j = 0; j<x.size();j++){
                //System.out.println(toBoolean(1,7));
                if(!getHamming(toBoolean(i,b), toBoolean(x.get(j),b), d)){
                    //System.out.println(i+ " "+ x.get(j));
                    continue outerloop;
                }
            }
            x.add(i);
        }
        return x;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        //System.out.println(toBoolean(0,7));
        ArrayList<Integer> x = getAllcert(n,b,d);
        for(int i = 0; i<x.size()-1;i++){
            if((i+1)%10==0&&i!=0){
                out.println(x.get(i));
            } else {
                out.print(x.get(i) + " ");
            }
        }
        out.println(x.get(x.size()-1));

        out.close();
        System.exit(0);



    }
}
