package DAY8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2458 {
    //플로이드 워셜 응용
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int arr[][] = new int[N+1][N+1];
        for(int r=0; r<N+1; r++){
            for(int c=0; c<N+1; c++){
                if(r==c) {
                    arr[r][c]=0;
                }else{
                    arr[r][c] = Integer.MAX_VALUE;
                }

            }
        }

        int a,b;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;//a가 b앞에 있을 경우 1체크
        }

        for(int k=1; k<N+1; k++){
            for(int r=1; r<N+1; r++){
                for(int c=1; c<N+1; c++){
                    //최댓값 오버플로우 나서 잘못된 값 들어가지 못하게
                    if(arr[r][k]!=Integer.MAX_VALUE && arr[k][c]!=Integer.MAX_VALUE && arr[r][c]>arr[r][k]+arr[k][c]){
                        arr[r][c] = arr[r][k]+arr[k][c];
                    }
                }
            }
        }

        int count;
        int answer = 0;
        for(int r=1; r<N+1; r++){
            count = 0;
            for(int c=1; c<N+1; c++){
                //arr[r][c]를 출발점으로 하는 노드 + 도착점으로 하는 노드의 수 == N이 될 경우
                if(arr[r][c] != Integer.MAX_VALUE || arr[c][r] != Integer.MAX_VALUE){
                    count++;
                }
            }
            if(count==N){
                answer ++;
            }
        }

        System.out.println(answer);

    }
}
