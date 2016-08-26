/*
ID: xiaoyun4
LANG: JAVA
TASK: snail
*/
//import com.sun.org.apache.xerces.internal.util.SymbolHash;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class snail
{

    static boolean cantturn(int x, int y, boolean[][] map, boolean[][] a, int dir){
        if(dir == 0){
            if(x==a.length-1||map[x+1][y]||a[x+1][y]){
                return true;
            }
            else{
                return false;

            }

        }
        else if(dir==1){
            if(y==a.length-1||map[x][y+1]||a[x][y+1]){
                return true;
            }
            else{
                return false;

            }

        }
        else if(dir==2){
            if(x==0||map[x-1][y]||a[x-1][y]){
                return true;
            }
            else{
                return false;
            }

        }else{
            if(y==0||map[x][y-1]||a[x][y-1]){
                return true;
            }
            else{
                return false;

            }


        }

    }

    static int ooo(boolean[][] a, boolean[][] map, int direction, int x, int y){
        map[x][y] = true;
        for(int i = 0; i<map.length;i++){
            for(int j = 0; j<map.length;j++){
                if(map[i][j]){
                    if(a[i][j]){
                        System.out.println("w");
                    }
                    else {
                        System.out.print(1);
                    }
                }
                else if(a[i][j]) {
                    System.out.print("#");
                }

                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        //System.out.println();

        for(int i = 0;i<map.length;i++){
            System.out.print("-");
        }
        System.out.println();
        int max = 0;
        if(direction==0){
            if(x==a.length-1){
                if(!cantturn(x,y,a,map,1)){
                    max = Math.max(max,ooo(a,map,1,x,y+1)+1);
                }
                if(!cantturn(x,y,a,map,3)) {
                    max = Math.max(max,ooo(a,map,3,x,y-1)+1);
                }
            }
            else if(map[x+1][y]==true){
                max = Math.max(max,1);
            }
            else if(a[x+1][y]==true){
                if(!cantturn(x,y,a,map,1)){
                    max = Math.max(max,ooo(a,map,1,x,y+1)+1);
                }
                if(!cantturn(x,y,a,map,3)) {
                    max = Math.max(max,ooo(a,map,3,x,y-1)+1);
                }

            }
            else{
                max = Math.max(ooo(a,map,0,x+1,y)+1,max);

            }

        }
        else if(direction==1){
            if(y==a.length-1){
                if(!cantturn(x,y,a,map,0)){
                    max = Math.max(max,ooo(a,map,0,x+1,y)+1);
                }
                if(!cantturn(x,y,a,map,2)){
                    max = Math.max(max,ooo(a,map,2,x-1,y)+1);

                }
            }
            else if(map[x][y+1]==true){
                max = Math.max(max,1);
            }
            else if(a[x][y+1]==true){
                if(!cantturn(x,y,a,map,0)){
                    max = Math.max(max,ooo(a,map,0,x+1,y)+1);
                }
                if(!cantturn(x,y,a,map,2)){
                    max = Math.max(max,ooo(a,map,2,x-1,y)+1);

                }

            }
            else{
                max = Math.max(ooo(a,map,1,x,y+1)+1,max);

            }
        }
        else if(direction==2){

            if(x==0){
                if(!cantturn(x,y,a,map,1)){
                    max = Math.max(max,ooo(a,map,1,x,y+1)+1);
                }
                if(!cantturn(x,y,a,map,3)) {
                    max = Math.max(max,ooo(a,map,3,x,y-1)+1);
                }
            }
            else if(map[x-1][y]==true){
                max = Math.max(max,1);
            }
            else if(x==0||a[x-1][y]==true){
                if(!cantturn(x,y,a,map,1)){
                    max = Math.max(max,ooo(a,map,1,x,y+1)+1);
                }
                if(!cantturn(x,y,a,map,3)) {
                    max = Math.max(max,ooo(a,map,3,x,y-1)+1);
                }

            }
            else{
                max = Math.max(ooo(a,map,2,x-1,y)+1,max);
            }
        }
        else{
            if(y==0){
                if(!cantturn(x,y,a,map,0)){
                    max = Math.max(max,ooo(a,map,0,x+1,y)+1);
                }
                if(!cantturn(x,y,a,map,2)) {
                    max = Math.max(max,ooo(a,map,2,x-1,y)+1);
                }
            }

            else if(map[x][y-1]==true){
                max = Math.max(max,1);
            }
            else if(a[x][y-1]==true){
                if(!cantturn(x,y,a,map,0)){
                    max = Math.max(max,ooo(a,map,0,x+1,y)+1);
                }
                if(!cantturn(x,y,a,map,2)){
                    max = Math.max(max,ooo(a,map,2,x-1,y)+1);

                }
            }
            else{
                max = Math.max(ooo(a,map,3,x,y-1)+1,max);
            }
        }
        map[x][y] = false;
        return max;
    }
    public static void main (String[] args) throws IOException
    {

        BufferedReader f = new BufferedReader(new FileReader("snail.in"));
        PrintWriter out = new PrintWriter(new FileWriter("snail.out"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        boolean[][] p = new boolean[n][n];
        String k = f.readLine();
        while(k!=null){
            int b = k.charAt(0)-'A';
            int a = Integer.parseInt(k.substring(1,2))-1;
            p[a][b] = true;
            k=f.readLine();
        }
        int max = 0;
        boolean[][] fire = new boolean[n][n];
        max = ooo(p,fire,0,0,0);
        max = Math.max(max, ooo(p,fire,1,0,0));
        out.println(max);
        System.out.println(max);

        
        out.close();
        System.exit(0);
    }
}