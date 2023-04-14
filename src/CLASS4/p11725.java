package CLASS4;
import java.io.*;
import java.util.*;
public class p11725 {
    static int N;
    static int[] parents;
    static ArrayList<Integer>[] graph;
    static boolean visited[];
    static int find_parent(int u){
        if( parents[u]==u ) return u;

        return parents[u] = find_parent(parents[u]);
    }

    static void union(int u, int v){
        int u_parent = find_parent(u);
        int v_parent = find_parent(v);

        if(u_parent<v_parent){
            parents[v_parent] = u_parent;
        }else{
            parents[u_parent] = v_parent;
        }
        System.out.println("union "+u+","+v+" : "+u_parent+","+v_parent);
        System.out.println(Arrays.toString(parents));
    }
    public static void main(String[] args) throws Exception{
        //union-find로 왜 못풂?? 이해안감,,, 왜?? 왜???????????
        //처음에 루트 없다가 나중에 1이 루트 됐건 처음부터 1이 루트인 상태로 구현하건 똑같은거 아님??????
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parents = new int[N+1];
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }


        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        visited[1] = true;
        dfs(1);

        for(int i=2; i<=N; i++){
            System.out.println(parents[i]);
        }
    }

    static void dfs(int p){
//        System.out.println("node "+p+" : "+Arrays.toString(graph[p].toArray()));
        for(int i=0; i<graph[p].size(); i++){
            if(visited[graph[p].get(i)]) continue;
            visited[graph[p].get(i)] = true;
            parents[graph[p].get(i)] = p;//방문 안한 링크드 노드에 대해서
            dfs(graph[p].get(i));//그 노드의 링크드 노드 탐색

        }

    }
}
