package NONCLASS;

import java.io.*;
import java.util.*;

public class p1459 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Integer.parseInt(st.nextToken());
        long Y = Integer.parseInt(st.nextToken());
        long W = Integer.parseInt(st.nextToken());
        long S = Integer.parseInt(st.nextToken());

        //(0,0)에서 (X,Y)로 가는 최소시간
        //W: 가로, 세로로 걸리는 시간
        //S: 대각선 이동 시간

        //가로세로로만 이동하거나
        long straight_only = (X+Y)*W;

        //대각선으로만 이동하거나
        long diagnoal_only;
        if( (X+Y)%2==0 ) diagnoal_only = S * Math.max(X,Y);
        else{
            diagnoal_only = S * (Math.max(X,Y)-1) + W;
        }

        //대각선으로 이동 + 가로세로 추가이동
        long diagonal = X>Y ? S*Y : S*X;
        long straight = X>Y ? W*(X-Y) : W*(Y-X);
        long complex = Math.min(X,Y)*S + Math.abs(X-Y)*W;
//        System.out.println(straight_only);
//        System.out.println(diagnoal_only);
//        System.out.println(complex);

        System.out.println(Math.min(straight_only, Math.min(diagnoal_only, complex)));






    }


}
