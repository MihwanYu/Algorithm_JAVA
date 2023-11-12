package CLASS2;

import java.io.*;
import java.util.*;
public class p10866 {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<>();
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int number = st.hasMoreTokens()? Integer.parseInt(st.nextToken()) : -1;

            StringBuilder sb = new StringBuilder();
            switch(command){
                case "push_back":
                    deque.add(number);
                    break;
                case "push_front":
                    deque.addFirst(number);
                    break;
                case "pop_front":
                    if(deque.size()==0){
//                        sb.append(-1).append("\n");
                        System.out.println(-1);
                    }else{
//                        sb.append(deque.pollFirst()).append("\n") ;
                        System.out.println(deque.pollFirst());
                    }
                    break;
                case "pop_back":
                    if(deque.size()==0){
//                        sb.append(-1).append("\n");
                        System.out.println(-1);
                    }else{
//                        sb.append(deque.pollLast()).append("\n");
                        System.out.println(deque.pollLast());
                    }
                    break;
                case "size":
//                    sb.append(deque.size()).append("\n");
                    System.out.println(deque.size());
                    break;
                case "empty":
                    int n = deque.isEmpty() ? 1 : 0;
//                    sb.append(n).append("\n");
                    System.out.println(n);
                    break;
                case "front":
                    if(deque.size()==0){
//                        sb.append(-1).append("\n");
                        System.out.println(-1);
                    }else{
//                        sb.append(deque.peek()).append("\n");
                        System.out.println(deque.peek());
                    }
                    break;
                case "back":
                    if(deque.size()==0){
//                        sb.append(-1).append("\n");
                        System.out.println(-1);
                    }else{
//                        sb.append(deque.peekLast()).append("\n");
                        System.out.println(deque.peekLast());
                    }
                    break;
                default:
                    break;
            }

//            if(sb.length()!=0){
//                sb.deleteCharAt(sb.length()-1);
//            }
//
//            System.out.println(sb.toString());



        }

    }
}
