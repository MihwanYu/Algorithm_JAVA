package DAY5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p1837 {

    static int K;
    static char[] P;
    static boolean[] checked; //에라토스테네스의 체
    static List<Integer> primes = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        //에라토스테네스의 체
        /*
        long pq, k;//입력받기... 큰 수인데 어떻게 하지
        long[] pqlist = new long[100];
        for (long i=2; i< pqlist.length; i++){
            pqlist[i] = i;
        }
        for(long i=2; i< pqlist.length; i++){
            if(pqlist[i]==0) continue;
            if (pq%i==0){
                System.out.println("BAD "+i);
                break;
            }
            for(long j = 2*i; j<pqlist.length; ){
                pqlist[j] = 0;
                j += i;
            }
        }
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        checked = new boolean[K+1];
        for(int i=2; i<=K ; i++) {
            if (checked[i] == false) {
                primes.add(i);
                for (int j = i * 2; j <= K; j += i) {
                    checked[j] = true;
                }
            }
        }
        for(int i=0; i<primes.size(); i++){
            if(checkIsBad(primes.get(i))){
                System.out.println("BAD "+primes.get(i));
                return;
            }
        }
        System.out.println("GOOD");

    }

    static boolean checkIsBad(int x){
        int r=0;
        for(int i=0; i<P.length; i++){
            r = (r * 10 + (P[i] - '0')) %x;
        }
        if(r==0){
            return true;
        }else{
            return false;
        }
    }
}
