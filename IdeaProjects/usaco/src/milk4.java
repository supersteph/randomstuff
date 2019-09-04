/*
ID: xiaoyun4
LANG: JAVA
TASK: milk4
*/
//import com.sun.org.apache.xerces.internal.util.SymbolHash;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class milk4
{
    public static void main (String[] args) throws IOException
    {

        BufferedReader f = new BufferedReader(new FileReader("milk4.in"));
        PrintWriter out = new PrintWriter(new FileWriter("milk4.out"));
        int n = Integer.parseInt(f.readLine());
        int p = Integer.parseInt(f.readLine());
        int[] blocks = new int[p];
        for(int i = 0; i<p;i++){
            blocks[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(blocks);
        ArrayList<ArrayList<Integer>> lol = new ArrayList<ArrayList<Integer>>();
        lol.add(new ArrayList<Integer>());
        int m = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 1 ;i<n+1;i++){
            System.out.println(lol);
            lol.add(null);
            int j;
            int maxsize = Integer.MAX_VALUE;
            int maxind = -1;
            for(j=p-1;j>=0;j--){
                if(i-blocks[j]<0){
                    continue;
                }else if(lol.get(i-blocks[j])==null){
                    continue;
                }
                else {
                    if(maxsize<lol.get(i-blocks[j]).size()) {
                        maxsize = lol.get(i - blocks[j]).size();
                        maxind = j;
                    }
                }
            }
            j=maxind;
            if(j!=-1){
                lol.set(i,(ArrayList<Integer>) lol.get(i-blocks[j]).clone());
                if(!lol.get(i-blocks[j]).contains(j)){
                    lol.get(i).add(j);
                }
                if((n/i)*i==n){
                    System.out.println();
                    if(lol.get(i).size()<min){
                        min = lol.get(i).size();
                        m = i;
                    }
                    continue;
                }
            }
        }
        System.out.println(m);
        out.print(lol.get(m).size()+" ");

        for(int haha = 0; haha<lol.get(m).size()-1;haha++){
            System.out.print(blocks[lol.get(m).get(haha)]);
            out.print(blocks[lol.get(m).get(haha)]+" ");
        }
        out.println(blocks[lol.get(m).get(lol.get(m).size()-1)]);
        out.close();
        System.exit(0);
    }
}