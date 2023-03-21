package WEEKLY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.IntToDoubleFunction;

public class p15738 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] intarr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            intarr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++){
            int oper = Integer.parseInt(br.readLine());
            if(oper>0){
                //앞에 oper개 뒤집기: idx 1 ~ oper
                //뒤집는 범위에 K가 있다면 K값 변경
                if(K <= oper){
                    K = oper - (K-1);
                }
            }else{
                //뒤에 Math.abs(oper)개 뒤집기: idx (N-oper+1) ~ N
                int start = N+oper+1;
                if(K >= start){
                    K = N - (K-start);
                }
            }
        }

        System.out.println(K);
    }
}
