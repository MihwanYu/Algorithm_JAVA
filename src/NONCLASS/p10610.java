package NONCLASS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class p10610 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 큰 수 N -> string type 입력
        // 30의 배수 생성 가능 여부 -> 2,3,5의 배수인지 -> 10의배수 & 3의배수
        // 30의 배수 중 가장 큰 수 -> char array 내림차순 정렬
        char[] string = br.readLine().toCharArray();

        boolean isZero = false;
        int digitSum = 0;
        for(int i=0; i<string.length; i++){
            if (string[i]=='0'){
                isZero = true;
            }else{
                digitSum += (string[i]-'0');
            }
        }
        if(digitSum%3==0 && isZero){//3의배수 & 10의배수 만족할 때 가장 큰 수 만들기
            Arrays.sort(string);
            String reverse = new StringBuilder(new String(string)).reverse().toString();
            System.out.println(reverse);

            // Arrays.sort 로 reverse 정렬하기 -> char[] 말고 Character[]이었으면 가능
            /*
            Arrays.sort(string, new Comparator<Character>(){
                @Override
                public int compare(Character char1, Character char2){
                    return char2.compareTo(char1);
                }
            });
             */

        }else{//3의배수 & 10의배수 만족하지 못할때
            System.out.println(-1);
        }

    }
}
