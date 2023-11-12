package CLASS2;

import java.io.*;
import java.util.*;

public class p1874 {
    //나중에 다시풀깅....
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //스택: 반드시 오름차순
        //push와 pop을 몇 번 해야하는지 계산하기
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];

        Stack<Integer> stack = new Stack<>();
        // 1부터 n까지 스택에 넣었다가 뽑아서 늘어놓는다.
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int curnum = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            int tgt = arr[i];
            while(curnum != tgt){

            }

        }
        //1 2 3 4 5 6 7 8 넣었다가 뽑아 늘어놔서 수열 만들기
        //1 push [1]
        //2 push [1,2]
        //3 push [1,2,3]
        //4 push [1,2,3,4]
        //4 pop  [1,2,3]    -> arr[0] 4
        //3 pop  [1,2]      -> arr[1] 3
        //5 push [1,2,5]
        //6 push
        //6 pop             -> arr[2] 6
        //7 push
        //8 push
        //8 pop             -> arr[3] 8
        //7 pop             -> arr[4] 7
        //5 pop             -> arr[5] 5
        //2 pop             -> arr[6] 2
        //1 pop             -> arr[7] 1
    }
}
