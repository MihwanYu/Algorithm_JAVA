package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class p16928 {
    static int N, K;
    static int[] gamemap;
    static boolean[] visited;

    static int[][] jumpto;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        gamemap = new int[101];
        visited = new boolean[101];
        jumpto = new int[N+K][2];
        
        gamemap[1] = 0;
        visited[1] = true;
        for(int i=0; i<N+K; i++){
            st = new StringTokenizer(br.readLine());
            jumpto[i][0] = Integer.parseInt(st.nextToken());
            jumpto[i][1] = Integer.parseInt(st.nextToken());

        }

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);

        while(true){
            int current = queue.poll();
            boolean findDst = false;
            for(int i=1; i<=6; i++){
                boolean isjump = false;
                //최솟값 할당: 방문한 적 없을 때만 값 업데이트, 방문한 적 있으면 continue
                if(visited[current+i]) continue;
                visited[current+i] = true;

//                https://hagisilecoding.tistory.com/85

                //만약 current+i를 시작점으로 하는 사다리나 뱀이 있다면 무조건 거기로 점프
                int s;
                for(s=0; s<N+K; s++){
                    if(jumpto[s][0]==current+i ){//뱀, 사다리 있으면 목적지 방문함

                        //목적지에 값이 없을 때만 update -> 아래 코드로 업데이트 ,,,
//                        if( gamemap[jumpto[s][1]] ==0){
//                            gamemap[jumpto[s][1]] = gamemap[current+i];
//                        }

                        //jump[s][1], 즉 목적지를 방문한 적 없다면 -> queue에 추가, 방문 처리, 카운트
                        if(!visited[jumpto[s][1]]){
                            queue.add(jumpto[s][1]);
                            visited[jumpto[s][1]] = true;
                            gamemap[jumpto[s][1]] = gamemap[current]+1;
                        }

                        isjump = true;
                        break;
                    }
                }

                //jump 없으면 current+i를 queue에 추가
                if(isjump){
//                    queue.add(jumpto[s][1]);
//                    System.out.println(jumpto[s][1]);
                }else if(!isjump){
                    queue.add(current+i);
                    gamemap[current+i] = gamemap[current]+1;
//                    System.out.println(current+i);
                }
                if(current+i==100) {
                    findDst = true;
                    break;
                }
            }
            if(findDst) break;

        }
//        System.out.println(Arrays.toString(gamemap));
        System.out.println(gamemap[100]);

    }
}
