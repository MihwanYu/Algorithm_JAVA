package CLASS3;

import java.io.*;
import java.util.*;

public class p17626 {
    static int[] arr = new int[4];
    static int min = 4;
    public static void main(String[] args) throws Exception{
        //알고리즘 븐류: 브루트 포스 / 다이나믹 프로그래밍
        //dp로 푸는 법: https://www.acmicpc.net/board/view/95409
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = dfs(N, N, 0);
        System.out.println(min);
    }

    static int dfs(int N, int prenum, int cnt){

        if(cnt>=4){
            return -1;
        }else if(N==0){

            if(cnt<min){
                min=cnt;
                System.out.println(Arrays.toString(arr));
            }
            return cnt;
        }

        int num = (int)Math.sqrt(N);
        int count=-1;
        while(num>0){
            if(num>prenum){
                break;
            }
            int paramN = N-num*num;

            //num^2를 N에서 뺀 값 넘기기
            arr[cnt] = num;
            count = dfs(paramN, num, cnt+1);

//            if(count>0) break;
            num--;
        }

        return count;
    }
}
