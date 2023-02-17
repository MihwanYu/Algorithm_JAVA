package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p6588 {
    static boolean[] che;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        che = new boolean[1000000+1];
        //초기화: 소수면 false, 소수가 아니면 true
        che[0] = true;
        che[1] = true;
        for(int c = 2; c<che.length; c++){
            if(che[c]) continue;
            for(int m=2; m*c<che.length; m++){
                che[c*m] = true;
            }
        }

        while(true){
            int num = Integer.parseInt(br.readLine());
            if(num==0) break;

            goldbach(num);
        }
    }

    static void goldbach(int num){
        StringBuilder sb = new StringBuilder();
        for(int p1 = num; p1>2; p1--){
            if(che[p1] || che[num-p1]) continue;
            int p2 = num-p1;
            if(p1 + p2 == num){
                sb.append(num).append(" = ").append(p2).append(" + ").append(p1);
                return;
            }


        }
    }
}
