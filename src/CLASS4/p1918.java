package CLASS4;

import java.io.*;
import java.util.*;

public class p1918 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        HashMap<Character, Integer> priority = new HashMap<>(){{
            put('+', 3); put('-', 3); put('*', 2); put('/', 2); put('(',1); put(')', 1); }};

        //stack에는 연산자만, 알파벳은 나오는 대로 바로 string에 덧셈
        Stack<Character> stack = new Stack<>();
        // A  B  C
        // +  *
        StringBuilder sb = new StringBuilder("");
        for(char a: input.toCharArray()){
            if(a>=65){
                sb.append(a);
            }else{
                if(stack.isEmpty()) stack.add(a);
                else if(a=='(') stack.add(a);
                else if(a==')'){
                    //( 만날때까지 다 pop해서 sb에 append
                    while(stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                }else{
                    //  *, / : (만나거나 empty일때까지 모두 pop한뒤 a 추가
                    if(a=='*' || a=='/'){
                        while( !stack.isEmpty() &&(stack.peek()=='*' || stack.peek()=='/')){
                            sb.append(stack.pop());
                        }
                        stack.add(a);
                    }else{
                        //a가 +, - : 같은 +, -만 pop
                        while( !stack.isEmpty() &&  stack.peek() != '('){
                            sb.append(stack.pop());
                        }
                        stack.add(a);
                    }
                }
            }
        }

        //스택 남은 값 제거
        while( !stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());



    }



}
