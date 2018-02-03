package backend.graph;

public class Edge {
    private String destination;
    private String current;

    Edge(String dest, String curr) {
        destination = dest;
        current = curr;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return current;
    }

}
