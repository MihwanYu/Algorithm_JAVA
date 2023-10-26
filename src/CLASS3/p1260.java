package CLASS3;

import java.io.*;
import java.util.*;
public class p1260 {
    static ArrayList<Integer>[] linkedarr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        linkedarr = new ArrayList[N+1];
        for(int n=1; n<=N; n++){
            linkedarr[n] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            linkedarr[u].add(v);
            linkedarr[v].add(u);
        }

        for(int n=1; n<=N; n++){
            //작은것부터 방문하기 위해 정렬
            Collections.sort(linkedarr[n]);
        }



        boolean[] visited = new boolean[N+1];
        visited[V] = true;

        StringBuilder sb = new StringBuilder();
        sb.append(V).append(" ");
        sb.append(dfs(V, visited));
        System.out.println(sb.toString());

        visited = new boolean[N+1];
        bfs(V, visited);

    }

    //1: 2,3,4
    //2: 1,4
    //3: 1,4
    //4: 1,3

    static StringBuilder dfs(int v, boolean[] visited){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<linkedarr[v].size(); i++){
            //v와 연결된 노드를 방문한 적 없을때에만,
            if(visited[linkedarr[v].get(i)]) continue;
            visited[linkedarr[v].get(i)] = true;
            sb.append(linkedarr[v].get(i)).append(" ");
            StringBuilder newsb = dfs(linkedarr[v].get(i), visited);
            sb.append(newsb);
        }

        return sb;
    }


    static void bfs(int v, boolean[] visited){
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();

        sb.append(v).append(" ");
        visited[v] = true;
        queue.add(v);

        while( !queue.isEmpty() ){
            int cur = queue.poll();

            for(int i=0; i<linkedarr[cur].size(); i++){
                if(visited[linkedarr[cur].get(i)]) continue;

                sb.append(linkedarr[cur].get(i)).append(" ");
                visited[linkedarr[cur].get(i)] = true;
                queue.add(linkedarr[cur].get(i));
            }
        }
        System.out.println(sb.toString());



    }
}
