package DAY3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class p11279 {


    public static void main(String[] args){
//        System.out.println()
        Scanner sc = new Scanner(System.in);
        List<Integer> heap = new ArrayList<>();
        int N = sc.nextInt();
        int[] input = new int[N+1];
        for(int i = 1; i<N+1; i++){
            input[i] = sc.nextInt();
        }
//        System.out.println(Arrays.toString(input));
        //heap에 데이터 삽입
        heap.add(0);
        for(int i=1; i<N+1; i++){
            if(input[i] ==0){
//                System.out.println("print. input[i]="+input[i]);
                if(heap.size()==1){
                    System.out.println(0);
                }else{
                    System.out.println(heap.get(1));

                    heap.set(1, heap.get(heap.size()-1)); //맨 뒤에 있는 값 가져오기
                    heap.remove(heap.size()-1); //그 값 삭제

//                    System.out.println(Arrays.toString(heap.toArray()));
                    int idx = 1;
                    while(idx*2<heap.size()){
//                        System.out.println("idx: "+idx);
                        int leftvalue = heap.get(idx*2);
                        int targetvalue = leftvalue;
                        int targetidx = idx*2;
                        //바꿀 대상(=현재 노드와 비교할 대상)은 leftnode, right node 중 큰 것
                        //이렇게 안하고 그냥 왼쪽먼저 비교 - 오른쪽비교하면 heap={0,7,12,13,8,11...}일 때 7이랑 13 안바꾸고 7이랑 12랑 바꾸게됨
                        if(idx*2+1<heap.size() && leftvalue<heap.get(idx*2+1)) {
                            targetvalue = heap.get(idx*2+1);
                            targetidx = idx*2+1;
                        }

                        if(heap.get(idx) < targetvalue){
                            //타겟이 더 클때 -> idx와 target 바꿔줘야 함
                            int temp = heap.get(idx);
                            heap.set(idx, targetvalue);
                            heap.set(targetidx, temp);
                            idx = targetidx;
                        }else{
                            //타겟이 더 작을때 -> 정렬됐으므로 break
                            break;
                        }

                        /*비교 잘못돼서 틀린 코드
                        if( heap.get(idx) < heap.get(idx*2) ){ //왼쪽 노드와 비교
                            int temp = heap.get(idx);
                            heap.set(idx, heap.get(idx*2));
                            heap.set(idx*2, temp);
                            idx = idx*2;
                        }else if (idx*2+1<heap.size() && heap.get(idx) < heap.get(idx*2+1)){
                            int temp = heap.get(idx);
                            heap.set(idx, heap.get(idx*2+1));
                            heap.set(idx*2, heap.get(temp));
                            idx = idx*2+1;
                        }else{
                            break;
                        }
                        */

                        if (idx*2>=heap.size()){
                            break;
                        }

                    }
//                    System.out.println(Arrays.toString(heap.toArray()));
                }

            }else{
//                System.out.println("insert. input[i] = "+input[i]);
                heap.add(input[i]);
                //삽입 후 부모와 대소 비교
                int idx = heap.size()-1;
                while(idx>1){
                    int parent = idx/2;
                    if (heap.get(parent) < heap.get(idx)){
                        int temp = heap.get(idx);
                        heap.set(idx, heap.get(parent));
                        heap.set(parent, temp);
                        idx = parent;
                    }else{
                        break;
                    }
                }
            }
//            System.out.println(Arrays.toString(heap.toArray()));
        }

    }
}
