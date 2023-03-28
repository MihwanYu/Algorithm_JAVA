package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p13549 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[100001];
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.MAX_VALUE; //초기화
        }

        if(N > K){
            System.out.println(N-K);
            return;
        }

        Queue<Integer> q = new LinkedList<>();

        arr[N] = 0;
        q.add(N);
        while( ! q.isEmpty() ){
            int n = q.poll();

            if(n*2 < arr.length && arr[n] < arr[n*2]){
                arr[n*2] = Math.min(arr[n*2], arr[n]); //순간이동
                q.add(n*2);
            }

            if(n+1<arr.length && arr[n]+1 < arr[n+1]){
                arr[n+1] = Math.min(arr[n+1], arr[n]+1); //걸어서 이동
                q.add(n+1);
            }

            if(n-1>=0 && arr[n]+1 < arr[n-1]){
                arr[n-1] = Math.min(arr[n-1], arr[n]+1); //걸어서 이동
                q.add(n-1);
            }
//            System.out.println("loop");
        }
        System.out.println(arr[K]);

    }
}
