package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11403 {
    static int N;
    static int[][] graph;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        graph = new int[N][N]; // i -> j
        visited = new boolean[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r=0; r<N; r++){
            clearvisited(); //all false
//            System.out.println("=========================\nr="+r);
            dfs(r);
        }

        StringBuilder sb = new StringBuilder();
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                sb.append(graph[r][c]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int r){
        if(visited[r]) return;
        visited[r] = true;
        for(int i=0; i<N; i++){
            if(graph[r][i] == 1){
                dfs(i);
                operateOR(r,i);
            }
        }
    }

    static void operateOR(int r, int i){
        //i번째 줄의 j번째 col에 1이 있으면 r번째 줄의 j번째 col에도 1 추가
        for(int j=0; j<N; j++){
            if(graph[i][j]==1){
                graph[r][j] = 1;
            }
        }

    }

    static void clearvisited(){
        visited = new boolean[N];
    }
}
