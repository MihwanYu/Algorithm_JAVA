package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class p7662 {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int testcases = Integer.parseInt(br.readLine());
        for(int i=0; i<testcases; i++){
            int operations = Integer.parseInt(br.readLine());
            pq_operate(operations);
        }

    }

    public static void pq_operate(int operations) throws Exception{
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        PriorityQueue<Integer> pq_desc = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

//        int pqsize = 0;
        Map<Integer, Integer> map = new HashMap<>();//숫자 정보 저장

        for(int i=0; i<operations; i++){
            st = new StringTokenizer(br.readLine());
            String opType = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            if(opType.equals("I")){
                pq.add(num);
                pq_desc.add(num);

                map.put(num, map.getOrDefault(num, 0)+1);
            }
            else if( !map.isEmpty() ){//Q 비어있는데 D연산 수행 시 무시
                if(num==1){
//                    int rm = pq_desc.poll();//최댓값 제거
//                    pq.remove(rm);//<- remove는 순차적으로 접근 후 equal==true일 때 제거하기 때문에 시간복잡도 O(N) 시간초과
                    //remove 대신 map 이용해서 queue 값 제거
                    removemap(pq_desc, map);
                }
                else{
//                    int rm = pq.poll();
//                    pq_desc.remove(rm);
                    removemap(pq, map);
                }

            }
        }

        if( map.isEmpty() ){
            System.out.println("EMPTY");
        }else if(map.size()==1){
            int n = removemap(pq, map);
            System.out.println(n+" "+n);
//            System.out.println(pq_desc.peek()+" "+pq.peek());
        }else{
            int n1 = removemap(pq_desc, map);
            int n2 = removemap(pq, map);
            System.out.println(n1+" "+n2);
        }

    }

    static int removemap(PriorityQueue<Integer> queue, Map<Integer, Integer> map){
        int n;
        while(true){
            n = queue.poll();
            int count = map.getOrDefault(n, 0);
            if(count==0){
                //queue에서 뺀 값이 map에 없을때 -> 이미 다른 queue 제거 연산에서 빠진 값이므로 다음 값 빼기
                continue;
            }

            if(count==1){
                //queue에서 뺀 값이 map에 1개 -> map에서 제거
                map.remove(n); //hashmap의 remove는 시간복잡도 O(1)
            }
            else{
                map.put(n, count-1);
            }
            //map의 특정 n값 count변경했으면 break
            break;
        }
        return n; //마지막 최댓값,최솟값 출력할 때 필요
    }
}
