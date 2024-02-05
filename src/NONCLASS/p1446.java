package NONCLASS;

import java.io.*;
import java.util.*;

public class p1446 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[][] map = new int[D+1][D+1];

        for(int i=0; i<D; i++){
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            map[src][dst] = Math.min(map[src][dst], len );
        }



    }
}
