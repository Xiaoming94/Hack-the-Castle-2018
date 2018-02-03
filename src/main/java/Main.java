import backend.graph.Edge;
import backend.graph.LinkGraph;
import backend.graph.PageNode;
import fastily.jwiki.core.Wiki;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Wiki wiki = new Wiki("sv.wikipedia.org");
        String randUrl = wiki.getRandomPages(1).get(0);
        LinkGraph linkGraph = new LinkGraph();
        linkGraph.buildGraph(new PageNode(randUrl));
        ArrayList<Edge> edges = linkGraph.getVisitedEdges();
        System.out.println("Begin at: " + edges.get(0).getCurrent() + " and find: " + edges.get(edges.size() - 1).getDestination());
    }

}
