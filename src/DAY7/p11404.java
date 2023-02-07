package DAY7;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p11404 {
    static int N, M;
    static long[][] distance;
    public static void main(String[] args) throws Exception{
        //플로이드 워셜: 모든 정점 a->b의 최단경로 -> 시간복잡도 O(n^3)의 악랄한 반복문 ..
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        //distance: row -> col까지 가는 최단경로
        distance = new long[N+1][N+1];

        //distance 초기화
        for (int r=1; r<N+1; r++){
            for(int c = 1; c<N+1; c++){
                distance[r][c] = Integer.MAX_VALUE;
                if(r==c){
                    distance[r][c] = 0;
                }
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            //시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
            if(distance[a][b]>c) distance[a][b] = c;
        }

        //중간 노드 역할을 k가 함
        for(int k = 1; k<= N; k++){
            for(int r = 1; r <= N; r++){
                for(int c = 1; c<=N; c++){
                    //r->c로 가는 길 vs r->k 거친 후 k->c 거쳤을 때 비교 후 업데이트
                    distance[r][c] = Long.min(distance[r][c], distance[r][k]+distance[k][c]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int r = 1; r<=N; r++){
            for(int c = 1; c<=N; c++){
                if(distance[r][c]==Integer.MAX_VALUE){
                    sb.append(0);
                }else{
                    sb.append(distance[r][c]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());



    }
}
