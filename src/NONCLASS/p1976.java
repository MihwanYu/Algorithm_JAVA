package NONCLASS;

import java.io.*;
import java.util.*;
public class p1976 {
    static int[] parent;

    public static void main(String[] args) throws Exception{
        //연결이 되어 있는지만 확인하면 됨. yes or no
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i=1; i<N+1; i++) parent[i] = i;

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                //1일 경우 합치기
                if(Integer.parseInt(st.nextToken())==1){
                    union(i+1,j+1);
                }
            }
        }
//        System.out.println(Arrays.toString(parent));

        //여행 계획 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        //---여행 가능한지 여부 확인하기---
        int cur = Integer.parseInt(st.nextToken());
        String answer = "YES";
        for(int i=1; i<M; i++){
            int next = Integer.parseInt(st.nextToken());

            if(parent[cur]==parent[next]) {
                cur = next;
            }
            else{
                answer = "NO";
                break;
            }
        }
        System.out.println(answer);

    }

    static void union(int i, int j){
        int p1 = find(i);
        int p2 = find(j);
        if(p1==p2) return;
        if(p1<p2) parent[p2] = p1;
        else parent[p1] = p2;
    }

    static int find(int i){
        if(parent[i]==i) return i;
        else return parent[i] = find(parent[i]);

    }




}
