import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Frontera {

    public enum CompareTypes {
        BPA,
        BPP,
        BPPV,
        HEUR,
        ASTAR,
    }

    private CompareTypes compareType;
    private List<Tree.Node> nodes;

    public Frontera(CompareTypes compareType) {
        this.compareType = compareType;
        nodes = new ArrayList<Tree.Node>();
    }

    public void sort() {
        switch (compareType) {
            case BPA:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getDepth)); // bfs
                break;
            case BPP: // dfs & its variable version
            case BPPV:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getDepth).reversed());
                break;
            case HEUR:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getHeur)); // heuristicas
                break;
            case ASTAR:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getF)); // a*
                break;

        }
    }

    public Tree.Node getFirst() {
        if (nodes.size() > 0) {
            return nodes.remove(0);
        }
        return null;
    }

}
