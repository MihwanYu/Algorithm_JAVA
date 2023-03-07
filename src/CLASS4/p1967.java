package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1967 {
    // 트리 : 사이클 없는 '무방향' 그래프 -> parent, child 구분할 필요 없음
    // 이진 트리 아닐 수 있음
    // -> 인접리스트의 배열로 구현
    static class Node{
        int v, w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    static ArrayList<Node>[] nodelist;
    static boolean[] visited;
    static int maxidx, maxlength;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        if(N==1){
            System.out.println(0);
            return;
        }

        nodelist = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            nodelist[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); //src
            int v = Integer.parseInt(st.nextToken()); //dst
            int w = Integer.parseInt(st.nextToken()); //weight
            nodelist[u].add(new Node(v, w));
            nodelist[v].add(new Node(u, w));
        }

        dfs(1, 0); // node 1에서 시작해서 가장 거리가 먼 곳을 maxidx에 저장, maxlength 갱신
        visited = new boolean[N+1];
        dfs(maxidx, 0); //maxlength 갱신

        System.out.println(maxlength);

    }

    static void dfs(int idx, int length){
        //node idx에서 방문할 수 있는 node가 더이상 없으면, 즉 leaf면
        //현재까지의 length와 max length 비교, 현재가 더 길면
        //maxidx <- idx, maxlength <- length

        boolean is_leaf = true;
        visited[idx] = true;
        //nodelist[idx]에 있는 Node 순회
        for(int i=0; i<nodelist[idx].size(); i++){
            //방문하려는 node
            Node dst = nodelist[idx].get(i);
            if(!visited[dst.v]){
                //dst node 방문한 적 없다면 -> 방문
                is_leaf = false;
                dfs(dst.v, length + dst.w);
            }
        }

        if(is_leaf){
            if(length > maxlength){
                maxlength = length;
                maxidx = idx;
            }
        }

    }



}
