package PROGRAMMERS;

public class pskp4_0223 {

    public static void main(String[] args) {
        int ans1 = solution(3,6,1);
        System.out.println(ans1);

        int ans2 = solution(10, 6, 5);
        System.out.println(ans2);

    }

    public static int solution(int n, int m, int k) {
        //조합인줄 알았는데 아무래도 dfs를 이용해서 풀었어야 했던 것 같은......

        int answer = -1;
        // m을 n으로 나눴을 때 몫이(=동일한 폭 설정시) k보다 작거나 같아야 함. 아니면 0 리턴
        if(n>m || m/n>k){
            return 0;
        }

        for(int i=1; i<m-n; i++){
            answer += dfs(i, m, k, 1); //첫번째 차선이 i번까지일 때 두번째 차선의 경우에 따른 값 구하기
        }

        //숫자 너무 커질 수 있으므로 1 000 007로 나눈 나머지 값 리턴
        return answer;
    }

    public static int dfs(int n, int m, int k, int now){
        if(n+k==m){
            return 0;
        }
        int ans = 0;

        return 0;
    }
}
