package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p15663 {
    static int N, M;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder("");
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());//N개의 수로
        M = Integer.parseInt(st.nextToken());//M 길이의 수열 만들기
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0 ;i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);//오름차순

        int[] arr = new int[M];
        boolean[] exIdx = new boolean[N];
        int prenum = 0;
        for(int i=0; i<N; i++){
            if(prenum==numbers[i]) continue;//중복되는 수열 여러번 x
            arr[0] = numbers[i];
            exIdx[i] = true;//배열 앞에서 사용한 idx 자리 true 표시
            dfs(arr, 1, exIdx);
            exIdx[i] = false;
            prenum = arr[0];
        }

        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb.toString());
    }

    static void dfs(int[] arr, int idx, boolean[] exIdx){
        if(idx==M){
//            System.out.println(Arrays.toString(arr));
            for(int i=0; i<M; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        //idx+1자리에 값 넣기
        int prenum = 0;
        for(int i=0; i<N; i++){
            if(prenum==numbers[i]) continue;//중복되는 수열 여러번 x
            if(exIdx[i]) continue;//이미 사용한 idx 자리면 넘어가기
            arr[idx] = numbers[i];
            exIdx[i] = true;
            dfs(arr, idx+1, exIdx);
            exIdx[i] = false;
            prenum = arr[idx];
        }

    }
}
