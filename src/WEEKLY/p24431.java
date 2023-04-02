package WEEKLY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class p24431 {

    static int n, L, F;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testcase = Integer.parseInt(br.readLine());

        for(int i=0; i<testcase; i++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            F = Integer.parseInt(st.nextToken());
            String[] strarr = new String[n];
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                strarr[j] = st.nextToken();
            }
            findrhymepairs(strarr);
        }
    }

    static void findrhymepairs(String[] strarr){
        //strarr에서 맨 뒤에서 f개 이상 동일한 단어 나열을 갖는 pair의 갯수
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<strarr.length; i++){
            String partial = strarr[i].substring(L-F);
            map.put(partial, map.getOrDefault(partial, 0) + 1);
        }

        int count = 0;
        for(String key: map.keySet()){
//            System.out.println("("+key+"): "+map.get(key));
            count += map.get(key)/2;
        }

        System.out.println(count);
    }
}
