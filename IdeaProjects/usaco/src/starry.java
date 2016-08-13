/*
ID: xiaoyun4
LANG: JAVA
TASK: starry
*/
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class starry
{
    static class Point{
        int x,y;
        Point(int x,int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x+" "+y;
        }

        @Override
        public boolean equals(Object o) {
            Point p =(Point)o;
            return x==p.x&&y==p.y;
        }

        @Override
        public int hashCode() {
            return 37*x+y;
        }
    }
    static class Config{
        ArrayList<ArrayList<Point>> other;
        char letter;
        int x;
        int y;
    }
    static ArrayList<ArrayList<Point>> gen8(ArrayList<Point> shape, int x, int y){
        x++;
        y++;
        ArrayList<ArrayList<Point>> stuff = new ArrayList<ArrayList<Point>>();
        stuff.add(shape);
        ArrayList<Point> temp = new ArrayList<Point>();
        ArrayList<Point> temp1 = new ArrayList<Point>();
        ArrayList<Point> temp2= new ArrayList<Point>();
        ArrayList<Point> temp3 = new ArrayList<Point>();
        ArrayList<Point> temp4 = new ArrayList<Point>();
        ArrayList<Point> temp5 = new ArrayList<Point>();
        ArrayList<Point> temp6 = new ArrayList<Point>();

        for (Point p:shape){
            temp.add(new Point(x-p.x-1,p.y));
            temp1.add(new Point(p.x,y-p.y-1));
            temp2.add(new Point(x-p.x-1,y-p.y-1));
            temp3.add(new Point(y-p.y-1,x-p.x-1));
            temp4.add(new Point(y-p.y-1,p.x));
            temp5.add(new Point(p.y,x-p.x-1));
            temp6.add(new Point(p.y,p.x));

        }

        stuff.add(temp);
        stuff.add(temp1);
        stuff.add(temp2);
        stuff.add(temp3);
        stuff.add(temp4);
        stuff.add(temp5);
        stuff.add(temp6);

        return stuff;
    }

    public static void main (String[] args) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader("starry.in"));

        int x = Integer.parseInt(f.readLine());
        int y = Integer.parseInt(f.readLine());
        boolean[][] been = new boolean[y][x];
        boolean[][] all = new boolean[y][x];
        char[][] everything = new char[y][x];
        for(int i = 0; i<y;i++){



            String k = f.readLine();
            for(int j = 0;j<x;j++){
    
                everything[i][j]='0';
                if(k.charAt(j)=='1'){

                    all[i][j] = true;

                }
            }
        }
        //System.out.println(x+" "+y);
        ArrayList<Config> allthe = new ArrayList<Config>();
        for(int i = 0; i<x;i++){
            for(int j = 0; j<y;j++){
                //System.out.println();
                if(!been[j][i]&&all[j][i]){

                    been[j][i]=true;
                    ArrayList<Point> cur = new ArrayList<Point>();
                    ArrayList<Point> total = new ArrayList<Point>();
                    total.add(new Point(i,j));
                    int maxx =i;
                    int maxy = j;
                    int minx = i;
                    int miny = j;

                    while(total.size()!=0){
                        //System.out.println(total.get(0).x+" "+total.get(0).y+" "+i+" "+j);

                        if(total.get(0).x>maxx){
                            maxx = total.get(0).x;
                            //System.out.println("max x has been set to the"+total.get(0).y);
                        }
                        if(total.get(0).y>maxy){
                            maxy = total.get(0).y;
                            //System.out.println("max y has been set to the "+ total.get(0).y);
                        }
                        if(total.get(0).x<minx){
                            minx = total.get(0).x;
                            //System.out.println("min x has been set to the"+total.get(0).x);
                        }
                        if(total.get(0).y<miny){
                            miny = total.get(0).y;
                            //System.out.println("max y has been set to the "+total.get(0).y);
                        }

                        cur.add(total.get(0));

                        //been[total.get(0).y][total.get(0).x]=true;

                        boolean topx = total.get(0).x+1>=x;
                        boolean bottomx = total.get(0).x-1<0;

                        boolean topy = total.get(0).y+1>=y;
                        boolean bottomy=total.get(0).y-1<0;


                        if(!topx&&!been[total.get(0).y][total.get(0).x+1]&&all[total.get(0).y][total.get(0).x+1]){
                            total.add(new Point(total.get(0).x+1,total.get(0).y));
                            been[total.get(0).y][total.get(0).x+1]=true;
                        }
                        if(!topy&&!been[total.get(0).y+1][total.get(0).x]&&all[total.get(0).y+1][total.get(0).x]){
                            total.add(new Point(total.get(0).x,total.get(0).y+1));
                            been[total.get(0).y+1][total.get(0).x]=true;
                        }
                        if(!bottomx&&!been[total.get(0).y][total.get(0).x-1]&&all[total.get(0).y][total.get(0).x-1]){
                            total.add(new Point(total.get(0).x-1,total.get(0).y));
                            been[total.get(0).y][total.get(0).x-1]=true;
                        }
                        if(!bottomy&&!been[total.get(0).y-1][total.get(0).x]&&all[total.get(0).y-1][total.get(0).x]){
                            total.add(new Point(total.get(0).x,total.get(0).y-1));
                            been[total.get(0).y-1][total.get(0).x]=true;
                        }
                        if(!topx&&!topy&&!been[total.get(0).y+1][total.get(0).x+1]&&all[total.get(0).y+1][total.get(0).x+1]){
                            total.add(new Point(total.get(0).x+1,total.get(0).y+1));
                            been[total.get(0).y+1][total.get(0).x+1]=true;
                        }
                        if(!topy&&!bottomx&&!been[total.get(0).y+1][total.get(0).x-1]&&all[total.get(0).y+1][total.get(0).x-1]){
                            total.add(new Point(total.get(0).x-1,total.get(0).y+1));
                            been[total.get(0).y+1][total.get(0).x-1]=true;
                        }
                        if(!bottomx&&!bottomy&&!been[total.get(0).y-1][total.get(0).x-1]&&all[total.get(0).y-1][total.get(0).x-1]){
                            total.add(new Point(total.get(0).x-1,total.get(0).y-1));
                            been[total.get(0).y-1][total.get(0).x-1]=true;
                        }
                        if(!bottomy&&!topx&&!been[total.get(0).y-1][total.get(0).x+1]&&all[total.get(0).y-1][total.get(0).x+1]){
                            total.add(new Point(total.get(0).x+1,total.get(0).y-1));
                            been[total.get(0).y-1][total.get(0).x+1]=true;
                        }
                        total.remove(0);

                    }

                    char poss = (char)(allthe.size()+'a');
                    boolean changed = false;

                    for(Config config: allthe){
                        if(((config.x==maxx-minx&&config.y==maxy-miny)||(config.y==maxx-minx&&config.x==maxy-miny))&&(config.other.get(0).size()==cur.size())){
                            System.out.println(config.letter+" "+poss);
                            int k;
                            outerloop:
                            for(k=0;k<8;k++){
                                boolean fo = true;
                                for(Point p:cur){
                                    for(Point ha:config.other.get(k)){
                                        if(ha.x==p.x&&p.y==ha.y){
                                            fo = false;
                                            continue outerloop;
                                        }
                                    }

                                }
                                System.out.println("DOING");
                                //we made it!\
                                if(fo) {


                                    System.out.println("we found a same one");
                                    poss = config.letter;
                                    changed = true;
                                    break;
                                }

                            }

                        }

                    }

                    for(Point p:cur){
                        everything[p.y][p.x] = poss;

                        p.y-=miny;
                        p.x-=minx;

                    }
                    //System.out.println(poss+" "+minx+" "+miny+" "+maxx+" "+maxy);
                    if(!changed){
                        Config m = new Config();
                        m.other=gen8(cur,maxx-minx,maxy-miny);
                        m.letter=poss;
                        m.x=maxx-minx;
                        m.y=maxy-miny;
                        //System.out.println(m.x+" "+m.y);
                        //System.out.println(m.other.size());
                        allthe.add(m);
                    }


                }

            }


        }
        /*
        System.out.println();
        for(int i = 0; i<8;i++){

            char[][] stuff = new char[6][6];
            Arrays.fill(stuff[0], '0');
            Arrays.fill(stuff[1], '0');
            Arrays.fill(stuff[2], '0');
            Arrays.fill(stuff[3], '0');
            Arrays.fill(stuff[4], '0');
            Arrays.fill(stuff[5], '0');

            for(Point p:allthe.get(6).other.get(i)){
                //System.out.println(p.x+" "+p.y);
                stuff[p.x][p.y]='A';
            }

            for(char[] s:stuff){
                for(char lol:s){
                    if(lol=='0'){
                        System.out.print(" ");
                    }else {
                        System.out.print('8');
                    }
                }
                System.out.println();
            }
            System.out.println();
        }

        */

        PrintWriter out = new PrintWriter(new FileWriter("starry.out"));
        for(char[] ch: everything){
            for(char lol:ch){
                if(lol=='0'){
                    System.out.print(" ");
                }
                else {
                    System.out.print(lol);
                }
                out.print(lol);
            }
            System.out.println();
            out.println();
        }
        out.close();
        System.exit(0);
    }
}