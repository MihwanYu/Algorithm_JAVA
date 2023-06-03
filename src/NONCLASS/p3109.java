package NONCLASS;

import java.io.*;
import java.util.*;
public class p3109 {
    static int R, C;
    static char[][] map;
    static int[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new int[R][C];

        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = s.charAt(j);
            }
        }

        int count = 0;
        for(int i=0; i<R; i++){
            boolean res = dfs(i, 0);
            if(res) count++;
        }

        System.out.println(count);
    }

    static boolean dfs(int r, int c){
        if(c==C-1){
            //방문처리
            visited[r][c] = 1;
            return true;
        }

        visited[r][c] = 1;
        boolean res = false;
        //오른쪽 위 탐색
        if(r-1>=0 && c+1<C && map[r-1][c+1]=='.' && visited[r-1][c+1]==0){
            res = dfs(r-1, c+1);
        }
        //오른쪽 직진 탐색: 벽x, 방문한적x, 앞선(오른쪽위) 경로없을 때
        if(c+1<C && map[r][c+1]=='.' && visited[r][c+1]==0 && !res){
            res = dfs(r, c+1);
        }
        //오른쪽 아래 탐색
        if(r+1<R && c+1<C && map[r+1][c+1]=='.' && visited[r+1][c+1]==0 && !res){
            res = dfs(r+1, c+1);
        }

        //backtrack에서 false로 돌아오게 될 경우
        if( !res )
        visited[r][c] = -1;
        //true로 돌아오면 해당 경로 사용했으므로 true

        return res;
    }
}
