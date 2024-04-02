import java.util.ArrayList;
import java.util.Stack;

/**
 * An Integer Binary Search Tree
 * @author: Stevie K. Halprin
 * @version: 4/1/2024
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val) {
        // Returns the boolean value given by subSearch method
        // If val already exists in the tree, returns true, false otherwise
        return subSearch(val, this.root);
    }

    // Recursive method that returns true if the given value is in tree
    public boolean subSearch(int value, BSTNode current) {
        // Base Case
        if (value == current.getVal()) {
            // Return true if the current Node has value val
            return true;
        }
        // Otherwise if current Node has value greater than val,
        // And has no left child, return false
        else if (value < current.getVal()) {
            if (current.getLeft() == null) {
                return false;
            }
            // Recursive step
            // Returns the boolean value given by subSearch,
            // But changes current Node to the left child of current
            return subSearch(value, current.getLeft());
        }
        // Otherwise if current Node has value less than val,
        // And has no right child, return false
        if (current.getRight() == null) {
            return false;
        }
        // Recursive step
        // Returns the boolean value given by subSearch,
        // But changes current Node to the right child of current
        return subSearch(value, current.getRight());
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder1() {
        // TODO: Complete inorder traversal
        // ArrayList of Nodes to be returned
        ArrayList<BSTNode> ioTrav = new ArrayList<BSTNode>();
        // Stack of Nodes declared
        Stack<BSTNode> order = new Stack<>();
        // Sets current node to the root
        BSTNode current = root;
        // Adds root to Stack 'order'
        order.push(current);

        // While Stack 'order' has elements and doesn't contain right child of root, continue
        while (!order.empty() || !ioTrav.contains(root.getRight())) {
            // Current equals top element of order, remove top element
            current = order.pop();

            if (ioTrav.contains(current)) {
                // If ioTrav already contains current Node, continue (with parent node)
                continue;
            }

            // If the left child is not valid, add current node to ioTrav
            if (!isValidBST(ioTrav, current, true)) {
                ioTrav.add(current);
            }

            // If the left child is valid, add it to order
            if (isValidBST(ioTrav, current, true)) {
                // Also add current Node to the order
                order.push(current);
                order.push(current.getLeft());
            }
            // Otherwise if the right child is valid, add it to order
            else if (isValidBST(ioTrav, current, false)) {
                // Also add current Node to the order
                order.push(current);
                order.push(current.getRight());
            }
        }

        // Returns ArrayList of Nodes ioTrav
        return ioTrav;
    }

    public ArrayList<BSTNode> getInorder() {
        // TODO: Complete inorder traversal

        // ArrayList of Nodes to be returned
        ArrayList<BSTNode> ioTrav = new ArrayList<BSTNode>();
        // Calls inorder traversal on the ArrayList poTrav
        inorder(ioTrav, root);
        // Return ArrayList of Nodes preTrav
        return ioTrav;
    }
    // Recursively adds the nodes of the tree to ArrayList in inorder traversal
    public void inorder(ArrayList<BSTNode> ioTrav, BSTNode current) {
        // Base case
        // If the current Node is null, return
        if (current == null) {
            return;
        }

        // Call postorder on the left child of current, then the right
        postorder(ioTrav, current.getLeft());
        // Add current Node to ArrayList of Nodes
        ioTrav.add(current);
        postorder(ioTrav, current.getRight());

    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder1() {
        // TODO: Complete preorder traversal

        // ArrayList of Nodes to be returned
        ArrayList<BSTNode> preTrav = new ArrayList<BSTNode>();
        // Stack of Nodes declared
        Stack<BSTNode> order = new Stack<>();
        // Sets current node to the root
        BSTNode current = root;
        // Adds root to Stack 'order'
        order.push(current);

        // While Stack 'order' has elements and doesn't contain right child of root, continue
        while (!order.empty() || !preTrav.contains(root.getRight())) {
            // Set current node to top element of order, remove top element
            current = order.pop();

            // If preTrav doesn't already have current node, add it
            if (!preTrav.contains(current)) {
                preTrav.add(current);
            }

            // If left child is valid, add current, then left child to order
            if (isValidBST(preTrav, current, true)) {
                order.push(current);
                order.push(current.getLeft());
            }
            // Otherwise if right child is valid, add current, then right child to order
            else if (isValidBST(preTrav, current, false)) {
                order.push(current);
                order.push(current.getRight());
            }
        }

        // Return ArrayList of Nodes preTrav
        return preTrav;
    }

    public ArrayList<BSTNode> getPreorder() {
        // TODO: Complete preorder traversal

        // ArrayList of Nodes to be returned
        ArrayList<BSTNode> preTrav = new ArrayList<BSTNode>();
        // Stack of Nodes declared
        Stack<BSTNode> order = new Stack<>();
        // Calls preorder traversal on the ArrayList poTrav
        preorder(preTrav, root);
        // Return ArrayList of Nodes preTrav
        return preTrav;
    }
    // Recursively adds the nodes of the tree to ArrayList in preorder traversal
    public void preorder(ArrayList<BSTNode> preTrav, BSTNode current) {
        // Base case
        // If the current Node is null, return
        if (current == null) {
            return;
        }
        // Add current Node to ArrayList of Nodes
        preTrav.add(current);
        // Call preorder on the left child of current, then the right
        preorder(preTrav, current.getLeft());
        preorder(preTrav, current.getRight());
    }


    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder1() {
        // TODO: Complete postorder traversal
        // ArrayList of Nodes to be returned
        ArrayList<BSTNode> poTrav = new ArrayList<BSTNode>();
        // Stack of Nodes declared
        Stack<BSTNode> order = new Stack<>();
        // Sets current node to the root
        BSTNode current = root;
        // Adds root to Stack 'order'
        order.push(current);

        // While Stack 'order' doesn't contain right child of root, continue
        while (current != root.getRight()) {
            // Set current Node to top element of order, remove top element
            current = order.pop();

            // If poTrav already contains current Node, continue (with next element in order)
            if (poTrav.contains(current)) {
                continue;
            }

            // If left child is valid, add current, then left child to order
            if (isValidBST(poTrav, current, true)) {
                order.push(current);
                order.push(current.getLeft());
            }
            // If right child is valid, add current, then right child to order
            else if (isValidBST(poTrav, current, false)) {
                order.push(current);
                order.push(current.getRight());
            }
            // Otherwise add current node to poTrav
            else {
                poTrav.add(current);
            }
        }
        // Add left child of root to order
        order.push(root.getLeft());
        // While there are still elements in order, continue
        while (!order.empty()) {
            // Set current Node to top element of order, remove top element
            current = order.pop();

            // If poTrav already contains current Node, continue (with next element in order)
            if (poTrav.contains(current)) {
                continue;
            }

            // If left child is valid, add current, then left child to order
            if (isValidBST(poTrav, current, true)) {
                order.push(current);
                order.push(current.getLeft());
            }
            // If right child is valid, add current, then right child to order
            else if (isValidBST(poTrav, current, false)) {
                order.push(current);
                order.push(current.getRight());
            }
            // Otherwise add current node to poTrav
            else {
                poTrav.add(current);
            }
        }

        // Return ArrayList of nodes poTrav
        return poTrav;
    }

    public ArrayList<BSTNode> getPostorder() {
        // TODO: Complete preorder traversal

        // ArrayList of Nodes to be returned
        ArrayList<BSTNode> poTrav = new ArrayList<BSTNode>();
        // Stack of Nodes declared
        Stack<BSTNode> order = new Stack<>();
        // Calls postorder traversal on the ArrayList poTrav
        postorder(poTrav, root);
        // Return ArrayList of Nodes preTrav
        return poTrav;
    }
    // Recursively adds the nodes of the tree to ArrayList in postorder traversal
    public void postorder(ArrayList<BSTNode> poTrav, BSTNode current) {
        // Base case
        // If the current Node is null, return
        if (current == null) {
            return;
        }

        // Call postorder on the left child of current, then the right
        postorder(poTrav, current.getLeft());
        postorder(poTrav, current.getRight());

        // Add current Node to ArrayList of Nodes
        poTrav.add(current);
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val) {
        // TODO: Complete insert

        // If value already exists in tree, return
        if (search(val)) {
            return;
        }

        // Declare new node to be inserted with value val
        BSTNode insertee = new BSTNode(val);
        // Set current node to the root
        BSTNode current = root;

        // While current node still has valid children nodes, continue
        while (current.getLeft() != null && current.getRight() != null) {
            // If value is lower than val of current node, current node equals left child of current
            if (val < current.getVal()) {
                current = current.getLeft();
            }
            // Otherwise, if value is higher than val of current node, current node equals right child of current
            else {
                current = current.getRight();
            }
        }
        // Set node with value val 'insertee' to the left child of current
         current.setLeft(insertee);
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */


    // Returns whether the given cell should be accessed in various traversal methods
    public boolean isValidBST(ArrayList<BSTNode> path, BSTNode parent, boolean isLC) {
        // If node to be accessed is left child of parent, and isn't null and isn't already in given ArrayList, return true
        if (isLC) {
            if (parent.getLeft() != null && !path.contains(parent.getLeft())) {
                return true;
            }
        }
        // If node to be accessed is right child of parent, and isn't null and isn't already in given ArrayList, return true
        else {
            if (parent.getRight() != null && !path.contains(parent.getRight())) {
                return true;
            }
        }
        // Return false otherwise
        return false;
    }


    public boolean isBST(ArrayList<BSTNode> path, BSTNode parent, boolean isLC) {
        // TODO: Optional Challenge!
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
