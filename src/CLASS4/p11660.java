package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p11660 {
    static int[][] grid, cumm_grid;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        cumm_grid = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i==0){
                    if(j==0){
                        cumm_grid[i][j] = grid[i][j];
                    }else{
                        cumm_grid[i][j] = grid[i][j] + cumm_grid[i][j-1];
                    }
                }else if(j==0){
                    cumm_grid[i][j] = grid[i][j] + cumm_grid[i-1][j];
                }else{
                    cumm_grid[i][j] = grid[i][j] + cumm_grid[i][j-1] + cumm_grid[i-1][j] - cumm_grid[i-1][j-1];
                }
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int ans = partialsum(x1-1 ,y1-1 ,x2-1, y2-1);
            System.out.println(ans);
        }
    }

    static int partialsum(int x1, int y1, int x2, int y2){
        if(x1==x2 && y1==y2){
            return grid[x2][y2];
        }
        if(x1==0 && y1==0){
            return cumm_grid[x2][y2];
        }

        int upper, left, upperleft;
        upper = x1==0? 0: cumm_grid[x1-1][y2];
        left = y1==0? 0: cumm_grid[x2][y1-1];
        upperleft = x1==0 || y1==0 ? 0 : cumm_grid[x1-1][y1-1];
        int answer = cumm_grid[x2][y2] - upper - left + upperleft;
        return answer;
    }
}
