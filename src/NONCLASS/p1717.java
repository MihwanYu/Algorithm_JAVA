package NONCLASS;

import java.io.*;
import java.util.*;
public class p1717 {
    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i=0; i<n+1; i++){
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(cmd==0){
                //union 연산
                union(a,b);
            }else{
                //find 연산
                boolean isSame = find(a,b);
                if(isSame){
                    sb.append("yes\n");
                }else{
                    sb.append("no\n");
                }
            }

        }
        if(sb.length()>1){
            sb.deleteCharAt(sb.length()-1);
        }
        System.out.println(sb.toString());

    }

    static void union(int a, int b){
        //a,b 합하기
        if(a==b) return;
        int parentA = find_parent(a);
        int parentB = find_parent(b);
        if(parentA<parentB){
            parent[parentB] = parentA;
        }else{
            parent[parentA] = parentB;
        }

    }

    static boolean find(int a, int b){
        if(a==b) return true;
        int parentA = find_parent(a);
        int parentB = find_parent(b);
        if(parentA == parentB) return true;
        else return false;
    }

    static int find_parent(int a){
        if(parent[a]==a) return a;
        else return parent[a] = find_parent(parent[a]);
    }


}
