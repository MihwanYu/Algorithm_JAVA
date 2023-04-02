package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p18870{
    public static void main(String[]args) throws Exception{
        //접근방식: priority queue, hashmap

        // 배열 2개 만들어서 하나 정렬해놓고 -> 해시맵에 idx 넣는 방식으로도 가능했음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] values = new int[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });


        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            values[i] = num;
            pq.add(num);
        }

//        System.out.println(pq.toString());

        HashMap<Integer, Integer> hp = new HashMap<>();
        int idx = 0;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            if(hp.containsKey(cur)){
                continue;
            }
            hp.put(cur, idx);
            idx++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(hp.get(values[i])).append(" ");
//            System.out.print(hp.get(values[i])+" ");
        }
        System.out.println(sb.toString());
/*
int curidx = 0;
while():
int cur = pq.poll() 현재 값 뽑기: 2, 4, ...
hashmap에 2,4,-10,4,-9 각각 -1 들어가있음
hashmap[cur] <- 이미 존재한다면 pass idx 증가x, 없다면 curidx 삽입하고 idx 증가o

 */

    }
}
