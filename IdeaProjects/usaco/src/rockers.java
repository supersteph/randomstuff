/*
ID: xiaoyun4
LANG: JAVA
TASK: rockers
*/
import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
    int x,y,T;
    pair(int a,int b,int t){
        x = a;
        y = b;
        T = t;
    }
    pair(pair tmp){
        this.x = tmp.x;
        this.y = tmp.y;
        this.T = tmp.T;
    }
    public void add(int w){
        if(y+w<=T){
            y = y+w;
        }else{
            x++;
            y = w;
        }
    }
    @Override
    public int compareTo(pair t) {
        return (this.x*this.T+this.y) - (t.x*t.T+t.y);
    }
    public void print(){
        System.out.print(x+"|"+y);
    }
}

public class rockers {
    static int N,T,M; //N: songs T: size of disk M: num of disk
    static int[] weight;
    static pair[][]table;
    static int work(){
        int max = 0;
        for(int i=0;i<=N;i++){
            for(int j=0;j<=N;j++) {
                if(i==0||j==0){
                    table[i][j] = new pair(0,0,T);
                }else{
                    table[i][j] = new pair(T,T-1,T);
                }
            }
        }
        table[1][1] = new pair(weight[1]/T,weight[1]%T,T);
        for(int i=1;i<=N;i++){
            for(int j=2;j<=N;j++){
                int w = weight[j];
                pair p1 = new pair(table[i-1][j-1]);
                p1.add(w);
                pair p2 = new pair(table[i][j-1]);
                pair tmp = new pair(p1.compareTo(p2)<0?p1:p2);
                table[i][j] = new pair(table[i][j].compareTo(tmp)<0?table[i][j]:tmp);
                if(table[i][j].x*T+table[i][j].y<=T*M){
                    max = Math.max(max,i);
                }
            }
        }
//        for(int i=0;i<=N;i++){
//            for(int j=0;j<=N;j++){
//                table[i][j].print();
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
        return max;
    }
    public static void main (String [] args) throws Exception {
        Scanner cin = new Scanner(new FileReader("rockers.in"));
        N = cin.nextInt();
        T = cin.nextInt();
        M = cin.nextInt();
        weight = new int[N+1];
        table = new pair[N+1][N+1];
        for(int i=1;i<=N;i++){
            weight[i] = cin.nextInt();
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
        int val = work();
        System.out.println(val);
        out.println(val);
        out.close();
    }
}