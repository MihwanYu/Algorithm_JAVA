package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class p1916 {
    static class Edge{
        int u, v, cost;
        public Edge(int v, int cost){
//            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", v=" + v +
                    ", cost=" + cost +
                    '}';
        }
    }
    static int N, M, start, end;
    static ArrayList<Edge>[] edges;
    static int[] mintable;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        mintable = new int[N+1];
        visited = new boolean[N+1];
        for(int i=1; i<=N; i++){
            mintable[i] = Integer.MAX_VALUE;
        }
        edges = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
          edges[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            Edge eg = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            edges[start].add(eg);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Dijstra(start);
        System.out.println(mintable[end]);

    }

    static void Dijstra(int num){
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });

        mintable[num] = 0;
        pq.add(new Edge(num, 0));
        while( !pq.isEmpty() ){
            Edge cur = pq.poll();
            int dst = cur.v;
            if( visited[dst] ) continue;
            visited[dst] = true;

            for(int i=0; i<edges[dst].size(); i++){
                Edge e = edges[dst].get(i);
                int nextV = e.v;
                int nextC = e.cost;

                int newcost = cur.cost+e.cost;
                if(mintable[e.v] > newcost){
                    mintable[e.v] = newcost;
                    pq.add(new Edge(e.v, mintable[e.v]));
                }
            }
//            System.out.println(cur);


        }


    }
}
