package WEEKLY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class p9997 {
    static int N;
    static int[] alphabets;
    static String[] words;
    public static void main(String[] args) throws Exception {
        //알고리즘 분류: 비트마스킹
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        alphabets = new int[26];
        words = new String[N];

        for(int i=0; i<N; i++){
//            char[] chars = br.readLine().toCharArray();
            String word = br.readLine();
            words[i] = word;
            for(int c=0; c<word.length(); c++){
                alphabets[word.charAt(c)-97] ++;
            }
        }

        for(int i=0; i<alphabets.length; i++){
            if(alphabets[i]==0){
                System.out.println(0);
                return;
            }
        }

        for(int i=0; i<words.length; i++){

        }

    }
}
