package Y2024.M01;

import java.io.*;
import java.util.*;
public class p7562 {
    static class Point{
        int r, c;
        Point(int r, int c){this.r = r; this.c = c;}
    }

    static int[] dr = {1,1,2,2,-1,-1,-2,-2};
    static int[] dc = {2,-2,1,-1,2,-2,1,-1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t=0; t<tc; t++){
            int I = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Point src = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            Point dst = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            int ans = BFS(I, src, dst);
            System.out.println(ans);

        }

    }

    public static int BFS(int I, Point src, Point dst){
        if(src.r==dst.r && src.c==dst.c) return 0;

        int[][] grid = new int[I][I];
//        for(int r=0; r<I; r++){
//            Arrays.fill(grid[r], -1);
//        }
        Queue<Point> queue = new LinkedList<>();
        queue.add(src);
        boolean findDst = false;
        while( !queue.isEmpty()){
            Point cur = queue.poll();

            for(int i=0; i<8; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];
                if(nr>=0 && nr<I && nc>=0 && nc<I){
                    if(grid[nr][nc]==0 || grid[nr][nc]>grid[cur.r][cur.c]+1){
                        grid[nr][nc] = grid[cur.r][cur.c]+1;
                        queue.add(new Point(nr,nc));
                        if(nr==dst.r && nc==dst.c) {
                            findDst = true; break;
                        }
                    }
                }


            }
            if(findDst) break;

        }
//        System.out.println("-----------------");
//        for(int r=0; r<I; r++){
//            System.out.println(Arrays.toString(grid[r]));
//        }


        return grid[dst.r][dst.c];
    }


}
