package PROGRAMMERS;

import java.util.StringTokenizer;

public class p155651 {
    public static void main(String[] args) {
        String[][] input1 = {{"15:00", "17:00" },{"16:40", "18:20"},{"14:20", "15:20"},{"14:10", "19:20"}, {"18:20", "21:20"}};
        int answer1 = solution(input1);
        System.out.println(answer1);

        String[][] input2 = {{"09:10", "10:10"}, {"10:20", "12:20"}};
        int answer2 = solution(input2);
        System.out.println(answer2);

        String[][] input3 = {{"10:20", "12:30"},{"10:20", "12:30"},{"10:20", "12:30"}};
        int answer3 = solution(input3);
        System.out.println(answer3);

        String[][] input4 = {{"00:00", "09:00"}, {"09:05", "12:00"}, {"18:00", "23:59"}};
        int answer4 = solution(input4);
        System.out.println(answer4);
    }

    public static int solution(String[][] book_time) {
        StringTokenizer st;
        int answer = 0;
        int[] dp = new int[60*24];
        int cabin = 0;
        int startmin = 60*23;
        int endmax = 0;
        for(int i=0; i<book_time.length; i++){
            st = new StringTokenizer(book_time[i][0], ":");
            int start = Integer.parseInt(st.nextToken()) *60; //HH -> MM
            start += Integer.parseInt(st.nextToken());
            st = new StringTokenizer(book_time[i][1], ":");
            int end = Integer.parseInt(st.nextToken()) *60;
            end += Integer.parseInt(st.nextToken());
//            if(startmin>start){
//                startmin = start;
//            }
//            if(endmax<end){
//                endmax = end;
//            }
//            System.out.println("start: "+start+", end: "+end);
            for(int t=start; t<end+10 && t<dp.length; t++){
                dp[t] ++;
                if(dp[t]>cabin){
                    cabin = dp[t];
                }
            }
        }

//        for(int t=startmin; t<endmax;){
//            System.out.print(dp[t]+" ");
//            t +=10;
//        }
//        System.out.println();
        return cabin;
    }
}
