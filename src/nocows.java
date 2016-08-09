//import com.sun.org.apache.bcel.internal.generic.ArithmeticInstruction;

import java.io.*;
import java.lang.Integer;
import java.util.*;

import java.util.concurrent.Exchanger;
/*
ID: xiaoyun4
LANG: JAVA
TASK: nocows
*/

public class nocows {
    static class Inte {
        int real;
        int fake;
        Inte(int it){
            this.real=it;
            fake = 0;
        }
        Inte(int re, int fa){
            real = re;
            fake = fa;
        }
        boolean isnega(){
            if(real==-1&&fake==-1){
                return true;
            }
            return false;
        }
        Inte multiply(Inte k2){
            return new Inte(this.fake*k2.real+k2.fake*this.real+k2.real*this.real,this.fake*k2.fake);

        }
        void add(Inte stuff){
            this.real+=stuff.real;
            this.fake += stuff.fake;
        }
        void multiply(int k2){
           this.real*=k2;
            this.fake*=k2;

        }
        void subtract(Inte stuff){
            this.real-=stuff.real;
            this.fake -= stuff.fake;

        }

        @Override
        public String toString() {
            return real + " "+ fake;
        }
        public void mod(){
            this.real%=9901;
            this.fake%=9901;
        }
    }
    static int[][] pascal;
    static int totheight;
    static int totnodes;
    public static void print(Inte[] x){

        for (Inte m:x) {
            System.out.print("("+m.real+","+m.fake+") ");
        }
        System.out.println();

        //System.out.println();
    }
    public static int choose(int big, int small){
        int stuff = 1;
        for(int i = big;i>big-small;i--){
            stuff*=i;
            stuff/=big-i+1;
            //  System.out.println(stuff);
        }
        //System.out.println();
        return stuff;
    }

    public static int[][] generatepascals(int height){
        int[][] stuff = new int[height][1];
        int[] start = {1};
        stuff[0] = start;
        for(int i = 1; i<height;i++){
            int[] to_store= new int[i+1];
            for(int j=0;j<=i;j++) {
                //System.out.print(thing + " ");
                if(j==0||j==i) {
                    to_store[j] =1;
                } else {
                    to_store[j]=stuff[i-1][j-1]+stuff[i-1][j];
                }

            }
            stuff[i] = to_store;
    }
        return stuff;
    }

    public static class pedi{
        int counts;
        int nodes;
        int previous;
        int count;
        pedi(){
            nodes = 1;
            previous = 1;
            counts = 1;
            count = 1;

        }
        pedi(pedi x){
            this.counts = x.counts;
            this.count = x.count;
            this.previous = x.previous;
            this.nodes = x.nodes;
        }

        @Override
        public String toString() {
            return "height:"+this.count+" cows:"+ this.nodes+ " howmany:"+counts+" sizeofcurent:" + previous;
        }
    }
    public static int expandpedigree(int count,int height) {
        System.out.println();
        Stack<pedi> x = new Stack<pedi>();
        int sum = 0;
        x.push(new pedi());
        if(2*height-1>count||Math.pow(2,height)-1<count){
            return 0;
        }
        while(x.size()!=0){



            if(x.peek().nodes>count){
                x.pop();
                continue;

            }
            //System.out.println(x.peek());

            //print(x.get(0).counts);

            if(x.peek().count==height){
                if(x.peek().nodes == count){
                    //print(x.get(0).stuff);
                    //System.out.print(x.get(0).counts);
                    //System.out.println();
                    int pro = 1;

                    pro*=x.peek().counts;

                    sum+=pro;

                }
                x.pop();
                continue;

            }
            pedi k = x.pop();
            int m = k.previous;

            for(int i = 2; i<=2*m;i+=2){
                //pedi nex = new pedi(k);
                if(k.nodes+i>=count&&k.count!=height-1){
                    break;
                }
                pedi nex = new pedi(k);
                //pedi nex = new pedi(k);
                if(k.count==height-1&&k.nodes+i==count){

                }
                nex.counts *= choose(nex.previous,i/2);
                nex.previous = i;
                nex.nodes += i;


                nex.count +=1;
                x.push(nex);
            }



        }
        return sum;

    }
    public static Inte getnodes(int nodes, int height,Inte[] it){
        //print(it);
        //System.out.println(nodes + " "+ height);

        if(Math.pow(2,totheight-height+1)-1<nodes){
            return new Inte(-1,-1);

        }


        if (height == totheight-1&&nodes==3) {
            return new Inte(1,0);
        } else if(nodes==1||nodes==3){
            return new Inte(0,1);
        }

        //System.out.println(height + " "+ nodes+" "+ ((nodes-1)/2-1) +" "+ it.length);
        int j = (nodes-1)/2-1;
        int ip= (height-1);
        if(it[(totheight-1)*j+ip]!=null){
            return it[(totheight-1)*j+ip];
        }
        Inte all = new Inte(0);
        Inte subtract = new Inte(0);

        for(int i = 1; i<=(nodes-1)/2;i+=2){
            Inte k1 = getnodes(i,height+1,it);
            k1.mod();
            Inte k2= new Inte(0,0);
            if(i!=nodes-1-i) {
                k2 = getnodes(nodes - 1 - i, height + 1,it);
                k2.mod();
            }
            if(height==1)System.out.println(k1+" "+k2);
            //System.out.println(k1+" "+k2+" "+i+" "+(nodes-1-i)+" "+ height);
            if(k1.isnega()||k2.isnega()){
                continue;
            }
            else if(i==nodes-1-i){
                Inte stuff = k1.multiply(k1);
                stuff.mod();
                all.add(stuff);
                stuff.multiply(-1);
                subtract.add(stuff);
            }
            else{
                Inte stuff =k1.multiply(k2);
                stuff.mod();
                all.add(stuff);
            }
            //all.mod();

        }
        all.multiply(2);
        all.add(subtract);
        it[(totheight-1)*j+ip] = all;

        return all;
    }
    public static Inte getnodes(int nodes, int height){
        //print(it);

        if(Math.pow(2,totheight-height+1)-1<nodes){
            return new Inte(-1,-1);

        }


        if (height == totheight-1&&nodes==3) {
            return new Inte(1,0);
        } else if(nodes==1||nodes==3){
            return new Inte(0,1);
        }

        Inte all = new Inte(0);
        Inte subtract = new Inte(0);

        for(int i = 1; i<=(nodes-1)/2;i+=2){
            all.mod();
            Inte k1 = getnodes(i,height+1);
            Inte k2= new Inte(0,0);
            if(i!=nodes-1-i) {
                k2 = getnodes(nodes - 1 - i, height + 1);
            }
            if(k1.isnega()||k2.isnega()){
                continue;
            }
            else if(i==nodes-1-i){
                Inte stuff = k1.multiply(k1);
                all.add(stuff);
                stuff.multiply(-1);
                subtract.add(stuff);
            }
            else{
                all.add(k1.multiply(k2));
            }

        }
        all.multiply(2);
        all.add(subtract);
        //it[(totheight-2)*ip+j] = all;



        return all;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("nocows.in"));

        // input file name goes above

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int nodes = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        //Inte nod =
        //pascal = generatepascals((int)Math.pow(2,height-1));
        if(Math.pow(2,height)-1<nodes||2*height-1>nodes||nodes%2==0){
            out.println(0);

        } else {
            totheight = height;
            totnodes = nodes;

            //System.out.println(nodes+ " "+ height);
            Inte[] stuff = new Inte[height*((nodes-1)/2)];
            System.out.println(stuff.length);
            Inte k = getnodes(nodes, 1, stuff);
            //System.out.println();
            out.println(k.real % 9901);
        }
        //System.out.println(num);
        //System.out.println(choose(3,2));

        out.close();
        System.exit(0);



    }
}