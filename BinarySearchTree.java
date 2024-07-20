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
    int numberOfLevels = 0;
    TreeNode root;
    Queue<TreeNode> mainList = new Queue();

    public BinarySearchTree() {
        root = null;
    }

    void countNumberOfLevels(int x) {
        int counter = 0;
        while (x != 0)
        {
            x = (x - 1) / 2;
            counter++;
        }
        if (numberOfLevels < counter)
            numberOfLevels = counter;
    }

    void insert(int x)
    {
        int currentIndex = 0;
        System.out.println("\n--> insert: " + x);

        if (root == null) {
            root = new TreeNode(x);
            System.out.println("Inserted @ index: " +  currentIndex);
            numberOfLevels++;
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

                    // determining number of levels
                    countNumberOfLevels(currentIndex);
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

                    // determining number of levels
                    countNumberOfLevels(currentIndex);
                    break;
                }
            }
        }

    }

    void Level(int x)
    {
        while (x != 0)
        {
            x = (x - 1) / 2;
            System.out.print("-|");
        }
    }

    void previousInOrder(TreeNode node, int currentIndex) {
        if (node != null) {
            previousInOrder(node.left, 2*currentIndex + 1);
            Level(currentIndex);
            System.out.println(node.data);
            previousInOrder(node.right, 2*currentIndex + 2);
        }
    }


    void inOrder() {
        for (int i = 0; i < numberOfLevels+1; i++) {
            inputter();
            printer();
        }

    }
    void inputter() {
        if (index == 0) {
            mainList.Push(root);
            index++;
            return;
        }
        int length = mainList.GetLength();
        Queue<TreeNode> newList = new Queue();
        for (int i = 0; i < length; i++) {
            if (mainList.Peek() == null) {
                newList.Push(null);
                newList.Push(null);
                mainList.Pop();
            } else {
                newList.Push(mainList.Peek().left);
                newList.Push(mainList.Peek().right);
                mainList.Pop();
            }
        }

        length = newList.GetLength();
        for (int i = 0; i < length; i++) {
            if (newList.Peek() == null) {
                mainList.Push(null);
            } else {
                mainList.Push(newList.Peek());
            }
            newList.Pop();
        }

    }
    void printer() {

        int length = mainList.GetLength();
        Queue<TreeNode> copy = new Queue();
        for (int i = 0; i < length; i++) {
            if (mainList.Peek() == null) {
                copy.Push(null);
                mainList.Pop();
            } else {
                copy.Push(mainList.Pop());
            }
        }

        while(!copy.IsEmpty()) {
            if (copy.Peek() == null) {
                System.out.print("-\t");
            } else {
                System.out.print(copy.Peek().data + "\t");
            }
            mainList.Push(copy.Pop());
        }
        System.out.println("|");
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
        bst.previousInOrder(bst.root, 0);
        System.out.println("\nInOrder v2 Horizontal Tree Level top-bottom");
        bst.inOrder();
    }
}
