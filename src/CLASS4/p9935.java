package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class p9935 {
    static Stack<Character> stack;
    static String bomb;
    static void bombchars(){
        //count부터 bomb.length()만큼의 숫자를 stack에서 꺼내서 모두 동일한지 확인
        Stack<Character> peekstack = new Stack<>();
        int bomblength = bomb.length();

        //for문 돌려서 하나씩 pop해서 확인하고 모두 동일하다면 그대로 종료


        boolean isallsame = true;
        for(int i=0; i<bomblength; i++){
            char c = stack.pop();
            peekstack.add(c);
            if(bomb.charAt(bomblength-1-i)!=c){
                isallsame = false;
                break;
            }
        }

        //하나라도 다르다면 peekstack에서 하나씩 다시 쌓기
        if( !isallsame ){
            while( !peekstack.isEmpty() ){
                stack.add(peekstack.pop());
            }
        }


    }
    public static void main(String[] args) throws Exception {
        //알고리즘 분류: ★스택 인 것만 빨리 눈치챘어도 좋았을것을..굳이 링크드리스트 쓰느라 ..
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        bomb = br.readLine();

        stack = new Stack<>();
        char lastchar = bomb.charAt(bomb.length()-1);
        for(char c: input.toCharArray()){
            stack.add(c);
            //stack에 넣은 c가 bomb의 마지막 문자와 동일할 경우 && stack.size()가 bomb길이 이상일 경우
            if(c==lastchar && stack.size()>=bomb.length()){
                //bombchars: count부터 bomb.length()만큼의 숫자를 stack에서 꺼내서 모두 동일한지 확인
                bombchars();
            }
        }

        //stack이 비어있다면
        if( stack.isEmpty() ){
            System.out.println("FRULA");
        }else{
            StringBuilder sb = new StringBuilder();
            while( !stack.isEmpty() ){
                sb.append(stack.pop());
            }
            sb.reverse();
            System.out.println(sb.toString());
        }





    }
}
