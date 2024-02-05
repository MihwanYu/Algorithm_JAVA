package Y2024.M01;

import java.io.*;
import java.util.*;
public class p1138 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
//        HashMap<Integer, Integer> people = new HashMap<>();
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken()); //사람 i의 왼족에 i보다 키 큰 사람의 수.
//            people.put(arr[i], i+1);
        }

        int[] answer = new int[N];
        for(int i=0; i<N; i++){
            //i번째 사람에 대해, 앞에 arr[i]만큼의 빈 공간을 남겨둔 뒤 j번째 자리에 서야 함
            int cnt = 0;
            for(int j=0; j<N; j++){
                if(answer[j]==0){
                    if(cnt==arr[i]){
                        answer[j] = i+1; //arr[j]가 비어있고 && 앞서 센 빈 공간이 arr[i]와 같다면
//                        System.out.println("answer["+j+"] = "+(i+1));
                        break; //i+1 사람을 arr[j]에 넣었으므로 다음 사람 진행
                    }
                    else cnt++;
                }
            }

        }
//        System.out.println(Arrays.toString(answer));
        for(int i=0; i<N; i++){
            System.out.print(answer[i]+" ");
        }

        // 1   2   3   4   5   6   7
        // 6   1   1   1   2   0   0  ->  키가 1~7인 사람들에 대해 왼쪽에 키 큰 사람이 몇 명 있었는지
        //                줄을 선 순서대로 키를 출력(키: idx)

        // 1 2 3 4
        // 2 1 1 0
        // 4 2 1 3

        // (5) (3) (7) (1) (4) (2) 1 (0) (0) 0
        // 8 4 6 2 5 1 9 3 10 7

        // 1 2 3 4 5 6 7
        // 6 1 1 1 2 0 0
        //   6 2 3 4  7  5   1
        // 6 2 3 4 7 5 1

    }


}
