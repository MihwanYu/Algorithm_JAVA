package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p13460 {
    //boj 13460
    static int N, M, mincount;
    static char[][] grid;
    static Point hole;
    static class Point{
        int r, c;
        Point(int r, int c){
            this.r = r; this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                     r +
                    ", " + c +
                    '}';
        }
    }

    static class Pair{
        Point blue, red;
        int count;
        Pair(Point blue, Point red,int count){
            this.blue = blue; this.red = red; this.count = count;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];

        Point blue= null, red= null;

        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<M; j++){
                grid[i][j] = s.charAt(j);
                if(grid[i][j]=='B') blue = new Point(i,j);
                else if(grid[i][j] =='R') red = new Point(i,j);
                else if(grid[i][j] == 'O') hole = new Point(i,j);
            }
        }
        mincount = 11;
        bfs(new Pair(blue, red, 0));
        System.out.println(mincount);
    }

    static int bfs(Pair pair){
        boolean visited[][][][] = new boolean[N][M][N][M];

        Queue<Pair> queue = new LinkedList<>();
        queue.add(pair);

        while( !queue.isEmpty() ){
            Pair cur = queue.poll();
            Point blue = cur.blue;
            Point red = cur.red;
            int cnt = cur.count;
            if(cnt>10) {
                continue;
            }
            //i=0: 오른쪽, i=1: 아래, i=2: 왼쪽, i=3: 위쪽
            for(int i=0; i<4; i++){
                Point newblue = movemarble(blue, i);
                Point newred = movemarble(red, i);

                //새로 이동할 때 파란 구슬==구멍 -> 해당 케이스 넘어감
                if(newblue.r==hole.r && newblue.c==hole.c) continue;
                else if(newred.r==hole.r && newred.c==hole.c){
                    //빨간 구슬==구멍 -> count이 미니멈 갱신하는지 확인
                    if(cnt+1<mincount) mincount = cnt+1;
                    continue;
                }
                //그렇지 않을 경우 위치 조정 후 queue에 변경된 구슬 위치 정보 객체 삽입

                //이동 후 위치가 똑같을 때 더 많이 움직인 구슬의 위치를 1만큼 조정
                if(newblue.r==newred.r && newblue.c==newred.c){
//
//                    System.out.println(newblue+", "+newred);
                    if(i==0){//오른쪽 이동
                        if(blue.c<red.c)newblue.c--;
                        else newred.c--;
                    }else if(i==1){//아래쪽 이동
                        if(blue.r<red.r) newblue.r--;
                        else newred.r--;
                    }else if(i==2){//왼쪽 이동
                        if(blue.c>red.c) newblue.c++;
                        else newred.c++;
                    }else{//위쪽 이동
                        if(blue.r>red.r) newblue.r++;
                        else newred.r++;
                    }
//                    System.out.println("조정: "+newblue+", "+newred);
                }

                //방문한 적 없다면 -> 방문 처리 휴 큐에 추가
                if( !visited[newblue.r][newblue.c][newred.r][newred.c] ){
                    visited[newblue.r][newblue.c][newred.r][newred.c] = true;
                    queue.add(new Pair(newblue, newred, cnt+1));
                }
            }
        }

        if(mincount>10){
            mincount = -1;
        }
        return mincount;
    }

    static Point movemarble(Point marble, int dir){
        int r = marble.r;
        int c = marble.c;
        if(dir==0){//오른쪽 끝으로 이동
            while(grid[r][c+1] != '#'){
                c++;
                if(r==hole.r && c==hole.c) break;
            }
        }else if(dir==1){//아래 끝
            while(grid[r+1][c] != '#'){
                r++;
                if(r==hole.r && c==hole.c) break;
            }
        }else if(dir==2){//왼쪽 끝
            while(grid[r][c-1] != '#'){
                c--;
                if(r==hole.r && c==hole.c) break;
            }
        }else {//위 끝
            while(grid[r-1][c] != '#'){
                r--;
                if(r==hole.r && c==hole.c) break;
            }
        }

        return new Point(r,c);
    }



}
