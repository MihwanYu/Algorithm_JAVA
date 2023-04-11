package NONCLASS;

import java.io.*;
import java.util.*;
public class p27114 {
    static class Pair{
        int dir, count, stemina;
        Pair(int d, int s, int c){
            this.dir = d; this.count = c; this.stemina = s;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] stemina = new int[3];

        for(int i=0; i<3; i++){
            stemina[i] = Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(st.nextToken());

        int answer = 0;
        int[] dir = {-1,1,2};

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0,0,0));

        while( !queue.isEmpty() ){
            Pair cur = queue.poll();
            //왼쪽으로 가는경우
            //오른쪽
            //뒤로돌아
            for(int i=0; i<3; i++){
                int nd = cur.dir + dir[i];
                if(nd<0) nd += 4;
                else if(nd>=4) nd = nd%4;

                int ns = cur.stemina + stemina[i];

                if(ns==K && nd==0) {
                    answer = cur.count+1;
                    break;
                }else if(ns<K){
                    queue.add(new Pair(nd, ns, cur.count+1));
                }
            }
            if(answer > 0) break;
        }
        if(answer==0) System.out.println(-1);
        else System.out.println(answer);


    }
}
