package NONCLASS;

import java.io.*;
import java.util.*;
public class p8972 {
    static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1}, dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    static class Point{
        int r, c;
        Point(int r, int c){
            this.r = r;this.c = c;
        }
        public int getLength(Point o2){
            return Math.abs(o2.r-this.r)+Math.abs(o2.c-this.c);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];

        Point player = null;
        ArrayList<Point> madness = new ArrayList<>();
        for(int i=0; i<R; i++){
            String r = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = r.charAt(j);
                if(map[i][j]=='I'){
                    player = new Point(i,j);
                }else if(map[i][j]=='R'){
                    madness.add(new Point(i,j));
                }
            }
        }

        String movement = br.readLine();
        int count = 0;
        boolean gamebroken = false;
        for(char m:movement.toCharArray()){
            count++;
            int dir = m-'0';
//            System.out.println("move to "+dir);
            //1. player 방향 dir로 이동
            map[player.r][player.c] = '.';
            player.r += dr[dir];
            player.c += dc[dir];

            //2. 이동 장소에 아두이노가 있다면: 종료
            if(map[player.r][player.c]=='R'){
                gamebroken = true;
                break;
            }else{
                map[player.r][player.c] = 'I';
            }

            //3. 미친 아두이노 방향: 종수와 가장 가까운 곳으로 한 칸 이동
            ArrayList<Point> existence = new ArrayList<>();
            ArrayList<Point>[][] newMadness = new ArrayList[R][C];

            for(int i=0; i<madness.size(); i++){
                Point p = madness.get(i);
                if(p.r==-1 || p.c==-1) continue;//이전에 파괴된 아두이노

                //가까운 곳으로 이동할 new R, C 생성
                int[] nrc = getCloser(p, player);

                //4. 미친 아두이노가 종수와 같은 위치라면
                if(nrc[0]==player.r && nrc[1]==player.c){
                    gamebroken = true; break;
                }
//                 System.out.println("("+p.r+","+p.c+") -> ("+nrc[0]+","+nrc[1]+")");
                //미친 아두이노 위치 변경
                map[p.r][p.c]='.';
                p.r = nrc[0];
                p.c = nrc[1];

                //5. 미친 아두이노 여러개가 같은 공간에 있다면:

                //해당 공간에 미친 아두이노 처음으로 들어간다면
                if(newMadness[p.r][p.c]==null) {
                    newMadness[p.r][p.c] = new ArrayList<>();
                    existence.add(new Point(p.r, p.c));//아두이노가존재하는 위치 리스트에 추가
                }

                //각 칸에 여러개의 아두이노 리스트 넣음
                newMadness[p.r][p.c].add(p);

            }
            if(gamebroken) break;

            //newMadness의 위치로 map의 R 수정 / 만약 2개 이상이라면 폭발
            for(int i=0; i<existence.size(); i++){
                Point loc = existence.get(i);
                //newMadness[loc.r][loc.c]의 size가 1보다 크다면, 그 안에 존재하는 R들을 모두 -1로 바꾸고(폭발)
                //size가 1이라면 map[loc.r][loc.c] = 'R'
                if(newMadness[loc.r][loc.c].size()==1){
                    map[loc.r][loc.c] = 'R';
                }else{
                    map[loc.r][loc.c] = '.';
//                    System.out.println("("+loc.r+","+loc.c+") bomb");
                    for(int mad=0; mad<newMadness[loc.r][loc.c].size(); mad++){
                        newMadness[loc.r][loc.c].get(mad).r = -1;
                    }
                }
            }

        }
//        System.out.println("ANSWER");
        if(gamebroken){
            System.out.println("kraj "+count);
        }else{
            StringBuilder sb = new StringBuilder("");
            for(int i=0; i<R; i++){
                sb.append(map[i]).append("\n");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb.toString());
        }

    }

    static int[] getCloser(Point p, Point player){
        int minlen = p.getLength(player);
        int minr = p.r, minc = p.c;
        for(int i=1; i<10; i++){
            int nr = p.r + dr[i];
            int nc = p.c + dc[i];
            if(Math.abs(nr-player.r)+Math.abs(nc-player.c) < minlen){
                minlen = Math.abs(nr-player.r)+Math.abs(nc-player.c);
                minr = nr; minc = nc;
            }
        }
        return new int[] {minr,minc};
    }
}
