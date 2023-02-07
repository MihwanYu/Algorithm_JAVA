package DAY7;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class p1753 {
    static int N, M, K;
    static int[] visited;
    static int[] minTable;
    static ArrayList<int[]> adjList[];

    static class Edge{
        int v, w;
        public Edge(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception{
        //다익스트라 최단경로
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        List<Edge>[] path = new List[N+1];

        minTable = new int[N+1];
        for(int i = 1; i<N+1; i++){
            minTable[i] = Integer.MAX_VALUE;
            path[i] = new ArrayList<>();
        }

        //adjacent list
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            path[u].add(new Edge(v,w)); //u에서 시작점인 간선 리스트에 (u->v, weight: w)인 edge 추가
        }

        //오름차순 우선순위 큐
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.w - o2.w;
            }
        });

        minTable[K] = 0; //시작점 K는 weight가 0
        pq.add(new Edge(K, 0));
        while(!pq.isEmpty()){
            Edge cur = pq.poll(); //우선순위 큐에서 weight가 가장 작은 엣지
            int v = cur.v; //그 엣지의 목적지
            int w = cur.w;

            //v의 인접리스트 path[v]순회: 목적지인 특정 노드 v에서 인접한 i번째 edge들에 접근하는 비용 갱신
            // minTable에 있는 기존 값 vs pq에서 뺀 값 비교, 최솟값 갱신
            for(int i=0; i<path[v].size(); i++ ){
                int nextV = path[v].get(i).v;
                int nextW = path[v].get(i).w;
                if(minTable[nextV] > minTable[v]+nextW){
                    //nextV까지의 weight가 기존 값보다 v를 경유해 가는 게 더 저렴할 경우 v를 경유해 가는것으로 갱신
                    minTable[nextV] = minTable[v]+nextW;
                    pq.add(new Edge(nextV, minTable[nextV]));
                }
            }
        }

        for(int i=1; i<N+1; i++){
            if(minTable[i]==Integer.MAX_VALUE){
                //한번도 갱신 x -> INF 출력
                System.out.println("INF");
            }else{
                System.out.println(minTable[i]);
            }
        }

    }
}
