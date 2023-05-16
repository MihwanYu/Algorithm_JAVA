package NONCLASS;
import java.io.*;
import java.util.*;

public class p2110 {
    public static void main(String[] args) throws Exception{
        // 문제 유형: 이분탐색. 이분탐색 항상 어려웡 ..
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] houses = new int[N];
        for(int i=0; i<N; i++){
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        // 문제 이해: https://st-lab.tistory.com/277
        // 접근 방법 이해: https://velog.io/@ngchaneok/%EB%B0%B1%EC%A4%80-2110%EB%B2%88-%EA%B3%B5%EC%9C%A0%EA%B8%B0-%EC%84%A4%EC%B9%98-C

        // 공유기 사이의 거리: 최소 1, 최대 houses[N-1]-houses[0]
        // 이분탐색: 거리에 대한 이분탐색
        // -> 최소-최대 거리 중앙값에 대해서, 그 거리 간격만큼 띄워서 공유기를 설치

        // 거리 별 공유기 대수
        int low = 1;
        int high = houses[N-1] - houses[0];

        int maxlen = 1;

        while(low<=high){
            int mid = (low+high)/2;

            //mid 간격으로 공유기 세웠을 때 개수 확인하기
            int count = 1;
            int prev = houses[0];//공유기를 설치한 직전 좌표
            for(int i=1; i<N; i++){
                if(houses[i]-prev >= mid){
                    count++;
                    prev = houses[i];
                }
            }

            //개수가 조건에 맞으면, mid가 lengthmax보다 크면 -> lengthmax = mid;
            if(count>=C){
                //공유기 세운 수가 지정 값보다 많다면
                //거리를 더 늘려주기
                low = mid+1;
                maxlen = Math.max(mid, maxlen);
            }else{
                //공유기 세운 수가 지정 값보다 적다면
                //거리를 더 좁혀주기
                high = mid-1;
            }

        }

        System.out.println(maxlen);


    }
}
