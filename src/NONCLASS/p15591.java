package NONCLASS;

import java.io.*;
import java.util.*;
public class p15591 {
    static int N, Q;
    static ArrayList<Node>[] graph;

    static class Node{
        int v, cost;
        Node(int v, int cost){this.v = v; this.cost = cost;}
    }

    public static void main(String[] args) throws Exception{
        //알고리즘 : bfs
        //참고: https://imnotabear.tistory.com/228
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        //graph 초기화
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        //p, q, r 입력(N-1쌍)
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int usado = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, usado));
            graph[v].add(new Node(u, usado));
        }

        //Q개 질문
        for(int i=0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int answer = bfs(k,v);
            System.out.println(answer);
        }

    }

    static int bfs(int k, int start){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        //visited true되는 조건: 처음으로 유사도가 k이상이 나왔을 때
        boolean[] visited = new boolean[N+1];
        visited[start] = true;

        int count = 0;
        while( !queue.isEmpty() ){
            int idx = queue.poll();

            //graph에서 node.v에 있는 node를 탐색하기
            for(int i=0; i<graph[idx].size(); i++){
                Node nextnode = graph[idx].get(i);
                //node.v와 연결된 next node 중 방문하지 않은(유사도 모르는 애들)
                int nextidx = nextnode.v;
                if(visited[nextidx]) continue;

                //next node의 cost와 node의 cost를 비교하기
                if(nextnode.cost>=k){
                    visited[nextidx] = true;
                    count++;
                    queue.add(nextidx);
                }

            }
        }


        return count;
    }

    //다익스트라로 처음엔 풀었지만 최솟값 비교 하기에 적절치 못한 것 같아서 포기 ..
    static int dijkstra(int k, int start){
        //mintable 생성 후 초기값 최대로 설정
        int[] mintable = new int[N+1];
        Arrays.fill(mintable, Integer.MAX_VALUE);

        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });

        mintable[start] = 0;
        pq.add(new Node(start, 0));
        while( !pq.isEmpty() ){
            Node now = pq.poll();
            if( !visited[now.v] ) visited[now.v] = true;

            for(Node next: graph[now.v]){
                int mincost = Math.min(now.cost, next.cost);
                if( !visited[next.v] && mintable[next.v] > mincost){
                    mintable[next.v] = mincost;
                    pq.add(new Node(next.v, mintable[next.v]));
                }
            }

        }

        int count = 0;
        for(int i=1; i<=N; i++){
            if(i==start) continue;
            if(mintable[i]>=k) count++;
        }
        System.out.println(Arrays.toString(mintable));
        return count;
    }


}
