package services;

import data.Page;
import kotlin.text.Charsets;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class ParserTest {
    @Test
    void getLinksOnPageTest() {
        /**
         * Note:
         * This methods runs only Jsoup code
         */
    }

    @Test
    void parsePageTest() {
        // Given
        Page rootPage = new Page("main page");
        Page secondPage = new Page("second page");
        secondPage.addLinks(new Page("fourth page"));
        rootPage.addLinks(secondPage);
        rootPage.addLinks(new Page("third page"));

        Parser parser = new Parser(new ObjectMapper());
        String expectedParsedAsJsonStr = readFileFromClasspath("parsedJsonStr.json");

        // When
        String pageParsedAsJsonStr = parser.parsePage(rootPage);

        // Verify
        assertThat(pageParsedAsJsonStr).isEqualTo(expectedParsedAsJsonStr);
    }

    private String readFileFromClasspath(String fileName) {
        try (
                InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
                InputStreamReader isr = new InputStreamReader(is, Charsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)) {
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}