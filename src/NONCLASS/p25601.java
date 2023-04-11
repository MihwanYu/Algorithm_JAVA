package NONCLASS;

import java.io.*;
import java.util.*;
public class p25601 {
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()) ;

        HashMap<String, String> family = new HashMap<>();

        for(int i=0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            family.put(st.nextToken(), st.nextToken());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        String class1 = st.nextToken();
        String class2 = st.nextToken();

        boolean isconnected = false;

        String child = class1;
        //class1(자식) - class2(부모) 관계 확인
        while(true){
            String parent = family.getOrDefault(child, "n");
//            System.out.println("child("+child+"), parent("+parent+")");
            if(parent.equals("n")){
                break;
            }else if(parent.equals(class2)){
                isconnected = true;
                break;
            }else{
                child = parent;
            }
        }


        child = class2;
        //class1(부모) - class1(자식) 관계 확인
        while( !isconnected ){
            String parent = family.getOrDefault(child, "n");
            if(parent.equals("n")){
                break;
            }else if(parent.equals(class1)){
                isconnected = true;
                break;
            }else{
                child = parent;
            }
        }

        if(isconnected) System.out.println(1);
        else System.out.println(0);



    }
}
