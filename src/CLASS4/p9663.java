package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p9663 {
    static int[] tag, answer;
    static int N, count;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = new int[N];
        for(int i=0; i<N; i++){
            answer[i] = -1;
        }
        count = 0;
        nqueens(0);
        System.out.println(count);
    }

    static void nqueens(int idx){
        if(idx==N ){
//            System.out.println("idx: "+idx+", "+Arrays.toString(answer));
            count++;
            return;
        }

        for(int i=0; i<N; i++){ // answer[idx]자리에 0 ~ N-1 중 넣을 수 있는 경우의 수 확인
            boolean tag_available = true;

            if(answer[0]==i || Math.abs(answer[0] - i)==idx ){
                tag_available = false;
            }

            for(int k=1; k<idx; k++){
                if(!tag_available){
                    break;
                }
                //idx가 2일때 -> idx-1의 값과 i 값이 1차이 나면 안됨 , i값이 이전 idx에 들어가 있으면 안됨
                if(Math.abs(answer[idx-k] - i)==k || answer[k]==i){
                    tag_available = false;
                }
            }
//            if(answer[0]==0 && answer[1]==5 && answer[2]==7 && answer[3]==2){
//                System.out.println("idx: "+idx+", "+Arrays.toString(answer));
//                System.out.println("answer["+idx+"] = "+i+": "+tag_available);
//            }

            if(tag_available){
                answer[idx] = i;
                nqueens(idx+1);
            }

        }



    }

}
