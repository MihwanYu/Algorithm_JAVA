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
//    static List<Integer> primes = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        //에라토스테네스의 체

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken().toCharArray(); //소수는 아주 큰 수가 들어오니 문자열로 받아옴
        K = Integer.parseInt(st.nextToken());

        checked = new boolean[K+1]; //체에서 소수만 거르기
        for(int i=2; i<K ; i++) {
            if (checked[i] == false) {
//                primes.add(i);
                if(checkIsBad(i)){
                    System.out.println("BAD "+i);
                    return;
                }
                for (int j = i * 2; j <= K; j += i) {
                    checked[j] = true;
                }
            }
        }

//        for(int i=0; i<primes.size(); i++){
//            if(checkIsBad(primes.get(i))){
//                System.out.println("BAD "+primes.get(i));
//                return;
//            }
//        }
        System.out.println("GOOD");

    }

    static boolean checkIsBad(int x){
        int r=0;
        for(int i=0; i<P.length; i++){
            r = (r * 10 + (P[i] - '0')) %x;
        }
        //아주 큰 수를 자리수별로 쪼개본다음에 x로 나누어떨어지는지 확인
        if(r==0){
            return true;
        }else{
            return false;
        }
    }
}
