package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p1204 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/SWEA/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        for(int t=0; t<testcase; t++){
            int tc = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int answer = getFrequent(st);
            System.out.println("#"+tc+" "+answer);
        }
    }

    static class Pair{
        int n, count;//점수 n이 count번 존재함
        Pair(int n, int count){this.n = n; this.count = count;}
    }
    static int getFrequent(StringTokenizer st){
        HashMap<Integer, Integer> freq = new HashMap<>();
        while(st.hasMoreTokens()){
            int n = Integer.parseInt(st.nextToken());
            freq.put(n, freq.getOrDefault(n,0)+1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.count==o2.count){
                    return Integer.compare(o2.n, o1.n);
                }
                return Integer.compare(o2.count, o1.count);
            }
        });

        for(Integer n: freq.keySet()){
            pq.add(new Pair(n, freq.get(n)));
        }


        return pq.peek().n;
    }
}
