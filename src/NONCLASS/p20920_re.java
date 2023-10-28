package NONCLASS;

import java.io.*;
import java.util.*;

public class p20920_re {

    static class Word implements Comparable<Word>{
        int freq, len;
        String word;
        Word(int freq, int len, String word){this.freq = freq; this.len = len; this.word = word;}
        @Override
        public int compareTo(Word o2){
            if(this.freq != o2.freq) return Integer.compare(o2.freq, this.freq);
            if(this.len != o2.len) return Integer.compare(o2.len, this.len);
            for(int i=0; i<this.len; i++){
                if(this.word.charAt(i)!=o2.word.charAt(i)){
                    return this.word.charAt(i)-o2.word.charAt(i);
                }
            }
            return 0;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // M 이상의 길이인 단어들 중에서

        // 자주 나오는 단어일수록
        // 단어의 길이가 길수록
        // 사전순으로 앞에 있을수록

        // 앞에서부터 하나씩 출력
        HashMap<String, Integer> freq = new HashMap<>();

        for(int i=0; i<N; i++){
            String word = br.readLine();
            if(word.length()<M) continue;
            freq.put(word, freq.getOrDefault(word,0)+1);
        }

        PriorityQueue<Word> wordset = new PriorityQueue<>();
        for(String key: freq.keySet()){
            wordset.add(new Word(freq.get(key), key.length(), key));
        }

        StringBuilder sb = new StringBuilder();
        while( !wordset.isEmpty() ){
            sb.append(wordset.poll().word).append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());




    }



}
