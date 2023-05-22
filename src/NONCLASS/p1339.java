package NONCLASS;

import java.io.*;
import java.util.*;
public class p1339 {
    static class Pair{
        char c;
        int count;
        Pair(char c, int count){
            this.c = c; this.count = count;
        }
    }
    public static void main(String[] args) throws Exception{
        //alphabets에 어떤 알파벳에 어떤 숫자 할당했는지 저장했는데 문제 풀이 자체에는 없어도 됐다
        //어떤 알파벳인지 고려x인 채로 빈도수 내림차순 & 숫자 할당했을 때 시간 16ms 더 빨랐음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        HashMap<Character, Integer> digitpairs = new HashMap<>();

        for(int i=0; i<N; i++){
            String s = br.readLine();
            words[i] = s;
            int maxIdx = s.length();
            for(int c=0; c<maxIdx; c++){
                int pow = (int) Math.pow(10, maxIdx-c);
                digitpairs.put(s.charAt(c), digitpairs.getOrDefault(s.charAt(c),0)+pow);
            }
        }

        //alphabets: a~z, 단어에 포함되는 10개 알파벳은 value 넣기
        //자릿수 -- 소거해나가면서 바꿔치기
        int[] alphabets = new int[26];
        Arrays.fill(alphabets, -1);

        int curnum = 9;

        // pq: digitpairs[i]에 있는 알파벳들 value 내림차순 하기
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o2.count, o1.count));

        for(Character key: digitpairs.keySet()){
            pq.add(new Pair(key, digitpairs.get(key)));
//            System.out.print(" "+key+"("+digitpairs.get(key)+")");
        }
//        System.out.println();

        while(! pq.isEmpty() ){
            Pair cur = pq.poll();
            if( alphabets[cur.c-'A'] ==-1 ){
                //아직 알파벳에 숫자 부여가 안 됐으면
                alphabets[cur.c-'A'] = curnum;
                curnum--;
            }
        }
        // alphabets의 해당 알파벳에 각각 숫자 9 -> 0 하나씩 부여해주면서 내려오기

        int countall = 0;
        for(String word: words){
            StringBuilder sb = new StringBuilder("");
            //입력받은 word각각에 대해 sb에 숫자로 변환한 형태 생성
            for(char c: word.toCharArray()){
                sb.append(alphabets[c-'A']);
            }
            //"숫자" 형태 문자열 -> 정수 변환 후 단어 합 덧셈
            countall += Integer.parseInt(sb.toString());
        }
        System.out.println(countall);
        //GCF, ACDEB
        // ACDEB: 98754
        // GCF:     683
        //        99437

    }
}
