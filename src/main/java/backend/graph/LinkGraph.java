package backend.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class LinkGraph {
    private Queue<Edge> edgeQueue = new ArrayDeque<>();
    private ArrayList<Edge> visitedEdges = new ArrayList<>();
    private ArrayList<PageNode> pageNodes = new ArrayList<>();
    private String finalDestination;

    public Queue<Edge> getEdgeQueue() {
        return edgeQueue;
    }

    public ArrayList<Edge> getVisitedEdges() {
        return visitedEdges;
    }

    public ArrayList<PageNode> getPageNodes() {
        return pageNodes;
    }

    LinkGraph(String dest) {
        finalDestination = dest;
    }

    public void moveEdge(Edge e) {
        visitedEdges.add(e);
        edgeQueue.remove(e);
    }

    public boolean isDest(PageNode node) {
        for (String str : node.getNeighbours()) {
            if (str == finalDestination) {
                return true;
            }
        }
        return false;
    }

    public void addEdges(PageNode node) {
        for (String str : node.getNeighbours()) {
            edgeQueue.add(new Edge(node.getPageURL(), str));
        }
    }

    public void addNode(Edge e) {
        pageNodes.add(new PageNode(e.getDestination()));
    }



}

