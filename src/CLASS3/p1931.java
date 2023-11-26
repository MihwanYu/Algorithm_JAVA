package CLASS3;

import java.io.*;
import java.util.*;

public class p1931 {
    static class Meet implements Comparable<Meet>{
        int start, end;
        Meet(int st, int end){this.start = st; this.end = end;}
        @Override
        public int compareTo(Meet o2){
            if(this.end != o2.end){
                return Integer.compare(this.end, o2.end);
            }else{
                return Integer.compare(this.start, o2.start);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Meet[] meetings = new Meet[N];
        //N개의 회의에 대해 회의실을 사용할 수 있는 회의의 최대 개수

        //접근법 문제: 소요시간 짧은순으로 하려고 함

        //끝나는 시간이 빠른 것 중 소요 시간이 짧은 것들 먼저.
        int timemax = 0;

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i] = new Meet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            if(meetings[i].end>timemax) timemax = meetings[i].end;
        }

        Arrays.sort(meetings);
        boolean[] availables = new boolean[timemax+1];
        Arrays.fill(availables, true);

        int count = 0;
        int recent_start = 0;
        for(int i=0; i<N; i++){

            //meeting i의 start부터 end가 available한지 확인 : recent_start보다 meeint[i].start가 크거나 같아야 한다.
            if(meetings[i].start>=recent_start){
                count++;
                recent_start = meetings[i].end;
//                System.out.println(meetings[i].start+"~"+meetings[i].end+" 추가");
            }


//            boolean isavailable = true;
//            for(int t=meetings[i].start; t<meetings[i].end; t++){
//                if(!availables[t]) {
//                    isavailable = false;
//                    break;
//                }
//            }
//            if(!isavailable) continue;

            //들어갈 수 있는 meeting에 대해서 [start]~[end-1]까지 available <-false
            //회의 수 count++
//            count++;
//            recent_start = meetings[i].start;
//            System.out.println(Arrays.toString(availables));
//            System.out.println(meetings[i].start+"~"+meetings[i].end+" 추가");
//            for(int t=meetings[i].start; t<meetings[i].end; t++){
//                availables[t] = false;
//            }

        }

        System.out.println(count);



    }


}
