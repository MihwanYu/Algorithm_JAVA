package Y2024.M01;

import java.io.*;
import java.util.*;
public class p14499 {
    static int[][] grid;
    static Dice dice;
    static class Dice{
        int[] row, col;
        int[] val = new int[7];
        int up, bottom, r, c;
        Dice(int r, int c){
            int[] nrow = {4,1,3,6};
            int[] ncol = {2,1,5,6};
            this.row = nrow;
            this.col = ncol;
            this.up = 1;
            this.bottom = 6;
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int cmd = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new Dice(r,c);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<cmd; i++){
            int command = Integer.parseInt(st.nextToken());
            boolean res = rollDice(command, dice.r, dice.c);
            //만약 newRC가 (r,c)와 같다면 -> 이동하지 않았음, continue
//            System.out.println((i+1)+"번째============");
            if(!res) continue;

            //grid(Dice.r, Dice.c)에 0이 있다면 -> grid[Dice.r][Dice.c] = val[bottom] 주사위값 복사
            //       0 말고 다른 숫자면 val[bottom] = grid[Dice.r][Dice.c],grid[Dice.r][Dice.c] = 0 주사위에 값 이동

//            System.out.println(Arrays.toString(dice.val));
//            System.out.println("UP("+dice.up+") BOTTOM("+dice.bottom+"), LOC("+dice.r+","+dice.c+")");
//            System.out.println("row "+Arrays.toString(dice.row)+"   col "+Arrays.toString(dice.col));
            if(grid[dice.r][dice.c] == 0){
                grid[dice.r][dice.c] = dice.val[dice.bottom];
            }else{
                dice.val[dice.bottom] = grid[dice.r][dice.c];
                grid[dice.r][dice.c] = 0;
            }

            System.out.println(dice.val[dice.up]);

        }
    }

    static boolean rollDice(int command, int r, int c){
        //(r,c)에서 command 방향으로 이동여부에 따라 true/false
        //이동시 dice 위치 바꿈
        int[] nrow = new int[4];
        int[] ncol = new int[4];

        if(command==1){
            if(c==grid[0].length-1) return false;
            dice.c++;
            //dice.row 변경
            for(int i=1; i<4; i++){
                nrow[i] = dice.row[i-1];
            }
            nrow[0] = dice.row[3];
//            ncol[3] = nrow[3];
            dice.row = nrow;
            dice.col[3] = nrow[3];
            dice.col[1] = nrow[1];
        }else if(command==2){
            if(c==0) return false;
            dice.c--;
            for(int i=0; i<3; i++){
                nrow[i] = dice.row[i+1];
            }
            nrow[3] = dice.row[0];
//            ncol[3] = nrow[3];
            dice.row = nrow;
            dice.col[3] = nrow[3];
            dice.col[1] = nrow[1];
        }else if(command==3){
            if(r==0) return false;
            dice.r--;
            for(int i=0; i<3; i++){
                ncol[i] = dice.col[i+1];
            }
            ncol[3] = dice.col[0];
            dice.col = ncol;
            dice.row[3] = ncol[3];
            dice.row[1] = ncol[1];

        }else{
            if(r==grid.length-1) return false;
            dice.r++;
            for(int i=1; i<4; i++){
                ncol[i] = dice.col[i-1];
            }
            ncol[0] = dice.col[3];
//            nrow[3] = ncol[3];
            dice.col = ncol;
            dice.row[3] = ncol[3];
            dice.row[1] = ncol[1];
        }

//        System.out.println(Arrays.toString(dice.row));
//        System.out.println(Arrays.toString(dice.col));
//        System.out.println(Arrays.toString(nrow));
//        System.out.println(Arrays.toString(ncol));
//        dice.row = nrow;
//        dice.col = ncol;
        dice.up = dice.row[1];
        dice.bottom = dice.row[3];

        return true;
    }
}
