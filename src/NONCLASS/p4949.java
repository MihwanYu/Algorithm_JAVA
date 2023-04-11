package NONCLASS;
import java.io.*;
import java.util.*;

public class p4949 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            String input = br.readLine();
            if(input.equals(".")) break;
            StringTokenizer st = new StringTokenizer(input);
            boolean isbalanced = check_balance(input);
            if(isbalanced) sb.append("yes ").append("\n");
            else sb.append("no ").append("\n");
        }

        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    static boolean check_balance(String st){
        Stack<Character> stack = new Stack<>();

        boolean status = true;
        for(char c:st.toCharArray()){
            switch(c){
                case '(' :
                case '{' :
                case '[' :
                    stack.add(c); break;
                case ')':
                    if(stack.size()==0){
                        status = false;
                    }else{
                        char top = stack.peek();
                        if(top=='('){
                            stack.pop();
                        }else{
                            status = false;
                        }
                    }

                    break;
                case '}':
                    if(stack.size()==0){
                        status = false;
                    }else{
                        char top = stack.peek();
                        if(top=='{'){
                            stack.pop();
                        }else{
                            status = false;
                        }
                    }

                    break;
                case ']':
                    if(stack.size()==0){
                        status = false;
                    }else{
                        char top = stack.peek();
                        if(top=='['){
                            stack.pop();
                        }else{
                            status = false;
                        }
                    }

                    break;
            }
            if( !status ) break;
        }

        if(stack.size()!=0) status = false;
        //문자열 모두 탐색했는데 stack이 비어있지 않은 경우 -> 균형x

        return status;
    }
}
