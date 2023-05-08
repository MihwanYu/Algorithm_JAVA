package CLASS3;
import java.io.*;
import java.util.*;
public class p11723 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int operations = Integer.parseInt(br.readLine());
        int[] S = new int[21];// S[x]==0 -> 집합에 x 없음 / 1이면 집합에 x 있음

        for(int i=0; i<operations; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x;
            switch(command){
                case "all":
                    //전체 1 적용
                    for(int j=1; j<21; j++){
                        S[j] = 1;
                    }
                    break;
                case "empty":
                    //전체 0 적용
                    for(int j=1; j<21; j++){
                        S[j] = 0;
                    }
                    break;
                case "add":
                    x = Integer.parseInt(st.nextToken());
                    S[x] = 1;
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    S[x] = 0;
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    if(S[x]==1){
                        sb.append(1).append("\n");
                    }else{
                        sb.append(0).append("\n");
                    }
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    if(S[x]==1){
                        S[x]=0;
                    }else{
                        S[x]=1;
                    }
                    break;
            }

        }
        if(sb.length()>1){
            sb.deleteCharAt(sb.length()-1);
        }

        System.out.println(sb.toString());


    }
}
