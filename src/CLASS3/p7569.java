package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p7569 {
    static class Tomato{
        int r,c,h;
        public Tomato(int r, int c, int h){
            this.r = r; this.c = c; this.h = h;
        }

        @Override
        public String toString() {
            return "Tomato{" +
                    "r=" + r +
                    ", c=" + c +
                    ", h=" + h +
                    '}';
        }
    }
    static int[][][] tomabox, visited;
    static int M, N, H;
    static int[] dr = {1,0,-1,0,0,0};
    static int[] dc = {0,1,0,-1,0,0};
    static int[] dh = {0,0,0,0,1,-1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomabox = new int[H][N][M];
        visited = new int[H][N][M];
        Queue<Tomato> matures = new LinkedList<>();

        for(int h=0; h<H; h++){
            for(int n=0; n<N; n++){
                st = new StringTokenizer(br.readLine());
                for(int m=0; m<M; m++){
                    tomabox[h][n][m] = Integer.parseInt(st.nextToken());
                    if(tomabox[h][n][m]==1){
                        matures.add(new Tomato(n,m,h) );
                        visited[h][n][m] = 1;
                    }else if(tomabox[h][n][m]==-1){
                        visited[h][n][m] = -1;
                    }
                }
            }
        }

        int matureday = 1;
        while(!matures.isEmpty()){
            Tomato cur = matures.poll();
            for(int i=0; i<6; i++){
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                int nh = cur.h + dh[i];

                if(nr>=0 && nr<N && nc>=0 && nc<M && nh>=0 && nh<H && tomabox[nh][nr][nc]==0){
                    tomabox[nh][nr][nc] =1;
                    visited[nh][nr][nc] = visited[cur.h][cur.r][cur.c]+1;
                    if(visited[nh][nr][nc]>matureday){
                        matureday = visited[nh][nr][nc];
                    }
                    matures.add(new Tomato( nr, nc, nh));
                }
                //?????? ????????? ????????? ?????? ?????? ?????? ??? && ????????? ????????? ????????? ??? ?????? ???????????? ???(0)
                //????????? ???????????? (1)
                //?????? ?????? ?????????(day +1) ?????? ????????? ???????????? ??????????????? ????????? ??????
                //Queue??? ???????????????

            }

//            System.out.println(cur.toString());
//            for(int h=0; h<H; h++){
//                for(int n=0; n<N; n++){
//                    System.out.println(Arrays.toString(visited[h][n]));
//                }
//                System.out.println();
//            }
        }

        //??? ?????? ???????????? ?????? ?????? ???????????? ?????? ??????( visited??? 0 ??? ?????? ?????? )-> -1 ??????
        boolean isAllMature = true;
        for(int h=0; h<H; h++){
            for(int n=0; n<N; n++){
                for(int m=0; m<M; m++){
                    if(visited[h][n][m]==0){
                        isAllMature = false;
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(matureday-1);


    }
}
