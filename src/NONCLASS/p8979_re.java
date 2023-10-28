package NONCLASS;

import java.io.*;
import java.util.*;
public class p8979_re {
    public static class Nation implements Comparable<Nation>{
        int idx, gold, silver, copper;
        Nation(int idx, int gold, int silver, int copper){
            this.idx = idx; this.gold = gold; this.silver = silver; this.copper = copper;
        }

        @Override
        public int compareTo(Nation o2){
            //금메달 내림차순
            if(this.gold != o2.gold) return Integer.compare(o2.gold, this.gold);
            //은메달 내림차순
            if(this.silver != o2.silver) return Integer.compare(o2.silver, this.silver);
            //동메달 내림차순
            return Integer.compare(o2.copper, this.copper);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Nation> nations = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            nations.add(new Nation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int count = 1;
        Nation prior = nations.poll();
        if(prior.idx==K){
            System.out.println(count);
            return;
        }
        int rank = count;
        while( !nations.isEmpty() ){
            Nation cur = nations.poll();

            //prior과 비교해서 cur이 더 작다면
            //앞에 있던 국가들 count +1
            if( !(prior.gold == cur.gold && prior.silver == cur.silver && prior.copper == cur.copper)){
                rank = count+1;
            }

            //cur.idx가 K와 같다면 break
            //아니면 prior <- cur
            if(cur.idx==K) break;
            else prior = cur;
            count++;
        }
        System.out.println(rank);

        //국가별 등수 알려주기

//        int[][] rankings = new int[2][N];
//        //rankings[0][i] = 국가 idx
//        //rankings[1][i] = 국가 등수
//        int rank = 1;
//        for(int i=0; i<N; i++){
//            rankings[0][i] = nations[i].idx;
//            if(i>0){
//                //바로 앞 nations와 비교, 등수 차이가 있다면 rank++;
//                if(nations[i]<nations[i-1]){
//                    rank++;
//                }
//            }
//            rankings[1][i] = rank;
//        }



    }
}
