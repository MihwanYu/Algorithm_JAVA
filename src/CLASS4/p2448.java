package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class p2448 {
    static String[] arr;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new String[N+1];
        arr[1] = "*";
        arr[2] = "* *";
        arr[3] = "*****";

        if(N==3){
            printstars();
            return;
        }

        fillstars(N);
        printstars();
    }

    static void printstars(){
        sb = new StringBuilder();
        for(int i=1; i<arr.length; i++){

            for(int k=1; k<arr.length-i; k++){
                sb.append(" ");
            }
            sb.append(arr[i]);
            for(int k=1; k<=arr.length-i; k++){
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    static void fillstars(int n){
        int halfnum = n/2;
        if(halfnum != 3){
            fillstars(halfnum);
        }
        for(int i = 1; i<=halfnum; i++){
            int ni = halfnum+i;
            sb = new StringBuilder(arr[i]);
            int empty = arr[halfnum+1-i].length();
            for(int k=0; k<empty; k++){
                sb.append(" ");
            }
            sb.append(arr[i]);
            arr[halfnum+i] = sb.toString();
//            System.out.println("fill line "+(halfnum+i));
        }


    }
}
