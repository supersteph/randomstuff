import java.io.*;
import java.lang.Integer;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Exchanger;
/*
ID: xiaoyun4
LANG: JAVA
TASK: lamps
*/

public class lamps {
    public static int  lel = 0;


    public static class confg implements Comparable{
        boolean[] it;
        int count;
        int sum;
        public confg(boolean[] x, int m){
            //works = k;
            it = x;
            count = m;
            sum = 0;
            for(int i = 0; i<it.length;i++){
                if(it[i]==true){
                    sum= sum*2 +1;
                } else{
                    sum= sum*2;
                }

            }
            sum+= count;

        }

        @Override
        public boolean equals(Object o) {
            confg x = (confg) o;
            if(x.count!=count){
                return false;
            }
            for(int i = 0 ; i< it.length;i++){
                if(it[i]!=x.it[i]){
                    return false;
                }


            }
            return true;
        }

        @Override
        public int hashCode() {

            return sum;

        }

        @Override
        public int compareTo(Object o) {
            confg second = (confg) o;
            for (int i = 0; i < it.length; i++) {
                if (second.it[i] == true&& it[i]==false) {
                    return -1;
                }else if(second.it[i] == false && it[i]==true){
                    return 1;
                }
            }
            return 0;

        }

        @Override
        public String toString() {
            StringBuffer lel = new StringBuffer();
            for(int i = 0; i<it.length;i++){
                if(it[i]==true){
                    lel.append(1);
                } else{
                    lel.append(0);
                }
                //lel.append(it[i]);

            }
            return lel.toString();
        }
    }

    public static void button1(confg stuff){

        //boolean[] otherstuff = stuff.clone();
        for(int i = 0; i<stuff.it.length;i++){
            stuff.it[i]=!(stuff.it[i]);

        }

    }

    public static void button2(confg stuff){
        for(int i = 0; i<stuff.it.length;i+=2){
            if(i%2==0){
                //means that it is odd
                stuff.it[i]=!(stuff.it[i]);

            }
        }


        //otherstuff[otherstuff.length-1] ++;
        //System.out.println(stuff[stuff.length-1]+ " " +otherstuff[stuff.length-1]);
        //return otherstuff;
    }
    public static void button3(confg stuff){

        for(int i = 1; i<stuff.it.length;i+=2){
            if((i+1)%2==0){
                //means that it is odd
                stuff.it[i]=!(stuff.it[i]);

            }
        }
        //otherstuff[otherstuff.length-1] ++;
        //return otherstuff;
    }

    public static void button4(confg stuff){

        for(int i = 0; i<stuff.it.length;i+=3){
            if(i%3==0){
                //means that it is odd
                stuff.it[i]=!(stuff.it[i]);

            }
        }
        //otherstuff[otherstuff.length-1] ++;
        //return otherstuff;
    }
    public static void print(boolean[] toprint, PrintWriter out){
        for(int i = 0; i<toprint.length-1;i++){
            if(toprint[i]==true) {
                out.print(1);
            }else{
                out.print(0);
            }
        }
        if(toprint[toprint.length-1]==true) {
            out.println(1);
        }else{
            out.println(0);
        }
    }

    public static int sum(String x){
        int sum =0;
        for(int i = 0; i<x.length();i++){
            sum += Integer.valueOf(x.substring(i,i+1));
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("lamps.in"));

        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));

        int n = Integer.parseInt(f.readLine());
        int count = Integer.parseInt(f.readLine());
        ArrayList<Integer> on = new ArrayList<Integer>();
        StringTokenizer ston = new StringTokenizer(f.readLine());
        int shouldbeon = Integer.parseInt(ston.nextToken());
        while(shouldbeon!=-1){
            on.add(shouldbeon);
            shouldbeon=Integer.parseInt(ston.nextToken());
        }
        ArrayList<Integer> off = new ArrayList<Integer>();
        StringTokenizer stoff = new StringTokenizer(f.readLine());
        int shouldbeoff = Integer.parseInt(stoff.nextToken());
        while(shouldbeoff!=-1){
            off.add(shouldbeoff);
            shouldbeoff = Integer.parseInt(stoff.nextToken());
        }


        //confg start=  new confg(it,0);
        ArrayList<String> x = new ArrayList<String>();


        for(int i = 0; i< 16;i++){
            StringBuffer m = new StringBuffer();
            m.append(Integer.toBinaryString(i));

            while(m.length()!=4){
                m.insert(0,"0");
            }
            x.add(m.toString());

        }


        for(int i =0; i<x.size();i++){
            if(count%2!=sum(x.get(i))%2||count<sum(x.get(i))){
                x.remove(i);
                i--;
            }
        }
        boolean[] it = new boolean[n];
        for(int i = 0; i <it.length;i++){
            it[i] = true;
        }

        confg start=  new confg(it,0);


        ArrayList<confg> stuff = new ArrayList<confg>();

        for(int i = 0; i<x.size();i++){
            String mpd = x.get(i);
            confg lel = new confg(it.clone(),count);
            if(mpd.charAt(0)=='1'){
                button1(lel);

            }
            if(mpd.charAt(1)=='1'){
                button2(lel);

            }
            if(mpd.charAt(2)=='1'){
                button3(lel);

            }
            if(mpd.charAt(3)=='1'){
                button4(lel);

            }
            stuff.add(lel);
        }
        System.out.println(stuff);

        outerloop:
        for(int i = 0; i<stuff.size();i++){
            //System.out.println(stuff+ " " + i);
            boolean[] kk = stuff.get(i).it;
            for(int j = 0; j<on.size();j++){
                if(kk[on.get(j)-1]!=true){
                    stuff.remove(i);
                    i--;
                    continue outerloop;

                }
            }
            for(int j = 0; j<off.size();j++){
                //print(kk);
                if(kk[off.get(j)-1]!=false){
                    //System.out.println("removed " +(off.get(j)-1));

                    stuff.remove(i);
                    i--;
                    continue outerloop;
                }
            }


        }



        Collections.sort(stuff);

        for(int i = 0; i< stuff.size();i++){
            print(stuff.get(i).it,out);
        }
        if(stuff.size()==0){
            out.println("IMPOSSIBLE");

        }

        //System.out.println(sum("010"));


        //int[] l = {0,1,0};
        //int[] k = {1,0,1};
        //ArrayList<confg> test = new ArrayList<confg>();
        //test.add(new confg(l,0));
        //test.add(new confg(k,0));
        //quicksort(0, 1,test);

        //System.out.println(everything.contains(x));









        //out.println(i);

        out.close();
        System.exit(0);



    }
}