package NONCLASS;

import java.io.*;
import java.util.*;

public class p2108 {
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int countup = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        int maxfreq = 0;
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(br.readLine());
            countup += numbers[i];
            freq.put(numbers[i], freq.getOrDefault(numbers[i],0)+1);
            if(freq.get(numbers[i])>maxfreq){
                maxfreq = freq.get(numbers[i]);
            }
        }

        int[] freqnum = new int[N];
        Arrays.fill(freqnum, Integer.MAX_VALUE);
        int i=0;
        for(int key: freq.keySet()){
            if(freq.get(key)==maxfreq){
                freqnum[i] = key;
            }
            i++;
        }//1, 1, 2, 2, 3
        Arrays.sort(numbers);
        Arrays.sort(freqnum);
//        System.out.println("최빈: "+Arrays.toString(freqnum));

        System.out.println(Math.round((double)(countup)/N));//산술평균
        System.out.println(numbers[N/2]);//중앙값
        //최빈값, 두번째 작은 값
        if(N==1){
            System.out.println(numbers[0]);
        }else{
            if(freqnum[1]==Integer.MAX_VALUE){
                System.out.println(freqnum[0]);
            }else{
                System.out.println(freqnum[1]);
            }

        }
        System.out.println(numbers[N-1]-numbers[0]);//범위
    }
}
