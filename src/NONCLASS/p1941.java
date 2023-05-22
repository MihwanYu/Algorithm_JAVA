package NONCLASS;

import java.io.*;
import java.util.*;
public class p1941 {
    static int[][] map;
    static int countall;
    public static void main(String[] args ) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5];
        for(int i=0; i<5; i++){
            String s= br.readLine();
            for(int j=0; j<5; j++){
                if(s.charAt(j)=='Y'){
                    map[i][j] = 0;
                }else{
                    map[i][j] = 1;
                }
            }
        }

        int[] indexes = new int[7];
        countall = 0;
        for(int i=0; i<18; i++){
            indexes[0] = i;
            int countS = map[i/5][i%5];
            dfs(1, indexes, countS);
        }

        System.out.println(countall);

    }

    static void dfs(int idx, int[] indexes, int countS){
        if(idx==7){
            //map[idx]가 1이 4개 이상일 경우 확인
            //그때 모두 인접해있는지 확인
            if(countS >=4 && isconnected(indexes)) {
//                System.out.println(Arrays.toString(indexes));
                countall++;
            }
            return;
        }

        for(int i=indexes[idx-1]+1; i<25; i++){
            indexes[idx] = i;
            int nS = map[i/5][i%5];
            //nS + countS: 지금까지 1의 개수.
            //4-(nS + countS): 앞으로 나와야 하는 1의 개수 < 남은 칸 수6-idx -> 넘김
//            if((4-(countS+nS)) < (6-idx)) continue;
            dfs(idx+1, indexes, countS+nS);
        }

    }

    static boolean isconnected(int[] indexes){

        //인접했다 -> bfs
        int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
        Queue<Integer> q = new LinkedList<>();

        boolean visited[] = new boolean[7];//indexes 방문 확인
        visited[0] = true;
        q.add(indexes[0]);
        int cnt = 1, sCnt = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();
            int r = cur/5;
            int c = cur%5;
            if(map[r][c] == 'S') sCnt++;

            for(int i=0; i<4; i++) {
                for(int next=1; next<7; next++) {
                    if(!visited[next] && r+dr[i] == indexes[next]/5 && c+dc[i] == indexes[next]%5) {
                        visited[next] = true;
                        q.add(indexes[next]);
                        cnt++;
                    }
                }
            }
        }
        if(cnt == 7) {
            return true;
        }
        return false;
    }

}
