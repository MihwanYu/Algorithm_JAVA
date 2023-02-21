package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14500 {
    static int[][] grid;
    static int N, M;
    static int max;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];

        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++){
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                tetromino(r,c);
            }
        }

        System.out.println(max);

    }

    static void tetromino(int r, int c){
        int temp=0;
        if(c+2<M){// (r,c)에서 가로 3개 연속 덧셈 가능할 경우
            temp=0;
            for(int i=c; i<c+3; i++){
                temp += grid[r][i];
            }
            // ㅁㅁㅁㅁ
            if(c+3<M){
                if(temp+grid[r][c+3]>max){
                    max = temp+grid[r][c+3];
                }
            }

            // ㅁ        ㅁ       ㅁ
            // ㅁㅁㅁ   ㅁㅁㅁ  ㅁㅁㅁ
            if(r-1>=0){
                if(temp+grid[r-1][c]>max){
                    max = temp+grid[r-1][c];
                }
                if(temp+grid[r-1][c+1]>max){
                    max = temp+grid[r-1][c+1];
                }
                if(temp+grid[r-1][c+2]>max){
                    max = temp+grid[r-1][c+2];
                }
            }
            // ㅁㅁㅁ   ㅁㅁㅁ  ㅁㅁㅁ
            // ㅁ        ㅁ       ㅁ
            if(r+1<N){
                if(temp+grid[r+1][c]>max){
                    max = temp+grid[r+1][c];
                }
                if(temp+grid[r+1][c+1]>max){
                    max = temp+grid[r+1][c+1];
                }
                if(temp+grid[r+1][c+2]>max){
                    max = temp+grid[r+1][c+2];
                }
            }

        }

        if(r+2<N){//(r,c)에서 세로 3개 연속 덧셈 가능할 경우
            temp=0;
            for(int i=r; i<r+3; i++){
                temp += grid[i][c];
            }
            // ㅁㅁㅁㅁ 세로로 돌린버전
            if(r+3<N){
                if(temp+grid[r+3][c]>max){
                    max = temp+grid[r+3][c];
                }
            }

            //ㅁㅁ     ㅁ
            //  ㅁ   ㅁㅁ
            //  ㅁ     ㅁ
            if(c-1>=0){
                if(temp+grid[r][c-1]>max){
                    max = temp+grid[r][c-1];
                }
                if(temp+grid[r+1][c-1]>max){
                    max = temp+grid[r+1][c-1];
                }
                if(temp+grid[r+2][c-1]>max){
                    max = temp+grid[r+2][c-1];
                }
            }

            //ㅁㅁ
            //ㅁ
            //ㅁ
            if(c+1<M){
                if(temp+grid[r][c+1]>max){
                    max = temp+grid[r][c+1];
                }
                if(temp+grid[r+1][c+1]>max){
                    max = temp+grid[r+1][c+1];
                }
                if(temp+grid[r+2][c+1]>max){
                    max = temp+grid[r+2][c+1];
                }
            }
        }

        //ㅁㅁ
        //ㅁㅁ (r,c)에서 가로연속2개 + 세로연속2개 가능할경우
        if(c+1<M && r+1<N){
            temp=0;
            for(int i=r; i<r+2; i++){
                temp += grid[i][c];
                temp += grid[i][c+1];
            }
            if(temp>max){
                max = temp;
            }
//            System.out.println("("+r+","+c+") "+temp);

            if(r+2<N){//한쪽 밑으로 내리기 가능할경우
                //왼쪽꺼 밑으로 내려보기
                if(temp-grid[r][c]+grid[r+2][c]>max){
                    max = temp-grid[r][c]+grid[r+2][c];
                }
                //오른쪽꺼 밑으로
                if(temp-grid[r][c+1]+grid[r+2][c+1]>max) {
                    max = temp - grid[r][c + 1] + grid[r + 2][c + 1];
                }
            }

            if(c+2<M){//한쪽 오른쪽 밀기 가능할경우
                //위쪽꺼 오른쪽으로 밀기
                if(temp-grid[r][c]+grid[r][c+2]>max){
                    max = temp-grid[r][c]+grid[r][c+2];
                }
                //아래쪽꺼 오른쪽 밀기
                if(temp-grid[r+1][c]+grid[r+1][c+2]>max){
                    max = temp-grid[r+1][c]+grid[r+1][c+2];
                }
            }

        }


    }
}
