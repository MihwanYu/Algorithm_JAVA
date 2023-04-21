package NONCLASS;
import java.io.*;
import java.util.*;

public class p8979 {
    static class Nation{
        int nid, gold, silver, cu;
        Nation(int nid, int gold, int silver, int cu){
            this.nid = nid; this.gold = gold; this.silver= silver; this.cu = cu;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Nation> pq = new PriorityQueue<>(new Comparator<>(){
            @Override
            //금->은->동 더 많은 나라 => 내림차순
            public int compare(Nation o1, Nation o2){
                if(o1.gold != o2.gold){
                    return Integer.compare(o2.gold, o1.gold);
                }else if(o1.silver != o2.silver){
                    return Integer.compare(o2.silver, o1.silver);
                }else if(o1.cu != o2.cu){
                    return Integer.compare(o2.cu, o1.cu);
                }else{
                    //동메달 수까지 같으면 공동 등수
                    return Integer.compare(o1.nid, o2.nid);
                }
            }
        });
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(new Nation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

        }

        int order = 1;
        int pre_gold=0, pre_silver=0, pre_cu=0;
        int lastwinner = 1;
        while(!pq.isEmpty()){
            Nation cur = pq.poll();

            //현재값==이전값 완벽히 똑같다면
            //lastwinner 변동x
            //현재랑 이전이랑 다르다면
            //lastwinner = order
            if(!(pre_gold==cur.gold && pre_silver==cur.silver && pre_cu==cur.cu)){
                lastwinner = order;
            }

            order ++;//말그대로순서
            pre_gold = cur.gold;
            pre_silver = cur.silver;
            pre_cu = cur.cu;

            if(cur.nid==K){
                break;
            }

        }

        System.out.println(lastwinner);



    }
}
