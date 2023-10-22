package CLASS2;

import java.io.*;
import java.util.*;

public class p1966 {

    static class Paper implements Comparable<Paper>{
        int idx, priority;
        Paper(int idx, int priority){
            this.idx = idx; this.priority = priority;
        }

        @Override
        public int compareTo(Paper o2){
            if(o2.priority == this.priority){
                return Integer.compare(this.idx, o2.idx);
            }else{
                return Integer.compare(o2.priority, this.priority);
            }
        }

        @Override
        public String toString(){
            return "idx("+this.idx+"), priority("+this.priority+")";
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine());

        for(int i=0; i<testcase; i++){
//            System.out.println("====CASE "+i+"====");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

//            PriorityQueue<Paper> pq = new PriorityQueue<>();
            Queue<Paper> paper = new LinkedList<>();

            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });

            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int priority = Integer.parseInt(st.nextToken());

                pq.add(priority);
                paper.add(new Paper(j, priority));

            }
            //count <- idx가 몇 번째로 출력된 문서인지
            //먼저 입력된 문서 먼저 출력
            //가장 앞 문서에 대해) prior_max보다 중요도가 낮아면 -> 맨 뒤로 재배치
            int count = 1;
            int max_prior = pq.poll();
            while(! paper.isEmpty() ){
                Paper cur = paper.poll();
                //현재 가장 중요한 문서인지 확인
//                System.out.println("cur: "+cur);
//                System.out.println("max prior: "+max_prior);

                if(cur.priority == max_prior){
                    //중요한데 타겟 아니면 넘어가고
                    //중요한데 타겟이면 break
                    if(cur.idx == idx) break;
                    max_prior = pq.poll();
                    count++;
//                    System.out.println("최우선순위 제거");
//                    System.out.println("업데이트 우선순위: "+max_prior);
                }else{
                    //중요하지 않으면 큐 맨 뒤로 넘기기
                    paper.add(cur);
//                    System.out.println("not 최우선순위 뒤로");
                }
            }

            System.out.println(count);
        }




    }
}
