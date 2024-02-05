package Y2024.M01;

import java.io.*;
import java.util.*;
public class p20922 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());



        Queue<Integer>[] idxes = new LinkedList[100001];
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        int start = 0;
        int max_length = 0;
        for(int i=0; i<N; i++){
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            if(idxes[n]==null){
                idxes[n] = new LinkedList<>();
                idxes[n].add(i);//정수 K가 등장한 인덱스 i 추가
            }else{
                //index[n] 크기가 K보다 작으면 숫자 들어가면 그냥 삽입
                if(idxes[n].size()<K) idxes[n].add(i);
                else{
                    //아니면 지금까지 길이 계산, start 업데이트, 새로운 값 추가
//                    max_length = Math.max(max_length, i-start);
                    max_length = Math.max(max_length, i-start);
                    idxes[n].add(i);// 새로운 값 추가
                    start = Math.max(start, idxes[n].poll()+1);//start 업데이트
//                    System.out.println("N: "+n+", start updated: "+start+" , now: "+i);
//                    System.out.println("max len: "+max_length);
                }
            }
        }
        max_length = Math.max(max_length, N-start);
        System.out.println(max_length);




    }
}
