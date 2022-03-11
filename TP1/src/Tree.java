import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Node root;

    public Tree(String rootString) {
        this.root = new Node();
        this.root.tablero = new Tablero(rootString);
        this.root.children = new ArrayList<Node>();
        this.root.parent = null;
    }

    public static class Node {
        private int depth;
        private Tablero tablero;
        private Node parent;
        private List<Node> children;

        public int getDepth() {
            return depth;
        }

        public double getHeur() {
            return tablero.getH();
        }

        public double getF() {
            return 1 + getHeur(); // cost of moving to a next state is always 1
        }
    }
}