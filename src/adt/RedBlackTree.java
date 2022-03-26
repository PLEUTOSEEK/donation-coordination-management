/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import java.lang.*;
import java.time.LocalDateTime;

/**
 *
 * @author Tee Zhuo Xuan
 * @param <U>
 */
public class RedBlackTree<U extends Comparable<? super U>, T extends Comparable<T>> implements BinaryTreeInterface<U, T> {

    private Node root;
    private Node newNode;
    private int length;

    public RedBlackTree() {
        this.root = null;
        this.newNode = null;
        this.length = 0;
    }

    public void test(T data) {
        Node newNode = new Node(data);
        newNode.getListData().addLast(data);
        LocalDateTime zz = LocalDateTime.now();
        //newNode.getLabel().compareTo(zz); // U (Date time)
        //((T) newNode.getListData().getFirst()).compareTo(data); //T (Object)
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    public Node getRoot() {
        return root;
    }

    public Node getNewNode() {
        return newNode;
    }

    public int getLength() {
        return length;
    }

    public void incLength() {
        this.length += 1;
    }

    public void decLength() {
        this.length -= 1;
    }

    @Override
    public boolean addData(U label, T data) {
        this.newNode = new Node(label);
        this.newNode.getListData().addLast(data);

        Node currentNode = this.root;

        boolean inserted = false;

        if (isEmpty()) {
            this.root = this.newNode;
            reColor(this.root, false);
            incLength();
        } else {
            while (inserted == false) {

                if (this.newNode.getLabel().compareTo(currentNode.getLabel()) > 0) {

                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } else {
                        this.newNode.setParent(currentNode);

                        currentNode.setRight(this.newNode);
                        incLength();
                        inserted = true;
                    }
                } else if (this.newNode.getLabel().compareTo(currentNode.getLabel()) < 0) {

                    if (currentNode.getLeft() != null) {
                        currentNode = currentNode.getLeft();
                    } else {
                        this.newNode.setParent(currentNode);
                        currentNode.setLeft(this.newNode);
                        incLength();
                        inserted = true;
                    }

                } else if (this.newNode.getLabel().compareTo(currentNode.getLabel()) == 0) {
                    this.newNode = currentNode;
                    currentNode.getListData().addLast(data);
                    inserted = true;
                }
            }

            if (currentNode.getColor() == this.newNode.getColor() && this.newNode.getLabel().compareTo(currentNode.getLabel()) != 0) {
                fixInsert(this.newNode);
            }
        }

        return inserted;
    }

    private void fixInsert(Node currentNewNode) {

        while (currentNewNode != this.root) {

            Node uncle = null;
            Node parent = currentNewNode.getParent();
            Node grandParent = currentNewNode.getParent().getParent();

            if (currentNewNode.getColor() == true && parent.getColor() == true) {

                // <editor-fold defaultstate="collapsed" desc="Case 1">
                if (grandParent.getLeft() == null || grandParent.getRight() == null) {
                    uncle = null;
                } else if (grandParent.getLeft().equals(parent)) {
                    uncle = grandParent.getRight();
                } else if (grandParent.getRight().equals(parent)) {
                    uncle = grandParent.getLeft();
                }

                if (uncle != null) {
                    if (uncle.getColor() == true) {

                        parent.setColor(false);
                        uncle.setColor(false);
                        grandParent.setColor(true);

                        currentNewNode = grandParent; // problem
                    } else {
                        fourInsertionCase(currentNewNode, parent, grandParent);
                    }
                } else {
                    fourInsertionCase(currentNewNode, parent, grandParent);
                }

                // </editor-fold>
            } else {
                break;
            }
        }
        this.root.setColor(false);
    }

    private void fourInsertionCase(Node currentNewNode, Node parent, Node grandParent) {

        if (grandParent.getLeft() != null && grandParent.getLeft().equals(parent)) {
            if (parent.getLeft() != null && parent.getLeft().equals(currentNewNode)) {
                llCase(currentNewNode);
            } else {
                lrCase(currentNewNode);
            }
        } else {
            if (parent.getLeft() != null && parent.getLeft().equals(currentNewNode)) {
                rlCase(currentNewNode);
            } else {
                rrCase(currentNewNode);
            }
        }

        /*
         if (grandParent.getLeft() != null) {
            if (grandParent.getLeft().equals(parent)) {//parent left
                if (parent.getLeft() != null) {
                    if (parent.getLeft().equals(currentNewNode)) { //child left
                        // ll
                        llCase(currentNewNode);
                    } else {//child right
                        //lr
                        lrCase(currentNewNode);
                    }
                } else { // child right
                    //lr
                    lrCase(currentNewNode);
                }

            } else {//parent right
                if (parent.getLeft() != null) {
                    if (parent.getLeft().equals(currentNewNode)) { //child left
                        //rl
                        rlCase(currentNewNode);
                    } else {//child right
                        //rr
                        rrCase(currentNewNode);
                    }
                } else { // child right
                    //rr
                    rrCase(currentNewNode);
                }
            }
        } else { //parent right
            if (parent.getLeft() != null) {
                if (parent.getLeft().equals(currentNewNode)) { //child left
                    // rl
                    llCase(currentNewNode);
                } else {//child right
                    //rr
                    lrCase(currentNewNode);
                }
            } else { // child right
                //rr
                lrCase(currentNewNode);
            }
        }
         */
    }

    private void llCase(Node currentNewNode) {
        Node grandParent = currentNewNode.getParent().getParent();
        Node parent = currentNewNode.getParent();

        if (grandParent != null) {
            rightRotate(grandParent);
            boolean gpColor = grandParent.getColor();
            grandParent.setColor(parent.getColor());
            parent.setColor(gpColor);
        } else {
            rightRotate(parent);
            boolean pColor = parent.getColor();
            parent.setColor(currentNewNode.getColor());
            currentNewNode.setColor(pColor);
        }

        currentNewNode = parent;
    }

    private void lrCase(Node currentNewNode) {
        Node parent = currentNewNode.getParent();
        leftRotate(parent);
        llCase(currentNewNode.getLeft());

    }

    private void rrCase(Node currentNewNode) {
        Node grandParent = currentNewNode.getParent().getParent();
        Node parent = currentNewNode.getParent();

        if (grandParent != null) {
            leftRotate(grandParent);
            boolean gpColor = grandParent.getColor();
            grandParent.setColor(parent.getColor());
            parent.setColor(gpColor);
        } else {
            leftRotate(parent);
            boolean pColor = parent.getColor();
            parent.setColor(currentNewNode.getColor());
            currentNewNode.setColor(pColor);
        }

        currentNewNode = parent;

    }

    private void rlCase(Node currentNewNode) {
        Node parent = currentNewNode.getParent();
        rightRotate(parent);
        rrCase(currentNewNode.getRight());
    }

    private void leftRotate(Node x) {
        /*
        x.parent = 000
        x = 222
        x.left = 999
        x.right = 111

        y.parent = 222
        y = 111
        y.left = 333
        y.right = 444

        b.parent = 111
        b = 333
        b.left = 956
        b.right = 256

        =============
        b.parent = 222
        y.parent = 000
        x.parent = 111

        y.left = 222
        x.right = 333
        x.parent.child = 111
         */

        Node y = x.getRight();
        Node b = y.getLeft();

        if (b != null) {
            b.setParent(x);
        }

        y.setParent(x.getParent());

        x.setParent(y);

        x.setRight(b);
        y.setLeft(x);

        if (y.getParent() != null) {
            if (y.getParent().getLeft() != null && y.getParent().getLeft().equals(x)) {
                y.getParent().setLeft(y);
            } else {
                y.getParent().setRight(y);
            }
        } else {
            this.root = y;
        }

    }

    private void rightRotate(Node y) {
        /*
        y.parent = 000
        y = 111
        y.left = 222
        y.right = 333

        x.parent = 111
        x = 222
        x.left = 666
        x.right = 777

        b.parent = 222
        b = 777
        b.left = 999
        b.right = 121

        =============
        b.parent = 111
        x.parent = 000
        y.parent = 222

        x.right = 111
        y.left = 777
        y.parent.child = 222
         */
        Node x = y.getLeft();
        Node b = x.getRight();

        if (b != null) {
            b.setParent(y);
        }

        x.setParent(y.getParent());
        y.setParent(x);

        y.setLeft(b);
        x.setRight(y);

        if (x.getParent() != null) {
            if (x.getParent().getLeft() != null && x.getParent().getLeft().equals(y)) {
                x.getParent().setLeft(x);
            } else {
                x.getParent().setRight(x);
            }
        } else {
            this.root = x;
        }

    }

    private void reColor(Node node, boolean color) {
        node.setColor(color);
    }

    @Override
    public boolean delData(U label, T data) {
        Node node = getNode(label);
        boolean deleted = false;
        if (node != null) {
            node.getListData().delAt(node.getListData().indexOf(data));
            if (node.getListData().isEmpty()) {
                this.deleteNode((U) node.getLabel());
                deleted = true;
            }
        }
        return deleted;
    }

    @Override
    public boolean clear() {
        this.root = null;
        this.newNode = null;
        this.length = 0;

        return true;
    }

    public Node getNode(U label) {
        Node currentNode = this.root;
        Node targetNode = getRec(label, currentNode);
        return targetNode;
    }

    private Object getRec(U label, T data, Node currentNode) {

        if (currentNode == null) {
            return null;
        }

        if (label.equals(currentNode.getLabel())) {
            return currentNode.getListData().getAt(currentNode.getListData().indexOf(data));
        }

        Object something = getRec(label, data, currentNode.getLeft());

        if (something != null) {
            return something;
        }

        return getRec(label, data, currentNode.getRight());
    }

    private Node getRec(U label, Node currentNode) {

        if (currentNode == null) {
            return null;
        }

        if (label.equals(currentNode.getLabel())) {
            return currentNode;
        }

        Object something = getRec(label, currentNode.getLeft());

        if (something != null) {
            return (Node) something;
        }

        return getRec(label, currentNode.getRight());

    }

    //==================DELETE
    @Override
    public boolean deleteNode(U label) {

        Node node = getNode(label);
        boolean deleted = false;

        if (node == null) {
            return deleted;
        }

        Node successor = getLeftMostNode(node.right);
        Node predecessor = getRightMostNode(node.left);

        if (hasLeftChild(node) && hasRightChild(node)) {

            if (replaceWithCessor(node, successor, predecessor) == true) {//if use successor
                if (isRedNode(successor)) {
                    removeFromTree(successor);
                    deleted = true;
                    decLength();
                } else {
                    //DB case
                    successor = this.relocateDeleteNodeForSuccessor(successor);
                    fixDB(successor);
                    removeFromTree(successor);
                    deleted = true;
                    decLength();
                }
            } else { //if use predecessor
                if (isRedNode(predecessor)) {
                    removeFromTree(predecessor);
                    deleted = true;
                    decLength();
                } else {
                    //DB case
                    predecessor = this.relocateDeleteNodeForPredecessor(predecessor);
                    fixDB(predecessor);
                    removeFromTree(predecessor);
                    deleted = true;
                    decLength();
                }
            }

        } else if (hasLeftChild(node) || hasRightChild(node)) {

            if (replaceWithCessor(node, successor, predecessor) == true) {//if use successor
                if (isRedNode(successor)) {
                    removeFromTree(successor);
                    deleted = true;
                } else {
                    //DB case
                    successor = this.relocateDeleteNodeForSuccessor(successor);
                    fixDB(successor);
                    removeFromTree(successor);
                    deleted = true;
                }
            } else { //if use predecessor
                if (isRedNode(predecessor)) {
                    removeFromTree(predecessor);
                    deleted = true;
                } else {
                    //DB case
                    predecessor = relocateDeleteNodeForPredecessor(predecessor);
                    fixDB(predecessor);
                    removeFromTree(predecessor);
                    deleted = true;
                }
            }

        } else {
            //case 1
            if (isRedNode(node)) {
                removeFromTree(node);
                deleted = true;
            } else {
                //DB case
                fixDB(node);
                removeFromTree(node);
                deleted = true;
            }
        }

        return deleted;
    }

    private Node relocateDeleteNodeForPredecessor(Node predecessor) {
        if (hasChild(predecessor)) {
            this.replace(predecessor, predecessor.left);
            return predecessor.left;
        } else {
            return predecessor;
        }

    }

    private Node relocateDeleteNodeForSuccessor(Node successor) {
        if (hasChild(successor)) {
            this.replace(successor, successor.right);
            return successor.right;
        } else {
            return successor;
        }
    }

    private void fixDB(Node node) {
        if (isRoot(node)) {
            this.root.color = false;
        } // if DB's sibling is black, & both its children are black (black also include null)
        else if (isRedNode(node)) {

            removeFromTree(node);

        } else if ((isRedNode(sibling(node)) == false && hasBothBlackChild(sibling(node)) == true)) {
            // Change Parent become black
            //  if parent already is black, then become DB
            //  if parent is red, then become black
            // Change sibling become red

            sibling(node).color = true;

            if (isRedNode(node.parent) == false) {
                // parent is now DB
                fixDB(node.parent);
            } else {
                node.parent.color = false;
            }

        } //if DB's sibling is red
        else if (isRedNode(sibling(node)) == true) {
            // analogy: parent calm sibling, parent become angry
            // swap color (parent & sibling)
            // left rotate
            // reapply cases
            swapColor(sibling(node), node.parent);

            if (isLeftChild(node)) {
                leftRotate(node.parent);
            } else {
                rightRotate(node.parent);
            }

            fixDB(node);
        } //case 5 have mirror cases
        //DB's sibling is black, sibling;s child who is far from DB is black, but near child to DB is red
        // below two case is mirror cases
        //  delete node is left; sibling's left is red; sibling's right is black
        else if (isRedNode(sibling(node)) == false && isLeftChild(node) == true && hasLeftRedRightBlack(sibling(node))) {
            swapColor(sibling(node), sibling(node).left);

            rightRotate(sibling(node));

            fixDB(node);
        } //  delete node is right; sibling's left is black; sibling's right is red
        else if (isRedNode(sibling(node)) == false && isRightChild(node) == true && hasLeftBlackRightRed(sibling(node))) {
            swapColor(sibling(node), sibling(node).right);

            leftRotate(sibling(node));

            fixDB(node);
        } //case 6: have mirror cases
        // if DB's sibling is black, far child is red
        // below two case is mirror cases
        //
        // delete node is left; DB's sibling is black; DB's sibling's right is red;
        else if (isRedNode(sibling(node)) == false && isLeftChild(node) == true && isRightChildRed(sibling(node))) {
            Node waitForReColor = sibling(node).right;
            swapColor(node.parent, sibling(node));
            leftRotate(node.parent);
            waitForReColor.color = false;
        } // delete node is right; DB's sibling is black; DB's sibling's left is red;
        else if (isRedNode(sibling(node)) == false && isRightChild(node) == true && isLeftChildRed(sibling(node))) {
            Node waitForReColor = sibling(node).left;

            swapColor(node.parent, sibling(node));

            rightRotate(node.parent);

            waitForReColor.color = false;
        }
    }

    private void swapColor(Node node, Node otherNode) {
        boolean parentColor = node.color;
        node.color = otherNode.color;
        otherNode.color = parentColor;
    }

    private boolean isLeftChild(Node node) {
        return node.parent.left == node;
    }

    private boolean isRightChild(Node node) {
        return node.parent.right == node;
    }

    private boolean hasLeftRedRightBlack(Node node) {
        return isLeftChildRed(node) == true && isRightChildRed(node) == false;
    }

    private boolean hasLeftBlackRightRed(Node node) {
        return isLeftChildRed(node) == false && isRightChildRed(node) == true;
    }

    private boolean isLeftChildRed(Node node) {
        if (hasChild(node) == false) {
            return false;
        }

        if (hasLeftChild(node)) {
            if (isRedNode(node.left) == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isRightChildRed(Node node) {
        if (hasChild(node) == false) {
            return false;
        }

        if (hasRightChild(node)) {
            if (isRedNode(node.right) == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean hasBothBlackChild(Node node) {
        if (hasChild(node) == false) {
            return true;
        }
        boolean leftIsBlack = true;
        boolean rightIsBlack = true;

        if (hasLeftChild(node)) {
            if (isRedNode(node.left) == false) {
                leftIsBlack = true;
            } else {
                leftIsBlack = false;
            }
        } else {
            leftIsBlack = true;
        }

        if (hasRightChild(node)) {
            if (isRedNode(node.right) == false) {
                rightIsBlack = true;
            } else {
                rightIsBlack = false;
            }
        } else {
            rightIsBlack = true;
        }

        if (leftIsBlack == true && rightIsBlack == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasSuccessor(Node node) {
        return getLeftMostNode(node.right) != null;
    }

    private boolean hasPredecessor(Node node) {
        return getRightMostNode(node.left) != null;
    }

    private Node getLeftMostNode(Node node) {
        if (node == null) {
            return null;
        }
        if (hasChild(node) == false) {
            return node;
        }

        while (hasLeftChild(node)) {
            node = node.left;
        }

        return node;
    }

    private Node getRightMostNode(Node node) {
        if (node == null) {
            return null;
        }
        if (hasChild(node) == false) {
            return node;
        }

        while (hasRightChild(node)) {
            node = node.right;
        }

        return node;
    }

    private void replaceWithSuccessor(Node oldNode, Node newNode) {
        if (hasSuccessor(oldNode)) {
            replace(oldNode, newNode);
        }
    }

    private void replaceWithPredecessor(Node oldNode, Node newNode) {
        if (hasPredecessor(oldNode)) {
            replace(oldNode, newNode);
        }
    }

    // decide use predecessor or successor to replace node need to delete
    // true = use successor; false = use predecessor;
    // Condition: before use this method, the node wait for replace must have at least one child
    //original method
    private boolean replaceWithCessor(Node node, Node successor, Node predecessor) {
        if (hasLeftChild(node) && hasRightChild(node)) {
            replaceWithPredecessor(node, predecessor);
            return false;
        } else if (hasLeftChild(node) || hasRightChild(node)) {
            if (hasLeftChild(node)) {
                replaceWithPredecessor(node, predecessor);
                return false;
            } else {
                replaceWithSuccessor(node, successor);
                return true;
            }
        }

        return false;
    }

    private void replace(Node oldNode, Node newNode) {

        oldNode.setLabel(newNode.getLabel());

    }

    private void removeFromTree(Node node) {
        if (hasLeftChild(node) == false && hasRightChild(node) == false && hasParent(node) == true) {
            if (isOnLeft(node)) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    private boolean isRoot(Node node) {
        return this.root == node;
    }

    private boolean isRedNode(Node node) {
        return node != null && node.color == true;
    }

    private boolean hasParent(Node node) {
        return node.parent != null;
    }

    private boolean hasChild(Node node) {
        return hasLeftChild(node) || hasRightChild(node);
    }

    private boolean hasLeftChild(Node node) {
        return node.left != null;
    }

    private boolean hasRightChild(Node node) {
        return node.right != null;
    }

    private Node sibling(Node node) {
        if (isRoot(node)) {
            return null;
        }

        if (isOnLeft(node)) {
            return node.parent.right;
        } else {
            return node.parent.left;
        }

    }

    private boolean isOnLeft(Node node) {
        //before use this must check is root or not
        return node.parent.left == node;
    }

    @Override
    public boolean updateData(U label, T data) {
        Node node = getNode(label);
        boolean updated = false;
        if (node != null) {
            if (node.getListData().indexOf(data) != -1) {
                node.getListData().replaceAt(data, node.getListData().indexOf(data));
                updated = true;
            }
        }

        return updated;
    }

    @Override
    public ListInterface<T> getMin() {
        return getLeftMostNode(this.root).getListData();
    }

    @Override
    public ListInterface<T> getMax() {
        return getRightMostNode(this.root).getListData();
    }

    @Override
    public boolean containsData(U label, T data) {
        Node targetNode = getNode(label);

        if (targetNode != null) {
            if (targetNode.getListData().indexOf(data) != -1) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    @Override
    public boolean containsNode(U label) {
        Node targetNode = getNode(label);

        if (targetNode != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T[] getAllListInArray(T[] array) {
        DoublyLinkedList<T> allList = new DoublyLinkedList();
        getAllList(this.root, allList);
        allList.quickSort();

        return (T[]) allList.toArray(array);
    }

    public DoublyLinkedList<T> getAllList() {
        DoublyLinkedList<T> allList = new DoublyLinkedList();
        getAllList(this.root, allList);
        allList.quickSort();

        return allList;
    }

    public void getAllList(Node currentNode, DoublyLinkedList<T> allList) {
        if (currentNode != null) {
            getAllList(currentNode.left, allList);

            allList.joinLast(currentNode.getListData());

            getAllList(currentNode.right, allList);
        }
    }

    public boolean contains(T element) {
        return get(element) != null;
    }

    @Override
    public T get(T data) {

        if (root == null) {
            return null;
        }

        boolean[] founded = new boolean[1];
        founded[0] = false;
        T result = get(data, root, founded);

        if (founded[0] == false) {
            return null;
        } else {
            return result;
        }

        /* Direct solution, but not efficiency
        DoublyLinkedList<T> allList = getAllList();
        return allList.getAt(allList.indexOf(data));
         */
    }

    public T get(T data, Node currentNode, boolean[] founded) {
        if (currentNode.getListData().contains(data)) {
            founded[0] = true;
            return (T) currentNode.getListData().getAt(currentNode.getListData().indexOf(data));
        } else {
            if (currentNode.left != null && founded[0] == false) {
                data = get(data, currentNode.left, founded);
                if (founded[0] == true) {
                    return data;
                }
            }

            if (currentNode.right != null && founded[0] == false) {
                data = get(data, currentNode.right, founded);
                if (founded[0] == true) {
                    return data;
                }
            }
        }

        return data;
    }

    // =====================DELETE
    private class Node<U extends Comparable<? super U>, T extends Comparable<T>> {

        private Node parent;
        private Node left;
        private U label;
        private ListInterface<T> listData;
        private Node right;
        // true = red; false = black;
        private boolean color;

        public Node(U label) {
            this.parent = null;
            this.label = label;
            this.listData = new DoublyLinkedList();
            this.left = null;
            this.right = null;
            this.color = true;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public U getLabel() {
            return label;
        }

        public ListInterface<T> getListData() {
            return listData;
        }

        public void setListData(ListInterface<T> elements) {
            this.listData = elements;
        }

        public void setLabel(U label) {
            this.label = label;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }
    }

}
