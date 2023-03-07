package CLASS3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class p1764 {
    static int n, m;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        String[] nonlistenseen = new String[n+m];

        for(int i=0; i<n+m; i++){
            nonlistenseen[i] = br.readLine();
        }

        Arrays.sort(nonlistenseen);

        ArrayList<String> duplicate = new ArrayList<>();
        for(int i=1; i<n+m; i++){
            if(nonlistenseen[i-1].equals(nonlistenseen[i])){
                duplicate.add(nonlistenseen[i]);
            }
        }
        System.out.println(duplicate.size());
        for(int i=0; i<duplicate.size(); i++){
            System.out.println(duplicate.get(i));
        }

    }
}
