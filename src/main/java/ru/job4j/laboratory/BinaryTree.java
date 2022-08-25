package ru.job4j.laboratory;

public class BinaryTree {

    Node root;

    public void addNode(int key, String name) {
        Node newNode = new Node(key, name);
        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;
            while (true) {
                parent = focusNode;
                if (key < focusNode.key) {
                    focusNode = focusNode.leftChild;
                    if (focusNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    focusNode = focusNode.rightChild;
                    if (focusNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void inOrderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            inOrderTraverseTree(focusNode.leftChild);
            System.out.println(focusNode);
            inOrderTraverseTree(focusNode.rightChild);
        }
    }

    public void preorderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            System.out.println(focusNode);
            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);
        }
    }

    public void postorderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);
            System.out.println(focusNode);
        }
    }

    public Node findNode(int key) {
        Node focusNode = root;
        while (focusNode.key != key) {
            if (key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }
            if (focusNode == null) {
                return null;
            }
        }
        return focusNode;
    }

    public static void main(String[] args) {
        BinaryTree theTree = new BinaryTree();
        theTree.addNode(50, "Oleg");
        theTree.addNode(25, "Anna");
        theTree.addNode(15, "Ivan");
        theTree.addNode(30, "Olga");
        theTree.addNode(75, "Inna");
        theTree.addNode(85, "Petr");

        System.out.println("inOrder");
        theTree.inOrderTraverseTree(theTree.root);
        System.out.println("preOrder");
        theTree.preorderTraverseTree(theTree.root);
        System.out.println("postOrder");
        theTree.postorderTraverseTree(theTree.root);

        /*      theTree
                  50
                /   \
               25    75
              /  \     \
            15    30    85

         */

        System.out.println("Search for 30 ");
        System.out.println(theTree.findNode(30));
    }

    class Node {
        int key;
        String name;

        Node leftChild;
        Node rightChild;


        public Node(int key, String name) {
            this.key = key;
            this.name = name;
        }

        public String toString() {
            return name + " has a key -> " + key;
        }
    }
}
