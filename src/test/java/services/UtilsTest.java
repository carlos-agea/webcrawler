package services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UtilsTest {

    private static Stream<Arguments> urlStringTestsCases() {
        return Stream.of(
                Arguments.of("https://github.com/", "github.com"),
                Arguments.of("http://carlos-agea123.com/blog/2020-01-14_auto/", "carlos-agea123.com"),
                Arguments.of("https://github.com/carlos.agea", "github.com"),
                Arguments.of("https://docs.github.com/articles/supported-browsers", "github.com")
        );
    }

    @ParameterizedTest
    @MethodSource("urlStringTestsCases")
    void getDomainNameTest(String urlStr, String expectedDomainName) {
        // When
        String domainName = Utils.getDomainName(urlStr);

        // Verify
        assertThat(domainName).isEqualTo(expectedDomainName);
    }
}