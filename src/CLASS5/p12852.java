package CLASS5;

import java.io.*;
import java.util.*;

public class p12852 {
    static int N;
    static class Pair{
        int cnt, pre;//cnt: 연산 횟수, pre: 앞 연산 number
//        ArrayList<Integer> arr;
        Pair(){
            this.cnt = Integer.MAX_VALUE;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());


        Pair numbers[] = new Pair[N+1];
        for(int i=0; i<=N; i++){
            numbers[i] = new Pair();
        }

        numbers[N].cnt = 0;
        numbers[N-1].cnt = 1;
        numbers[N-1].pre = N;

        if(N%2==0) {
            numbers[N/2].cnt = Math.min(numbers[N/2].cnt, numbers[N].cnt+1);
            if(numbers[N/2].cnt==numbers[N].cnt+1){
                numbers[N/2].pre = N;
            }
        }
        if(N%3==0) {
            numbers[N/3].cnt = Math.min(numbers[N/3].cnt, numbers[N].cnt+1);
            if(numbers[N/3].cnt==numbers[N].cnt+1){
                numbers[N/3].pre = N;
            }
        }

        for(int i=N-1; i>1; i--){
//            System.out.println("i: "+i);
            numbers[i-1].cnt = Math.min(numbers[i-1].cnt, numbers[i].cnt+1);//1 뺀거
            //값 바뀌었으면 pre 업데이트
            if(numbers[i-1].cnt==numbers[i].cnt+1){
                numbers[i-1].pre = i;
            }

            //2 나누기
            if(i%2==0){
                numbers[i/2].cnt = Math.min(numbers[i/2].cnt, numbers[i].cnt+1);
                //값 바뀌었으면 arr 업데이트
                if(numbers[i/2].cnt==numbers[i].cnt+1){
                    numbers[i/2].pre = i;
                }
                if(i/2==1) break;
            }

            //3 나누기
            if(i%3==0){
                numbers[i/3].cnt = Math.min(numbers[i/3].cnt, numbers[i].cnt+1);//1 뺀거
                //값 바뀌었으면 arr 업데이트

                if(numbers[i/3].cnt==numbers[i].cnt+1){
//                    System.out.println("i/3="+(i/3)+" , "+Arrays.toString(numbers[i].arr.toArray()));
                    numbers[i/3].pre = i;
                }
                if(i/3==1) break;
            }


        }

        System.out.println(numbers[1].cnt);
//        System.out.println(Arrays.toString(numbers[1].arr.toArray()));
        StringBuilder sb = new StringBuilder("");
        sb = getParents(1, numbers);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());

        //10 -> 1
        //9: pre : 10, 9
        //3: pre: 10, 9, 3
        //1: pre: 10, 9, 3, 1

    }

    static StringBuilder getParents(int idx, Pair[] numbers){
        if(idx==N){
            StringBuilder sb = new StringBuilder("");
            sb.append(N).append(" ");
            return sb;
        }

        StringBuilder sb = getParents(numbers[idx].pre, numbers);
        sb.append(idx).append(" ");

        return sb;
    }
}
