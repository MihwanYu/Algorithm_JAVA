package NONCLASS;

import java.io.*;
import java.util.*;

public class p1802 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine()) ;

        for(int t=0; t<testcase; t++){
            String input = br.readLine();
            boolean istrue = isflipAvailable(input);
            String answer = istrue? "YES" : "NO";
            System.out.println(answer);
        }

    }

    static boolean isflipAvailable(String input){
        if(input.length()==1) return true;
        else if(input.length()==3){
            //길이 3일 때 가운데 기점 대칭 확인
            if(Math.abs(input.charAt(0)-input.charAt(2))==0) return false;
            return true;
        }

        int mid = input.length()/2;
        boolean flippre = isflipAvailable(input.substring(0, mid));
        boolean flippost = isflipAvailable(input.substring(mid+1));
        //둘중에 하나라도 틀리면 false
        //맞다면 가운데 기점 대칭 확인
        if(flippre && flippost){
            int midpre = (mid-1)/2;
            //flippre와 flippost의 정 가운데 글자 대칭 확인

            //flippre와 flippost의 절반의 대칭 확인
            for(int i=1; i<=mid-midpre; i++){
                if(Math.abs(input.charAt(mid-i)-input.charAt(mid+i))==0) return false;
            }

            return true;
        }else{
            return false;
        }

    }
}
