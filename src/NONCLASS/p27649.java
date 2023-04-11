package NONCLASS;

import java.io.*;
import java.util.*;

public class p27649 {
    static String[] spliters = {"<", ">", "&&", "||", "(", ")", " "};
    public static void main(String[] args) throws Exception{
        //spliters dfs로 넣어서 구현했다가 맞왜틀 당함 ,,, 그냥 if 조건 때려박으니까 됐다
        //가끔은 단순하게 가보자
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<input.length(); i++){
            //가 spliters 중 하나라면 공백 추가\
            char c = input.charAt(i);
            if(input.charAt(i)=='<' || input.charAt(i)=='>' || input.charAt(i)=='(' || input.charAt(i)==')' ){
                sb.append(" ").append(c).append(" ");
            }else if(input.charAt(i)=='|' || input.charAt(i)=='&'){
                sb.append(" ").append(c).append(c).append(" ");
                i++;
            }else{
                sb.append(c);
            }

        }

        StringTokenizer st = new StringTokenizer(sb.toString());
        sb = new StringBuilder();
        while(st.hasMoreTokens()){
            sb.append(st.nextToken()).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);

        System.out.println(sb.toString());
    }

}
