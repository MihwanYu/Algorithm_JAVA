package DAY1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p1062 {
    static int N,K,selectedCount;
    static String[] words;
    static boolean[] visited;

    static int MAX;
    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("src/DAY1/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        visited = new boolean[26];
        visited['a'-'a'] = true;
        visited['n'-'a'] = true;
        visited['t'-'a'] = true;
        visited['i'-'a'] = true;
        visited['c'-'a'] = true;
        //정규식에서 antic을 다 없애버림

        words = new String[N];
        for(int i=0; i<N; i++){
            words[i] = sc.next().replaceAll("[antic]","");
        }
        selectedCount = 5;
        
        //K<5 ->0
        //K==5 -> count
        //K>5 -> dfs
        //K==26-> 최대
        //백준 99에서 막혔음 . 반례
//        1 5
//        antatica
        if(K<5){
            MAX = 0;
        }else if(K==5){
            MAX = countReadable();
        }else if(K==26){
            MAX = N;
        }else{
            for(int i=0; i<26; i++){
                if(visited[i]==false){
                    dfs(i);
                }
            }
        }

        System.out.println(MAX);
    }

    static void dfs(int index){
        //1. 체크인 -> visited, selectedCount 조절해야 함 -> 6 체크아웃에서 같이 해줘야함
        visited[index] = true;
        selectedCount++;

        //2. 목적지인가 -> selectedCount == K 일 때 목적지 => 도착했을 경우 읽을 수 있는 단어 계산
        if (selectedCount==K){
            //나머지 먼저 다 만들고나서 구현해도 ㅇㅋ
            MAX = Math.max(countReadable(), MAX);
            //여기다가 return은 탐색을 '완전히' 종료할 때만 쓸 것

        }else{
            //3. 연결된 곳을 순회: 현재 index +1 ~z
            for(int i=index+1; i<26; i++){
                //4. 갈 수 있는가? -> visited 확인,
                if(visited[i]==false){
                    //5. 간다 (dfs호출)
                    dfs(i);
                }
            }

        }

        //6. 체크아웃 -> visited, selectedCount
        visited[index]=false;
        selectedCount--;
    }

    static int countReadable(){
        int count = 0;
        for(int i=0; i<N; i++){
            boolean isReadable = true;
            String word = words[i];
            for(int j = 0; j<word.length(); j++){
                if(visited[word.charAt(j)-'a']==false){
                    //하나라도 방문체크 안되어있으면 해당 단어는 읽을 수 없음
                    isReadable = false;
                    break;
                }
            }
            if (isReadable==true){
                count ++;
            }
        }

        return count;
    }


}
