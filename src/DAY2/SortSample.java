package DAY2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;

public class SortSample {
    public static void main(String[] args){
        Integer[] nums = {1,4,2,3,5};
        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums);

        System.out.println(Arrays.toString(nums));

//        Arrays.sort(nums, (o1, o2)-> 0 );
        Arrays.sort(nums, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return o1-o2; //o2-o1으로 하면 역순정렬 되긴 함
            }
        });
        System.out.println(Arrays.toString(nums));

        Arrays.sort(nums, Comparator.reverseOrder()); //역순정렬 . 바로 위 처럼 안 해도 reverseorder로 동일 결과 낼 수 있음
        System.out.println(Arrays.toString(nums));

        System.out.println("----------------------------------------");
        Item[] items = new Item[5]; //값이 2개 이상인 오브젝트의 정렬
        items[0] = new Item(1,5);
        items[1] = new Item(2,4);
        items[2] = new Item(3,3);
        items[3] = new Item(4,2);
        items[4] = new Item(5,1);

        System.out.println(Arrays.toString(items));

        //comparator 만들어서 정렬하기
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
        //value1과 value2를 복합적으로 고려
                int comp1 = o1.value2-o2.value2;
                if(comp1 ==0){
                    return o2.value1 - o1.value1;
                }else{
                    return comp1;
                }
            }

        });

        System.out.println(Arrays.toString(items));

        Arrays.sort(items); //<-- Item class의 comparable을 기준으로 정렬하게 됨

        System.out.println(Arrays.toString(items));

        //getter 사용해서 comparing 하는 방법(현업에서 사용한다고 하심)
//        Arrays.sort(items, Comparator.comparingInt(Item::getValue1).reversed());
    }
}


class Item implements Comparable<Item>{
    int value1, value2;

    public Item(int value1, int value2){
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString(){
        return "{"+value1+","+value2+"}";
    }


    @Override
    public int compareTo(Item o2) {
//        return value1-o2.value1; //<--value1기준으로 오름차순 정렬
        return Integer.compare(o2.value1, value1);//기존 비교자를 사용해서 비교하는 방식
    }
}