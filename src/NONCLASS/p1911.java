package NONCLASS;

import java.io.*;
import java.util.*;
public class p1911 {
    static class Pair implements Comparable<Pair>{
        int start, end;
        Pair(int start, int end){this.start = start; this.end = end;}
        @Override
        public int compareTo(Pair o2){
            return Integer.compare(this.start, o2.start);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

//        boolean[] load = new boolean[1000000001];
        Pair[] holes = new Pair[N];


        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            holes[i] = new Pair(start, end);
//            for(int p = start; p<end; p++){
//                load[p] = false;
//            }
        }

        Arrays.sort(holes);

        int answer = 0;
        int pre_end = -1;
        for(int h = 0; h<N; h++){
            int sp = pre_end>holes[h].start ? pre_end : holes[h].start;
            int lapp = (holes[h].end -sp)/L;
            if((holes[h].end - sp)%L>0) lapp++;
            pre_end = sp + lapp*L;
//            System.out.println(sp+" -> "+holes[h].end+" : "+lapp);
            answer += lapp;
        }
        System.out.println(answer);

    }
}
