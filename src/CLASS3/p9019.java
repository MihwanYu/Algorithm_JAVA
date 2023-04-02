package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p9019 {
    static class DSLR{
        int prev, idx;
        String command;
        public DSLR(int n, int idx, String s){
            this.idx = idx;
            this.prev = n;
            this.command = s;
        }

        @Override
        public String toString(){
            return "("+this.prev+", "+this.idx+", "+this.command+")";
        }
    }
    static DSLR[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testcase = Integer.parseInt(br.readLine());
        dp = new DSLR[10000];

        for(int i=0; i<testcase; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());

            //DP 전체 null
            for(int idx=0; idx<dp.length; idx++){
                dp[idx] = null;
            }
            findCommand(start, dst);
        }

    }

    static void findCommand(int start, int dst){
        dp[start] = new DSLR(-1, start, "");
        Queue<DSLR> q = new LinkedList<>();
        q.add(dp[start]);
        while(true){
            DSLR cur = q.poll();
            if(cur.idx==dst){
//                System.out.println("find out");
                //역으로 돌아가기
                StringBuilder sb = new StringBuilder();
                int count=0;
                while(true){

                    sb.append(cur.command);
//                    System.out.println(cur);
                    if(cur.prev==-1) break;
                    cur = dp[cur.prev];
                }
                sb.reverse();
                System.out.println(sb.toString());
                break;
            }

            for(int i=0; i<4; i++){
                DSLR nd;
                if(i==0){
                    nd = methodD(cur.idx);
                }else if(i==1){
                    nd = methodS(cur.idx);
                }else if(i==2){
                    nd = methodL(cur.idx);
                }else{
                    nd = methodR(cur.idx);
                }

                if(dp[nd.idx]!= null) continue;
//                System.out.println(nd);
                dp[nd.idx] = nd;
                q.add(nd);
            }
            /*
            int newD = methodD(cur);
            int newS = methodS(cur);
            int newL = methodL(cur);
            int newR = methodR(cur);

            dp[newD] = cur;
            dp[newS] = cur;
            dp[newL] = cur;
            dp[newR] = cur;

            q.add(newD);
            q.add(newS);
            q.add(newL);
            q.add(newR);
             */

        }


    }

    static DSLR methodD(int n){
        if(n*2<10000){
            return new DSLR(n, n*2, "D");
        }else{
            return new DSLR (n, n*2%10000,"D");
        }
    }

    static DSLR methodS(int n){
        if(n-1>=0){
            return new DSLR( n,n-1, "S");
        }else{
            return new DSLR(n,9999, "S");
        }
    }

    static DSLR methodL(int n){
        int mod = n*10%10000;
        int div = n*10/10000;
        return new DSLR(n,mod+div,"L");
    }

    static DSLR methodR(int n){
        int mod = n%10;
        int div = n/10;
        return new DSLR(n,div + mod*1000, "R");
    }

}
