package PROGRAMMERS;

import java.util.Arrays;

public class pskp3_0223 {
    static boolean isGoodMap;
    static int roadNum;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    public static void main(String[] args) {
        String[][] board1 = {{"00011", "01111", "21001", "11001", "01111"},
                                {"00011", "00011", "11111", "12101", "11111"}};
        int ans1[] = solution(board1);
        System.out.println(Arrays.toString(ans1));

        String[][] board2 = {{"1111", "1121", "1001", "1111"},
                                {"0000", "0000", "0000", "0002"},
                                {"0000", "0100", "0000", "0002"},
                                {"0000", "0010", "0121", "0010"}};
        int ans2[] = solution(board2);
        System.out.println(Arrays.toString(ans2));
    }

    public static int[] solution(String[][] boards) {
        int[] answer = new int[boards.length];
        //각각의 보드에 대해(for문)
        for(int b=0; b<boards.length; b++){

            int[][] map = new int[boards[b].length][boards[b].length];
            boolean[][] visited = new boolean[map.length][map.length];
            int startR=-1, startC=-1;
            isGoodMap = false;
            roadNum = 0;//꽃을 심을 수 있는 모든 길(처음 시작 위치를 포함)
            //map 및 시작 위치 초기화
            for(int r=0; r<map.length; r++){
                String bline = boards[b][r];
                for(int c=0; c<map.length; c++){
                    map[r][c] = bline.charAt(c)-'0';
                    if(map[r][c]==2){
                        startR = r;
                        startC = c;
                        roadNum++;
                    }else if(map[r][c]==1){
                        roadNum++;
                    }
                }
            }

            //start 지점에서 map에 모두 꽃 심을 수 있는지 여부 확인해서 isGoodMap에 저장
            dfs(startR, startC, map, visited, 0);
            if(isGoodMap){
                answer[b] = 1;
            }else{
                answer[b] = 0;
            }

        }

        return answer;
    }

    public static void dfs(int startR, int startC, int [][]map, boolean [][] visited, int flower){
        if(isGoodMap) return;
        //현 위치 체크인하고 꽃 심기
        visited[startR][startC] = true;
        flower ++;
//        System.out.println("현위치: "+startR+" , "+startC);
        //만약 상하좌우 더이상 나갈 수 없다면
        //현 위치에까지 심은 꽃 수 == 원래 길 수 일 때 isGoodMap = true
        //현 위치까지 심은 꽃 수 != 원래 길 수-> isGoodMap = false; -> 꽃 심었던거 되돌려야할듯?
        for(int i=0; i<4; i++){
            int nr = startR + dr[i];
            int nc = startC + dc[i];
            //새로운 좌표로 이동할 수 있을 때: 범위 내에 있고, 길이 있고, 방문한 적 없을 때
            if(nr>=0 && nr<map.length && nc>=0 && nc<map.length && map[nr][nc]==1 && visited[nr][nc]==false){
                //그 자리에 방문하러 가기
                dfs(nr, nc, map, visited, flower);
            }
//            System.out.println("새로운길 탐색");
        }

        //현재 위치에서 더이상 방문할 수 있는 곳 없다면 꽃 수==길 수 확인해 보기
        if(roadNum==flower){
            isGoodMap = true;
        }

        //현 위치 체크아웃하고 꽃 뽑기
        flower --;
        visited[startR][startC] = false;
    }
}
