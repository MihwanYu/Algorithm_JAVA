package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class p1043 {
    static class Person{
        boolean istrue;
        LinkedList<Integer> parties;//person이 참여하는 party 리스트
        public Person(){
            this.parties = new LinkedList<>();
        }
    }

    static ArrayList<Integer>[] partylist; //i번째 party에 참여하는 사람 리스트
    static Person[] people;
    static boolean[] trueparty, visitedperson, visitedparty;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //전체 인원
        int M = Integer.parseInt(st.nextToken()); //파티 수
        people = new Person[N+1];
        for(int i=1; i<people.length; i++){
            people[i] = new Person();
        }
        partylist = new ArrayList[M+1];
        trueparty = new boolean[M+1]; //answer: true인 party 경우 세야함


        //진실 아는 사람 입력
        st = new StringTokenizer(br.readLine());
        boolean[] truelist = new boolean[Integer.parseInt(st.nextToken())];
        for(int i=0; i<truelist.length; i++){
            int idx = Integer.parseInt(st.nextToken());
            people[idx].istrue = true;
        }

        //파티 참여 인원 정보(idx) 입력
        for(int i=1; i<M+1; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); //party당 참여인원 수
            partylist[i] = new ArrayList<>();
            for(int j=0; j<num; j++){
                int personidx = Integer.parseInt(st.nextToken());
                partylist[i].add(personidx);
                people[personidx].parties.add(i);
            }
        }
//        System.out.println(Arrays.toString(partylist[1].toArray()));


        if(truelist.length == 0){
            System.out.println(M);
            return;
        }

        //dfs로 party정보 채우기
        for(int i=1; i<people.length; i++){
            visitedperson = new boolean[N+1];//dfs 방문할때 쓸것
            visitedparty = new boolean[M+1];
            if(people[i].istrue){
                //진실을 아는 person의 partylist에 모두 탐색
                visitedperson[i] = true;
                for(int j=0; j<people[i].parties.size(); j++){
                    int partyIdx = people[i].parties.get(j);
                    dfs_party(partyIdx);
                }
            }
        }

        int count = 0;
        for(int i=1; i<trueparty.length; i++){
            if(! trueparty[i]){
                //party에 진실을 아는 사람이 한명도 없으면 count++
                count ++;
            }
        }
        System.out.println(count);

    }

    static void dfs_party(int idx){
        // parties[idx]번째 방문
        if(trueparty[idx]) return;

        trueparty[idx] = true;
        visitedparty[idx] = true;
        for(int i=0; i<partylist[idx].size(); i++){
            int personIdx = partylist[idx].get(i);//번째 사람이 visitedperson==true면 넘어가고, 아니면 dfs2로 걔의 partylist 탐구하기
            if( ! visitedperson[personIdx] ){
                dfs_people(personIdx);
            }
        }
    }

    static void dfs_people(int idx){
        // 특정 party를 방문한 사람 정보 방문
        if(people[idx].istrue) return;
        // 이 사람의 진실 여부 바꿔주고 파티리스트 검색하기
        people[idx].istrue = true;
        visitedperson[idx] = true;
        for(int j=0; j<people[idx].parties.size(); j++){
            int partyIdx = people[idx].parties.get(j);
            if( ! visitedparty[partyIdx]){
                dfs_party(partyIdx);
            }
        }
    }


}
