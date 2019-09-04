/*
 ID: xiaoyun4
 PROB: butter
 LANG: JAVA
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class butter {
    static boolean[] work;
    static PriorityQueue<Node> pls = new PriorityQueue<Node>();
    private static class Edge {
        int val;
        int end;
        Edge(int a, int b){
            val = a;
            end = b;
        }

    }
    private static class Node implements Comparable<Node>{
        int val;
        ArrayList<Edge> stuff;
        Node(){
            stuff=new ArrayList<Edge>();
        }
        @Override
        public boolean equals(Object o) {
            return val==((Node)o).val;
        }

        @Override
        public int compareTo(Node node) {
            return val-node.val;
        }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    private static void spaf(Node p, int[] s, Node[] ref){
        //PriorityQueue<Node> pls = new PriorityQueue<Node>();
        pls.add(p);
        for(int i = 0; i<s.length;i++){
            if(i==p.val){
                s[i] =0;
            }
            else{
                s[i]=-1;
            }
        }


        work[p.val]=true;
        while (pls.size()!=0){
            Node head = pls.poll();
            for(Edge help:head.stuff){
                if(help.val+s[head.val]<s[help.end]||s[help.end]==-1){
                    s[help.end]=help.val+s[head.val];
                    if(work[help.end]==false){
                        pls.add(ref[help.end]);
//                        work[ref[help.end].val]=true;
                        work[help.end] =true;
                    }
                }

            }
            work[head.val]=false;
//            pls.poll();

        }


    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("butter.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
        int n,p,c;

        StringTokenizer start = new StringTokenizer(in.readLine());
        n = Integer.parseInt(start.nextToken());
        p = Integer.parseInt(start.nextToken());
        c = Integer.parseInt(start.nextToken());
        work=new boolean[p];
        Node[] map = new Node[p];
        int[] cowplace = new int[n];
        for(int i = 0; i<n;i++){
            int ha = Integer.parseInt(in.readLine());
            cowplace[i]=ha;
        }

        for(int i = 0; i<c;i++) {

            StringTokenizer path = new StringTokenizer(in.readLine());
            int ind1, ind2;
            ind1=Integer.parseInt(path.nextToken());
            ind2=Integer.parseInt(path.nextToken());

            int val = Integer.parseInt(path.nextToken());

            if(map[ind1-1]==null) {
                map[ind1 - 1] = new Node();
            }
            if(map[ind2-1]==null) {
                map[ind2 - 1] = new Node();
            }
            map[ind1 - 1].val = ind1-1;
            map[ind2 - 1].val = ind2-1;
            //System.out.println(map[ind1-1].val);
            map[ind1-1].stuff.add(new Edge(val,ind2-1));
            map[ind2-1].stuff.add(new Edge(val,ind1-1));

        }
        int[][] master = new int[n][p];
        int min = -1;

        for(int i = 0; i<n;i++){
            int k = cowplace[i];
            k--;
            Node inded = map[k];
            //System.out.println(inded);
            spaf(inded,master[i],map);

        }

        for(int i = 0; i<p;i++){
            int minmid=0;
            for(int j = 0; j<n;j++){
                minmid+=master[j][i];
            }
            if(minmid<min||min==-1){
                min=minmid;
            }
        }



        out.println(min);
        out.close();
        System.out.println(min);
        System.exit(0);
    }



}