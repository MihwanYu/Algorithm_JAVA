package DAY3;

import java.util.Arrays;
import java.util.Scanner;

public class p2042 {
    static int N, M, K;
    static long[] nums = {1,2,3,4,5};
    static long[] tree;
    static int S;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = 5;
        int M = 2;
        int K = 2;

        S = 1;
        while(S < N){
            S *= 2;
        }

        tree = new long[S*2];
        //nums = 1,2,3,4,5
        init();
        //1,3,6: 3번째 값 6으로 변경
        nums[3-1]=6;



    }

    static void init(){
        //leaf는 data로
        for(int i=0; i<N; i++){
            //data 개수까지만 순회하면 됨
            tree[S + i ] = nums[i];
        }
        //내부노드 = 자식의 합
        //leaf부터 순회를 시작함
        for(int i=S-1; i>0; i--){
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    //Query top down 방식
    //parameter에 left / queryLeft 개념 구분 명확히 하김
    static long query(int left, int right, int node, int queryLeft, int queryRight){
        //1. 연관 없음
        if(queryRight < left|| right< queryLeft){
            return 0; //영향 없으니까 0 리턴
        }
        //2. 판단 가능(현재 값이 query에 들어감)
        else if(queryLeft<=left && right<=queryRight){
            return tree[node];
        }
        //3. 판단 불가(걸쳐 있음)
        else{
            //현재 값으로는 판단 불가능하기 때문에 쪼개서 재귀 호출을 한다
            int mid = (left+right)/2;
            //                       재귀에서 mid까지 가기 때문에 범위 이렇게 쪼개고 지정해준거
            long leftResult = query(left, mid, node*2, queryLeft, queryRight);
            long rightResult = query(mid+1, right, node*2+1, queryLeft, queryRight);
            return leftResult+ rightResult;

        }
    }

    //Query update
    static void update(int left, int right, int node, int target, long diff){
        //1. 연관 없음
        if(target<left || target>right){
            //target이 범위를 벗어나는 상황: 아무것도 안한다
            return;
        }
        //2. 연관 있음 - leaf 노드인지 or 내부노드인지
        //어쨌든 diff 연산을 해줘야 함
        else{
            tree[node] += diff;
            if(left!=right){ //내부노드
                int mid = (left+right)/2;
                update(left, mid, node*2, target, diff);
                update(mid+1, right, node*2+1, target, diff);
            }
            //아 leaft노드면 아무것도 안하는 건가 ?...
        }
    }

}
