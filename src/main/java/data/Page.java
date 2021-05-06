package data;

import java.util.ArrayList;
import java.util.List;

public class Page {
    public final String url;
    public final List<Page> links;

    public Page(String url) {
        this.url = url;
        this.links = new ArrayList<>();
    }

    public void addLinks(Page page) {
        links.add(page);
    }
}
