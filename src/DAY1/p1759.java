package DAY1;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;

public class p1759 {
    static int L, C;
    static char[] input;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        C = sc.nextInt();
        input = new char[C];

        for (int i=0; i< input.length; i++){
            input[i] = sc.next().charAt(0);
        }

        Arrays.sort(input);
        char[] alphabets = new char[L];
//        System.out.println("sorted: "+ Arrays.toString(input));
        for(int i=0; i<input.length; i++){
            dfs(i, alphabets, 0, 0);
        }

        /*
        solution 호출

        dfs2(-1,0,0,0,"");
        for(int i=0; i<C; i++){
            if(input[i]=='a' || input[i]=='e' || input[i]=='i' || input[i]=='o' || input[i]=='u'){
                dfs2(i,1,0,1,"")
            }
            else{
                dfs2(i,1,1,0,"");
            }
        }
        */

    }

    static void dfs(int idx, char[] alphabets, int countJ, int countM){
        //1. 체크인: 현 idx 값이 모음이면 모음 카운트 +1, 아니면 자음 카운트 1
        boolean isJaeum;
        alphabets[countJ+countM] = input[idx];
        if (input[idx]=='a' || input[idx]=='e' || input[idx]=='i' || input[idx]=='o' || input[idx]=='u'){
            countM++;
            isJaeum = false;
        }else{
            countJ++;
            isJaeum = true;
        }

        //2. 목적지인가
        if(countJ+countM==L){
            if(countJ>=2 && countM>=1){
//                System.out.println(Arrays.toString(alphabets));
                System.out.println(new String((alphabets)));
            }

            //자음+모음
        }else{
            //3. 연결된 곳 순회
            for(int i= idx+1; i< input.length; i++){
                //4. 갈 수 있는가 ??<-생략 가능(이미 정렬된상태기때문)
                //5. 간다
                dfs(i, alphabets, countJ, countM);
            }
        }


        //6. 체크아웃
//        alphabets[countJ+countM] = "";
        if (isJaeum){
            countJ--;
        }else{
            countM--;
        }
    }

    //dfs solution
    static void dfs2(int current, int length, int ja, int mo, String pwd){
        //1. 체크인 -> 생략가능
        //2. 목적지인가 length == L -> 자,모 개수
        if(length == L){
            if(ja>=2 && mo>=1){
                System.out.println(pwd);
            }

        }else{
            //3. 연결된 곳 순회 current ~ C
            for(int i = current+1; i<C; i++){
                //4. 갈 수 있는가 ? -> 생략 가능
                //5. 간다 -> 자, 모
                if (input[i]=='a' || input[i]=='e' || input[i]=='i' || input[i]=='o' || input[i]=='u'){
                    dfs2(i, length+1, ja, mo+1, pwd+input[i]); //실제로는 이렇게 하면 x, 성능 느려짐
                }else{
                    dfs2(i, length+1, ja+1, mo, pwd+input[i]);
                }

            }
        }


        //6. 체크아웃 -> 생략가능
    }

}
