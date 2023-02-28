package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11047 {
    public static void main(String[] args) throws Exception {
        //알고리즘 분류: 그리디
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] values = new int[N];
        for(int i=0; i<N; i++){
            values[i] = Integer.parseInt(br.readLine());
        }

        int total = 0;
        for(int i=N-1; i>=0; i--){
            if(values[i]>K) continue;
            total += K/values[i];
            K = K%values[i];
        }

        System.out.println(total);

    }
}
