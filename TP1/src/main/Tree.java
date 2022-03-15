package main;

import java.util.ArrayList;
import java.util.List;

import main.Config.Heuristics;

public class Tree {
    private Node root;

    // TODO: pass config params as parameters...
    public Tree(String rootString) {
        this.root = new Node();
        this.root.tablero = new Tablero(rootString);
        this.root.children = new ArrayList<Node>();
        this.root.parent = null;
    }

    public Node getRoot() {
        return root;
    }

    public static class Node {
        private Heuristics hSelected;
        private int depth;
        private double costH;
        private Tablero tablero;
        private Node parent;
        private List<Node> children;

        @Override
        public String toString() {
            return String.format("Node[h: %s, depth: %d, costH: %f, tablero: %s]", hSelected, depth, costH,
                    tablero.getEstado());
        }

        public Node() {
            children = new ArrayList<>();
            parent = null;
        }

        public Node(Node parent) {
            children = new ArrayList<>();
            this.parent = parent;
        }

        public Node(String rootString) {
            children = new ArrayList<>();
            this.tablero = new Tablero(rootString);
        }

        public Node(String rootString, int depth) {
            children = new ArrayList<>();
            this.tablero = new Tablero(rootString);
            this.depth = depth;
        }

        public Node(String rootString, Heuristics newHeuristic, double costH) {
            children = new ArrayList<>();
            this.tablero = new Tablero(rootString);
            this.hSelected = newHeuristic;
            this.costH = costH;
        }

        public boolean goalReached() {
            return tablero.goalReached();
        }

        public double getHeur() {
            switch (hSelected) {
                case EUCLIDEAN:
                    return tablero.getEuclidean();
                case MANHATTAN:
                    return tablero.getManhattan();
                case INVALID:
                default:
                    return tablero.getInvalid();
            }
        }

        public double getF() {
            return (this.depth + getHeur()); // cost of moving to a next state is always 1
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

        public void setHeuristic(Heuristics newHeuristic) {
            this.hSelected = newHeuristic;
        }

        public void setHeuristicCost() {
            this.costH = getHeur();
        }

        // Para pruebas
        public void printTablero() {
            this.tablero.printTablero();
        }

        public void printPath() {
            if (this.parent != null) {
                this.parent.printPath();
            }

            printTablero();
            System.out.println("^^^Step " + depth + "^^^\n");
        }

    }
}