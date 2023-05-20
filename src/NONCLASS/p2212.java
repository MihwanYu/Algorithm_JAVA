package NONCLASS;

import java.io.*;
import java.util.*;
public class p2212 {
    static class Pair{
        int preidx, diff;
        Pair(int idx, int diff){
            this.preidx = idx; this.diff = diff;
        }
    }
    public static void main(String[] args) throws Exception{
        //처음 풀이 런타임에러(nullpoint) -> N<K일 경우 고려해서 다시 풂
        //처음 접근: 좌표 간 거리 가장 큰 구간에서 분할하고 나머지 더했는데
        //우선순위 큐로 구간 내림차순 쓸 필요 없이 가장 작은 구간부터 N-K개 구간 더하면 될 일이었다

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensors = new int[N];
        //N개의 센서는 동일 좌표에 있을 수도 있다.

        //각 집중국의 수신 가능 영역 길이의 합을 최소화한다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensors);
        //1 3 6 6 7 9

        int[] diff = new int[N-1];
        for(int i=0; i<N-1; i++){
            diff[i] = sensors[i+1]-sensors[i];
        }
        Arrays.sort(diff);
        int countall = 0;
        for(int i=0; i<N-K; i++){
            countall += diff[i];
        }
        System.out.println(countall);

        /* 처음 접근
        //pairs: 좌표 간 거리 내림차순
        PriorityQueue<Pair> pairs = new PriorityQueue<>((o1,o2)->Integer.compare(o2.diff,o1.diff));


        for(int i=0; i<N-1; i++){
            pairs.add(new Pair(i, sensors[i+1]-sensors[i])) ;
        }

        int[] partitions = new int[K-1];
        Arrays.fill(partitions, 10000);
        for(int k=0; k<K-1 && !pairs.isEmpty(); k++){
            Pair p = pairs.poll();
            partitions[k] = p.preidx;
        }

        Arrays.sort(partitions);
        int preIdx = 0;
        int countall = 0;
        for(int k=0; k<K-1; k++){
            if(partitions[k]==10000) break;
            countall += sensors[partitions[k]] - sensors[preIdx];
            preIdx = partitions[k]+1;
        }
        countall += sensors[N-1] - sensors[preIdx];
        System.out.println(countall);

         */



    }
}
