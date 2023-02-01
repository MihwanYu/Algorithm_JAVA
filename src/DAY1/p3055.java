package DAY1;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

class Point{
    int x,y;
    char type;

    Point(int x, int y, char shape){
        this.x = x;
        this.y = y;
        this.type = shape;
    }
}
public class p3055 {
    static final int[] MX = {-1,1,0,0};
    static final int[] MY = {0,0,-1,1};
    static int R,C;
    static char[][] map;
    static int [][] dp;
    static Queue<Point> queue;

//    static Point st
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        Point st = null;
        dp = new int[R][C];
        queue = new LinkedList<>();
//        int x = 0;
//        int y = 0;
//
//        for(int i=0; i<4; i++){
//            int tx = x+MX[i];
//            int ty = y+MY[i];
//        }
        for(int r = 0; r<R; r++){
            String line = sc.next();
            for (int c = 0; c<C; c++){
                map[r][c] = line.charAt(c);
                if(map[r][c]=='*'){
                    queue.add(new Point(r,c,'*'));
                }else if(map[r][c]=='S'){
                    queue.add(new Point(r,c,'S'));
                }
            }
        }



        queue.add(st);
        boolean foundAnswer = false;

        while (!queue.isEmpty()){
            //1. 큐에서 꺼냄
            Point p = queue.poll();
            //2. 목적지인가 ? -> 고슴도치만 D에 도착
            if(p.type=='D'){
                //bfs에서 return으로 끊어버리는것은 좋지 않다: main이 끝나게 되는 거니까
                System.out.println(dp[p.y][p.x]);
                foundAnswer = true;
                break;
            }

            //3. 연결된 곳 순회
            for(int i = 0; i<4; i++){
                int ty = p.y + MY[i];
                int tx = p.x + MX[i];
                //4. 갈 수 있는가 -A
                if(0<=ty && ty<R && 0<=tx && tx<C){
                    //4. 갈 수 있는가?(고슴도치 -> ., D , 방문체크
                    if(p.type=='.' || p.type =='S'){
                        if((map[ty][tx]=='.' || map[ty][tx]=='D') && dp[ty][tx] ==0 ){
                            //5. 체크인(고슴도치) -> dp
                            dp[ty][tx] = dp[p.y][p.x]+1;
                            //6. 큐에 넣음
                            queue.add(new Point(ty,tx,map[ty][tx]));
                        }
                    }//4. 갈 수 있는가?(물) -> ., S
                    else if(p.type=='*'){
                        if(map[ty][tx]=='.' || map[ty][tx]=='S'){
                            map[ty][tx]='*';
                            queue.add(new Point(ty,tx,'*'));
                        }
                    }


                    //5. 체크인(물) -> map

                }
            }
        }

        if(foundAnswer==false){
            System.out.println("KACTUS");
        }

    }

    static void bfs(){
        //1. 큐에서 가져옴
        //2. 목적지인가
        //3. 연결된 큐 순회
        //4. 갈 수 있는가
        //5. 체크인 : 고슴도치 / 물 방식이 다름. 고슴도치: 시간 체크, 물: * 찍기
        //6. 큐에 넣음
    }
}
