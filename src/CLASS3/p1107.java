package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1107 {
    static int[] brokenNum;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int brokens = Integer.parseInt(br.readLine());
        brokenNum = new int[brokens];
        Queue<Integer> queue= new LinkedList<Integer>(); //dp용인데 크기 범위 무한대라서

        if(brokens==0){
            //고장난 버튼 없으면 고장버튼 리스트 안받고 끝남
            int length;
            // +-로 움직이는게 더 가까울 경우
            if(target>98 && target <103){
                length = Math.abs(100-target);
            }else{
                length = numlength(target);
            }

            System.out.println(length);
            return;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<brokens; i++){
            brokenNum[i] = Integer.parseInt(st.nextToken());
        }

        if(target>97 && target<103){//현위치(100)와 타겟 차가 3보다 작으면(98~103) 알고리즘 돌릴 필요도 x
            int diff = Math.abs(target-100);
            System.out.println(diff);
            return;
        }

        if(!hasbroken(target)){
            //한번에 이동 가능하면 자릿수 만큼 누르고 끝
            int length = numlength(target);
            System.out.println(length);
            return;
        }

        if(brokens==10){
            //버튼 다 고장나면 100에서 이동
            System.out.println(Math.abs(target-100));
            return;
        }

        //목적지 번호로 한번에 이동할 수 없을 경우
        queue.add(target+1);
        if(target>0){
            queue.add(target-1);
        }
        int moveto = -1;
        while (!queue.isEmpty()){
            int current = queue.poll();
//            System.out.println(current);
            if(hasbroken(current)){
                if(current<target && current>0){
                    queue.add(current-1);
                }
                if(current>target){
                    queue.add(current+1);
                }

            }else{
                if(moveto==-1){
                    moveto = current;
                }
                else{
                    if( Math.abs(moveto-target)+numlength(moveto) > Math.abs(current-target) + numlength(current) ){
                        moveto = current;
                    }
                    break;
                }

            }
            if(moveto != -1 && Math.abs(current-target)>Math.abs(moveto-target)+numlength(moveto)){
                break;
            }

        }


        int diff;
        int length = numlength(moveto);
        if(Math.abs(target-100) > Math.abs(target-moveto) + length){
            diff = Math.abs(target-moveto);
//            System.out.println("moveto: "+moveto);
            System.out.println(diff+length);
        }else{
            diff = Math.abs(target-100);
            System.out.println(diff);
        }

    }

    public static int numlength(int num){//num 자릿수 리턴
        int r=0;
        while(true){
            num = num/10;
            r++;
            if(num==0) break;
        }
        return r;
    }

    public static boolean hasbroken(int num){

        while(num/10>0){
            int n=num%10;
            num = num/10;
            for(int i=0; i<brokenNum.length; i++){
                if(n==brokenNum[i]){
                    return true;
                }
            }
        }
        for(int i=0; i<brokenNum.length; i++){
            if(num==brokenNum[i]){
                return true;
            }
        }
        return false;
    }
}
