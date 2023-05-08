package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p15666 {
    static int N, M;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder("");
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int pre = 0;
        for(int i=0; i<N; i++){
            if(numbers[i]==pre) continue;

            int[] arr = new int[M];
            arr[0] = numbers[i];
            dfs(1, arr, numbers[i]);
            pre = arr[0];
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    static void dfs(int idx, int[] arr, int num){
        if(idx==M){
//            System.out.println(Arrays.toString(arr));
            for(int i=0; i<M; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int pre = 0;
        for(int i=0; i<N; i++){
            //동일한 index의 앞 수열과 똑같지 않게 체크 + 수열의 이전 index보다 크거나 같은 값만 다음 index에 추가
            if(numbers[i]==pre || numbers[i]<num) continue;

            arr[idx] = numbers[i];
            dfs(idx+1, arr, numbers[i]);
            pre = arr[idx];
        }

    }
}
