import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Exchanger;
/*
ID: xiaoyun4
LANG: JAVA
TASK: preface
*/

/**
 * Created by tooawesome on 4/4/15.
 */
public class preface {

    static String[] rkey = {"I","II","III","IV", "V","VI","VII","VIII", "IX","X","XX","XXX","XL","L","LX","LXX","LXXX", "XC","C","CC","CCC","CD", "D","DC","DCC","DCCC","CM","M","MM","MMM"};

    public static String toRomanNumeral(int k){
        String ks = String.valueOf(k);
        String toput = "";
        for(int i = ks.length()-1; i>=0;i--){
            if((Integer.parseInt(ks.substring(i,i+1)))==0){
                continue;
            }
            System.out.println(ks.length()-i-1);
            toput+=rkey[((Integer.parseInt(ks.substring(i,i+1)))+(9*(ks.length()-i-1)))-1];

        }
        return toput;
    }
    public static int[] getCount(int end){
        int[] all = new int[7];
        for(int i = 0; i<=end;i++){
            String stuff = toRomanNumeral(i);
            for(int j = 0; j<stuff.length();j++){
                char it = stuff.charAt(j);
                if(it=='I'){
                    all[0]++;
                } else if( it=='V'){
                    all[1]++;
                }else if(it=='X'){
                    all[2]++;
                } else if(it == 'L'){
                    all[3]++;
                } else if(it == 'C'){
                    all[4]++;
                } else if(it == 'D'){
                    all[5]++;
                } else if(it == 'M'){
                    all[6]++;
                }


            }
        }
        return all;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("preface.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
        int k = Integer.parseInt(f.readLine());
        int[] all = getCount(k);
        for(int i = 0; i<all.length;i++){
            if(all[i]==0){
                continue;
            }
            if(i==0){
                out.println('I'+ " " +  all[i]);

            }
            else if(i==1){
                out.println('V'+ " " +  all[i]);

            }
            else if(i==2){
                out.println('X'+ " " +  all[i]);

            }
            else if(i==3){
                out.println('L'+ " " +  all[i]);

            }
            else if(i==4){
                out.println('C'+ " " +  all[i]);

            }
            else if(i==5){
                out.println('D'+ " " +  all[i]);

            }
            else if(i==6){
                out.println('M'+ " " +  all[i]);

            }


        }



        out.close();
        System.exit(0);



    }
}
