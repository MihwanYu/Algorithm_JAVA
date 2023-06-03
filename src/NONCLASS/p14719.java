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

        int p1=0, p2=0;
        int rainsum = 0;
        while(p2<W-1){
            //raindrops[p2] >= raindrops[p1]이면

            //앞 높이 < 뒤 높이일 때
            if(raindrops[p2] >= raindrops[p1]  ){
//                System.out.println(p1+"~"+p2);
                int shorter = raindrops[p1];
                int rainpart = 0;
                for(int p=p1+1; p<p2; p++){
                    rainpart += shorter-raindrops[p];
                }
                rainsum += rainpart;
                p1 = p2;
            }
            else if(raindrops[p2] >= raindrops[p2+1] && p2-p1>1){
                //p1부터 빗물 막아서 덧셈   *p1위치가 제일 낮은 곳이라 빗물 안 고였을 수도 있음
//                System.out.println("(sec)"+p1+"~"+p2);
                int shorter = raindrops[p1]<raindrops[p2] ? raindrops[p1] : raindrops[p2];
//                System.out.println("shorter:"+shorter);
                int rainpart = 0;
                for(int p=p1+1; p<p2; p++){
                    if(shorter-raindrops[p]>0)
                    rainpart += shorter-raindrops[p];
                }
                rainsum += rainpart;
                p1 = p2;
            }
            p2++;
        }


//        System.out.println("(last)"+p1+"~"+p2);
        int shorter = raindrops[p1]<raindrops[p2] ? raindrops[p1] : raindrops[p2];
        int rainpart = 0;
        for(int p=p1+1; p<p2; p++){
            if(shorter-raindrops[p]<0) break;
            rainpart += shorter-raindrops[p];
        }
        rainsum += rainpart;


        System.out.println(rainsum);

    }
}
