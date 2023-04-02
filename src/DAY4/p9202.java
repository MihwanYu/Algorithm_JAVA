package DAY4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class TrieNode{
    TrieNode[] children = new TrieNode[26];
    boolean isEnd, isHit;

    boolean hasChild(char c){
        return children[c - 'A'] != null;
    }

    TrieNode getChlid(char c){
        return children[c - 'A'];
    }

    //Hit를 초기화하는 방법(재귀)
    void clearHit(){
        this.isHit = false;
        for (int i = 0; i < children.length; i++) {
            if(children[i]!=null){
                children[i].clearHit();
            }
        }
    }
}
public class p9202 {
    static int W, B;
    static char[][] words;

    static int[] score = {0,0,0,1,1,2,3,5,11};
    static char[][] map;
    static int[] my = {1,-1,0,0,1,1,-1,-1};
    static int[] mx = {0,0,1,-1,1,-1,-1,1};
    static boolean[][] visited;
    static String answer;
    static int sum, count;
    static StringBuilder sb = new StringBuilder();
    static TrieNode root = new TrieNode();
    public static void main(String[] args) throws Exception{
        //이거 퓰어봐야 함 문제 접근법만 알려주심

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine());
//        words = new char[W][];
        StringTokenizer st;
        for(int i=0; i<W; i++){
            insert(br.readLine()); //trie에 word 삽입
        }

        //한칸 띄우는거 인풋 어케받음,, <- 그냥 br.readline() ㅎㅎ..
        br.readLine();
        visited = new boolean[4][4];
        B = Integer.parseInt(br.readLine());
        for(int b=0; b<B; b++){

            map = new char[4][4];
            answer = "";
            count = 0;
            sum = 0;

            for(int r = 0; r<4; r++){
                String row = br.readLine();
                for(int c = 0; c<4; c++){
                    map[r][c] = row.charAt(c);
                }
            }

            for(int r = 0; r<4; r++){
                for(int c = 0; c<4; c++){
                    if(root.hasChild(map[r][c]) && visited[r][c]==false){
                        search(r,c, root.getChlid(map[r][c]));
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(sum);
            sb.append(" ");
            sb.append(answer);
            sb.append(" ");
            sb.append(count);
            System.out.println(sb.toString())   ;
            if(b != B-1) br.readLine();
            root.clearHit();
        }
        root.clearHit();


        //Stringbuilder 예시
        /*
        String a = "abc";
        String b = "efg";
        String c = b+a; //<- 속도가 느림
        //String 연산을 해야할 때 + 대신 stringbuilder를 이용해 연산을 한다(이게더빠르니까
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(b);
        System.out.println(sb.toString());

        sb.deleteCharAt(0);//특정 범위, 특정 인덱스의 값을 지울 수 있음
//        StringBuilder vs StringBuffer
//        StringBuffer: 멀티스레드 sink맞추기 위해서 씀, 알고리즘 풀때는 single thread니까 고려안하는게 나음
        */

    }

    //dfs 용도
    static void search(int y, int x, TrieNode node){
        //1. 체크인
        visited[y][x] = true;
        //stringbuilder: map에 있는 character 넣었다 뺐다 할수있음
        sb.append(map[y][x]);
        //2. 목적지에 도달하였는가?
        if(node.isEnd && node.isHit==false){
            node.isHit = true;//도달 표시
            sum += score[sb.length()];//현재까지 만든 문자열의 길이가 sb에 들어가있음
            count++; //단어 찾았읜까
            String foundAnswer = sb.toString();
//            System.out.println(foundAnswer);
            if(compare(answer, foundAnswer )>0){
                answer = foundAnswer;
            }
        }
        //3. 연결된 곳을 순회
        for(int i=0; i<8; i++){
            int ty = y+my[i];
            int tx = x+mx[i];
            //4. 가능한가? : 맵을 벗어나지 않을 때, Trie에 단어 있고, 방문한 적 없을 때
            if (0 <= ty && ty < 4 && 0 <= tx && tx < 4) {
                if(node.hasChild(map[ty][tx]) && visited[ty][tx]==false){
                    //5. 간다
                    search(ty,tx,node.getChlid(map[ty][tx])); //노드 갱신: 다음으로 가야 할 child
                }
            }
        }
        //6. 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(sb.length() -1);
    }

    static int compare(String o1, String o2){
        int result = o2.length()-o1.length();
        if(result==0){
            //길이가 같을 때 -> 사전순 비교
            return o1.compareTo(o2);//String 오름차순 비교
        }
        return result;
    }

    static void insert(String word){
        TrieNode current = root;
        for(int i = 0; i<word.length(); i++){
            char c = word.charAt(i);
            if(current.hasChild(c)==false){
                current.children[c-'A'] = new TrieNode();
            }
            current = current.getChlid(c);
        }
        current.isEnd = true;
    }


}
