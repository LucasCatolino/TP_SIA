package main;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Node root;

    // TODO: pass config params as parameters...
    public Tree(String rootString) {
        this.root = new Node();
        this.root.tablero = new Tablero(rootString);
        this.root.children = new ArrayList<Node>();
        this.root.parent = null;
    }

    public static class Node {
        private int hSelected;
        private int depth;
        private Tablero tablero;
        private Node parent;
        private List<Node> children;

        public Node() {
            parent = null;
        }

        public Node(Node parent) {
            this.parent = parent;
        }

        public double getHeur() {
            switch (hSelected) {
                case 0:
                    return tablero.getEuclidean();
                case 1:
                    return tablero.getManhattan();
                case 2:
                default:
                    return tablero.getInvalid();
            }
        }

        public double getF() {
            return 1 + getHeur(); // cost of moving to a next state is always 1
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int newDepth) {
            depth = newDepth;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node newParent) {
            parent = newParent;
        }

        public Tablero getTablero() {
            return tablero;
        }

        public void setTablero(Tablero newTablero) {
            tablero = newTablero;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void addChild(Node newChild) {
            children.add(newChild);
        }
    }
}