package PROGRAMMERS;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class p172927 {
    public static void main(String[] args) throws Exception {
//        int[] picks1 = {1,3,2};
//        String[] minerals1 = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
//        int ans = solution(picks1, minerals1);
//        System.out.println("answer : "+ans);
////        System.out.println("================================");
//
//        int[] picks2 = {0,1,1};
//        String[] minerals2 = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
//        int ans2 = solution(picks2, minerals2);
//        System.out.println("answer : "+ans2);

        int[] picks3 = {1,0,0};
        String[] minerals3 = {"iron", "iron", "stone", "stone", "stone", "iron","iron", "stone", "diamond"};
        int ans3 = solution(picks3, minerals3);
        System.out.println("answer: "+ans3  );
    }

    static class Bundle implements Comparable<Bundle>{
        int dia, iron, stone, allamount;
        public void setAllamount(){
            this.allamount = this.dia*25 + this.iron*5 + this.stone;
        }
        @Override
        public int compareTo(Bundle o2){
            return Integer.compare(this.allamount, o2.allamount);
        }
    }
    public static int solution(int[] picks, String[] minerals) {
        /*계속 틀렸던 이유(TC 8)
        [1,0,0], ["iron", "iron", "stone", "stone", "stone", "iron", "iron", "stone", "diamond"]인 경우
        각 번들 피로도가 [13, 36] 이지만 광물을 캐는건 배열 순서대로기 때문에 36피로도를 먼저 캘 수 없음.
        번들 피로도 높은 순으로 정렬 후 다이아 > 철 > 돌 할당하는 방식이랑 상충되는 문제
         => 광물 5개를 묶어서 배열에 저장하는 코드를 곡괭이의 갯수만큼 실행, 이후 "가능한" 광물 번들들 끼리만 내림차순 정렬 시행
         */

        //picks[] 중 아무거나 선택 -> 사용 못할 때까지 사용(5개 후 파기)
        //minerals 순서 고정
        //minerals에 아무것도 없거나 or picks에 아무것도 없을 때까지
        //최소한의 피로도

        int pickSum = picks[0] + picks[1] + picks[2];//곡괭이 총 합
        Bundle[] bundlesum = new Bundle[minerals.length/5+1];
        for(int i=0; i<bundlesum.length; i++){
            bundlesum[i] = new Bundle();
        }
        for(int i=0; i<minerals.length; i++){
            if(i/5==pickSum) break;
            if(minerals[i].equals("diamond")){
                bundlesum[i/5].dia ++;
            }else if(minerals[i].equals("iron")){
                bundlesum[i/5].iron ++;
            }else{
                bundlesum[i/5].stone ++;
            }
            if(i%5==4){
                //해당 번들의 마지막 광물 종류 인풋 받으면 번들 별 amount 계산
                bundlesum[i/5].setAllamount();
            }
        }
        bundlesum[bundlesum.length-1].setAllamount();

        Arrays.sort(bundlesum, Comparator.reverseOrder());
        int pickIdx = 0;//곡괭이 종류 인덱스
        int answer = 0;


        for(int i=0; i<pickSum; i++){//곡괭이 수 만큼 반복
//            System.out.println("bundle 별 광물 info "+(i+1));
//            System.out.println("dia: "+bundlesum[i].dia + ", iron: "+bundlesum[i].iron + ", stone: "+ bundlesum[i].stone);

            if(i==bundlesum.length) break;//곡괭이 > 번들일 때 번들 다 반복하면 종료

            int fatigue = 0;
//            bundlesum[i]에 있는 광물 하나하나 뿌시면서 피로도 추가
            if(picks[0]>0){//다이아 곡괭이
                fatigue = bundlesum[i].dia + bundlesum[i].iron + bundlesum[i].stone;
                picks[0]--;
            }else if(picks[1] > 0){//철 곡괭이
                fatigue = bundlesum[i].dia*5 + bundlesum[i].iron + bundlesum[i].stone;
                picks[1] --;
            }else if(picks[2] > 0){
                fatigue = bundlesum[i].allamount;
                picks[2]--;
            }
            answer += fatigue;
//            System.out.println("번들 별 피로도: "+fatigue+"\n----------------------");
            picks[pickIdx] --;//다 뿌시면 곡괭이 하나 소진
        }


        return answer;
    }
}
