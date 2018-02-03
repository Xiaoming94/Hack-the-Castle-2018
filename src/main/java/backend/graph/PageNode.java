package backend.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import fastily.jwiki.core.Wiki;

public class PageNode {
    private Wiki wiki = new Wiki("sv.wikipedia.org");
    private final String pageURL;
    private final List<String> neighbourPage = new ArrayList();

    public PageNode(String pageURL){
        this.pageURL = pageURL;
    }

    public String getPageURL() {
        return pageURL;
    }

    public List<String> getSomeNeighbours() {
        if(this.neighbourPage.isEmpty()){
            neighbourPage.addAll(findSomeNeighbours());
        }
        return this.neighbourPage;
    }

    public List<String> getAllNeighbours() {
        return wiki.getLinksOnPage(true, this.pageURL);
    }

    /*
        Hardcoded limit so it doesn't take ages to generate a route, will change if we can get bot to accept input

        May or may not be a bit unstable
     */
    private List<String> findSomeNeighbours() {
        List<String> neighboursFound = getAllNeighbours();
        List<String> reducedNeighbours = new ArrayList<>();
            for (int i = 0; i < (ThreadLocalRandom.current().nextInt(1, 3 + 1)); i++) {
                reducedNeighbours.add(neighboursFound.get((ThreadLocalRandom.current().nextInt(0, neighboursFound.size()))));
        }
        return reducedNeighbours;
    }

    /*
        For filtering out duplicates, only used in debugging for the moment
     */
    public int hashcode() {
        return pageURL.hashCode() + neighbourPage.hashCode();
    }

}
