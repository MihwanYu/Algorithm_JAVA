package NONCLASS;

import java.io.*;
import java.util.*;
public class p2668 {
    static int N;
    static int[] numbers;
    static boolean[] numgroup;
    static ArrayList<Integer>[] chain;
    public static void main(String[] args) throws Exception {
        //알고리즘 분류: dfs
        //예제가 좀더 많으면 사고의 흐름이 좀 더 빨랐을 텐데
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new int[N+1];
        numgroup = new boolean[N+1];
        chain = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            chain[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++){
            int n = Integer.parseInt(br.readLine());
            numbers[i] = n;
            chain[n].add(i);
        }

        for(int i=1; i<=N; i++){
            if(numgroup[i]) continue;
            for(Integer nextnum : chain[i]){
                boolean hasval = dfs(nextnum, i);
                if(hasval) break;
            }
//            System.out.println(Arrays.toString(numgroup));
        }

        StringBuilder sb = new StringBuilder("");
        int count = 0;
        for(int i=1; i<=N; i++){
            if(numgroup[i]){
                count++;
                sb.append(i).append("\n");
            }
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(count);
        System.out.println(sb.toString());
    }

    static boolean dfs(int num, int root){

        if(num==root) {
            numgroup[num] = true;
            return true;
        }

        //자식 탐색하려고 하는데 자식이 num이면 true 리턴해가면서 chain <- true
        //자식 존재하지 않으면 걔는 false

        boolean hasval = false;
        for(Integer nextnum : chain[num]){

            hasval = dfs(nextnum, root);
            if(hasval) break;
        }

        if(hasval) {
            numgroup[num] = true;
        }

        return hasval;

    }
}
