package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class p11286 {
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //절댓값 기준 오름차순
                if(Math.abs(o1)==Math.abs(o2)){
                    //절댓값 동일하면 가장 작은 수(-> 음수)
                    return Integer.compare(o1, o2);
                }
                return Integer.compare(Math.abs(o1), Math.abs(o2));
            }
        });

        for(int i=0; i<N; i++){
            int input = Integer.parseInt(br.readLine());
            if(input==0){
                if(pq.isEmpty()){
                    System.out.println(0);
                }else{
                    System.out.println(pq.poll());
                }

            }else{
                pq.add(input);
            }
        }
    }
}
