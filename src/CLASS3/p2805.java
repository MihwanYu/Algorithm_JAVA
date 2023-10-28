package CLASS3;

import java.io.*;
import java.util.*;

public class p2805 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());
        //높이 H 지정
        //적어도 M미터의 나무를 집에 가져가기 위해 절단기에 설정할 높이 H의 최댓값

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        int maxH = 0;
        int minH = 1;
        int ansH = 0;

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            maxH = Math.max(maxH, arr[i]);
//            minH = Math.min(minH, arr[i]);
        }

        // arr[i] - H의 합(total)이 M 이상이어야 함
        while( minH <= maxH ){
            int midH = (maxH+minH) / 2;

            long total = 0;
            //midH 이상인 나무를 베었을 때의 total 구하기
            for(int i=0; i<N; i++){
                if(arr[i]>midH) total += arr[i]-midH;
            }
//            System.out.println("min("+minH+"), max("+maxH+"), mid("+midH+"), total("+total+")");
            //total이 M보다 적다면, midH가 너무 높은거임. maxH <- midH
            if(total < M){
                maxH = midH-1;
            }else{
                minH = midH+1;
                ansH = Math.max(ansH, midH);
            }
            //total이 M보다 크다면, minH <- midH
            //midH가 최적의 H이거나, 너무 낮은거임. minH <- midH, ansH = Math.max(ansH, midH)

        }
        System.out.println(ansH);


    }

}
