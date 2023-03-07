package NONCLASS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1011 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        for(int t=0; t<testcase; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long src = Integer.parseInt(st.nextToken());
            long dst = Integer.parseInt(st.nextToken());
            findcase(dst-src);
        }
    }

    static void findcase(long length){
        long n = 1;
        while(length>n*(n+1)){
            n ++;
        }
        if(length <= Math.pow(n, 2)){
            System.out.println(n*2-1);
        }else{

            System.out.println(n*2);
        }

    }
}
