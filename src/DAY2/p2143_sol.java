package DAY2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class p2143_sol {
    static int[] arr1, arr2;
    static ArrayList<Integer> subA, subB;
    static int p1, p2, T, N, M, count;

    static boolean movep1;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        N = sc.nextInt();
        arr1 = new int[N];
        for(int n = 0; n<N; n++){
            arr1[n] = sc.nextInt();
        }

        M = sc.nextInt();
        arr2 = new int[M];
        for(int m = 0; m<M; m++){
            arr2[m] = sc.nextInt();
        }

        subA = new ArrayList<>();
        subB = new ArrayList<>();


        //arr1의 부분합
        for(int i=0; i<N; i++){
            int sum = arr1[i];
            subA.add(sum);
            for(int j = i+1; j<N; j++){
                sum += arr1[j];
                subA.add(sum);
            }
        }
        //arr2의 부분합
        for(int i=0; i<M; i++){
            int sum = arr2[i];
            subB.add(sum);
//            System.out.println("*");
            for(int j = i+1; j<M; j++){
                sum += arr2[j];
//                System.out.println("*");
                subB.add(sum);
            }
        }

        Collections.sort(subA);
        Collections.sort(subB, Comparator.reverseOrder());

        long result = 0;
        int ptA = 0, ptB = 0;
        while (true){
            long currentA = subA.get(ptA);
            long target = T - currentA; //target을 subB에서 찾는 방식
            if(subB.get(ptB)> target){
                ptB++;
            }else if(subB.get(ptB) < target){
                ptA++;
            }else{
                //currentB == target인 경우 -> 동일 case를 찾아줘야 함
                long countA = 0;
                long countB = 0;
                while(ptA < subA.size() && subA.get(ptA) == currentA){
                    ptA++;
                    countA++;
                    //동일한 ptA 만큼 증가시켜주는건데 그냥 frequency 쓰는거랑 뭐가 다르지?
                    // -> 시간복잡도 때문, frequency는 처음부터 세기 때문에 O(N)을 여러번 쓰니까 결국 O(N^2)가 됨
                }
                while(ptB < subB.size() && subB.get(ptB) == target){
                    ptB++;
                    countB++;
                    //동일한 ptA 만큼 증가시켜주는건데 그냥 frequency 쓰는거랑 뭐가 다르지?
                }
                result += countA * countB; //여기서 int 범위 벗어날 수 있기 때문에 result는 long을 써야 함

            }

            if(ptA == subA.size() || ptB == subB.size()){
                break;
            }
        }
        System.out.println(result);


    }
}
