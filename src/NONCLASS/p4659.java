package NONCLASS;
import java.io.*;
import java.util.*;
public class p4659 {
    char[] mo = {'a','e','i','o','u'};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        //모음 반드시 포함,  / 3개 연속 모음 or 자음 x, / 두개 연속 같은글자 불가 , ee&oo 허용
        while(true){
            String input = br.readLine();
            if(input.equals("end")) break;

            boolean qualified = check_quality(input);
            if(qualified){
                System.out.println("<"+input+"> is acceptable.");
            }else{
                System.out.println("<"+input+"> is not acceptable.");
            }
        }

    }

    static boolean check_quality(String input){

        char pre_char = input.charAt(0);
        int cnt_mo = 1;// 자,모 연속 횟수 카운트
        int pre_mo = 0;// 0이면 자음, 1이면 모음


        boolean isacceptable = true;
        boolean mo_exist = false;

        if(pre_char=='a' || pre_char=='e' || pre_char=='i' ||pre_char=='o' || pre_char=='u'){
            pre_mo = 1;
            mo_exist = true;
        }

        for(int i=1; i<input.length(); i++){
            char cur_char = input.charAt(i);

            //두개 연속 같은글자 확인
            if(pre_char==cur_char){
                if(cur_char=='e' || cur_char=='o'){
                    //그 다음 char 미리 보고 e거나 o면 멈춤
                    if(i+1<input.length()){
                        if((cur_char=='e' && input.charAt(i+1)=='e') || (cur_char=='o' && input.charAt(i+1)=='o')){
                            isacceptable = false; break;
                        }
                    }
                }else{
                    isacceptable = false; break;
                }
            }

            //모음인 경우
            if(cur_char=='a' || cur_char=='e' || cur_char=='i' ||cur_char=='o' || cur_char=='u') {
                mo_exist = true;
                //앞이 자음이었다면
                if(pre_mo==0){
                    cnt_mo = 1;//연속 카운트 초기화
                }else{
                    //모음이였다면 연속 카운트 추가
                    cnt_mo++;
                    if(cnt_mo>=3){
                        isacceptable = false; break;
                    }
                }

                pre_mo = 1;
            }else{
                //자음인 경우, 앞이 자음이었다면
                if(pre_mo==0){
                    cnt_mo++;
                    if(cnt_mo>=3){
                        isacceptable = false; break;
                    }
                }else{
                    //앞이 모음, 현재 자음
                    cnt_mo = 1;
                }

                pre_mo = 0;
            }

            //조건 체크 후 pre_char, pre_mo 값 변경
            pre_char = cur_char;


        }
        if(!mo_exist) return false;//모음 한번도 안나옴
        if(!isacceptable) return false;
        return true;
    }
}
