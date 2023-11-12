package CLASS2;

import java.io.*;
import java.util.*;
public class p1018 {
    static char[][] arrorigin;
    static class Point{
        int r, c;
        char tobe;//(r,c)에 원래 있어야 하는 컬러
        Point(int r, int c, char tobe){this.r = r; this.c = c; this.tobe = tobe;}

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // MxN 크기의 보드 -> 8x8 크기 체스판
        // 체스판: 검,흰 번갈아 칠해져야함 -> 2가지 방법

        // 지민이가 다시 칠해야 하는 정사각형의 최소 개수?

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arrorigin = new char[N][M];
        for(int i=0; i<N; i++){
            arrorigin[i] = br.readLine().toCharArray();
        }

        // (r, c)에 대해서 -> 8x8 했을 때 변경 사각 개수
        int mincount = N*M;

        for(int r=0; r<N-7; r++){
            for(int c=0; c<M-7; c++){
                int count = bfs(r,c);
                if(mincount>count) mincount = count;
            }
        }

        System.out.println(mincount);
    }

    static int bfs(int r, int c){
        char[][] arr = new char[arrorigin.length][arrorigin[0].length];
        for(int i=0; i<arrorigin.length; i++){
            for(int j=0; j<arrorigin[0].length; j++){
                arr[i][j] = arrorigin[i][j];
            }
        }
        int count = 0;

        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(r, c, arr[r][c]));
        boolean[][] visited = new boolean[8][8];

        int[] dr = {0,1}, dc = {1,0};
        while( !queue.isEmpty() ){

            Point cur = queue.poll();

//            System.out.println("not empty, ("+cur.r+","+cur.c+")");
//            if( visited[cur.r-r][cur.c-c] ) continue;
//            visited[cur.r-r][cur.c-c] = true;


            for(int i=0; i<2; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];

                if(nr<r+8 && nc<c+8 && !visited[nr-r][nc-c]){
                    char tobe = arr[cur.r][cur.c]=='W' ? 'B' : 'W'; // 기존(r,c)에 W면 인접한 곳은 B여야되고, 아니면 W여야 함

                    if(arr[nr][nc]!=tobe) {
                        count++;
                        arr[nr][nc] = tobe; //기대하는 값으로 바꿔주기. 그 뒷 라인에서도 정상값과 비교하기위해
//                        System.out.println("이전(r,c)랑 동일함. 이전값: ("+cur.r+","+cur.c+")"+arr[cur.r][cur.c]);
//                        System.out.println("("+nr+","+nc+")"+tobe+" <=> "+arr[nr][nc]);
                    }
                    queue.add(new Point(nr,nc,tobe));
                    visited[nr-r][nc-c] = true;

                }
            }

        }

        if(count >32) count = 64-count;
        return count;
    }

}
