import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Frontera {

    private int compareType;
    private List<Tree.Node> nodes;

    public Frontera(int compareType) {
        this.compareType = compareType;
        nodes = new ArrayList<Tree.Node>();
    }

    public void sort() {
        switch (compareType) {
            case 0:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getDepth)); // bfs
            case 1:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getDepth).reversed()); // dfs
            case 2:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getHeur)); // heuristicas
            case 3:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getf)); // a*

        }
    }

    public Tree.Node getFirst() {
        if (nodes.size() > 0) {
            return nodes.remove(0);
        }
        return null;
    }

}
