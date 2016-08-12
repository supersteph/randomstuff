/*
ID: xiaoyun4
LANG: JAVA
TASK: starry
*/
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/** Class point **/


/** Class Jarvis **/
public class starry
{
    static class Point{
        int x,y;
        Point(int x,int y){
            this.x = x;
            this.y = y;
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
        ArrayList<ArrayList<Point>> stuff = new ArrayList<ArrayList<Point>>();
        stuff.add(shape);
        ArrayList<Point> temp = new ArrayList<Point>();
        for (Point p:shape){
            temp.add(new Point(x-p.x-1,p.y));

        }
        stuff.add(temp);
        temp = new ArrayList<Point>();
        for (Point p:shape){
            temp.add(new Point(p.x,y-p.y-1));
        }
        stuff.add(temp);
        temp = new ArrayList<Point>();
        for (Point p:shape){
            temp.add(new Point(x-p.x-1,y-p.y-1));

        }
        stuff.add(temp);
        temp = new ArrayList<Point>();
        for (Point p:shape){
            temp.add(new Point(y-p.y-1,x-p.x-1));

        }
        stuff.add(temp);
        temp = new ArrayList<Point>();
        for (Point p:shape){
            temp.add(new Point(y-p.y-1,p.x));

        }
        stuff.add(temp);
        for (Point p:shape){
            temp.add(new Point(p.y,x-p.x-1));
        }
        stuff.add(temp);
        temp = new ArrayList<Point>();
        for (Point p:shape){
            temp.add(new Point(p.y,p.x));

        }
        stuff.add(temp);
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

        ArrayList<Config> allthe = new ArrayList<Config>();
        for(int i = 0; i<y;i++){
            for(int j = 0; j<x;j++){
                if(!been[i][j]&&all[i][j]){

                    been[i][j]=true;
                    ArrayList<Point> cur = new ArrayList<Point>();
                    ArrayList<Point> total = new ArrayList<Point>();
                    total.add(new Point(i,j));
                    int maxx =j;
                    int maxy = i;
                    int minx = j;
                    int miny = i;
                    while(total.size()!=0){

                        if(total.get(0).x>maxx){
                            maxx = total.get(0).x;
                        }
                        if(total.get(0).y>maxy){
                            maxy = total.get(0).y;
                        }
                        if(total.get(0).x<minx){
                            minx = total.get(0).x;
                        }
                        if(total.get(0).y<miny){
                            miny = total.get(0).y;
                        }

                        cur.add(total.get(0));
                        been[total.get(0).x][total.get(0).y]=true;
                        boolean topx = j+1>=x;
                        boolean bottomx = j-1<0;
                        boolean topy = i+1>=y;
                        boolean bottomy=i-1<0;
                        
                        if(!topx&&!been[total.get(0).y][total.get(0).x+1]&&all[total.get(0).y][total.get(0).x+1]){
                            total.add(new Point(total.get(0).x+1,total.get(0).y));
                        }
                        if(!topy&&!been[total.get(0).y+1][total.get(0).x]&&all[total.get(0).y+1][total.get(0).x]){
                            total.add(new Point(total.get(0).x,total.get(0).y+1));
                        }
                        if(!bottomx&&!been[total.get(0).y][total.get(0).x-1]&&all[total.get(0).y][total.get(0).x-1]){
                            total.add(new Point(total.get(0).x-1,total.get(0).y));
                        }
                        if(!bottomy&&!been[total.get(0).y-1][total.get(0).x]&&all[total.get(0).y-1][total.get(0).x]){
                            total.add(new Point(total.get(0).x-1,total.get(0).y));
                        }
                        if(!topx&&topy&&!been[total.get(0).y+1][total.get(0).x+1]&&all[total.get(0).y+1][total.get(0).x+1]){
                            total.add(new Point(total.get(0).x+1,total.get(0).y+1));
                        }
                        if(!topy&&bottomx&&!been[total.get(0).y+1][total.get(0).x-1]&&all[total.get(0).y+1][total.get(0).x-1]){
                            total.add(new Point(total.get(0).x-1,total.get(0).y+1));
                        }
                        if(!bottomx&&bottomy&&!been[total.get(0).y-1][total.get(0).x-1]&&all[total.get(0).y-1][total.get(0).x-1]){
                            total.add(new Point(total.get(0).x-1,total.get(0).y-1));
                        }
                        if(!bottomy&&topx&&!been[total.get(0).y-1][total.get(0).x+1]&&all[total.get(0).y-1][total.get(0).x+1]){
                            total.add(new Point(total.get(0).x+1,total.get(0).y-1));
                        }
                        total.remove(0);

                    }

                    char poss = (char)(allthe.size()+64);
                    boolean changed = false;

                    for(Config config: allthe){
                        if((config.x==maxx-minx&&config.y==maxy-miny)||(config.y==maxx-minx&&config.x==maxy-miny)){
                            outerloop:
                            for(Point poin:cur){
                                for(ArrayList<Point> n: config.other){
                                    for(Point l: n){
                                        if(poin.x-minx!=l.x&&poin.y-miny!=l.y){
                                            continue outerloop;
                                        }
                                    }
                                }


                            }

                            poss = config.letter;
                            changed=true;
                            break;
                        }

                    }

                    for(Point p:cur){
                        everything[p.y][p.x] = poss;
                        p.y-=miny;
                        p.x-=maxy;
                    }

                    if(!changed){
                        Config m = new Config();
                        m.other=gen8(cur,maxx-minx,maxy-miny);
                        m.letter=poss;
                        allthe.add(m);
                    }

                }

            }


        }







        PrintWriter out = new PrintWriter(new FileWriter("starry.out"));
        for(char[] ch: everything){
            for(char lol:ch){
                out.print(lol);
            }
            out.println();
        }
        out.close();
        System.exit(0);
    }
}