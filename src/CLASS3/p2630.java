package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2630 {
    static int N;
    static int[][] grid;
    static int[] whiteblue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        whiteblue = new int[2]; //whiteblue[0] 흰색 종이 수 [1] 블루 종이 수
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,N);
        System.out.println(whiteblue[0]);
        System.out.println(whiteblue[1]);
    }

    static void dfs(int startR, int startC, int n){
        if(n==1){
            whiteblue[grid[startR][startC]]++;
            return;
        }
        //(startR, startC)부터 nxn 모두 같은 색인지 확인
        //하나라도 틀리면 바로 4개 쪼개서 dfs(n/2)시전

        boolean isIdentical = true;
        int firstcolor = grid[startR][startC];
        for(int r=startR; r<startR+n; r++){
            for(int c=startC; c<startC+n; c++){
                if(grid[r][c]==firstcolor) continue;

                isIdentical = false;
                break;
            }
        }

        //색상 동일할 경우 하나의 종이로 간주해서 증가
        if(isIdentical){
            whiteblue[firstcolor] ++;
            return;
        }

        //4개 쪼개서 dfs
        dfs(startR, startC, n/2);//좌측상단
        dfs(startR, startC+n/2, n/2);//우측상단
        dfs(startR+n/2, startC, n/2);//좌측하단
        dfs(startR+n/2, startC+n/2, n/2);//우측하단


    }
}
