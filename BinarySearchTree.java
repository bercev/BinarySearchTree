public class BinarySearchTree<T extends Comparable<T>>{

    // Nodes used instead of array
    public class TreeNode<T extends Comparable<T>> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;

        int nodeIndex;

        public TreeNode(T t) {
            left = null;
            right = null;
            data = t;
        }
    }
    int index = 0; // helps with printing
    int numberOfLevels = 0; // determines how many levels there are to the tree
    TreeNode<T> root; // root node
    Queue<TreeNode> mainList = new Queue(); // used with printing

    // constructor
    public BinarySearchTree() {
        root = null;
    }

    // determines the number of levels
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

    // inserts node in the bst
    void insert(T x)
    {
        int currentIndex = 0;
        System.out.println("\n--> insert: " + x);

        // if root is null, place first node there
        if (root == null) {
            root = new TreeNode(x);
            System.out.println("Inserted @ index: " +  currentIndex);
            numberOfLevels++;
            return;
        }

        TreeNode temp = root; // used to iterate
        TreeNode previous = null; // helps keep the root to the new node connected
        while (true)
        {
            previous = temp; //  sets it to iterator, keeping the present position and root connected
            if (x.compareTo((T) temp.data) < 0) // if less than root, go insert on the left side of the tree
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
            else { // if greater than root, go insert on the right side of the tree
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

    // used with the previousInOrder method
    void Level(int x)
    {
        while (x != 0)
        {
            x = (x - 1) / 2;
            System.out.print("-|");
        }
    }

    // previousInOrder method, to check if bst implementation was correct
    void previousInOrder(TreeNode node, int currentIndex) {
        if (node != null) {
            previousInOrder(node.left, 2*currentIndex + 1);
            Level(currentIndex);
            System.out.println(node.data);
            previousInOrder(node.right, 2*currentIndex + 2);
        }
    }


    // new inOrder method.
    void inOrder() {
        for (int i = 0; i < numberOfLevels+1; i++) {
            inputter();
            printer();
        }

    }

    // inputs each level of the tree into the mainList queue
    void inputter() {
        if (index == 0) { // if at first level, only push once
            mainList.Push(root);
            index++;
            return;
        }

        int length = mainList.GetLength();
        Queue<TreeNode> newList = new Queue(); // queue to hold the next level (mainList holds previous level)
        for (int i = 0; i < length; i++) {
            if (mainList.Peek() == null) { // if data is null, then push 2 nulls into the new list
                newList.Push(null);
                newList.Push(null);
                mainList.Pop();
            } else {
                newList.Push(mainList.Peek().left); // pushes left into queue
                newList.Push(mainList.Peek().right); // pushes right into queue
                mainList.Pop(); // removes the head of the queue so the next one in line can .push into newList
            }
        }

        // this code block refills the mainList in order to be used with printer()
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

    // prints out each level of the tree
    void printer() {
        int length = mainList.GetLength();
        Queue<TreeNode> copy = new Queue(); // copying the mainList to not modify it
        for (int i = 0; i < length; i++) {
            if (mainList.Peek() == null) {
                copy.Push(null);
                mainList.Pop();
            } else {
                copy.Push(mainList.Pop());
            }
        }

        while(!copy.IsEmpty()) {
            if (copy.Peek() == null) { // if null, represent it with a "-"
                System.out.print("-\t");
            } else {
                System.out.print(copy.Peek().data + "\t"); // else, print out data
            }
            mainList.Push(copy.Pop()); // refills the mainList back to normal, so it can work with insertter
        }
        System.out.println("|"); // end of level
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
        System.out.println("Previous InOrder function");
        bst.previousInOrder(bst.root, 0);
        System.out.println("\nNew InOrder function!");
        bst.inOrder();

        System.out.println("\n\n\n\n==============================================");
        System.out.println("Testing with random numbers in a new binary search tree");
        BinarySearchTree treeTwo = new BinarySearchTree();
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 101); // generating random numbers 0-100
            treeTwo.insert(random);
        }
        System.out.println("\n\n\n\n==============================================");
        System.out.println("Previous InOrder function");
        treeTwo.previousInOrder(treeTwo.root, 0);
        System.out.println("\nNew InOrder function!");
        treeTwo.inOrder();
    }
}