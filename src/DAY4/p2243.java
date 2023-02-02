package DAY4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2243 {
    static int N;
    static long[] nums = {1,2,3,4,5};
    static int[] tree;
    static int S;
    static long MAX = 2000000000;
    public static void main(String[] args) throws Exception{
        //이거 퓰어봐야 함 문제 접근법만 알려주심

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = 1;
        while(S<MAX){
            S*=2;
        }
        tree = new int[2*S];
        N = Integer.parseInt(br.readLine());


        int answer = query(1, S, 1, 2) ;
        update(1, S, 1, answer, -1); //재귀호출 시 answer 얻어왔으니 그만큼 -1 해주면 됨(이해못함 ..)
        StringTokenizer st;

    }

    static int query(int left, int right, int node, int target){
        //leaf: 사탕 찾음
        if(left==right){
            return left;
        }//내부노드인 경우
        else{
            int mid = (left+right)/2;
            if(tree[node*2]>=target){
                //왼쪽으로 갈경우
                return query(left, mid, node*2, target);
            }else{
                //오른쪽으로 갈 경우는 타겟 변경해주기
                return query(mid+1, right, node*2+1, target - tree[node*2]);
            }
        }
    }

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
