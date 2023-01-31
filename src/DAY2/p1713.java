package DAY2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Photo{
    int studentId;
    int recommend;
    int updated;
    public Photo(int studentId, int recommend, int updated){
        this.studentId = studentId;
        this.recommend = recommend;
        this.updated = updated;
    }
}
public class p1713 {
    static int N, R;
    static Queue<Integer> queue;
    static Photo[] photobook;
    static int photoidx;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        queue = new LinkedList<>();
        photobook = new Photo[N];

        for(int i=0; i<R; i++){
            int studentID = sc.nextInt();
            boolean isIn = false;
//            queue.add(studentID);
            //studentID가 이미 배열에 존재한다면 추천수 +1
            for(int p=0; p<photobook.length; p++){
                if (photobook[p]!= null && photobook[p].studentId==studentID){
                    photobook[p].recommend ++;
                    isIn = true;
                    break;
                }
            }
            if(isIn==false){
                //studentID가 존재하지 않는다면
                Photo newphoto = new Photo(studentID, 1, i);
                for(int p=0; p<photobook.length; p++){
                    if(photobook[p]==null){
                        //포토북 빈 공간 있으면 거기에 새로운 객체 추가
                        photobook[p] = newphoto;
                        break;
                    }else{
                        //정렬
                    }
                }
            }


        }

        //student id 입력
        //student id가 이미 photobook에 존재 -> 추천수 +1
        //student id가 photobook에 없음 -> photo 객체 생성, photobook에 추가할 수 있는지 공간 확인
        //공간이 없다면 -> recommendation -> updated 순으로 오름차순 정렬
        //인덱스 0 자리에 생성한 photo 추가



    }
}
