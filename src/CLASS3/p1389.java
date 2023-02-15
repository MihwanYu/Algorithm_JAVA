package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1389 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N+1][N+1];
        int MAX = Integer.MAX_VALUE; //연결되지 않을 경우

        for(int r=1; r<N+1; r++){
            for(int c=1; c<N+1; c++){
                if(r==c) continue;
                graph[r][c] = MAX;
            }
        }

        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        for(int k=1; k<N+1; k++){
            //k를 연결점으로 할 때
            for(int r=1; r<N+1; r++){
                for(int c=1; c<N+1; c++){
                    if(graph[r][k]==MAX || graph[k][c]==MAX) continue;
                    if(graph[r][c] > graph[r][k]+graph[k][c]){
                        graph[r][c] = graph[r][k]+graph[k][c];
                    }
                }
            }
        }

        int minsum = Integer.MAX_VALUE;
        int minIdx = 0;
        for(int r=1; r<N+1; r++){
            int partialsum = 0;
            for(int c=1; c<N+1; c++){
                partialsum += graph[r][c];
//                System.out.print(graph[r][c]+" ");
            }
//            System.out.println();
            if(minsum>partialsum){
                minsum = partialsum;
                minIdx = r;
            }

        }
        System.out.println(minIdx);

    }
}
