package DAY3;

public class indexedTree {
    static int N, M, K;
    static long[] nums = {1,2,3,4,5};
    static long[] tree;
    static int S;
    public static void main(String[] args){
        N = 5;

        S = 1;
        while(S < N){
            S *= 2;
            //S가 N보다 커질때까지 2 계속 곱 -> while 끝나면 leaf가 data를 커버할 수 있는 정도의 크기로 커짐
            // data가 5개 들어가있는 이 예시에서는 S <- 8이 됨
        }

        tree = new long[S*2]; //tree는 S의 2배만큼 만들어야 커버 가능
        //아래 메서드들을 활용해서 구간 내 최빈값/최댓값 등등등 짜야 하는 문제가 나옴

    }

    public void init(){
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

    //Query Bottom UP 방식: leaft로 바로 접근해서 싲가한느거
    static long queryBU(int queryLeft, int queryRight){
        long sum = 0;
        int left = S + queryLeft - 1;
        int right = S+ queryRight - 1;

        //두 경계가 뒤바뀌지 않을 때까지 반복
        while(left <= right){
            if(left%2==1){
                sum += tree[left++];
            }
            if(right%2==0){
                //right가 왼쪽 자식에 걸린 때
                sum += tree[right--];
            }
            //위 if로 경계 이동시켜주고 나면
            left /=2;
            right /=2;
        }
        return sum;
    }

    //Bottom UP update
    static void updateBU(int target, long value){
        int node = S + target - 1;
        tree[node] = value;
        node /= 2;
        while(node > 0){
            tree[node] = tree[node * 2] + tree[node*2 + 1];
            node /=2; //한단계 부모로 올려주기
        }
    }
}
