/*
ID: xiaoyun4
LANG: JAVA
TASK: ditch
*/
import java.io.*;
import java.util.*;
public class ditch{

    static class node{
        ArrayList<paths> connects;
        int cur;
    }
    static class paths{
        int end;
        int length;
    }


    static int stuff(int cur, int[] beento, node[] s, int curli){
        //return 50;
//        System.out.println(cur + "," + curli);
        if(beento[cur]!=0){
            //means a circle
            return 0;
        }

        if(s[cur]==null||s[cur].connects.size()==0){
            return curli;
        }
        beento[cur] = 0;


        int sum = 0;



        for(int i = 0; i<s[cur].connects.size();i++){
            if(sum>=curli&&curli!=-1){
                sum = curli;
                break;
            }
            else{
                if(curli==-1){
                    sum+=stuff(s[cur].connects.get(i).end, beento, s, s[cur].connects.get(i).length);
                }
                else{
                    sum+=stuff(s[cur].connects.get(i).end, beento, s, Math.min(curli, s[cur].connects.get(i).length));
                }
            }
        }
        beento[cur] = sum;
        return sum;


    }

    public static void main (String [] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("ditch.in"));



        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        node[] s = new node[n+1];

        for(int i = 0; i<m;i++){
            StringTokenizer sts = new StringTokenizer(f.readLine());
            int lol = Integer.parseInt(sts.nextToken());
            if(s[lol]==null){
                s[lol] = new node();
                s[lol].connects = new ArrayList<paths>();
            }

            paths thi = new paths();
            thi.end = Integer.parseInt(sts.nextToken());
            thi.length=Integer.parseInt(sts.nextToken());
            s[lol].connects.add(thi);

        }




        int[] been = new int[n+1];

        int k = stuff(1,been,s,-1);
        out.println(k);
        System.out.println(k);




        out.close();
    }

}