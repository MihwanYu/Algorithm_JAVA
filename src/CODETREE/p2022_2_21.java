package CODETREE;

import java.io.*;
import java.util.*;

public class p2022_2_21 {
    //삼성 하반기 오후 1번
    static int n, m;
    static int[][] map;
    static Person[] people;
    static int[] dr = {-1,0,0,1}, dc = {0,-1,1,0};
    static class Point{
        int r, c;
        Point(int r, int c){this.r = r; this.c = c;}
    }
    static class Person{
        int r, c, gr, gc, length, moveamount;//gr, gc: 목적지 편의점 위치, length: 최단거리
        boolean isReached = false;
        int[][] personalMap; //개인 이동거리 기록, -1로 초기화. 시작 위치 0
        Queue<Point> personalQueue;
        Person(int gr, int gc){
            this.gr = gr; this.gc = gc;
            this.moveamount = 0;
            this.personalMap = new int[n][n];
            this.personalQueue = new LinkedList<>();//move()함수에서 toMart 포인트 사용

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    this.personalMap[i][j] = -1;
                }
            }
        }
        void findRoute(){
            //gr, gc부터 가장 짧은 거리에 있는 목적지 찾기(map에서 1인 값들)
            //r,c 값을 해당 목적지로 설정, map에서 1인 값을 -1로 설정(못움직이게)
            //length 최단 거리 값 설정해주기

            int[][] visited = new int[n][n];//방문지까지 거리 처음에 0으로 초기화
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(gr, gc));
            visited[gr][gc] = 1;

            while( !queue.isEmpty() ){
                Point cur = queue.poll();
                if(map[cur.r][cur.c]==1){
                    //베이스캠프 발견할 경우
                    this.r = cur.r; this.c =cur.c; //r,c 시작위치 설정
                    this.length = visited[cur.r][cur.c]-1; //최단거리 설정
                    map[cur.r][cur.c] = -1; //공통 map -1설정

                    personalMap[cur.r][cur.c] = 0;//개인 이동 맵 시작 위치 값 지정
                    personalQueue.add(new Point(cur.r, cur.c));//큐에 값 추가
                    break;//while 종료 후 바로 함수 종료
                }

                for(int i=0; i<4; i++){
                    int nr = cur.r+dr[i];
                    int nc = cur.c+dc[i];
                    //유효한 위치 방문 한번도 안했을 때 && map에서도 이동 가능한 곳일 때
                    if(nr>=0 && nr<n && nc>=0 && nc<n && visited[nr][nc]==0 && map[nr][nc] != -1){
                        visited[nr][nc] = visited[cur.r][cur.c]+1;
                        queue.add(new Point(nr, nc));//큐에 삽입
                    }

                }
            }
            return;
        }

        //move()함수는 총 length만큼 실행되고, 그 때 목적지에 한 번은 도착해야 한다
        void move(){
            //personalmap에서 이동하기; 이전에 갔던곳(map에서 -1이 아닌 곳들은 가지 않음)
            //이동한 장소가 gr,gc와 같다면 -> 목적지 설정, map에서 값을 -1로 설정함
            //isreacehd -> true 설정

            //이전에 움직인게 0이면(moveamount) 0인 애들 다 뽑고 이번에 1자리 탐색 후 추가
            //이전에 움직인게 1이면 이번에 1인 애들 다 뽑고 2인 자리 탐색 후 추가
            Point firstp = this.personalQueue.peek();
            int searchlength = this.personalMap[firstp.r][firstp.c];
            while( true ){
                //personalQueue.peek()한 위치에서 personalmap움직인 값==moveamount여야 함, 아니면 break(-> move()종료)
                Point cur = this.personalQueue.peek();
                if(this.personalMap[cur.r][cur.c] != searchlength ) break;

                //맞으면 cur값을 poll()해주고, 현 위치가 목적지 편의점에 도달했는지 확인
                cur = this.personalQueue.poll();

                //다르다면 다른 인접한 장소 탐색, moveamount 값 갱신도 해주기
                for(int i=0; i<4; i++){
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    //유효한 해당 위치를 personalMap에서 방문한적 없다면(-1) && map에서 방문 가능한 곳이라면( != -1)
                    if(nr>=0 && nr<n && nc>=0 && nc<n && this.personalMap[nr][nc]==-1 && map[nr][nc] != -1){
                        this.personalMap[nr][nc] = this.personalMap[cur.r][cur.c]+1;
                        this.moveamount =this.personalMap[nr][nc];

                        //만약 새로운 위치가 목적지와 동일하다면 할일 지정하고 함수 종료
                        if(nr==this.gr && nc==this.gc){
                            map[nr][nc] = -1;
                            this.isReached = true;
                            return;
                        }

                        this.personalQueue.add(new Point(nr, nc));//큐에 삽입

                    }
                }

            }

        }


    }
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];//map에서 -1인 부분: 접근 못하는 곳, 1: 캠프 있는 곳
        people = new Person[m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            people[i] = new Person(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        }

        int time = 0;//시간
        int peopleReached = 0;
        while(true){
//            System.out.println("------------time "+(time+1)+"---------------");
            for(int i=0; i<m; i++){

                //사람 별 이동
                if(time==i){
                    //m번째 사람은 m초에 최단 거리를 찾아 베이스캠프로 이동한다
                    people[i].findRoute();
//                    System.out.println("person "+(i+1)+" find route ("+people[i].r+","+people[i].c+")");
                }else if(people[i].isReached) {
                    //목적지에 도착한 사람이라면 -> 이동 더이상 하지 않음
                    continue;
                }
                else if(time>i){
                    //m번째 사람은 편의점을 향해 이동한다
                    people[i].move();
//                    System.out.println("person "+(i+1)+" moved ("+people[i].moveamount+")");
                    //편의점 도착했다면 peopleReached 증가
                    if(people[i].isReached) {
                        peopleReached++;
//                        System.out.println("person "+(i+1)+" reached +");
                    }
                }
            }

            time++;
            if(peopleReached==m) break;//전체 인원이 편의점 도착했으면 break
        }

        System.out.println(time);



    }
}