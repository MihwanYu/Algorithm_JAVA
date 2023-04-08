package CODETREE;

import java.io.*;
import java.util.*;
public class p2022_2_12 {
    //삼성 하반기 오전 2번 - 시간 초과
    static Deque<Integer>[] belts;
    static int[] presentInfo;
//    static class Present{
//        int num, belt;//선물 번호, 속한 벨트 번호
//        Present(int num, int belt){this.num = num; this.belt = belt;}
//    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<q; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int answer = -1;
            if(command==100){
                //1번 명령 수행
                comm1(st);
            }else if(command == 200){
                //2번
                answer = comm2(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                sb.append(answer).append("\n");
            }else if(command==300){
                answer = comm3(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                sb.append(answer).append("\n");
            }else if(command==400){
                answer = comm4(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                sb.append(answer).append("\n");

            }else if(command==500){
                answer = comm5(Integer.parseInt(st.nextToken()));
                sb.append(answer).append("\n");
            }else{
                answer = comm6(Integer.parseInt(st.nextToken()));
                sb.append(answer).append("\n");
            }
//            System.out.println("comm("+command+") "+Arrays.toString(Arrays.stream(belts).toArray()));
//            System.out.println("        present info: "+Arrays.toString(presentInfo));
//            System.out.println(answer);
        }

        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());

    }

    static void comm1(StringTokenizer st){
        int beltLen = Integer.parseInt(st.nextToken());
        belts = new Deque[beltLen+1];
        for(int i=0; i<=beltLen; i++){
            belts[i] = new ArrayDeque<>();
            //이거 쓰면 결국 ArrayList에서 할 수 있는 get()등을 못씀 -> 그냥 ArrayList<>()써야했다
        }
        //벨트번호, 선물번호 모두 1부터 시작
        int present_num = Integer.parseInt(st.nextToken());
        presentInfo = new int[present_num+1];
        presentInfo[0] = -1;
        for(int i=1; i<=present_num; i++){
            int belt_num = Integer.parseInt(st.nextToken());
            belts[belt_num].add(i);
            presentInfo[i] = belt_num;
        }


    }

    static int comm2(int src, int dst){
        //src물건 -> dst로 모두 옮기기, presentInfo (선물이 위치한 벨트 정보)값 갱신
        while( !belts[src].isEmpty() ){
            int p_num = belts[src].pollLast();
            presentInfo[p_num] = dst;
            belts[dst].addFirst(p_num);
        }


        //dst 개수 리턴
        return belts[dst].size();
    }

    static int comm3(int src, int dst){
        //src물건 <-> dst 앞 교체

        int p_src = -1, p_dst = -1;
        if( !belts[src].isEmpty()){
            p_src = belts[src].pollFirst();
        }

        if( !belts[dst].isEmpty() ){
            p_dst = belts[dst].pollFirst();
        }

        if( p_dst != -1 ){
            belts[src].addFirst(p_dst);
            presentInfo[p_dst] = src;
        }

        if( p_src != -1){
            belts[dst].addFirst(p_src);
            presentInfo[p_src] = dst;
        }

        //dst 개수 리턴
        return belts[dst].size();
    }

    static int comm4(int src, int dst){
        //src물건 n/2개만큼 -> dst로 모두 옮기기
        int count = belts[src].size()/2;

        //src에서 기존 순서를 유지한 상태로 dst에 옮기는지 ? 2번 방식과 동일하게?
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<count; i++){
            stack.add(belts[src].pollFirst());
        }
        while( !stack.isEmpty() ){
            int p_num = stack.pop();
            belts[dst].addFirst(p_num);
            presentInfo[p_num] = dst;
        }

        //dst 개수 리턴
        return belts[dst].size();
    }

    static int comm5(int p_num){
        //선물 번호 앞,뒤 정보
        int b_num = presentInfo[p_num];
        int p_idx = 0;

//        Integer[] bArray = (Integer[]) belts[b_num].toArray();
//        Integer[] bArray = belts[b_num].stream().toArray();
        //deque인 barray를 어떻게 arr로 만들것인가
        Object[] bArray = belts[b_num].toArray();

        for(int i=0; i<belts[b_num].size(); i++){
            if((Integer)bArray[i]==p_num){
                p_idx = i;
            }
        }

        int a=0, b=0;
        a = p_idx==0? -1 : (Integer) bArray[p_idx-1];
        b = p_idx==bArray.length-1 ? -1 :(Integer) bArray[p_idx+1];


        return a + 2*b;
    }

    static int comm6(int b_num){
        //벨트 정보
        Object[] bArray = belts[b_num].toArray();

        int a, b, c;
        a = bArray.length>0 ? (Integer) bArray[0] : -1;
        b = bArray.length>0 ? (Integer) bArray[bArray.length-1] : -1;
        c = bArray.length;


        return a + 2*b + 3*c;
    }

}
