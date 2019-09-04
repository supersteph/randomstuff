/*
ID: xiaoyun4
LANG: JAVA
TASK: castle
*/

/**
 * How to solve it:
 * So I read in all of the data, and I add a 1 if it is a wall, and I just start at 1, and keep on going right until
 * I might be able to go up or down, and when it can i add up the spaces in the module, when i try to remove a wall, i
 * try to remove all the walls in the middle and see if there are any modules that are bigger
 *
 */


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class castle {
    public static class module {
        int y;
        int x;

        @Override
        public boolean equals(Object obj) {
            module ob = (module) obj;
            if (ob.x == x && ob.y == y) {
                return true;
            }
            return false;
        }
        public module(int i, int j){
            y=i;
            x = j;

        }


        @Override
        public int hashCode() {
            return 37 * y/ x;
        }
    }
    public static void remove(int[][]x, PrintWriter out){
        ArrayList<module> stuff = new ArrayList<module>();
        StringBuffer m = new StringBuffer();
        int[][] s = new int[x[0].length][x.length];
        for(int k = 0; k<x[0].length;k++){
            for(int l = 0; l<x.length;l++) {
                s[k][l] = x[l][k];
            }

        }
        for(int i = 1; i<x[0].length-1;i++){
            for(int j = x.length-2; j>0;j--){

                if(x[j][i]==1){
                    HashSet<module> h = new HashSet<module>();
                    s[i][j]= 0;

                    ArrayList<module> l = new ArrayList<module>();
                    if(i%2==0){
                        l = expand(i+1,j,h,s);
                    }else{
                        l = expand(i,j+1,h,s);
                    }


                    if(l.size()>stuff.size()){
                        stuff = l;

                        if(i%2==0){

                            m = new StringBuffer(String.valueOf(String.valueOf((j/2)+1) +" " + String.valueOf((i/2))+ " " + "E"));

                        } else if(j%2==0){
                            m = new StringBuffer(String.valueOf(String.valueOf((j/2)+1)+" " + String.valueOf((i/2)+1)+ " " + "N"));
                        }

                    }
                    s[i][j]=1;
                }
            }
        }
        out.println(stuff.size());
        out.println(m.toString());

    }

    


    public static ArrayList<ArrayList<module>> getmodulesizes(int[][]x) {
        ArrayList<ArrayList<module>> modules = new ArrayList<ArrayList<module>>();
        HashSet<module> explored = new HashSet<module>();
        for (int i = 1; i < x.length - 1; i += 2) {
            for (int j = 1; j < x[0].length - 1; j+=2) {
                if(x[i][j]==0&&!explored.contains(new module(i,j))) {
                    modules.add(expand(i, j, explored, x));
                }

            }
        }
        return modules;
    }

    public static ArrayList<module> expand(int i, int j, HashSet<module> y, int[][] x){
        ArrayList<module> stuff = new ArrayList<module>();
        ArrayList<module> fina = new ArrayList<module>();
        stuff.add(new module(i,j));
        fina.add(new module(i,j));
        y.add(new module(i,j));
        while(stuff.size()!=0){
            module m = stuff.get(0);
            if(x[m.y+1][m.x] != 1){
                if(!y.contains(new module(m.y+2,m.x))){
                    stuff.add(new module(m.y+2,m.x));
                    fina.add(new module(m.y+2,m.x));
                    y.add(new module(m.y+2,m.x));

                }

            }
            if(x[m.y-1][m.x] != 1){
                if(!y.contains(new module(m.y-2,m.x))){
                    stuff.add(new module(m.y-2,m.x));
                    y.add(new module(m.y-2,m.x));
                    fina.add(new module(m.y-2,m.x));
                }

            }
            if(x[m.y][m.x+1] != 1){
                if(!y.contains(new module(m.y,m.x+2))){
                    stuff.add(new module(m.y,m.x+2));
                    y.add(new module(m.y,m.x+2));
                    fina.add(new module(m.y,m.x+2));
                }

            }
            if(x[m.y][m.x-1] != 1){
                if(!y.contains(new module(m.y,m.x-2))){
                    stuff.add(new module(m.y,m.x-2));
                    y.add(new module(m.y,m.x-2));
                    fina.add(new module(m.y,m.x-2));
                }

            }
            stuff.remove(0);


        }
        return fina;


    }



    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("castle.in"));
        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        StringTokenizer sb = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(sb.nextToken());
        int n = Integer.parseInt(sb.nextToken());

        int[][] x = new int[2*n+1][2*m+1];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            for(int j = 0;j<m;j++){
                int k = Integer.parseInt(st.nextToken());
                if(k-8>=0){
                    x[2*i+2][2*j+1] = 1;
                    k-=8;
                }
                if(k-4>=0){
                    x[2*i+1][2*j+2] = 1;
                    k-=4;
                }
                if(k-2>=0){
                    x[2*i][2*j+1] = 1;
                    k-=2;
                }
                if(k-1>=0){
                    x[2*i+1][2*j]=1;
                    k-=1;

                }

            }
        }

        ArrayList<ArrayList<module>> modulesbefore = getmodulesizes(x);
        out.println(modulesbefore.size());
        int max = 0;
        for(int i = 0; i<modulesbefore.size();i++){
            if(modulesbefore.get(i).size()>max){
                max = modulesbefore.get(i).size();
            }
        }
        out.println(max);

        remove(x,out);


        out.close();
        System.exit(0);


    }
}
