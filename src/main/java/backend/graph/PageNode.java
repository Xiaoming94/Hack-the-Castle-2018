package backend.graph;

import java.util.ArrayList;
import java.util.List;
import fastily.jwiki.core.Wiki;

public class PageNode {
    private Wiki wiki = new Wiki("en.wikipedia.org");
    private final String pageURL;
    private final List<String> neighbourPage = new ArrayList();

    public PageNode(String pageURL){
        this.pageURL = pageURL;
    }

    public String getPageURL() {
        return pageURL;
    }

    public List<String> getNeighbours(){
        if(this.neighbourPage.isEmpty()){
            neighbourPage.addAll(findNeighbours());
        }
        return this.neighbourPage;
    }

    private List<String> findNeighbours() {
        List<String> neighboursFound = wiki.getLinksOnPage(this.pageURL);
        return neighboursFound;
    }

}
