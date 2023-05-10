package NONCLASS;

import java.io.*;
import java.util.*;

public class p12904 {
    public static void main(String[] args) throws Exception{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());

        boolean isAvailable = true;
        while(S.length()!=T.length()){
            //S, T 길이 같을 때까지 반복
            //S: 문자열의 뒤에 A를 추가 or 문자열을 뒤집고 뒤에 B를 추가
            //=> T: 문자열의 뒤에 A를 삭제 or 문자열의 뒤에 B를 삭제 후 뒤집기
            // ABBA -> ABB -> BA -> B

            if(T.charAt(T.length()-1)=='A'){
                //맨 뒤에 A면 A를 삭제
                T.deleteCharAt(T.length()-1);
            }else{
                T.deleteCharAt(T.length()-1);
                T = T.reverse();
            }

        }
        for(int i=0; i<S.length(); i++){
            if(S.charAt(i) != T.charAt(i)){
                isAvailable = false;
                break;
            }
        }

        int answer = isAvailable? 1: 0;
        System.out.println(answer);
    }

    static boolean isEqual(StringBuilder S, StringBuilder T, boolean isOrdered){
        if(isOrdered){
            if( !(T.charAt(T.length()-1)=='A')) return false;
            for(int i=1; i<=S.length(); i++){
                if( !(S.charAt(S.length()-i)==T.charAt(T.length()-1-i)) ) return false;
            }
        }else{
            //B -> BA -> ABB -> ABBA
            //ABBA

            for(int i=1; i<=S.length(); i++){
                if( !(S.charAt(S.length()-i)==T.charAt(i-1)) ) return false;
            }
            if( !(T.charAt(S.length())=='B')) return false;
        }
        return true;
    }

}
