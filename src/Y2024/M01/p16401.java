package Y2024.M01;


import java.io.*;
import java.util.*;

public class p16401 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //최대한 길이는 길어야 함
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Integer[] cookies = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            cookies[i] = Integer.parseInt(st.nextToken());

        }

        Arrays.sort(cookies, Collections.reverseOrder());
        int maxCookieLen = 0;
        int start = 0;
        int end = cookies[0];//최댓값

        if(end==1){
            //가장 긴 막대 길이가 1이라면 -> 길이 1짜리 막대가 N개인 것,
            //M<=N이면 1, 아니면 0이 정답
            int ans = M<=N? 1 : 0;
            System.out.println(ans);
            return;
        }
        while(start<=end){
            int mid = (start+end)/2;
//            System.out.println("mid: "+mid);
            int cnt = 0;
            for(int i=0; i<N; i++){
                if(cookies[i]<mid || mid==0) break;
                cnt += cookies[i]/mid;
            }

            if(cnt>=M){
                maxCookieLen = Math.max(maxCookieLen, mid);
                start = mid+1;
            }else{
                end = mid-1;
            }
            //mid길이의 과자가 M개 이상 생성이 된다면
            //그중 길이가 길게 해주기 위해
            //maxcookie 업데이트
            //start = mid+1;

            //mid길이의 과자가 M개가 안 되면
            //end = mid-1;


        }

        System.out.println(maxCookieLen);


    }
}
