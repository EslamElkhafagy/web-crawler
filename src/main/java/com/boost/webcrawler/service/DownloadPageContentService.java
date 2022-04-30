package com.boost.webcrawler.service;



import java.util.Optional;
import java.util.stream.Collectors;

import com.boost.webcrawler.dao.DomainRepository;
import com.boost.webcrawler.model.Domain;
import com.boost.webcrawler.model.URLContentInfo;
import com.boost.webcrawler.utils.DomainUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * Service to fetch Page contents.
 *
 *
 */
@Service
@Slf4j
public class DownloadPageContentService {

    @Autowired
    DomainRepository domainRepository;
    private final OutboundGatewayService outboundGatewayService;

    //Prefer constructor injection
    public DownloadPageContentService(OutboundGatewayService outboundGatewayService) {
        super();
        this.outboundGatewayService = outboundGatewayService;
    }



    /**
     *
     * Cacheable service returns {@link URLContentInfo}
     *
     */

    @Cacheable(cacheNames = "urlContentsCache", key = "#url")
    public URLContentInfo downloadPageContents(String url) {

        log.debug("Entering downloadPageContents() with URL [{}]", url);


        try {
            URLContentInfo urlContent = new URLContentInfo();

            urlContent.setUrl(url);

            //Don't wait for more than 5 seconds
            Document document = Jsoup.parse(outboundGatewayService.downloadHtmlContents(url));

            //links
            Elements linksOnPage = document.select("a[href]");

            urlContent.setTitle(document.title());

            //Get absolute URL, Filter empty links
            String clearDomain= DomainUtils.getDomainName(url);
            log.info("clear domain from URL : "+clearDomain);
            saveOrUpdateDomainCount(clearDomain);
            String pattern="(^|^[^:]+:\\/\\/|[^\\.]+\\.)"+clearDomain+"([\\/a-zA-Z0-9\\-]*)*";
            urlContent.getLinkNodes()
                    .addAll(linksOnPage.stream().filter(a -> !StringUtils.isEmpty(a.attr("abs:href")))
                            .filter(a->a.attr("abs:href").matches(pattern))
                            .map(a -> a.attr("abs:href")).collect(Collectors.toList()));

            log.debug("Exiting downloadPageContents() for URL [{}]", url);

            return urlContent;

        } catch (Exception e) {
            URLContentInfo urlContent = new URLContentInfo();
            urlContent.setUrl(url);
            urlContent.setTitle("Exception occured and title as exception message maybe it's not complete URL should be like http/s://example.com " + e.getMessage());
            log.error("Exception while fetching page details", e);
            return urlContent;
        }

    }

    private  void saveOrUpdateDomainCount(String clearDomain){

        Optional<Domain> domain =domainRepository.findByName(clearDomain);
        if (!domain.isPresent()) {
         Domain newDomain=new Domain();
         newDomain.setCount(1);
         newDomain.setName(clearDomain);
            domainRepository.save(newDomain);
        }else{
            domainRepository.updateCounter(domain.get().getId());
        }
    }


}
