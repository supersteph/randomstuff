/*
ID: daniel.20
LANG: JAVA
TASK: milk6
*/
//I will make it, I swear.
//There's no other way at all, all the other ways lead to hell.
//dyh 01.06.2014
import java.util.*;
import java.io.*;

class edge1{
    int from,to;
    long w;
    public edge1(int a, int b, long c){
        from=a;to=b;w=c;
    }
}

public class milk6 {
    static StringBuilder sb = new StringBuilder();
    //static long start = System.currentTimeMillis();
    static long graph[][];
    static edge1 edges[];
    static boolean vis[];
    static int n, m, index;
    static long graphCopy[][];
    static int level[];

    static boolean bfs(int s, int t) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        Arrays.fill(level, -1);
        level[s] = 0;
        while (!q.isEmpty()) {
            int tmp = q.poll();
            for (int i = 0; i < n; i++) {
                if (graph[tmp][i] > 0 && level[i] < 0) {
                    level[i] = level[tmp] + 1;
                    q.add(i);
                }
            }
        }
        return level[t] != -1;
    }

    static long dfs(int u, int t, long min) {
        if (u == t) return min;
        for (int i = 0; i < n; i++) {
            if (graph[u][i] == 0 || level[i] != level[u] + 1) continue;
            long d = dfs(i, t, Math.min(min, graph[u][i]));
            if (d > 0) {
                graph[u][i] -= d;
                graph[i][u] += d;

                return d;
            }
        }
        return 0;
    }

    static long maxFlow(int s, int t) {
        long flow = 0;
        while (bfs(s, t)) {
            flow += dfs(s, t, Long.MAX_VALUE);
        }
        return flow;
    }

    static void makeCopy() {
        for (int i = 0; i < n; i++) {
            System.arraycopy(graph[i], 0, graphCopy[i], 0, n);
        }
    }

    static void recover() {
        for (int i = 0; i < n; i++) {
            System.arraycopy(graphCopy[i], 0, graph[i], 0, n);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("milk6.in"));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());
        graph = new long[n][n];
        graphCopy = new long[n][n];
        edges = new edge1[2*m+1];
        vis = new boolean[n];
        level = new int[n];
        index=0;
        for(int i=0;i<m;i++){
            st = new StringTokenizer(reader.readLine());
            int t1 = Integer.valueOf(st.nextToken())-1;
            int t2 = Integer.valueOf(st.nextToken())-1;
            int t3 = Integer.valueOf(st.nextToken())*1001+1;
            graph[t1][t2]+=t3;
            edge1 tmp = new edge1(t1,t2,t3);
            edges[index++]=tmp;
        }
        makeCopy();
        long minCut = maxFlow(0,n-1);
        sb.append(minCut/1001).append(" ").append(minCut%1001).append("\n");
        recover();
        int edge_index=0, cnt=0;
        for(int i=0;i<index;i++){
            if(edges[i].w>0){
                edge_index++;
                graph[edges[i].from][edges[i].to]-=edges[i].w;
                long tmpCut = maxFlow(0,n-1);
                //System.out.println(tmpCut);
                if(minCut-tmpCut==edges[i].w){
                    cnt++;
                    sb.append(edge_index).append("\n");
                }
                if(cnt==minCut%1001) break;
                recover();
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));
        System.out.print(sb.toString());
        pw.print(sb.toString());
        pw.close();
        System.exit(0);
    }
}
