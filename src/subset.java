import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Exchanger;
/*
ID: xiaoyun4
LANG: JAVA
TASK: subset
*/

public class subset {
    public static int count = 0;
    public static int max = 0;

    public static int getmaxdiff(){
        int sum = 0;
        for(int k = 1; k<=max;k++){
            sum+=k;
        }
        return sum;
    }

    public static int NumOfMatch(int diff, int idx){
        if(idx==0){
            if(diff==0){
                return 1;
            }
            else {
                return 0;
            }
        }

        int frt = NumOfMatch(diff+idx+1,idx-1);
        int bck = NumOfMatch(diff-idx-1,idx-1);
        return frt+bck;
    }


    public static boolean isequal(int[] first, int[] second) {
        int firstsum = 0;
        int secondsum = 0;
        for (int i = 0; i < first.length; i++) {
            firstsum += first[i];
            secondsum += second[i];
        }
        if (firstsum == secondsum) {
            return true;
        }
        return false;
    }
    public static int sum(int[]x){
        int i = 0;
        for(int j:x){
            i+=j;
        }
        return i;
    }
    public static int sum(int i, int j){
        int sum = 0;
        for(int k = i; k<=j;k++){
            sum += k;
        }
        return sum;

    }

    static int[][] cache = null;
    static int base = 0;
    public static int getIt(int index, int diff){

        //System.out.println(diff+ " " + index);
        if(index == 0){
            if(diff==0){
                return 1;
            }else{
                return 0;
            }
        }

        if (cache[index-1][base + diff+index] == -1) {
            cache[index-1][base + diff+index] = getIt(index - 1, diff + index);
        }
        if (cache[index-1][base + diff-index] == -1) {
            cache[index-1][base + diff-index] = getIt(index - 1, diff - index);
        }

        return cache[index-1][base+ diff+index] + cache[index-1][base + diff-index];



    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("subset.in"));

        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        int k = Integer.parseInt(f.readLine());

        int maxDiff = k*(k + 1);
        cache = new int[k+1][maxDiff+1];
        base = maxDiff/2;
        for (int i = 0; i <= k; ++i) {
            for (int j = 0; j <= maxDiff; ++j) {
                cache[i][j] = -1;
             }
        }
        //allofthem(7);

        //System.out.println(toOppo("010"));

        //out.println(allofthem(k));
        max = k;


        //getIt(2,1,sum-1, -1);

        //out.println(count);
        int tot = getIt(k-1,k);
        //for(int[]ls:x){
            //tot+=ls[2]*ls[3];
        //}
        out.println(tot);

        out.close();
        System.exit(0);


    }
}