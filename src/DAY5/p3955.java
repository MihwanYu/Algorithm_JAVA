package DAY5;

public class p3955 {
    static int N, A, B;
    public static void main(String[] args) {
        //X: 인당 나눠줄 사탕의 수
        //Y: 사탕 봉지의 수
        //A * X + 1 = B * Y
        //Ax + By = C의 형태로 변환
        //-Ax + By = 1
        //A(-x) + By = 1의 형태로 변환 -> 추후 k를 구할 때 x의 범위가 반전된다
        A = 10;
        B = 7;
        EGResult result = agcd(A, B);
        //Ax + By = C일때 C % gcd(Ak, B)==0이어야 해를 가질 수 있다: 배주 항동식
        //이 문제는 gcd가 1이어야 하는 문제임. 따라서 if로 확인해보기
        if(result.r != 1){
            System.out.println("IMPOSSIBLE");
        }else{
            //As + Bt = r , Ax + By = C 두 식에서 C와 r을 일치시켜서 x0, y0를 구함 -> 초기해
            //x0 = s * C/r
            //y0 = t * C/r
            long x0 = result.s;
            long y0 = result.t;

            //일반해 공식
            //x = x0 + B/gcd * k
            //y = y0 - A/gcd * k

            //while문 할 수 있긴 한데 굳이 하지 않고 풀기 가능: 조건1,2 를 만족하는 k구하기

            // 주어진 범위는 x가 양수지만 구해놓은 x는 범위 반전돼서 x<0임
            //x0 + B * k<0 =====> k< -x0 / B <-------조건1

            // 0<y<=1e9
            // 0 < y0 - A * k <= 1e9 =====> (y0 - 1e9) / A <= k < y0 / A <----------조건2

            long kFromY = (long)(Math.ceil((double)y0 / (double)A) - 1);
            long kFromX = (long)(Math.ceil((double) - x0 / (double)B ) - 1);
            long k = Math.min(kFromX, kFromY);
            long kLimitFromY = (long) Math.ceil( (double) (y0 - 1e9) / (double) A);
            if(kLimitFromY <= k){
                System.out.println(y0 - A * k); //가장 좌측 경계의 k를 꺼내기
            }else{
                System.out.println("IMPOSSIBLE");

            }

        }
    }

    static EGResult agcd(long a, long b){
        long s0=1, t0=0, r0=a;
        long s1=0, t1 = 1, r1=b;
        long temp;
        while(r1 !=0){
            long q = r0 / r1;
            temp = r0 - q * r1; //r0 % r1
            r0 = r1;
            r1 = temp;

            temp = s0 - q*s1;
            r0 = s1;
            s1 = temp;

            temp = t0 - q*t1;
            t0 = t1;
            t1 = temp;
        }
        return new EGResult(s0,t0,r0);
    }
}

class EGResult{
    long s,t,r;
    public EGResult(long s, long t, long r){
        this.s = s;
        this.t = t;
        this.r = r;
    }

    @Override
    public String toString(){
        return "EGResult{"+"s="+s+", t="+t+", r="+r+"}";
    }
}