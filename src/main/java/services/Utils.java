package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static String DOMAIN_REGEX = ".+\\/\\/(?:[A-Za-z0-9-]+\\.)*([A-Za-z0-9-]+\\.[A-Za-z0-9-]+)\\/*.*";

    public static String getDomainName(String urlStr) {
        Pattern p = Pattern.compile(DOMAIN_REGEX);
        Matcher m = p.matcher(urlStr);
        if (m.matches()) {
            return m.group(1);
        } else {
            System.err.println("Error Url is not parseable:  '" + urlStr + "': ");
            return "";
        }
    }
}
