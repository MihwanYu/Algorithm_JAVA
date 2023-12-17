package NONCLASS;

import java.io.*;
import java.util.*;
public class p13335 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());//트럭의 수
        int w = Integer.parseInt(st.nextToken());//다리의 길이
        int L = Integer.parseInt(st.nextToken());//다리의 최대하중

        int[] trucks = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        int timer = 1;
        int weightTotal = trucks[0];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int truckIdx = 1;

        while(true){
//            System.out.println("다음트럭: "+truckIdx+" , 다리 위 트럭: "+Arrays.toString(queue.toArray())+" , time: "+timer);
            //더이상 다리에 남은 트럭이 없을 때: break
            //truckIdx가 n-1일 경우: 더이상 넣을 트럭이 없음 -> 빈공간 w-1개 넣어주기
            if(truckIdx==n){
                break;
            }

            //queue.size() == w 면 맨 앞 트럭 빼주기, 하중의 합 업데이트
            if(queue.size() >= w ){
                int tIdx = queue.poll();
                if(tIdx>-1) weightTotal -= trucks[tIdx];
            }

            //현재 weightTotal + 다음 트럭 weight <= L인지 보고 넣을 지 말 지 결정

            if(weightTotal + trucks[truckIdx] <= L){
                queue.add(truckIdx);
                weightTotal += trucks[truckIdx];
                truckIdx++;
            }else{
                queue.add(-1);//트럭 추가 못 할 때는 빈 공간
            }
            timer++;
        }

        timer += w;

        System.out.println(timer);

    }
}
