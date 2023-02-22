package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p11724 {
    static int N, M, count;
    static int[] parents;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        count = 0;
        for(int i=0; i<N+1; i++){
            //parents 초기화: 모든 노드가 자기 자신을 부모 노드로 가짐
            parents[i] = i;
        }

        int u, v;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
                //연결된 적 없으면 u의 root와 v의 root를 연결
            connectNode(u, v);

        }

        System.out.println(Arrays.toString(parents));
        for(int i=1; i<N+1; i++){
            //root node의 갯수 카운트
            if(parents[i]==i){
                count ++;
            }
        }
        System.out.println(count);
    }

    static int findparent(int u){
        if(parents[u]==u){
            return u;
        }else{
            return findparent(parents[u]);
        }
    }


    static void connectNode(int u, int v){
        int up = findparent(u);
        int vp = findparent(v);
        if(up==vp) return;
        if(up<vp){
            parents[vp] = up;
        }else{
            parents[up] = vp;
        }
    }

}
