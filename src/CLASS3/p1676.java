package CLASS3;

import java.io.*;
import java.util.*;


public class p1676 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //N = 0 -> 0! = 1
        // 10 ! => 10,9,8,7,6,5,4,3,2,1
        // 27! => 25, 20, 15, 10, 5==> 6, 2
        //30:
        //125, 25?
        int count = 0;
        for(int n = N; n>0; n--){
            if(n%5==0) {
                int temp = n;
                while(temp%5==0){
                    count++;
                    temp = temp/5;
                }
            }
        }
        /*
        똥멍청이.....
        for(int n = N; n>0; n--){
            if(n%5==0) {
                if(n/5>=5){//25, 50...
                    if(n/25>=5){//125, 250...
                        count += 3;
                    }else{
                        count += 2;
                    }
                }else{
                    count += 1;
                }
            }

        }

         */
        System.out.println(count);



    }
}
