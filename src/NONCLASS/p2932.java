package NONCLASS;

import java.io.*;
import java.util.*;

public class p2932 {
    static int N;
    static ArrayList<Pair> rotateinfo;
    static class Pair{
        int row, col, amtR, amtC;
        Pair(int row, int col, int amtR, int amtC){
            this.row = row; this.col = col;
            this.amtR = amtR; this.amtC = amtC;
        }
    }
    public static void main(String[] args) throws Exception{
        //이게어떻게실버3 ..
        //문제는간단하지만 한번에 생각하긴 어려운것같다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        rotateinfo = new ArrayList<>();
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());

            int cnt = rotateGrid(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
            System.out.println(cnt);
//            System.out.println(Arrays.toString(countRotate));
        }

    }

    static int rotateGrid(int target, int r, int c){
        int originR = target/N;
        int originC = target%N-1;
        if(originC<0) {
            originR--;
            originC = N-1;
        }


        /* 수정 전: 이동 정보 모두 더해버렸음
        System.out.println("\ntarget "+target+"-> ("+r+","+c+")");
        System.out.print("("+originR+","+originC+") => ");
        //이전에 row, col이 회전 영향을 받았을 경우
        //row: col이 회전한 횟수만큼 덧셈
        //col: row가 회전한 횟수만큼 덧셈 countRotate[originR]
        originC = (originC + countRotate[originR])%N;
        originR = (originR + countRotate[N+originC])%N;
        System.out.print("("+originR+","+originC+") + ");

        int rotateR = r-originR>=0? r-originR : r-originR + N;
        int rotateC = c-originC>=0? c-originC : c-originC + N;
        System.out.println("("+rotateR+","+rotateC+") => "+(rotateR+rotateC));


        //originC만큼 row를 이동해줌
        countRotate[originR] += rotateC;
        //이동 후 바뀐 열 c에서 rotateR만큼 이동해줌
        countRotate[N+c] += rotateR;
         */

//        System.out.print("("+originR+","+originC+") => ");
        for(int i=0; i<rotateinfo.size(); i++){
            //origin r, c가 변화 대상 pair에 속했다면 값 바꿔주기
            Pair p = rotateinfo.get(i);
//            System.out.println(p.row+"행 회전: "+p.amtR+" , "+p.col+"열 회전: "+p.amtC);
            if(originR==p.row){//회전 row 당첨되면
                //col 회전
                originC += p.amtR;
                originC %= N;
            }
            if(originC==p.col){//회전 col 당첨되면
                originR += p.amtC;
                originR %= N;
            }

        }

//        System.out.println("("+originR+","+originC+") ");
        //origin r, c가 가장 마지막까지 이동한 위치 -> k번째 단계에서 이동해야 하는 위치 정보 찾기

        int amtR = c-originC>=0? c-originC : c-originC+N;//c이동해야 하는 만큼 originR row 회전
        int amtC = r-originR>=0? r-originR : r-originR+N;//r이동해야 하는 만큼 target c col 회전
//        System.out.println(originR+"행 회전: "+amtR+" , "+c+"열 회전: "+amtC);
        rotateinfo.add(new Pair(originR, c, amtR, amtC));//움직인 col, row 각각 움직인 칸 수까지 params 4개

        return amtR + amtC;
    }
}
