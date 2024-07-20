import com.sun.source.tree.Tree;

import java.util.*;

public class BinarySearchTree {

    public class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        int nodeIndex;

        public TreeNode(int t) {
            left = null;
            right = null;
            data = t;
        }

        public TreeNode(TreeNode n) {
            left = n.left;
            right = n.right;
            data = n.data;
        }
    }
    int index = 0;
    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    void insert(int x)
    {
        int currentIndex = 0;
        System.out.println("\n--> insert: " + x);

        if (root == null) {
            root = new TreeNode(x);
            System.out.println("Inserted @ index: " +  currentIndex);
            return;
        }

        TreeNode temp = root;
        TreeNode previous = null;
        while (true)
        {
            previous = temp;
            if (x < temp.data)
            {
                currentIndex = (2 * currentIndex) + 1;
                System.out.println(" Left <<< " + currentIndex);
                temp = temp.left;
                if (temp == null)
                {
                    previous.left = new TreeNode(x);
                    System.out.println("Inserted @ index: " +  currentIndex);
                    break;
                }

            }
            else {
                currentIndex = (2 * currentIndex) + 2;
                temp = temp.right;
                System.out.println(" Right >>> " + currentIndex);
                if (temp == null) {
                    previous.right = new TreeNode(x);
                    System.out.println("Inserted @ index: " + currentIndex);
                    break;
                }
            }
        }

    }

    void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.data);
            inOrder(node.right);
        }
    }


    void Level(int x)
    {
        while (x != 0)
        {
            x = (x - 1) / 2;
            System.out.println("---|");
        }
    }



    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(42);
        bst.insert(68);
        bst.insert(35);
        bst.insert(1);
        bst.insert(70);
        bst.insert(25);
        bst.insert(79);
        bst.insert(59);
        bst.insert(63);
        bst.insert(65);
        System.out.println("\n\n\n\n==============================================");
        System.out.println("InOrder");
        bst.inOrder(bst.root);
    }




}