package DAY10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class p5430 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testcase = Integer.parseInt(br.readLine());
        for(int t=0; t<testcase; t++){
            String func = br.readLine();
            int N = Integer.parseInt(br.readLine());
//            int[] numbers = new int[N];
            Deque<Integer> numbers = new LinkedList<Integer>();
            st = new StringTokenizer(br.readLine(), "[],"); //stringtokenizer에 구분자 지정
            for(int i=0; i<N; i++){
                numbers.add(Integer.parseInt(st.nextToken()));
            }
            calculate(func, numbers);
        }
    }

    static void calculate(String func, Deque<Integer> numbers){
        boolean ascend = true;
        for(int i=0; i<func.length(); i++){
            if(func.charAt(i)=='R'){
                ascend = !ascend;
            }else{
                if(numbers.isEmpty()){
                    System.out.println("error");
                    return;
                }
                if(ascend){
                    numbers.removeFirst();
                }else{
                    numbers.removeLast();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if(numbers.size()==0){
            System.out.println("[]");
            return;
        }
        sb.append("[");
        if(!ascend){
            Deque<Integer> newnumbers = new LinkedList<>();
            while(!numbers.isEmpty()){
                sb.append(numbers.pollLast());
                sb.append(",");
//                newnumbers.add(numbers.getLast());
//                numbers.removeLast();
            }
            sb.replace(sb.length()-1, sb.length(), "]" );
            System.out.println(sb.toString());
        }
        else{
//            System.out.println(Arrays.toString(numbers.toArray()));
            while(!numbers.isEmpty()){
                sb.append(numbers.poll());
                sb.append(",");
            }
            sb.replace(sb.length()-1, sb.length(), "]");
            System.out.println(sb.toString());
        }




    }
}
