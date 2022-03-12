package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.Config.SearchMethods;

public class Frontera {

    private SearchMethods method;
    private List<Tree.Node> nodes;

    public Frontera(SearchMethods method) {
        this.method = method;
        nodes = new ArrayList<Tree.Node>();
    }

    public void sort() {
        switch (method) {
            case BPA:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getDepth)); // bfs
                break;
            case BPP: // dfs & its variable version
            case BPPV:
                Collections.sort(nodes, Comparator.comparing(Tree.Node::getDepth, Comparator.reverseOrder()));
                break;
            case HEURLOCAL: // TODO: is it necessary for local to sort?
            case HEURGLOBAL:
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

    public void add(Tree.Node node) {
        nodes.add(node);
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

}
