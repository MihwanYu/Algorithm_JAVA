package CLASS3;

import java.io.*;
import java.util.*;
public class p1620 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] poketmon = new String[N+1];
        HashMap<String, Integer> poket_dict = new HashMap<>();
        for(int i=1; i<=N; i++){
            poketmon[i] = br.readLine();//첫글자만 대문자+소문자 or 소문자 + 마지막만 대문자, 2~20
            poket_dict.put(poketmon[i], i);
        }

        for(int i=0; i<M; i++){
            //입력==숫자 -> 문자 출력 / 문자 -> 숫자 출력
            String q = br.readLine();
            if(q.charAt(0)>=65){
                //입력이 문자일 때
                System.out.println(poket_dict.get(q));
            }else{
                System.out.println(poketmon[Integer.parseInt(q)]);
            }
        }




    }
}
