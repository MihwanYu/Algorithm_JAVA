package DAY2;

import java.util.*;

public class p2143 {
    static long[] arr1, arr2;
    static List<Long> partial1, partial2;
    static int p1, p2, N, M;
    static long T;

    static boolean movep1;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        T = sc.nextLong();

        N = sc.nextInt();
        arr1 = new long[N];
        for(int n = 0; n<N; n++){
            arr1[n] = sc.nextLong();
        }

        M = sc.nextInt();
        arr2 = new long[M];
        for(int m = 0; m<M; m++){
            arr2[m] = sc.nextLong();
        }

        partial1 = new ArrayList<>();
        partial2 = new ArrayList<>();


        //arr1의 부분합
        for(int i=0; i<N; i++){
            long sum = arr1[i];
            partial1.add(sum);
            for(int j = i+1; j<N; j++){
                sum += arr1[j];
                partial1.add(sum);
            }
        }
        //arr2의 부분합
        for(int i=0; i<M; i++){
            long sum = arr2[i];
            partial2.add(sum);
//            System.out.println("*");
            for(int j = i+1; j<M; j++){
                sum += arr2[j];
//                System.out.println("*");
                partial2.add(sum);
            }
        }

        Collections.sort(partial1);
        Collections.sort(partial2, Comparator.reverseOrder());

        p1 = 0;
        p2 = 0;
        long result = 0;

        while (true){
            long currentA = partial1.get(p1);
            long currentB = partial2.get(p2);
//            long target = T - currentA;
            if(partial1.get(p1) + partial2.get(p2)==T){
                long count1=0, count2 = 0;
                while(p1 < partial1.size() && partial1.get(p1)==currentA){
                    count1++;
                    p1++;
                }
                while(p2 <partial2.size() && partial2.get(p2) == currentB) {
                    count2++;
                    p2++;
                }
                result += count1*count2;
            }
            else if(partial1.get(p1) + partial2.get(p2) > T ){
                p2 ++;
            }else{
                p1 ++;
            }

            if(p1==partial1.size() || p2==partial2.size()){
                break;
            }
        }

        System.out.println(result);

    }
}
