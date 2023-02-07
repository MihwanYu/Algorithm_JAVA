package DAY6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1717 {

    static int[] parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        for(int i=0; i<N+1; i++){
            parents[i] = i;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken());
            int e1 = Integer.parseInt(st.nextToken());
            int e2 = Integer.parseInt(st.nextToken());
            if(option==0){
                union(e1, e2);
            }else{
                if(findparent(e1)==findparent(e2)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }

    }

    static void union(int u, int v){
        if(findparent(u)==findparent(v)) return;
        int up = findparent(u);
        int vp = findparent(v);
        if (up<vp){
            parents[vp] = up;
        }else{
            parents[up] = vp;
        }
    }

    static int findparent(int u){
        if(u==parents[u]){
            return u;
        }else {
            return parents[u] = findparent(parents[u]); //경로압축 <- 시간 초과 문제 해결
        }
    }

}
