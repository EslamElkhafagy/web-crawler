package com.boost.webcrawler.utils;

import com.boost.webcrawler.exception.BusinessExcption;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class DomainUtils {

    private static Pattern pDomainNameOnly;
    private static final String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

    static {
        pDomainNameOnly = Pattern.compile(DOMAIN_NAME_PATTERN);
    }

    public static boolean isValidDomainName(String domainName) throws URISyntaxException, BusinessExcption {
        return pDomainNameOnly.matcher(getDomainName(domainName)).find();
    }

    public static String getDomainName(String url) throws URISyntaxException, BusinessExcption {
        URI uri = new URI(url);
        String domain = uri.getHost();
        if(domain==null)
            throw new BusinessExcption("invalid domain URL, maybe it's not complete URL should be like http/s://example.com", HttpStatus.BAD_REQUEST);
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
