package CLASS3;

import java.io.*;
import java.util.*;
public class p6064 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for(int t=0; t<testcase; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int answer = getAnswer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            System.out.println(answer);

        }

    }

    static int getAnswer(int N, int M, int x, int y){
        int answer = -1;

        for(int i=0; i<=M; i++){
            int year = i*N+x;
//            System.out.println("year: "+year);
            if(year%M==y || (year%M==0 && M==y)){
                answer = year;
                break;
            }
        }
        return answer;

    }

    static int getAnswer2(int N, int M, int x, int y){

        int n=1, m=1;
        int year = 1;
        boolean yearbreak = false;
        int count = 0;
        while(!(n==N && m==M)){
            if(n==x && m==y) {
                yearbreak = true;
                break;
            }
            if(n<N) n = n+1;
            else n = 1;

            if(m<M) m = m+1;
            else m = 1;

            year++;

        }

        if(!yearbreak && !(n==x && m==y)) year = -1;

        return year;

    }
}
