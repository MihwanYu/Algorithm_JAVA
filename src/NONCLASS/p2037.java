package NONCLASS;
import java.io.*;
import java.util.*;
public class p2037 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<Character, Integer[]> dic = new HashMap<>();
        dic.put(' ', new Integer[] {1, (int)' '});
        for(int i=65; i<97; i++){
            int n, c;
            if(i<'D') {
                n = 2;
                c = 'A';
            }
            else if(i<'G') {
                n = 3; c = 'D';
            }
            else if(i<'J') {
                n = 4; c = 'G';
            }
            else if(i<'M') {
                n = 5; c = 'J';
            }
            else if(i<'P') {
                n = 6; c = 'M';
            }
            else if(i<'T') {
                n = 7; c = 'P';
            }
            else if(i<'W') {
                n = 8; c = 'T';
            }
            else {
                n = 9; c = 'W';
            }
            dic.put((char)i, new Integer[] {n, c});
        }

        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        String input = (br.readLine());

        char prechar = '0';

        int wordtime = 0;

        for(char c:input.toCharArray()){

            int taptime = c - dic.get(c)[1]+1;
            int typetime = taptime * p;
            //같은 번호 있을때 웨이팅
            if(c != ' ' && dic.getOrDefault(prechar,new Integer[] {1,0})[0]==dic.get(c)[0]) {
                typetime += w;
            }
            prechar = c;
            wordtime += typetime;
            System.out.println("char : *"+c+"* , typetime: "+typetime);
        }

        int answer = wordtime ;
        System.out.println(answer);
    }
}
