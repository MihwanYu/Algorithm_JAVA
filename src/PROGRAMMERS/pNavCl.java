package PROGRAMMERS;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class pNavCl {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] games = {{66000, 0, 50}, {16000, 2, 10}, {84500, 3, 20}, {5500, 2, 75}};
        int answer = solution(games );
        System.out.println(answer);

        int[][] games2 = {{100,0,50}, {1000, 0, 50}, {10000, 0, 50}};
        answer = solution(games2);
        System.out.println(answer);

        int[][] games3 = {{100, 2, 50}, {100, 2, 50}, {1000, 4, 50}, {1000, 4, 50}, {1000, 4, 50}};
        answer = solution(games3);
        System.out.println(answer);

        int[][] games4 = {{10000, 0, 90}, {10000, 0, 90},{10000, 0, 90}, {10000, 0, 90}, {10000, 0, 90}, {10000, 0, 90},{10000, 0, 90}, {10000, 0, 90}, {10000, 0, 90},{10000, 0, 90}, {10000, 9, 90}};
        answer = solution(games4);
        System.out.println(answer);
        //day 2: (100,50), (100,50), (1000, 10), (1000, 10)
        //day 4: (1000, 50), (1000, 50)

    }

    static class Game implements Comparable<Game>{
        int originPrice, saledate, salerate, saleamount;
        Game(int price, int date, int rate, int saleamount){
            this.originPrice = price; this.saledate = date; this.salerate = rate; this.saleamount = saleamount;
        }
        @Override
        public int compareTo(Game o2){
            //saleamount 오름차순
            return Integer.compare(this.saleamount, o2.saleamount);
        }
    }

    static int solution(int[][] games){
        int n = games.length;
        int[] buying = new int[n]; // day n에 할인해서 구매한 금액 정보 입력. 안담으면 0 초기화
        ArrayList<Game>[] sales = new ArrayList[n]; //day n에 세일하는 게임 리스트
        //pq: buying에 바로 추가하지 않은 게임 리스트, 할인금액 내림차순 정렬
        PriorityQueue<Game> pq = new PriorityQueue<>(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.compareTo(o1);
            }
        });

        for(int i=0; i<n; i++){
            sales[i] = new ArrayList<>();
        }

        for(int[] game: games){
            int saledate = game[1];
            Game g = new Game(game[0], saledate, game[2], (int)(game[0]*game[2]*0.01 ));
            sales[saledate].add(g);
        }


        int itemday = -1;//item 할인 존재하는 day 갱신

        for(int i=0; i<n; i++){
            if( sales[i].size()==0 ) continue;
            else if( sales[i].size()==1 ){
                //day i에 세일하는 게임 하나만 -> 그 게임 구입하면 됨
                buying[i] = (sales[i].get(0).originPrice - sales[i].get(0).saleamount);
                itemday = i;
            }else{
                //day i에 세일하는 게임 여러개 : 1개 "이상" 구매 가능
                Collections.sort(sales[i], Collections.reverseOrder());
                //할인액 max(idx 0) 게임만 우선 추가
                buying[i] = (sales[i].get(0).originPrice - sales[i].get(0).saleamount);

                //idx 1이후 게임 -> pq에 추가
                for(int k=1; k<sales[i].size(); k++){
                    pq.add(sales[i].get(k));
                }
                itemday = i;
            }
        }


        int buysum = 0;//리턴할 누적 합

        //n=3인데 day0에서 3개 게임이 할인 -> 기존 추가(1개) 게임 외에 2개 게임을 추가로 day0에서 할인받음
        int saleplus = n-1-itemday;

        //saleplus만큼 pq에 있는 game을 할인된 금액으로 구매
        int idx = 0;
        while( !pq.isEmpty() ){
            Game g = pq.poll();
            if(idx<saleplus){
                buysum += (g.originPrice-g.saleamount);//할인가 구매
            }else{
                buysum += g.originPrice;//정가 구매
            }
            idx++;
        }

//        System.out.println(buysum);
//        System.out.println("+ buy array: "+Arrays.toString(buying));
        for(int i=0; i<n; i++){
            buysum += buying[i];
        }

        return buysum;
    }
}
