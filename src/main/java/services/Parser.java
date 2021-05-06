package services;

import data.Page;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {
    private final ObjectMapper jsonParser;

    public Parser(ObjectMapper jsonParser) {
        this.jsonParser = jsonParser;
    }

    public List<String> getLinksOnPage(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.err.println("Error connecting to url '" + url + "': " + e.getMessage());
            return Collections.emptyList();
        }
        return document.select("a[href]")
                .stream()
                .map(link -> link.attr("abs:href"))
                .collect(Collectors.toList());
    }

    public String parsePage(Page rootPage) {
        try {
            return jsonParser
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(rootPage);
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing page: '" + rootPage.url + "': : " + e.getMessage());
            return "";
        }
    }
}
