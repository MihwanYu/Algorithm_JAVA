package NONCLASS;


import java.io.*;
import java.util.*;

public class p10836 {
    static int M, N;
    static int[][] larva;
    public static void main(String[] args) throws Exception {
        // 알고리즘 분류: 구현, 시뮬레이션
        // 시간복잡도 고려를 깊이 해볼 수 있던 문제
        // 4번째 태스크 시간 초과 문제 참고: https://velog.io/@beclever/C-%EB%B0%B1%EC%A4%80-10836%EB%B2%88-%EC%97%AC%EC%99%95%EB%B2%8C
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        larva = new int[M][M];
        for(int i=0; i<M; i++){
            Arrays.fill(larva[i], 1);
        }

        int[] increase = new int[2*M-1];

        for(int i=0; i<N; i++){
            //N개의 날 동안 특정 애벌레들이 자라는 속도 입력
            st = new StringTokenizer(br.readLine());
            int zeros = Integer.parseInt(st.nextToken());
            int ones = zeros+Integer.parseInt(st.nextToken());
            int twos = Integer.parseInt(st.nextToken());

            int k=zeros;
            while(k<ones){
                increase[k] += 1;
                k++;
            }
            while(k<2*M-1){
                increase[k] += 2;
                k++;
            }

//            for(int r=0; r<M; r++){
//                for(int c=0; c<M; c++){
//                    System.out.print(larva[r][c]+" ");
//                }
//                System.out.println();
//            }
        }

        int j=0;
        int idx = M*(M-1);
        while(j<M*2-1){
//                System.out.println("idx "+idx+" , j "+j);

            if(idx==0){
                larva[0][0] += increase[j];
            }else{
                larva[idx/M][idx%M] += increase[j];
            }

            j++;
            if(j<M){
                idx -= M;
            }else{
                idx ++;
            }

        }


        //(r,c)가 1이상인 오른쪽 아래 영역의 애벌레들의 경우
        //크기 비교를 할 필요 없다: 왼쪽 vs 왼쪽위 vs 위쪽 비교하면 무조건 위쪽 애벌레 크기가 가장 큼
        //따라서 위쪽 애벌레 값을 그대로 출력하면 됨
        //그러면 배열 값 업데이트 할 필요도 없어짐

        StringBuilder sb = new StringBuilder("");
        for(int r=0; r<M; r++){
            sb.append(larva[r][0]).append(" ");//행 첫번째 col 추가
            for(int c=1; c<M; c++){//행 두번째 col부터는 0행 값과 일치하므로 동일 원소 반복 출력
                sb.append(larva[0][c]).append(" ");
            }
            sb.replace(sb.length()-1, sb.length(), "\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());

    }

}
