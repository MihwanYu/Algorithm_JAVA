package Y2024.M01;

import java.io.*;
import java.util.*;
public class p20529 {
    static int[][] score;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // testcase

        for(int t=0; t<T; t++){
            HashMap<String, Integer> mbticnt = new HashMap<>();
            N = Integer.parseInt(br.readLine()); // num of students
            String[] students = new String[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean noneed = N>32? true : false;

            for(int i=0; i<N; i++){
                students[i] = st.nextToken();
                mbticnt.put(students[i], mbticnt.getOrDefault(students[i], 0)+1);
                if(mbticnt.get(students[i])>=3) noneed = true;
            }

            int minLen = noneed? 0 : getMinLen(students);
            System.out.println(minLen);
        }
    }

    static int getMinLen(String[] students){

        score = new int[N][N];
        //NxN 스코어 보드( score[i][j]랑 score[j][i]가 같음
//        System.out.println("===================");
        for(int i=0; i<N; i++){
            String curMBTI = students[i];
            for(int j=i+1; j<N; j++){
                //심리적 거리 구하기
                if(curMBTI.equals(students[j])) continue;
                int len = 0;
                for(int c=0; c<4; c++){
                    if(curMBTI.charAt(c)!=students[j].charAt(c)) len++;
                }
                score[i][j] = score[j][i] = len;
            }
//            System.out.println(Arrays.toString(score[i]));
        }

        // (0,1,2), (0,1,3), ... 3명 뽑아서 점수 계산, min보다 작으면 min값 업데이트
        int minScore = Integer.MAX_VALUE;
//        int[] arr = new int[3];
//        int tempS = 0;
        for(int i=0; i<N-2; i++){
            for(int j=i+1; j<N-1; j++){
                //[i][j] 거리 구해서 더하기
//                tempS += score[i][j];
                for(int k=j+1; k<N; k++){
                    //[j][k]거리, [k][i] 거리 구하기
                    int tempS = (score[i][j] + score[j][k] + score[k][i]);
                    if(minScore>tempS) minScore = tempS;
                }
            }
//            tempS = 0;
        }
        return minScore;
    }

    static int dfs(int[] arr, int cnt, int lastIdx){
        if(cnt==3){
            int len = 0;
            for(int i=0; i<3; i++){
                len += i<2? score[arr[i]][arr[i+1]] : score[arr[i]][arr[0]];
            }
//            System.out.println(Arrays.toString(arr)+", score: "+len);
            return len;
        }

        int minScore = Integer.MAX_VALUE;
        for(int i=lastIdx+1; i<N; i++){
            arr[cnt] = i;
            int tempS = dfs(arr, cnt+1, i);
            if(minScore>tempS) minScore = tempS;
        }
        return minScore;
    }
}
