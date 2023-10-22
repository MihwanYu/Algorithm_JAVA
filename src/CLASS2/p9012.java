package CLASS2;

import java.io.*;
import java.util.*;

public class p9012 {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for(int i=0; i<testcase; i++){
            //입력 문자열 S이 VPS인지 아닌지 판단
            String s = br.readLine();

            Stack<Character> stack = new Stack<>();

            boolean valid = true;
            for(char c:s.toCharArray()){
                if(c=='('){
                    stack.push(c);
                }else{
                    if(stack.size()==0){
                        valid = false;
                        break;
                    }
                    stack.pop();
                }
            }

            String vps = stack.size()==0&&valid?"YES":"NO";

            System.out.println(vps);

        }

    }
}
