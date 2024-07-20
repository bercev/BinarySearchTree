import com.sun.source.tree.Tree;

import java.util.*;

public class BinarySearchTree<T> {

    public class TreeNode<T> {
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
        while (true)
        {
            if (temp == null)
            {
                temp = new TreeNode(x);
                System.out.println("Inserted @ index: " +  currentIndex);
                break;
            }
            else if (x < temp.data)
            {
                currentIndex = (2 * currentIndex) + 1;
                System.out.println(" Left <<< " + currentIndex);
                temp = temp.left;

            }
            else {
                currentIndex = (2 * currentIndex) + 2;
                temp = temp.right;
                System.out.println(" Right >>> " + currentIndex);
            }
        }

    }

    void inOrder(TreeNode node) {

        if (root != null) {
            node = root.left;
            inOrder(node);
            index = 2 * index + 1;
            Level(index);
            System.out.println(root.data);
            node = root.right;
            inOrder(node);
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
        System.out.println();
        System.out.println("InOrder");
      //  bst.inOrder(bst.root);
    }




}