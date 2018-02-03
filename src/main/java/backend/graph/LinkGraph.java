package backend.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

public class LinkGraph {
    private Queue<Edge> edgeQueue = new ArrayDeque<>();
    private ArrayList<Edge> visitedEdges = new ArrayList<>();
    private HashSet<PageNode> pageNodes = new HashSet<>();
    public LinkGraph() {
    }

    public HashSet<PageNode> getPageNodes() {
        return pageNodes;
    }

    public ArrayList<Edge> getVisitedEdges() {
        return visitedEdges;
    }

    private void addEdges(PageNode node) {
        for (String pn : node.getNeighbours()) {
                edgeQueue.add(new Edge(pn, node.getPageURL()));
        }
    }

    public boolean buildGraph(PageNode node) {
        while (pageNodes.size() < 10) {
            if (edgeQueue.isEmpty() && !visitedEdges.isEmpty()) {
                return true;
            } else {
                addEdges(node);
                Edge nextEdge = edgeQueue.poll();
                visitedEdges.add(nextEdge);
                pageNodes.add(node);
                System.out.println(nextEdge.edgeToString());
                return buildGraph(new PageNode(nextEdge.getDestination()));
            }
        }
        return true;
    }


}

