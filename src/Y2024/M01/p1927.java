package Y2024.M01;

import java.io.*;
import java.util.*;

public class p1927 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //최소 힙
        //입력 x에 대해서, 배열에 넣거나 or 0일 때 가장 작은 값 출력 및 제거
        int N = Integer.parseInt(br.readLine());
        PriorityQueue pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            int x = Integer.parseInt(br.readLine());
            if(x>0){
                pq.add(x);
            }else{
                if(pq.size()==0) sb.append(0).append("\n");
                else sb.append(pq.poll()).append("\n");
            }
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());


    }

}
