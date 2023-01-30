package DAY1;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

class Point{
    int x,y;
}
public class p3055 {
    static final int[] MX = {-1,1,0,0};
    static final int[] MY = {0,0,-1,1};
    static int R,C;
    static char[][] map;
    static int [][] dp;
    static Queue<Point> queue;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();
        int x = 0;
        int y = 0;

        for(int i=0; i<4; i++){
            int tx = x+MX[i];
            int ty = y+MY[i];
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
