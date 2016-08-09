/*
 ID: xiaoyun4
 PROB: shopping
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class shopping {

    public static boolean inlim(int[] cd){
        for(int i = 0; i<5;i++){
            if(cd[i]>5){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("shopping.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
        int s = Integer.parseInt(in.readLine());
        int[][] d = new int[s+5][1007];
        for(int i = 0; i<s;i++){
            int n;
            StringTokenizer st = new StringTokenizer(in.readLine());
            n=Integer.parseInt(st.nextToken());
            for(int j = 0; j<n; j++){
                int c = Integer.parseInt(st.nextToken());
                d[i][c+5]=Integer.parseInt(st.nextToken());
            }
            d[i][0]=Integer.parseInt(st.nextToken());

        }
        int b = Integer.parseInt(in.readLine());
        int[] id = new int[5];
        int[] cost = new int[5];
        int[] need = new int[5];
        for(int i = 0; i<b;i++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            id[i]=Integer.parseInt(st.nextToken());
            need[i]=Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<5;i++){
            d[s+i][0]=cost[i];
            d[s+i][i+1] = 1;
        }


        int[][][][][] t = new int[6][6][6][6][6];

        for(int d1 = 0; d1 < 6; d1++)
            for(int d2 = 0; d2 < 6; d2++)
                for(int d3 = 0; d3 < 6; d3++)
                    for(int d4 = 0; d4 < 6; d4++)
                        for(int d5 = 0; d5 < 6; d5++) t[d1][d2][d3][d4][d5] = 1000000;

        t[0][0][0][0][0]=0;

        for(int i = 0; i < s + 5; i++){ //iterates through different deals
            for(int d1 = 0; d1 < 6; d1++){
                for(int d2 = 0; d2 < 6; d2++){
                    for(int d3 = 0; d3 < 6; d3++){
                        for(int d4 = 0; d4 < 6; d4++){
                            for(int d5 = 0; d5 < 6; d5++){ //Picks starting point;
                                if(t[d1][d2][d3][d4][d5] == 1000000) continue;
                                int tu = 1;
                                int [] cds = {d1 + d[i][1], d2 + d[i][2], d3 + d[i][3], d4 + d[i][4], d5 + d[i][5]}; //tu - timesup (How many times we have used this deal to build on the current starting d1-d5 possition
                                while(inlim(cds)){
                                    t[cds[0]][cds[1]][cds[2]][cds[3]][cds[4]] = Math.min(t[cds[0]][cds[1]][cds[2]][cds[3]][cds[4]],
                                            t[d1][d2][d3][d4][d5] + tu * d[i][0]);
                                    for(int j = 0; j < 5; j++) cds[j] += d[i][j + 1];
                                    tu++;
                                }
                            }
                        }
                    }
                }
            }
        }
        int[] oc = {0,0,0,0,0};
        for(int i = 0; i<b;i++){
            oc[i]= need[i];
        }
        out.println(t[oc[0]][oc[1]][oc[2]][oc[3]][oc[4]]);

        out.close();
        System.exit(0);
    }



}