package CLASS2;

import java.io.*;
import java.util.*;
public class p1259 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String s = br.readLine();
            if(s.equals("0")) break;

            int len = s.length();

            boolean palindrome = true;
            for(int i=0; i<len/2; i++){
                if(s.charAt(i)==s.charAt(len-1-i)) continue;
                else{
                    palindrome = false;
                    break;
                }
            }
            String answer = palindrome ? "yes" : "no";
            System.out.println(answer);

        }




    }
}
