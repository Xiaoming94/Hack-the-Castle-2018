package backend.graph;

public class Edge {
    private String destination;
    private String current;

    Edge(String dest, String curr) {
        destination = dest;
        current = curr;
    }

    public String edgeToString() {
        return this.current + "-->" + this.destination;
    }

    public String getDestination() {
        return destination;
    }

    public String getCurrent() {
        return current;
    }

    public int hashCode(){
        return destination.hashCode() + current.hashCode();
    }

}
