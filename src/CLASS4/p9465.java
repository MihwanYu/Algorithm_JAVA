package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p9465 {
    public static void main(String[] args) throws Exception {
        //알고리즘 분류: dp
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // testcase 횟수 입력
        int testcase = Integer.parseInt(br.readLine());

        for(int t=0; t<testcase; t++){
            //case별 columns수, 배열 정보 입력
            int n = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][n];

            for(int r=0; r<2; r++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int c=0; c<n; c++){
                    stickers[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            //case별 스티커 점수 최댓값
            int score = getMaxScore(stickers);
            System.out.println(score);

        }
    }

    static int getMaxScore(int[][] stickers){
        int columns = stickers[0].length;
        int[][] scoreboard = new int[2][columns];

        //col 0의 최댓값
        scoreboard[0][0] = stickers[0][0];
        scoreboard[1][0] = stickers[1][0];

        for(int c=1; c<columns; c++){
            if(c==1){
                //col 1의 최댓값: 앞 대각선
                scoreboard[0][c] = stickers[0][c] + scoreboard[1][c-1];
                scoreboard[1][c] = stickers[1][c] + scoreboard[0][c-1];
                continue;
            }

            //col 2~ 최댓값: 앞 대각선 vs 앞앞 대각선
            scoreboard[0][c] = stickers[0][c] + Math.max(scoreboard[1][c-1], scoreboard[1][c-2]);
            scoreboard[1][c] = stickers[1][c] + Math.max(scoreboard[0][c-1], scoreboard[0][c-2]);
        }

        System.out.println(Arrays.toString(scoreboard[0]));
        System.out.println(Arrays.toString(scoreboard[1]));

        return Math.max(scoreboard[0][columns-1], scoreboard[1][columns-1]);

    }
}
