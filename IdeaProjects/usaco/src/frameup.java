/*
ID: xiaoyun4
LANG: JAVA
TASK: frameup
*/
import java.util.*;
import java.io.*;


public class frameup {
    static int H,W;
    static char[][] arr;
    static int left[] = new int[26];
    static int right[] = new int[26];

    static int top[] = new int[26];
    static int bottom[] = new int[26];
    static boolean vis[] = new boolean[26];
    static ArrayList<String> l = new ArrayList<String>();
    static int[][] relation = new int[26][26];
    static int count[] = new int[26];
    static char[] path = new char[26];
    static int total = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("frameup.in"));
        Arrays.fill(left, 999);
        Arrays.fill(right, -1);
        Arrays.fill(top, 999);
        Arrays.fill(bottom, -1);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        H=Integer.valueOf(st.nextToken());
        W=Integer.valueOf(st.nextToken());
        arr = new char[H][W];
        StringBuilder sb = new StringBuilder();
        long start = System.currentTimeMillis();

        for(int i=0;i<H;i++){
            String tmp = reader.readLine();
            for(int j=0;j<W;j++){
                arr[i][j]=tmp.charAt(j);
                if(arr[i][j]=='.') continue;
                int temp = arr[i][j]-'A';
                left[temp]=left[temp]<j?left[temp]:j;
                right[temp]=right[temp]>j?right[temp]:j;
                top[temp]=top[temp]<i?top[temp]:i;
                bottom[temp]=bottom[temp]>i?bottom[temp]:i;
            }
        }
        for(int i=0;i<26;i++){
            if(left[i]==999) continue;
            int cnt=0;
            total++;
            for(int k1=left[i];k1<=right[i];k1++){
                if(arr[top[i]][k1]-'A'!=i){
                    relation[i][arr[top[i]][k1]-'A'] = -1;
                    cnt++;
                }
                if(arr[bottom[i]][k1]-'A'!=i){
                    relation[i][arr[bottom[i]][k1]-'A'] = -1;
                    cnt++;
                }
            }
            for(int k2=top[i];k2<=bottom[i];k2++){
                if(arr[k2][left[i]]-'A'!=i){
                    relation[i][arr[k2][left[i]]-'A'] = -1;
                    cnt++;
                }
                if(arr[k2][right[i]]-'A'!=i){
                    relation[i][arr[k2][right[i]]-'A'] = -1;
                    cnt++;
                }
            }
            count[i]= cnt;
        }
        for(int i=0;i<26;i++){
            if(left[i]==999||!ok(i)) continue;
            path[0]=(char)(i+'A');
            dfs(i,1);
            path[0]=0;
        }
        Collections.sort(l);
        for(String x:l){
            sb.append(x).append("\n");
        }
        //System.out.print(sb.toString());
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frameup.out")));
        pw.print(sb.toString());
        pw.close();
        System.out.println("$:"+(System.currentTimeMillis()-start));
        System.exit(0);
    }
    static boolean ok(int n){
        for(int i=0;i<26;i++){
            if(left[i]==999) continue;
            if(!vis[i]&&relation[i][n]==-1) return false; //i should be before n, so when we about to place n, vis[i] should be true
        }
        return true;
    }
    static void dfs(int c, int level){
        if(level==total){
            String s = new String(path,0,level);
            l.add(s);
            return;
        }
        vis[c]=true;
        for(int i=0;i<26;i++){
            if(left[i]==999||vis[i]) continue;
            if(ok(i)){
                path[level]=(char)(i+'A');
                dfs(i,level+1);
                path[level]=0;
            }
        }
        vis[c]=false;
    }

}
