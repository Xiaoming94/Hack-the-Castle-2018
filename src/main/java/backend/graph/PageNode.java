package backend.graph;

import java.util.ArrayList;
import java.util.List;
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

    public List<String> getNeighbours(){
        if(this.neighbourPage.isEmpty()){
            neighbourPage.addAll(findSomeNeighbours());
        }
        return this.neighbourPage;
    }

    private List<String> findSomeNeighbours() {
        List<String> neighboursFound = wiki.getLinksOnPage(true, this.pageURL);
        List<String> reducedNeighbours = new ArrayList<>();
            for (int i = 0; i < (Math.random() * 2); i++) {
                reducedNeighbours.add(neighboursFound.get((int) (Math.random() * neighboursFound.size())));
        }
        return reducedNeighbours;
    }

    public int hashcode() {
        return pageURL.hashCode() + neighbourPage.hashCode();
    }

}
