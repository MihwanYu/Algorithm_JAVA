package PROGRAMMERS;
import java.util.*;
public class p169199 {
    static char[][] grid;
    static int[][] visited;
    static int R, C;
    static int minCount = Integer.MAX_VALUE;
    static Point start, goal;
    static class Point{
        int r,c;
        Point(int r, int c){this.r = r; this.c = c;}
    }
    public static void main(String[] args) {
        int res1 = solution(new String[] {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."});
        System.out.println(res1);
    }

    public static int solution(String[] board) {
        int answer = 0;
        start = new Point(0,0);
        goal = new Point(0,0);
        R = board.length;
        C = board[0].length();
        grid = new char[R][C];
        visited = new int[R][C];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length(); j++){
                grid[i][j] = board[i].charAt(j);
                if(grid[i][j]=='R'){
                    start = new Point(i,j);
                }else if(grid[i][j]=='G'){
                    goal = new Point(i,j);
                }
            }
        }

        answer = bfs(start, 0);

        // for(int i=0; i<board.length; i++){
        //     System.out.println(Arrays.toString(visited[i]));
        // }

        return answer;
    }

    static int bfs(Point cur, int count){
        Queue<Point> queue = new LinkedList<>();
        queue.add(cur);
        visited[cur.r][cur.c] = 1;

        while( !queue.isEmpty() ){
            cur = queue.poll();

            if(grid[cur.r][cur.c]=='G') break;

            for(int i=0; i<4; i++){
                //방향 미끄러지기
                Point np = robotSlide(cur, i);
                if(visited[np.r][np.c]==0){
                    queue.add(np);
                    visited[np.r][np.c] += visited[cur.r][cur.c]+1;
                }
            }

        }

        return visited[goal.r][goal.c]-1;



    }

    static Point robotSlide(Point cur, int dir){
        //dir=0: 아래 미끄러지기
        //dir=1: 위

        //dir=2: 왼쪽

        //dir=3: 오른쪽
        int dr=0, dc=0;
        int nr = cur.r, nc = cur.c;
        switch(dir){
            case 0:
                dr = 1; break;
            case 1:
                dr = -1; break;
            case 2:
                dc = -1; break;
            default:
                dc = 1; break;
        }
        while(true){
            nr = nr+dr; nc = nc+dc;
            if(!(nr>=0 && nr<R && nc>=0 && nc<C && grid[nr][nc] != 'D')){

                nr -= dr; nc -= dc;
                break;
            }
        }
        return new Point(nr, nc);
    }
}
