package Y2024.M01;


import java.io.*;
import java.util.*;
public class p3425 {
    static int max_val;
    static boolean end;
    static int n;
    static boolean error_flag = false;
    static ArrayList<String>order_list= new ArrayList<>();
    static ArrayList<Integer>order_num = new ArrayList<>();
    static Deque<Integer>deque = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        //개짜증나는문제 .. 더러워서 안본다 다신보지말자
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        max_val = (int)Math.pow(10, 9);
        while(true) {
            order_list.clear();
            order_num.clear();
            while(true) {
                String[] t = br.readLine().split(" ");
                if(t[0].equals("QUIT")) {
                    System.out.println(sb);
                    return;
                }   //EXIT명령이면 종료
                if(t[0].equals("END")) {
                    break;
                }
                if(t.length==1) {
                    order_list.add(t[0]);
                }
                else {
                    order_list.add(t[0]);
                    order_num.add(Integer.parseInt(t[1]));
                }
            }
            n = Integer.parseInt(br.readLine());
            for(int i=0; i<n; i++) {
                error_flag = false;
                deque.clear();
                int tmp = Integer.parseInt(br.readLine());
                deque.addFirst(tmp);

                solve();
                if(error_flag || deque.size()!=1) {
                    sb.append("ERROR\n");
                }
                else {
                    sb.append(deque.removeFirst()+"\n");
                }
            }
            sb.append("\n");
            String[] str = br.readLine().split("");
        }
    }
    public static void solve() {
        int order_num_cnt = 0;
        for(int i=0; i<order_list.size(); i++) {
            String order = order_list.get(i);
            if(order.equals("NUM")) {
                deque.addLast(order_num.get(order_num_cnt));
                order_num_cnt++;
            }
            if(order.equals("POP")) {
                if(deque.size()==0) {
                    error_flag=true;
                    break;
                }
                deque.removeLast();
            }
            if(order.equals("INV")) {
                if(deque.size()==0) {
                    error_flag=true;
                    break;
                }
                int tmp = deque.removeLast();
                tmp*=-1;
                deque.addLast(tmp);
            }
            if(order.equals("DUP")) {
                if(deque.size()==0) {
                    error_flag=true;
                    break;
                }
                int tmp = deque.getLast();
                deque.addLast(tmp);
            }
            if(order.equals("SWP")) {
                if(deque.size()<2) {
                    error_flag=true;
                    break;
                }
                int first = deque.removeLast();
                int second = deque.removeLast();

                deque.addLast(first);
                deque.addLast(second);
            }
            if(order.equals("ADD")) {
                if(deque.size()<2) {
                    error_flag=true;
                    break;
                }
                long first = deque.removeLast();
                long second = deque.removeLast();
                long sum = first+second;
                if(sum>max_val || sum<max_val*-1) {
                    error_flag=true;
                    break;
                }
                deque.addLast((int)sum);
            }
            if(order.equals("SUB")) {
                if(deque.size()<2) {
                    error_flag=true;
                    break;
                }
                long first = deque.removeLast();
                long second = deque.removeLast();
                long sub = second-first;
                if(sub>max_val ||sub<max_val*-1) {
                    error_flag= true;
                    break;
                }
                deque.addLast((int)sub);
            }
            if(order.equals("MUL")) {
                if(deque.size()<2) {
                    error_flag=true;
                    break;
                }
                long first = deque.removeLast();
                long second = deque.removeLast();
                long mul = second*first;
                if(mul>max_val || mul<max_val*-1) { //mul > max_cal도 고려
                    error_flag=true;
                    break;
                }
                deque.addLast((int)mul);
            }
            if(order.equals("DIV")) {
                if(deque.size()<2) {
                    error_flag=true;
                    break;
                }
                int minus_cnt=0;    // 음수 가리는 여부
                long first = deque.removeLast();
                long second = deque.removeLast();
                if(first<0) {
                    minus_cnt++;
                }
                if(second<0) {
                    minus_cnt++;
                }
                if(first==0) {
                    error_flag=true;
                    break;
                }
                long div = Math.abs(second)/Math.abs(first);

                if(minus_cnt==1) {
                    deque.addLast((int)div*-1);
                }
                else {
                    deque.addLast((int)div);
                }
            }
            if(order.equals("MOD")) {
                if(deque.size()<2) {
                    error_flag=true;
                    break;
                }
                long first = deque.removeLast();
                long second = deque.removeLast();
                if(first==0) {
                    error_flag=true;
                    break;
                }
                long mod = Math.abs(second) %Math.abs(first);
                if(second<0) {
                    mod*=-1;
                }
                deque.addLast((int)mod);
            }
        }
    }
}