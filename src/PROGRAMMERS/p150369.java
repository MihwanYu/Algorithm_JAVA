package PROGRAMMERS;

import java.util.Arrays;

class p150369 {
    //그리디?
    public static void main(String[] args) {
        long ans1 = solution(4, 5, new int[] {1,0,3,1,2}, new int[] {0,3,0,4,0});
        System.out.println(ans1);
        long ans2 = solution(2, 7, new int[] {1, 0, 2, 0, 1, 0, 2}, new int[] {0, 2, 0, 1, 0, 2, 0});
        System.out.println(ans2);

        long ans3 = solution(4, 5, new int[] {1,0,3,0,0}, new int[] {0,3,0,4,0});
        System.out.println(ans3);
    }
    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        //[1, 0, 3, 1, 2], [0, 3, 0, 4, 0]
        int p1=-1, p2=-1;
        long answer = 0;

        for(int i=n-1; i>0; i--){
            if(p1==-1 && deliveries[i]>0) p1 = i;
            if(p2==-1 && pickups[i]>0) p2 = i;
            if(p1 >-1 && p2 >-1) break;
        }

        int count=0;
        while(true){
            count++;
            //배달 후 거리 구해오기
            int endpoint = p1>p2? p1 : p2;//한 번 움직일 때 가장 멀리 이동하는 거리

            int deliver_cap = cap, pd;
            //용량만큼 deliver
            // 1,0,2,0,0   -> pd = 2, deliver_cap = -2,
            for(pd=p1; pd>=0; pd--){
                deliver_cap -= deliveries[pd];
                if(deliver_cap<0) {
                    deliveries[pd] = deliver_cap*(-1);
                    break;
                }
                deliveries[pd] = 0;
            }

            int pickup_cap = cap, pp;
            for(pp = p2; pp>=0; pp--){
                pickup_cap -= pickups[pp];
                if(pickup_cap<0) {
                    pickups[pp] = pickup_cap*(-1);
                    break;
                }
                pickups[pp] = 0;
            }

            answer += (endpoint+1);
            p1 = pd; p2 = pp;
            System.out.println("----count "+count+"----");
            System.out.println(Arrays.toString(deliveries));
            System.out.println(Arrays.toString(pickups));
            System.out.println("p1: "+p1+", p2: "+p2+", length: "+(endpoint+1));

            if(p1<=0 && p2<=0 && deliveries[0]==0 && pickups[0]==0) break;

        }

        return answer*2;
    }
}