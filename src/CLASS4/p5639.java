package CLASS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p5639 {
    static class Node{
        int key;
        Node left, right;
        public Node(int key, Node left, Node right){
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int rootval = Integer.parseInt(br.readLine());
        Node root = new Node(rootval, null, null);
        String key = "";
        while((key=br.readLine()) != null){
//            String key = br.readLine();
            if(key.equals("") || key==null){
                break;
            }

            Node newNode = new Node(Integer.parseInt(key), null, null);
            insertNode(newNode, root);
        }

        sb = new StringBuilder("");
        postorder(root);
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    static  void insertNode(Node newNode, Node root){
        if(newNode.key<root.key){
            //child < parent => parent 왼쪽
            if(root.left == null){
                root.left = newNode;
            }else{
                insertNode(newNode, root.left);
            }
        }else{
            if(root.right == null){
                root.right = newNode;
            }else{
                insertNode(newNode, root.right);
            }
        }
    }

    static void postorder(Node node){

        if(node.left != null){
            postorder(node.left);
        }
        if(node.right != null){
            postorder(node.right);
        }
        sb.append(node.key).append("\n");
//        System.out.println(node.key);
    }

}
