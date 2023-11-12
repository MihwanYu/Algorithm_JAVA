package CLASS2;

import java.io.*;
import java.util.*;

public class p10989 {
    //Priority Queue를 사용했을 때 시간초과가 나고, Array sort에서 통과 된 이유

    //PQ는 O(NlogN)인 만큼 원소의 push, pop이 빈번할 때 효율적이고,
    //정렬이 최종적으로 '한 번'만 필요할 경우 Array sort가 더 효과적이다.
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
//        PriorityQueue<Short> pq = new PriorityQueue<>();
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
//            pq.add(Short.parseShort(br.readLine()));
            arr[i] = Integer.parseInt(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        Arrays.sort(arr);
        for(int i=0; i<N; i++){
//            sb.append(pq.poll()).append("\n");
            sb.append(arr[i]).append("\n");
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());


    }
}
