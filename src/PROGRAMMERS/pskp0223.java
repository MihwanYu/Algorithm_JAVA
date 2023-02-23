package PROGRAMMERS;

import java.util.StringTokenizer;

public class pskp0223 {
    static StringTokenizer st;
    public static void main(String[] args) {
        String[] schedule = {"09:05 10", "12:20 5", "13:25 6", "14:24 5"};
        String current_time = "12:05";
        int k = 10;
        int ans1 = solution(schedule, current_time, k);
        System.out.println(ans1);

        String[] schedule2 = {"12:00 10"};
        String current_time2 = "12:00";
        int k2 = 10;
        int ans2 = solution(schedule2, current_time2, k2);
        System.out.println(ans2);

        String[] schedule3 = {"12:00 10"};
        String current_time3 = "12:00";
        int k3 = 11;
        int ans3 = solution(schedule3, current_time3, k3);
        System.out.println(ans3);
    }

    public static int solution(String[] bakery_schedule, String current_time, int k) {
        int answer = -2;
        int current = parseTime(current_time);

        int breadsum = 0;
        for(int i=0; i<bakery_schedule.length; i++){
            st = new StringTokenizer(bakery_schedule[i]);
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            int breadtime = parseTime(s1);
            int breadnum = Integer.parseInt(s2);
            if(current>breadtime) continue;
            //빵 나온 시간이 현재 시간과 같거나 그 이후면 빵 덧셈
            breadsum += breadnum;
            if(breadsum>=k){
                //목표하는 k 이상 빵 구워지면
                answer = breadtime-current;
                break;
            }
        }
        if(breadsum<k){
            answer = -1;
        }


        return answer;
    }

    public static int parseTime(String time){
        st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        return hour*60 + minute;
    }
}
