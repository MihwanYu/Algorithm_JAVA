package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1991 {
    static class Node{
        char value;
        Node left, right;
        public Node(char value, Node left, Node right){
            this.value= value;
            this.left = left;
            this.right = right;
        }
    }

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Node root = new Node('A', null, null);
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            Node leftnode = null, rightnode = null;
            if(left != '.'){
                leftnode = new Node(left, null, null);
            }
            if(right != '.'){
                rightnode = new Node(right, null, null);
            }
            insertNode(root, parent, leftnode, rightnode);
        }

        sb = new StringBuilder();
        preorder(root);
        System.out.println(sb.toString());

        sb = new StringBuilder();
        inorder(root);
        System.out.println(sb.toString());

        sb = new StringBuilder();
        postorder(root);
        System.out.println(sb.toString());


    }

    static void insertNode(Node root, char parent, Node left, Node right){
        if(root.value==parent){
            root.left = left;
            root.right = right;
        }
        //현 root가 parent랑 다르면 root.left, root.right로 내려가보기
        if(root.left != null)
        insertNode(root.left, parent, left, right);
        if(root.right != null){
            insertNode(root.right, parent, left, right);
        }
    }

    static void preorder(Node root){
        sb.append(root.value);
        if(root.left != null){
            preorder(root.left);
        }
        if(root.right != null){
            preorder(root.right);
        }



    }

    static void inorder(Node root){
        if(root.left != null){
            inorder(root.left);
        }
        sb.append(root.value);
        if(root.right != null){
            inorder(root.right);
        }

    }

    static void postorder(Node root){
        if(root.left != null){
            postorder(root.left);
        }

        if(root.right != null){
            postorder(root.right);
        }

        sb.append(root.value);

    }

}
