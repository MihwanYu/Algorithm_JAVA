package NONCLASS;

import java.io.*;
import java.util.*;
public class p20437 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for(int t=0; t<testcase; t++){
            String S = br.readLine();
            int K = Integer.parseInt(br.readLine());

            //S에 대해서, 어떤 문자를 정확히 K개 포함하는 가장 짧은 문자열 길이 구하기->첫,마지막 글자 동일함
            //S에 대해서, 어떤 문자를 정확히 K개 포함하면서 첫, 마지막 글자가 해당 문자로 같은 가장 긴 문자열 길이 구하기

            //previdx: 앞에서 동일한 알파벳이 나왔다면 그 idx 값 저장
            int[] previdx = new int[S.length()];
            //alphabets: 직전에 해당 알파벳 들어왔을 경우 그 idx값
            //counts: 알파벳 등장할 때마다 몇번째로 나왔는지 카운트
            int[] alphabets = new int[26];
            int[] counts = new int[26];
            Arrays.fill(previdx, -1);
            Arrays.fill(alphabets, -1);

            int maxlen = -1;
            int minlen = S.length()+1;

            //String 순회, s부터 등장한 idx 값 저장
            for(int i=0; i<S.length(); i++){
                char a = S.charAt(i);
                if(alphabets[a-97]!=-1){
                    previdx[i] = alphabets[a-97];
                }
                alphabets[a-97] = i;//인덱스 값으로 저장
                counts[a-97] ++;

                if(counts[a-97]>=K){
                    //char a가 K번 이상 등장했을 때: 끝에서부터 K번째 idx까지 탐색
                    int startidx = i;
                    for(int c=0; c<K-1; c++){
                        startidx = previdx[startidx];
                    }
//                    System.out.println("("+S.substring(startidx,i+1)+") start: "+startidx+" , end: "+i);
                    int slen = i-startidx+1;
                    //K번째 idx부터 i까지 길이와 maxlen, minlen 비교
                    if(slen<minlen){
                        minlen = slen;
                    }
                    if(slen>maxlen){
                        maxlen = slen;
                    }

                }
            }

            if(minlen==S.length()+1 || maxlen==-1){
                System.out.println(-1);
            }else{
                StringBuilder sb = new StringBuilder("");
                sb.append(minlen).append(" ").append(maxlen);
                System.out.println(sb.toString());
            }

        }


    }

}
