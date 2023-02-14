package DAY2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Photo implements Comparable<Photo>{
    int studentId;
    int recommend;
    int updated;
    public Photo(int studentId, int recommend, int updated){
        this.studentId = studentId;
        this.recommend = recommend;
        this.updated = updated;
    }

    @Override
    public int compareTo(Photo o2){
        int diff = this.recommend-o2.recommend;
        if(diff !=0) {
            return diff;
        }else{
            return this.updated-o2.updated;
        }

    }
}
public class p1713 {
    static int N, R;
    static Queue<Integer> queue;
    static Photo[] photobook;
    static int photoidx;
    public static void main(String[] args) throws Exception{
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());
        queue = new LinkedList<>();
        photobook = new Photo[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<R; i++){
            int studentID = Integer.parseInt(st.nextToken());
            boolean isIn = false;

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
                        isIn = true;
                        break;
                    }
                }
                if(isIn) continue;
                //빈 공간 없으면 정렬 후 가장 앞 자리에 새 객체 삽입
                Arrays.sort(photobook);
                photobook[0] = newphoto;
            }
        }

        Arrays.sort(photobook, new Comparator<Photo>() {
            @Override
            public int compare(Photo o1, Photo o2) {
                if(o1==null || o2==null){
                    return 0;
                }
                return o1.studentId - o2.studentId;
            }

        });


        for(int p=0; p<photobook.length; p++){
            if(photobook[p]==null)break;
            System.out.print(photobook[p].studentId+" ");
        }

        //student id 입력
        //student id가 이미 photobook에 존재 -> 추천수 +1
        //student id가 photobook에 없음 -> photo 객체 생성, photobook에 추가할 수 있는지 공간 확인
        //공간이 없다면 -> recommendation -> updated 순으로 오름차순 정렬
        //인덱스 0 자리에 생성한 photo 추가



    }
}
