package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p17144 {
    static int R, C, T, a_top=-1, a_bottom=-1;
    static int[][] grid;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        int dustcount = 0; //최종 미세먼지 총량

        grid = new int[R][C];
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j= 0; j<C; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j]==-1){//공기청정기 -> 위쪽 공기청정기, 아래쪽 공기청정기 각각 a_top, a_bottom
                    if(a_top==-1) a_top = i;
                    else a_bottom = i;
                }
            }
        }

        for(int t=0; t<T; t++){
            //t초동안 미세먼지 확산 -> 공기청정기 순환 반복
            spread();//미세먼지 확산
            circulate();//공청기 순환
            printgrid();
        }

        for(int i=0; i<R; i++){
            for(int j= 0; j<C; j++){
                //미세먼지 있으면 count
                if( !(grid[i][j] == 0 || grid[i][j] ==-1) ){
                    dustcount += grid[i][j];
                }
            }
        }

        System.out.println(dustcount);
    }

    static void spread(){
        int[][] newgrid = new int[R][C]; //미세먼지 확산후 grid
        for(int r=0; r<R; r++){

            for(int c=0; c<C; c++){

                //(r,c) 현재 위치의 findedust 양
                int dust = grid[r][c];
                if(dust==0 || dust==-1) continue; //해당좌표 먼지 없으면 & 공청기 위치면 pass
                int division = dust/5;

                for(int s=0; s<4; s++){
                    int nr = r + dr[s];
                    int nc = c + dc[s];
                    //(nr,nc) 유효한 좌표일 때: newgrid[][]에 division 추가, dust감소
                    if(nr>=0 && nr<R && nc>=0 && nc<C){
                        //(nr,nc)에 공청기가 있을 때
                        //바람이 나가는 방향이면 확산x : (r,c)가 공청기 오른쪽일때 == c==1일 경우
                        //바람 들어가는 방향이면 확산o : (r,c)가 공청기 위/아래일때
                        if(grid[nr][nc]==-1 && c==1){
                            continue;//확산안하고넘어감
                        }
                        //공청기로 들어갈 경우 & 다른 공간으로 확산할 경우
                        newgrid[nr][nc] += division;
                        dust -= division;
                    }

                }
                //(r,c) 좌표에 남아있는 dust추가
                newgrid[r][c] += dust;
            }

        }

        //공청기 위치에 있는 미세먼지 모두 제거
        newgrid[a_top][0] = -1;
        newgrid[a_bottom][0] = -1;
        grid = newgrid;//격자판 새로운거로 교체
    }

    static void circulate(){
        //공청기 위치는 무조건 위쪽순환 & 아래쪽순환 가능한곳에 위치함
        //위쪽 순환

        //위쪽순환 downside
        for(int r=a_top-1; r>0; r--){
            grid[r][0] = grid[r-1][0];
        }

        //위쪽순환 leftside
        for(int c=0; c<C-1; c++){
            grid[0][c] = grid[0][c+1];
        }

        //위쪽순환 upside
        for(int r=0; r<a_top; r++){
            grid[r][C-1] = grid[r+1][C-1];
        }

        //위족순환 rightside
        for(int c=C-1; c>0; c--){
            grid[a_top][c] = grid[a_top][c-1];
        }
        grid[a_top][0] = -1;
        grid[a_top][1] = 0;


        //아래쪽 순환
        //아래쪽 순환 upside
        for(int r=a_bottom-1; r<R-1; r++){
            grid[r][0] = grid[r+1][0];
        }

        //아래쪽 순환 leftside
        for(int c=0; c<C-1; c++){
            grid[R-1][c] = grid[R-1][c+1];
        }

        //아래족 순환 downside
        for(int r=R-1; r>a_bottom; r--){
            grid[r][C-1] = grid[r-1][C-1];
        }

        //아래쪽 순환 rightside
        for(int c=C-1; c>0; c--){
            grid[a_bottom][c] = grid[a_bottom][c-1];
        }
        grid[a_bottom][0] = -1;
        grid[a_bottom][1] = 0;

    }

    static void printgrid(){
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                System.out.print(grid[r][c]+" ");
            }
            System.out.println();
        }
    }
}
