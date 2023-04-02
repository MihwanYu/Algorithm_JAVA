package DAY3;

import java.util.*;

class Jewerly implements Comparable<Jewerly>{
    int M, V; //M: 무게, V: 가치
    Jewerly(int M, int V){
        this.M = M;
        this.V = V;
    }

    public int getValue(){
        return this.V;
    }
    @Override
    public int compareTo(Jewerly j){
        //무게 순으로 오름차순
        return this.M-j.M;
    }

    @Override
    public String toString(){
        return "("+this.M+","+this.V+")";
    }
}

public class p1202 {
    static int N, K;
    static int[] bags;
    static List<Jewerly> jewels;
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        bags = new int[K];
        jewels = new ArrayList<>();
//        heap = new ArrayList<>(); priority heap 사용 -> arraylist 대신 priority queue(data삽입 시 자동 정렬 이점)


        for(int i=0; i<N; i++){
            jewels.add(new Jewerly(sc.nextInt(), sc.nextInt()));
        }
        for(int i=0; i<K; i++){
            bags[i] = sc.nextInt();
        }

        Collections.sort(jewels); // Comparator.comparingInt(Jewel::getWeight) 방식으로도 가능
        Arrays.sort(bags);
        //보석 높은 값 기준 힙
        PriorityQueue<Jewerly> pq = new PriorityQueue<>(Comparator.comparingInt(Jewerly::getValue).reversed());

//        System.out.println(Arrays.toString(jewels.toArray()));
//        System.out.println(Arrays.toString(bags));

        long sum = 0;
        int idx = 0;
        for (int b=0; b<K; b++){
            while (idx<jewels.size() && jewels.get(idx).M<=bags[b]){
                //heap에  가능한 모든 jewel 넣기 및 heap 정렬
                pq.add(jewels.get(idx));
                idx ++;
            }
            if(!pq.isEmpty()){
                sum += pq.poll().V; //priority queue에서 빼낸 값 sum에 추가
            }

        }
        System.out.println(sum);





    }
}
