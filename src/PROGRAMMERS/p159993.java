package PROGRAMMERS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class p159993 {
    static char map[][];
    static int R,C;
    static Point start, lever, exit;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    static class Point{
        int r,c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        String[] input1 = {"OOOOOL", "OXOXOO", "OOSXOX", "OXXXOX", "EOOOOX"};
        int ans1 = solution(input1);
        System.out.println(ans1);
        String[] input2 = {"XXXXXL", "XXXXOO", "OOSXOX", "OXXXOX", "EOOOOX"};
        int ans2 = solution(input2);
        System.out.println(ans2);
        String[] input3 = {"XXXXL", "XOOSX", "XXXXX", "XXXOO", "EXXXX", "XXXXX"};
        int ans3 = solution(input3);
        System.out.println(ans3);
    }

    public static int solution(String[] maps){
        int answer = 0;
        R = maps.length;
        C = maps[0].length();
        map = new char[R][C];

        for(int r=0; r<R; r++){
            map[r] = maps[r].toCharArray();
            for(int c=0; c<C; c++){
                if(map[r][c]=='S'){
                    start = new Point(r,c);
                }else if(map[r][c] == 'L'){
                    lever = new Point(r,c);
                }else if(map[r][c]=='E'){
                    exit = new Point(r,c);
                }
            }
        }

        int count1 = bfs(start, lever);
        if(count1==0){ //start -> lever 못감
            return -1;
        }
        int count2 = bfs(lever, exit);
        if(count2==0){ //lever -> end 못감
            return -1;
        }
        answer = count1+count2;
        return answer;
    }

    public static int bfs(Point start, Point dst){
        int countvisit[][] = new int[R][C]; //bfs부를때마다 모두 0으로 초기화: start-> lever, lever->exit 각각 새로 처리해야함
        LinkedList<Point> queue = new LinkedList<Point>();
        queue.add(start);
        while(!queue.isEmpty()){
            Point cur = queue.poll();
            //cur이 dst와 동일하다면 break
            if(cur.r==dst.r && cur.c==dst.c){
                break;
            }

            for(int i=0; i<4; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];
                //if np 이동 가능하고(X가 아니어야 함) 한 번도 방문한 적 없다면(countvisit 값이 0)
                if(nr>=0 && nr<R && nc>=0 && nc<C && (map[nr][nc] != 'X') && (countvisit[nr][nc] == 0) ){
                    // queue에 np 추가하고 countvisit값을 1 올려서 저장해주기
                    queue.add(new Point(nr,nc));
                    countvisit[nr][nc] = countvisit[cur.r][cur.c]+1;
                }
            }

        }

        return countvisit[dst.r][dst.c];
    }
}
