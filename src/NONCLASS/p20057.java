package NONCLASS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p20057 {
    //풀다 말았음
    static int N, tr, tc, dir;
    static int[] dr = {0,1,0,-1}, dc = {-1,0,1,0};

    static int grid[][];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        tr = N/2; tc = N/2; //토네이도 시작점은 항상 가운데

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int move_count = 0;
        int dist_count = 1;
        int totalsand = 0;
        dir = 0;
        boolean isfinished = false;
        while(true){

            //특정 방향으로 1칸씩 움직이는 동작 n번 반복
            for(int i=0; i<dist_count; i++){
                int amt = moveSand(); //이동한 방향으로 모래 움직이기
                totalsand += amt;
                System.out.println("Point("+tr+","+tc+") dir"+dir+" (sand) "+amt);
                printgrid();
                if(tr==0 && tc==0) {
                    isfinished = true;
                    break;//토네이도 이동한 위치가 0,0이면 소멸
                }
            }
            if(isfinished) break;

            move_count++;//움직인 횟수 증가
            if(move_count==2){
                //해당 방향으로 2번 움직였으면
                dist_count ++;//그 방향으로 움직일 거리 1 증가
                move_count = 0;
            }
            dir = (dir+1)%4;
        }

        System.out.println(totalsand);
    }

    static void printgrid(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(tr==i && tc==j){
                    System.out.print("■ ");
                }else
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");


    }

    static int moveSand(){
        //tr, tc가 dir 방향으로 1칸 이동한다
        int nr = tr+dr[dir];
        int nc = tc+dc[dir];

        int sandoutside = 0;//밖으로 빠져나간 모래
        int sandamt = grid[nr][nc];
        grid[nr][nc] = 0;//모래 날아가서 더이상x
        int sand1 = (int)(sandamt * 0.01);
        int sand2 = (int)(sandamt * 0.02);
        int sand5 = (int)(sandamt * 0.05);
        int sand7 = (int)(sandamt * 0.07);
        int sand10 = (int)(sandamt * 0.1);
        int sandRemain = sandamt - ((sand1 + sand2 + sand7 + sand10)*2 + sand5);

        int[] sandpercs = {1,2,5,7,10,11};
        int[] sandamounts = {sand1, sand2, sand5, sand7, sand10, sandRemain};
        for(int s=0; s<sandpercs.length; s++){
            for(int t=0; t<2; t++){
                boolean top = t==0;
                if(t==1 && (s==5 || s==11)) continue;//한번만 실행

                int[] sandrc = findRC(nr,nc, dir, sandpercs[s], top);
                if(sandrc[0]>=0 && sandrc[0]<N && sandrc[1]>=0 && sandrc[1]<N){
                    //범위 내에 있다면 -> 해당 위치에 원래 있던 모래에 sand 값 더해주기
                    grid[sandrc[0]][sandrc[1]] += sandamounts[s];
                }else{
                    sandoutside += sandamounts[s];//이거 말고 샌드 양
                }
            }
        }

        tr = nr; tc = nc;

        return sandoutside;//grid 밖으로 빠져나간 모래 수 계산해서 리턴
    }

    static int[] findRC(int r, int c, int dir, int perc, boolean top){

        //dir 왼 아래 오른 위
        //토네이도 (r,c)로 dir로 이동할 때 top/bottom perc의 위치를 리턴
        //case 11 임의로 만들었음
        switch(perc){
            case 1:
                if(top){
                    if(dir==0){
                        return new int[] {r-1, c+1};
                    }else if(dir==1){
                        return new int[] {r-1, c-1};
                    }else if(dir==2){ return new int[] {r+1, c-1};}
                    else return new int[] {r+1, c+1};
                }else{
                    if(dir==0){
                        return new int[] {r+1, c+1};
                    }else if(dir==1){
                        return new int[] {r-1, c+1};
                    }else if(dir==2){ return new int[] {r-1, c-1};}
                    else return new int[] {r+1, c-1};
                }
            case 2:
                if(top){
                    if(dir==0){
                        return new int[] {r-2, c};
                    }else if(dir==1){
                        return new int[] {r, c-2};
                    }else if(dir==2){ return new int[] {r-2, c};}
                    else return new int[] {r, c+2};
                }else{
                    if(dir==0){
                        return new int[] {r+2, c};
                    }else if(dir==1){
                        return new int[] {r, c+2};
                    }else if(dir==2){ return new int[] {r+2, c};}
                    else return new int[] {r, c-2};
                }
            case 5:
                if(dir==0){
                    return new int[] {r, c-2};
                }else if(dir==1){
                    return new int[] {r+2, c};
                }else if(dir==2){ return new int[] {r, c+2};}
                else return new int[] {r-2, c};
            case 7:
                if(top){
                    if(dir==0){
                        return new int[] {r-1, c};
                    }else if(dir==1){
                        return new int[] {r, c-1};
                    }else if(dir==2){ return new int[] {r-1, c};}
                    else return new int[] {r, c+1};
                }else{
                    if(dir==0){
                        return new int[] {r+1, c};
                    }else if(dir==1){
                        return new int[] {r, c+1};
                    }else if(dir==2){ return new int[] {r+1, c};}
                    else return new int[] {r, c-1};
                }
            case 10:
                if(top){
                    if(dir==0){
                        return new int[] {r-1, c-1};
                    }else if(dir==1){
                        return new int[] {r+1, c-1};
                    }else if(dir==2){ return new int[] {r+1, c+1};}
                    else return new int[] {r-1, c+1};
                }else{
                    if(dir==0){
                        return new int[] {r+1, c-1};
                    }else if(dir==1){
                        return new int[] {r+1, c+1};
                    }else if(dir==2){ return new int[] {r-1, c+1};}
                    else return new int[] {r+1, c+1};
                }
            case 11:
                if(dir==0){
                    return new int[] {r, c-1};
                }else if(dir==1){
                    return new int[] {r+1, c};
                }else if(dir==2){ return new int[] {r, c+1};}
                else return new int[] {r-1, c};

        }
        System.out.println("wrong");
        return new int[] {-1,-1};
    }
}
