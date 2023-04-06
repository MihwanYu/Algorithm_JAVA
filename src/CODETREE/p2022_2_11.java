package CODETREE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p2022_2_11 {
    //2022 하반기 오전 1번문제
    static int n, m, k;
    static int[][] location;
    static PriorityQueue<Integer>[][] grid;//[i][j]에 gun이 여러개 들어갈 수 있음
    static Player[] players;

    //d: 0(위) 1(오른쪽) 2(아래) 3(왼쪽)
    static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
    static class Player{
        int x,y,d,s, idx;//x==r, y==c
        int gunpower; //보유한 총의 공격력
        int point; //획득한 포인트
        Player(int x, int y, int d, int s, int idx){
            this.x = x; this.y = y; this.d = d; this.s = s; this.idx = idx;
            this.gunpower = 0; this.point = 0;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());// 격자판 크기 nxn
        m = Integer.parseInt(st.nextToken());// 플레이어 수
        k = Integer.parseInt(st.nextToken());// 라운드 수

        grid = new PriorityQueue[n][n];//총의 공격력
        location = new int[n][n]; //플레이어의 위치 저장 맵
        players = new Player[m+1];//플레이어 정보 저장: 1,2,3,...n번 플레이어
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = new PriorityQueue<>(Collections.reverseOrder());
                int gun = Integer.parseInt(st.nextToken());
                if(gun>0) grid[i][j].add(gun);
            }
        }

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            //nxn 격자판은 0..n-1 범위임
            players[i] = new Player(x-1,y-1,d,s, i);
            location[x-1][y-1] = i;
        }


        for(int i=0; i<k; i++){
//            System.out.println("=========\nplay game : "+(i+1));
            playgame();
//            System.out.println("GAME TURNOVER");
//            printlocation();
//            printgrid();
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=m; i++){
            sb.append(players[i].point).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb.toString());
    }

    //하나의 위치에 총이 여러개가 있나 ? -> 닥히 제약 없지만 무조건 가장 큰 값만 grid에 저장하면 될 듯 ? -> 총 여러개 갈 수 있음 ..패착의원인
    static void playgame(){
        //플레이어 순서대로.
        for(int p=1; p<players.length; p++){
            Player cur = players[p];
            //1. 이동
            int nx = cur.x + dx[cur.d];//정해진 방향만큼 이동
            int ny = cur.y + dy[cur.d];
            if( !(nx>=0 && nx<n && ny>=0 && ny<n) ){
                //격자 벗어나면 방향 반대, 한칸 이동
                cur.d = (cur.d+2)%4;
                nx = cur.x + dx[cur.d];
                ny = cur.y + dy[cur.d];
            }

            //2. 마주친 플레이어 있는지 확인
            if(location[nx][ny] == 0){
                //없다면 -> 총이 있는가? + 변경된 위치 저장해주기
                        //있다면 -> 총 획득(원래 갖고 있는 것과 비교, 작은 것을 내려둔다)
                if(grid[nx][ny].size()>0 && grid[nx][ny].peek() > cur.gunpower){
                    if(cur.gunpower>0){
                        //cur player가 총을 갖고 있었다면 내려놓기
                        grid[nx][ny].add(cur.gunpower);
                    }
                    cur.gunpower = grid[nx][ny].poll(); //pq에서 총 빼서 cur player에게 주기
                }

                //location, player에 저장된 플레이어 위치 정보 변경
                location[cur.x][cur.y] = 0;
                cur.x = nx;
                cur.y = ny;
                location[nx][ny] = p;
            }else{
                //있다면 -> 싸운다
                // player.gunpower + player.s 합친것 비교, 똑같을 경우는 s가 커야 이김
                int otherIdx = location[nx][ny];
                Player other = players[otherIdx];
                Player winner, loser;
                int cur_sum = cur.gunpower + cur.s;
                int other_sum = other.gunpower + other.s;
                if( cur_sum > other_sum){
                    winner = cur;
                    loser = other;
                }else if(cur_sum < other_sum){
                    winner = other;
                    loser = cur;
                }else if(cur.s > other.s){
                    winner = cur; loser = other;
                }
                else {
                    winner = other; loser = cur;
                }
                // 이기면 -> (player.gunpower + player.s) 의 차이 만큼 포인트 획득
                winner.point += Math.abs((cur.gunpower+cur.s)-(other.gunpower+other.s));

                // 현재 플레이어의 위치 정보 변경
                location[cur.x][cur.y] = 0;
                cur.x = nx;
                cur.y = ny;

                // 지면 -> 총 내려둠, 이동
                if(loser.gunpower>0) grid[nx][ny].add(loser.gunpower);
                loser.gunpower = 0;

                for(int i=0; i<4; i++){
                    // 이동 장소에 플레이어 있거나 범위 밖이면 오른쪽 90도씩(dir++), 빈칸 보이면 이동
                    int nnx = loser.x + dx[loser.d];
                    int nny = loser.y + dy[loser.d];
                    if(nnx>=0 && nnx<n && nny>=0 && nny<n && location[nnx][nny]==0) {
                        loser.x=nnx; loser.y=nny;
                        //총이 있다면 -> 이동해서 총 획득할 수 있음
                        if(grid[nnx][nny].size()>0){
                            loser.gunpower = grid[nnx][nny].poll();
                        }
                        location[nnx][nny] = loser==cur? p : otherIdx;
                        break;
                    }else{
                        loser.d = (loser.d+1)%4;
                    }
                }

                winner.x = nx;
                winner.y = ny;
                // 이긴애 -> 진 애가 내려둔 총 비교, 가장 높은거 획득, 원래꺼 내려놓음
                if( grid[nx][ny].size()>0 && winner.gunpower < grid[nx][ny].peek()){
                    grid[nx][ny].add(winner.gunpower);
                    winner.gunpower = grid[nx][ny].poll();

                }
                location[nx][ny] = winner==cur ? p : otherIdx;
            }
                        // cur의 nx, ny 값도 이동할때 고려해줘야됨
                        // location[nx][ny]에 누가 남는지 확인해야 함
            // p번 플레이어가 움직이고 난 후
//            System.out.println("________________\nafter player moved: "+p);
//            printgrid();
//            printlocation();
//            System.out.println("----------------");
        }
    }

    static void printgrid(){
        System.out.println("----grid----");
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }

    static void printlocation(){
        System.out.println("----location----");
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(location[i][j]+" ");
            }
            System.out.println();
        }
    }
}
