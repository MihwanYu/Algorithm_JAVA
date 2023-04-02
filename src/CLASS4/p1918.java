package CLASS4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1918 {
    static class Node{
        char val;
        Node parent, leftchild, rightchild;
        public Node(char val, Node parent, Node leftchild, Node rightchild){
            this.val = val;
            this.parent = parent;
            this.leftchild = leftchild;
            this.rightchild = rightchild;
        }
    }

    static Node root;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Node prior = new Node(input.charAt(0), null, null, null);
        root = prior;

        for(int i=1; i<input.length(); i++){
            char a = input.charAt(i);
            prior = insertNode(a, prior); //prior node의 자식으로 a노드 삽입

        }

        inorder(root);

    }

    //Node 생성 및 삽입 후 생성 Node 반환
    static Node insertNode(char val, Node prior){
        if(val>=65){
            //prior의 child로 들어가야 함
            if(prior.leftchild == null){
                prior.leftchild = new Node(val, prior, null, null);
                return prior.leftchild;
            }else{
                prior.rightchild = new Node(val, prior, null, null);
                return prior.rightchild;
            }

        }else if(val=='+' || val=='-'){
            //prior의 parent로 들어가야 함
            if(prior.parent == null){
                prior.parent = new Node(val, null, prior, null);
                root = prior.parent;
            }else{
                //prior에 parent가 있다면 그것의 parent에 삽입
                prior.parent = insertNode(val, prior.parent);
            }
            return prior.parent;
        }else if(val=='*' || val=='/'){
            if(prior.parent == null){
                prior.parent = new Node(val, null, prior, null);
                root = prior.parent;
                return prior.parent;
            }

            Node temp = new Node(prior.val, null, prior.leftchild, prior.rightchild);
            prior = new Node(val, prior.parent, temp, null);
            prior.parent.rightchild = prior;
            temp.parent = prior;
            return prior;
        }
        else{
            // (, )
        }
        return prior;//고쳐야함
    }

    static void inorder(Node root){
        if(root.leftchild != null){
            inorder(root.leftchild);
        }

        if(root.rightchild != null){
            inorder(root.rightchild);
        }
        System.out.print(root.val + " ");
    }

}
