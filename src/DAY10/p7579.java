package DAY10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class p7579 {
    static class App implements Comparable<App>{
        int memory, cost;
        public App(int memory){
            this.memory = memory;
        }
        public void setCost(int cost){
            this.cost = cost;
        }

        @Override
        public int compareTo(App p2){
            int diffcost = this.cost-p2.cost;
            if(diffcost==0){
                return Integer.valueOf(this.memory).compareTo(p2.memory);
            }else{
//                return this.memory-p2.memory;
                return diffcost;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<App> applist = new ArrayList<App>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int v = Integer.parseInt(st.nextToken());
            applist.add(new App(v));
        }

        int costsum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int cost = Integer.parseInt(st.nextToken());
            applist.get(i).setCost(cost);
            costsum+= cost;
        }

        //cost 기준 오름차순 정렬
        Collections.sort(applist);

        int[] dp = new int[costsum+1];
        boolean findN = false;

        for(int i=0; i<applist.size(); i++){ //app들에 대해서
//            dp[applist.get(i).cost] = applist.get(i).memory;
            for(int idx=costsum; idx>=applist.get(i).cost; idx--){
                dp[idx] = Math.max(dp[idx], dp[idx - applist.get(i).cost] + applist.get(i).memory);

            }
        }

//        for(int i=0; i<dp.length; i++){
//            if(dp[i]>=M){
//                System.out.println(i);
//                break;
//            }
//        }
        int i = 0;
        while(dp[i]<M){
            i ++;
        }
        System.out.println(i);
    }
}
