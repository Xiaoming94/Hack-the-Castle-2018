import backend.graph.Edge;
import backend.graph.LinkGraph;
import backend.graph.PageNode;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LinkGraph linkGraph = new LinkGraph();
        linkGraph.buildGraph(new PageNode("Linux"));
        ArrayList<Edge> edges = linkGraph.getVisitedEdges();
        System.out.println("Begin at: " + edges.get(0).getCurrent() + " and find: " + edges.get(edges.size() - 1).getDestination());
    }
}
