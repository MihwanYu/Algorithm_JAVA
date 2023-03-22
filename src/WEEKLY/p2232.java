package WEEKLY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class p2232 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] landmines;
        int[] increases;

        int N = Integer.parseInt(br.readLine());
        landmines = new int[N+1];
        increases = new int[N+1];

        for(int i=1; i<N+1; i++){
            landmines[i] = Integer.parseInt(br.readLine());
            if(landmines[i] > landmines[i-1]){
                increases[i] = 1;
            }else if(landmines[i] < landmines[i-1]){
                increases[i] = -1;
            }
        }

        for(int i=1; i<N; i++){
            if(increases[i] > increases[i+1]){
                System.out.println(i);
            }else if(increases[i]==0 && increases[i+1]==0){
                System.out.println(i);
            }
        }
        if(increases[N]==1){
            System.out.println(N);
        }else if(increases[N]==0 && increases[N-1]==0){
            System.out.println(N);
        }

    }
}
