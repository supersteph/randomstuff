/*
ID: xiaoyun4
LANG: JAVA
TASK: sort3
*/


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class zerosum {
    static int[] addone(int[] stuff, int total){
        //System.out.println(stuff.length+" " + total);
        for(int i = 0; i<=0;i--){
            //print(stuff);
            if(stuff[i]<2){
                stuff[i]++;
                break;
            }
            else{
                stuff[i]=0;
                //stuff[i-1]++;
            }
        }
        return stuff;
    }

    static void print(int[] s){
        for(int i:s){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    static boolean work(int[] k){
        int sum = 1;
        //int store =0;
        for(int i = k.length-1; i>0;i--){


            if(k[i]==0){
                int store=0;

                if(k[i]==0){
                    sum-=i+1;
                    store = i+1;
                }
                else if(k[i]==1){
                    sum+=i+1;
                    store = -i-1;
                }

                while(i>0&&k[i]==2){
                    store*=10;
                    store+=i+2;
                    i--;
                }
                print(k);
                System.out.println(i+ " "+store);
                i--;
                sum+=store;

            }
            else if(k[i]==1){
                sum+=i+2;
            }
            else if(k[i]==2){
                sum-=i+2;
            }

        }
        if(sum==0){
            print(k);
            return true;
        }
        return false;
    }
    static ArrayList<int[]> getallgood(int k){
        ArrayList<int[]> all = new ArrayList<int[]>();
        int[] current = new int[k-1];
        for(int i = 0; i<Math.pow(3.0,k-1);i++){

            current = addone(current,k);
            if(work(current)){
                all.add(current);
            }

        }
        return all;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));

        int k = Integer.parseInt(f.readLine());
        ArrayList<int[]> toprint = getallgood(k);
        System.out.println(toprint.size());

        for(int[] x: toprint){
            out.print(1);
            for(int i = 1; i<k;i++){
                if(x[i-1]==0){
                    out.print(" ");
                }else if(x[i-1]==1){
                    out.print("+");
                } else if(x[i-1]==2){
                    out.print("-");
                }
                out.print(i+1);
            }
            out.println();
        }

        System.out.println();

        for (int[]x:toprint){
            print(x);
        }





        out.close();
        System.exit(0);


    }
}
