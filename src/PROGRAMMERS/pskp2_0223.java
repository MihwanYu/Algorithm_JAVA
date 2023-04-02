package PROGRAMMERS;

import java.util.Arrays;

public class pskp2_0223 {
    public static void main(String[] args) {
        int[] p1 = {2, 2, -1, 1, 5, -1, 5};
        int[] b1 = {2, 5};
        int[] ans1 = solution(p1, b1);
        System.out.println(Arrays.toString(ans1));

        int[] p2 = {2, 2, -1, 1, 5, -1, 5};
        int[] b2 = {1, 5};
        int[] ans2 = solution(p2, b2);
        System.out.println(Arrays.toString(ans2));

    }

    public static int[] solution(int[] p, int[] b) {
        int[] answer = new int[b.length];
        int[] underlings = new int[p.length];

        // 0번~1번 다 돌려서 각 i를 부하로 하는 수 업데이트
        for(int i=0; i<p.length; i++){
            int pi = findparent(p, i);
            underlings[pi]++;
        }
        // b에 있는 요소에 대해서,
        // p의 값이 b에 있는 요소일 경우 +1
        for(int i=0; i<b.length; i++){
//            b[i] 번 보스가 거느리는 부하 수는
            answer[i] = underlings[b[i]];
        }


        return answer;
    }

    public static int findparent(int[] p, int el){
        if(p[el]==-1){
            return el; //자기 자신이 root인 경우
        }else{
            return p[el] = findparent(p, p[el]);
        }
    }
}
