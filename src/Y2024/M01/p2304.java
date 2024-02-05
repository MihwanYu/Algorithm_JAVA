package Y2024.M01;


import java.io.*;
import java.util.*;
public class p2304 {

    static class Pillar implements Comparable<Pillar>{
        int x, height;
        Pillar(int x, int height){
            this.x = x; this.height = height;
        }

        @Override
        public int compareTo(Pillar o2){
            return Integer.compare(this.x, o2.x);
        }

        @Override
        public String toString(){
            return "loc("+this.x+"), "+this.height;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Pillar[] pillars = new Pillar[N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pillars[i] = new Pillar(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        }

        Arrays.sort(pillars);
        Stack<Integer> increase = new Stack<>();
        Stack<Integer> decrease = new Stack<>();
        int ascH = pillars[0].height;
        int descH = pillars[N-1].height;

        increase.add(pillars[0].x);
        decrease.add(pillars[N-1].x);

        int left = 0, right = 0;
        for(int i=1; i<N; i++){
            //pillars[i].height>ascH일때 stack에 추가
            if(ascH<pillars[i].height){
                left += ascH*(pillars[i].x-increase.peek());
                increase.add(pillars[i].x);
                ascH = pillars[i].height;
            }
            if(descH<pillars[N-1-i].height){
                right += descH*(decrease.peek()-pillars[N-1-i].x);
                decrease.add(pillars[N-1-i].x);
                descH = pillars[N-1-i].height;
            }
        }

        int answer = left+right+ascH;
        if(increase.peek()!=decrease.peek()){
            answer += ascH*(decrease.peek()-increase.peek());
        }

        System.out.println(answer);


    }
}
