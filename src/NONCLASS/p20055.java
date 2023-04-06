package NONCLASS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class p20055 {
    static int N, K;
//    static int[][] arr;
    static int[] arr2;
    public static void main(String[] args) throws Exception{
        //굳이 2차원 배열 만들 필요 없이 1차원으로 해결하는 것이 구현 측면, 메모리 측며에서 나았을 것 같은 문제
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

//        arr = new int[2][N];
        arr2 = new int[2*N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N*2; i++){//0 1 2 3 4 5
//            if(i/N==0){
//                arr[0][i] = Integer.parseInt(st.nextToken());
//            }else{
//                arr[1][N*2-i-1] = Integer.parseInt(st.nextToken());
//            }
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        //robot의 column값을 저장
        LinkedList<Integer> robots = new LinkedList<>();

        int step = 1;
        int countblank = 0;
        while(true){
            // 1. 벨트 회전, 로봇도 같이 회전하다가 arr[0][N-1]에 있다면 즉시 하차
//            rotatebelt();
            rotatebelt2();
            // 로봇 있다면 -> 한 칸 벨트랑 같이 이동
            for(int i=0; i<robots.size(); i++){
                int col = robots.get(i);
                if(col+1 == N-1) {
                    robots.poll();
                    i--;
                }
                else {
                    robots.set(i, col+1);
                }
            }
//            System.out.println("회전: "+Arrays.toString(robots.toArray()));

            // 2. 로봇 있다면 -> 한 칸 이동, 해당 위치 내구성 -1
            for(int i=0; i<robots.size(); i++){
                int col = robots.get(i);
                //이동하려는 위치(앞)에 robot이 있다면 움직이지 않는다
                if(i>0 && robots.get(i-1)==robots.get(i)+1) continue;

                //이동하려는 위치(앞, robots.get(i) + 1)의 내구성이 0이 아닐 때만 움직인다: 내구성 -1, 로봇 내림
//                if( arr[0][col+1] != 0){
//                    if(col+1 == N-1) {
//                        //poll하면 size()가 감소함
//                        robots.poll();i--;
//                    }
//                    else robots.set(i, col+1);
//                    arr[0][col+1] --;
//                    if(arr[0][col+1]==0) countblank++;
//                }

                if(arr2[col+1] != 0){
                    if(col+1==N-1){
                        robots.poll(); i--;
                    }
                    else robots.set(i, col+1);
                    arr2[col+1] --;
                    if(arr2[col+1]==0) countblank++;
                }
            }

            // 3. arr[0][0]에 로봇 올림, 해당 위치 내구성 -1
//            if(arr[0][0]>0){
//                robots.add(0);
//                arr[0][0] --;
//                if(arr[0][0]==0) countblank++;
//            }
            if(arr2[0] >0 ){
                robots.add(0);
                arr2[0]--;
                if(arr2[0]==0) countblank++;
            }



            // 4. 내구도 0인 칸이 K이상이면 과정 종료, 아니면 다시 반복
            if(countblank >= K) break;
            step++;
        }

        System.out.println(step);
    }

    /*
    static void rotatebelt(){
//        System.out.println("회전 전");
//        System.out.println(Arrays.toString(arr[0]));
//        System.out.println(Arrays.toString(arr[1]));
        int[][] temp = new int[2][N];
        temp[0][0] = arr[1][0];
        temp[1][0] = arr[1][1];
        temp[1][N-1] = arr[0][N-1];
        temp[0][N-1] = arr[0][N-2];
        for(int i=1; i<N-1; i++){
            temp[0][i] = arr[0][i-1];
            temp[1][i] = arr[1][i+1];
        }
//        System.out.println("회전 후");
//        System.out.println(Arrays.toString(temp[0]));
//        System.out.println(Arrays.toString(temp[1]));

        arr = temp;
    }

     */

    static void rotatebelt2(){
        int[] temp = new int[2*N];
        temp[0] = arr2[2*N-1];
        for(int i=1; i<arr2.length; i++){
            temp[i] = arr2[i-1];
        }
        arr2 = temp;
    }
}
