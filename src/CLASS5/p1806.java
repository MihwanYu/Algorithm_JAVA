package CLASS5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1806 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] accumArr = new int[N+1];//누적합 저장 배열

        st = new StringTokenizer(br.readLine());
        accumArr[1] = Integer.parseInt(st.nextToken());
        for(int i=2; i<=N; i++){
            int n = Integer.parseInt(st.nextToken());
            accumArr[i] = n + accumArr[i-1];
        }

//        System.out.println(Arrays.toString(accumArr));
        // [5 1 3 5 10 7 4 9 2 8] -> acc_sum [0 5 6 9 14 24 31 35 44 46 54]
        // p1선발, p2 후발

        int minlength = N+1;
        int p2 = 0;
        for(int p1= 1; p1<=N; p1++){
            if(accumArr[p1] < S) continue;
            //누적합 >= S
            while(true){
                if(accumArr[p1] - accumArr[p2] <= S){

                    int length = p1-p2;
                    if(accumArr[p1]-accumArr[p2] < S){ //[... 10(24) 7(31) ...]
                        length += 1;
                    }
                    if(length< minlength){
                        minlength = length;
                    }
//                    System.out.println(accumArr[p1]+", p1("+p1+"), p2("+p2+"), length: "+length);
                    break;
                }else{
                    //p1~p2 합 > S일 경우 p2 이동
                    p2++;
                }
            }

        }

        // 부분합 S를 만드는 것이 불가능할 경우 0 출력
        if(minlength==N+1){
            minlength = 0;
        }
        System.out.println(minlength);



    }
}
