import data.Page;
import services.Parser;
import services.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BasicWebCrawler {
    private HashSet<String> visitedUrls;
    private final Parser parser;
    private final int maxDeep;

    public BasicWebCrawler(Parser parser, int maxDeep) {
        this.parser = parser;
        this.maxDeep = maxDeep;
        visitedUrls = new HashSet<String>();
    }

    public void getPageLinks(Page rootPage, String mainDomainName, int currentDeep) {
        if (currentDeep >= maxDeep) return;
        if (!visitedUrls.contains(rootPage.url)) {
            visitedUrls.add(rootPage.url);
            List<String> links = parser.getLinksOnPage(rootPage.url);
            for (String link : links) {
                String domainName = Utils.getDomainName(link);
                if (mainDomainName.equals(domainName)) {
                    Page childPage = new Page(link);
                    rootPage.addLinks(childPage);
                    getPageLinks(childPage, mainDomainName, currentDeep + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        String mainPage = args[0];
        String mainDomainName = Utils.getDomainName(mainPage);
        Page rootPage = new Page(mainPage);
        Parser parser = new Parser(new ObjectMapper());
        int maxDeep = Integer.parseInt(args[2]);
        new BasicWebCrawler(parser, maxDeep).getPageLinks(rootPage, mainDomainName, 0);

        String outputPathName = args[1];
        String pageParsedAsJsonStr = parser.parsePage(rootPage);
        try {
            FileWriter fileWriter = new FileWriter(outputPathName);
            fileWriter.write(pageParsedAsJsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
