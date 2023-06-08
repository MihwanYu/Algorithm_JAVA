package CLASS5;

import java.io.*;
import java.util.*;
public class p1644 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] che = new boolean[4000000];
        Arrays.fill(che, true);
        //에라토스테네스 체: 소수만 true
        che[1] = false;
        for(int i=2; i<che.length; i++){
            if( !che[i] ) continue;
            for(int j=2; i*j<che.length; j++){
                che[i*j] = false;
            }
        }
        int caseN = 0;

        for(int i=2; i<che.length; i++){
            if( !che[i] ) continue;
            if( i==N ){
                caseN++; break;
            }
            else if(i>N/2) continue; //소수 i가 N보다 크거나 같으면 이후 경우의 수 존재x
            else if(i>N) break;
            int partialsum = i; //소수 i부터
//            StringBuilder sb = new StringBuilder(i+"+");
            //i 뒷 소수들 j 연속 덧셈
            for(int j=i+1; j<che.length; j++){
                if( !che[j] ) continue;
                partialsum += j;
//                sb.append(j+"+");
                if(partialsum==N){
                    //부분합 같다면
//                    System.out.println(sb.toString());
                    caseN ++; break;
                }else if(partialsum > N){
                    break;
                }
            }
//            System.out.println("\npartial: "+partialsum);
        }

        System.out.println(caseN);

    }
}
