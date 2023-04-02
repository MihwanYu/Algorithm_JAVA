package DAY6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2252 {
    public static void main(String[] args) throws Exception{
        //위상 정렬: 그래프 선후관계 조건이 주어지면 노드의 순서를 정렬
//        코드 참고 https://codingnojam.tistory.com/67
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] backcount = new int[N+1]; //진입차수

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //a->b 순서일 때 graph index a의 arraylist에 b 추가
            graph.get(a).add(b);
            backcount[b] ++;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for(int i=1; i<backcount.length; i++){
            if (backcount[i]==0){ //i를 도착점으로 하는 경우가 하나도 없으면 (= i는 i랑 연결된 모든 노드 중 첫번째)
                q.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur);
            sb.append(" ");
            for(int i=0; i<graph.get(cur).size(); i++){
                int connected = graph.get(cur).get(i);
                backcount[connected]--;
                if(backcount[connected]==0){
                    q.add(connected);
                }
            }
        }
        System.out.println(sb.toString());

    }
}
