package Y2024.M01;

import java.io.*;
import java.util.*;
public class p14889 {
    static int[] mem_start;
    static int N;
    static int g_minimum = Integer.MAX_VALUE;
    static int[][] strength;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        strength = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                strength[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //N명의 사람을 2 그룹으로 나누는 모든 방법에 대해서, 능력치의 합
        mem_start = new int[N/2];
        mem_start[0] = 0;//idx 0번인 사람
        for(int i=1; i<N; i++){
            mem_start[1] = i;
            dfs(1, i);
        }
        System.out.println(g_minimum);

    }

    static void dfs(int lastIdx, int val){
        if(lastIdx==mem_start.length-1){
            //경우의 수 다 채워넣었을 경우
            //start 합, link 합 구하기
            //최솟값 업데이트: Math.min(g_minimum, Math.abs(startSum-linkSum)
            int diff = getDiff();
            g_minimum = Math.min(g_minimum, diff);
            return;
        }

        // mem_start[lastId+1]자리에 val보다 큰 값 넣어야 함
        for(int i=val+1; i<N; i++){
            mem_start[lastIdx+1] = i;
            dfs(lastIdx+1, i);
        }


    }

    static int getDiff(){
        int startSum = 0;
        int linkSum = 0;

        boolean[] isStart = new boolean[N];

        for(int i=0; i<mem_start.length; i++){
            isStart[mem_start[i]] = true;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                //i, j 둘다 start팀일때 / i, j 둘다 link팀일때
                if(isStart[i]&&isStart[j]) startSum += strength[i][j];
                else if( !(isStart[i] || isStart[j]) ) linkSum += strength[i][j];
            }
        }
        return Math.abs(startSum-linkSum);

    }
}
