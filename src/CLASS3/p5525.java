package CLASS3;

import java.io.*;
import java.util.*;

public class p5525 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());//I갯수 N+1만큼. N=1 -> IOI
        int M = Integer.parseInt(br.readLine());//S의 길이
        String S = br.readLine();

        int answer = 0;
        char[] charArr = S.toCharArray();

        //흑흑,,
        int count = 0;
        for(int i=1; i<M-1; i++){
            // i-1, i, i+1이 IOI형태인지 확인
            if(charArr[i-1]=='I' && charArr[i]=='O' && charArr[i+1]=='I'){
                count++;
                if(count>=N){
                    answer++;
                }

                i++;
            }else{
                count = 0;
            }


        }


        /*
        맞왜틀 .... 하
        char preChar = S.charAt(0);
        int count = preChar=='I' ? 1: 0;
        for(int i=1; i<M; i++){
            char a = S.charAt(i);
            //IOIOIOI... 형태가 나올 때마다 길이 몇인지 확인하기
            // P1 = IOI, I(4) - I(2)  => 3개
            // P2 = IOIOI, I(4) - I(3) => 2개
            if(a==preChar){
                //같은 char이 연속될 경우
                //앞의 IOIOI 길이 구하기
                //Pn 몇개인지 구하기 -> count

                //OOIOIOIOIIOII
                if (count > 1) {
                    answer += (count-N);
                }
                //count 수 구하기
                count = a=='I'? 1 : 0;// ...II면 1, ...OO면 0
            }else if(a=='I'){
                // ...OI 일때
                count++;

            }
            preChar = a;
        }
        //마지막 char을 포함한 문자열에서 IOIOI 형태 나왔을 때 길이 계산하기
        if (count > 1) {
            answer += (count-N);
        }

         */

        System.out.println(answer);
    }
}
/*
1
20
OIOIIIOIOIOIOIOIOIOO

1
3
IOI

1
3
III
 */