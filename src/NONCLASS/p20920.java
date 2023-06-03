package NONCLASS;
import java.io.*;
import java.util.*;
public class p20920 {
    static HashMap<String, Integer> wordfreq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        //우선순위
        //1. 자주 나오는 단어: 앞
        //2. 단어 길이 길수록: 앞
        //3. 알파벳 사전 순 앞일수록: 앞
        //단어 길이 M이상일 경우만 단어장에 추가

        wordfreq = new HashMap<>();
        for(int i=0; i<N; i++){
            String s = br.readLine();
            if(s.length()>=M){
                wordfreq.put(s, wordfreq.getOrDefault(s, 0)+1);
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                //단어 빈도수 클수록 앞
                if(wordfreq.get(o1)!=wordfreq.get(o2)){
                    return Integer.compare(wordfreq.get(o2), wordfreq.get(o1));
                }//단어 길이 길수록 앞
                if(o1.length() != o2.length()){
                    return Integer.compare(o2.length(), o1.length());
                }
                int i=0;
                for( ; i<o1.length(); i++){
                    if(o1.charAt(i) != o2.charAt(i)) break;
                }
                return Integer.compare(o1.charAt(i), o2.charAt(i));
            }
        });

        for(String key:wordfreq.keySet()){
            pq.add(key);
        }

        StringBuilder sb = new StringBuilder("");
        while(!pq.isEmpty()){
//            String s = pq.poll();
//            System.out.println(s+"("+wordfreq.get(s)+")");
            sb.append(pq.poll()).append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());


    }
}
