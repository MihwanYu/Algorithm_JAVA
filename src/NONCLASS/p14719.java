package NONCLASS;

import java.io.*;
import java.util.*;
public class p14719 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] raindrops = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++){
            raindrops[i] = Integer.parseInt(st.nextToken());
        }
        //11 3 4 5 3 4 5 3 4 5 7<--반례
        int rainsum = 0;
        for(int i=1; i<W-1; i++){
            //i부터 왼쪽 가장 높은 곳
            //i부터 오른쪽 가장 높은 곳

            int maxl = 0, maxr = 0;
            for(int l=i; l>=0; l--){
                maxl = Math.max(maxl, raindrops[l]);
            }
            for(int r=i; r<W; r++){
                maxr = Math.max(maxr, raindrops[r]);
            }

            //maxl, maxr 중 낮은 높이 - 현재 높이 만큼 물 차오름
            int water = Math.min(maxl, maxr) - raindrops[i];
            if(water>0){
                rainsum += water;
            }

        }

        System.out.println(rainsum);

    }
}
