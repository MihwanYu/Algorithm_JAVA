package NONCLASS;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class p1504 {
    static int N, E;
//    static int[][] graph;
    static int[] minTable, visited;
    static ArrayList<int[]> adjList[];
    static List<Edge>[] path;

    static class Edge{
        int v, c;
        public Edge(int v, int c){
            this.v = v;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
//        graph = new int[N+1][N+1];
        //2차원배열 graph 대신 인접 Edge의 리스트 저장하는 배열 path
        path = new List[N+1];


        for(int i=1; i<N+1; i++){
//            minTable[i] = Integer.MAX_VALUE;
            path[i] = new ArrayList<>();
        }

        //u, v 각각 인접리스트에 Edge 추가
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            path[u].add(new Edge(v, c));
            path[v].add(new Edge(u,c));
        }

        st = new StringTokenizer(br.readLine());
        //1->N 갈때 경유해야하는 두 간선
        int u1 = Integer.parseInt(st.nextToken());
        int u2 = Integer.parseInt(st.nextToken());

        //최단경로 update:
        Dijstra(1);//1 -> u1, 1 -> u2 minTable에 저장
        int stToU1 = minTable[u1];
        int stToU2 = minTable[u2];
//        System.out.println(Arrays.toString(minTable));
        Dijstra(u1); // u1 -> u2, u1 -> N
        int u1Tou2 = minTable[u2];
        int u1Todst = minTable[N];
//        System.out.println(Arrays.toString(minTable));
        Dijstra(u2); // u2-> u1, u2 -> dst
        int u2Tou1 = minTable[u1];
        int u2Todst = minTable[N];
//        System.out.println(Arrays.toString(minTable));

        int cost1 = stToU1 + u1Tou2 + u2Todst;
        int cost2 = stToU2 + u2Tou1 + u1Todst;
//        System.out.println(Math.min(cost1, cost2));
//        System.out.println("cost1: "+stToU1+" + "+u1Tou2+" + "+u2Todst);
//        System.out.println("cost2: "+stToU2+" + "+u2Tou1+" + "+u1Todst);
//        System.out.println(cost1+" vs "+cost2);
        //아예 방문 불가능할경우 처리
        if(stToU1==Integer.MAX_VALUE || u1Tou2==Integer.MAX_VALUE || u2Todst==Integer.MAX_VALUE){
            cost1 = Integer.MAX_VALUE;
        }
        if(stToU2==Integer.MAX_VALUE || u2Tou1==Integer.MAX_VALUE || u1Todst==Integer.MAX_VALUE){
            cost2 = Integer.MAX_VALUE;
        }

        int answer = Math.min(Integer.MAX_VALUE, cost1);
        answer = Math.min(answer, cost2);
        if(answer==Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }
    }


    static void Dijstra(int start){
        //ascending priority queue 생성
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.c, o2.c);
            }
        });

        minTable = new int[N+1];
        for(int i=1; i<N+1; i++){
            minTable[i] = Integer.MAX_VALUE;
        }
        minTable[start] = 0;
        pq.add(new Edge(start,0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll(); //minimum cost Edge
            int v = cur.v;
            int c = cur.c;

            //v의 adjacent list path[v] 순회, v에서 각 edge까지 cost 비용 갱신
            for(int i=0; i<path[v].size(); i++){
                int nextV = path[v].get(i).v;
                int nextC = path[v].get(i).c;
                if(minTable[nextV] > c+nextC){
                    minTable[nextV] = c+nextC;
                    pq.add(new Edge(nextV, minTable[nextV]));
                }
            }
        }
    }

}
