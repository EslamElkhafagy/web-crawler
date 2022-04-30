package com.boost.webcrawler.controller;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.boost.webcrawler.api.CrawlerApi;
import com.boost.webcrawler.exception.BusinessExcption;
import com.boost.webcrawler.model.ApiModelCrawlerNode;
import com.boost.webcrawler.service.WebCrawlerProcessor;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Optional;


@RestController
@Slf4j
public class CrawlerApiController implements CrawlerApi {

    private final WebCrawlerProcessor webCrawlerService;

    @Value("${webcrawler.default.depth}")
    private int defaultDepth;

    @Value("${webcrawler.default.breadth}")
    private int defaultBreadth;


    public CrawlerApiController(WebCrawlerProcessor webCrawlerService) {
        super();
        this.webCrawlerService = webCrawlerService;
    }

    @Override
    public ResponseEntity<ApiModelCrawlerNode> getWebCrawler(
            @NotNull @ApiParam(value = "Query param for 'url'", required = true) @Valid @RequestParam(value = "url", required = true) String url,
            @ApiParam(value = "Query param for crawling depth level") @Valid @RequestParam(value = "depth", required = false) Integer depth,
            @ApiParam(value = "Query param for crawling breadth level") @Valid @RequestParam(value = "breadth", required = false) Integer breadth) throws BusinessExcption, URISyntaxException {

        log.debug("Entering getWebCrawler() with URL [{}]", url);

        final int effectiveDepth = Optional.ofNullable(depth).orElse(defaultDepth);

        final int effectiveBreadth = Optional.ofNullable(breadth).orElse(defaultBreadth);

        ApiModelCrawlerNode result = webCrawlerService.getWebCrawlerResult(url, effectiveDepth, effectiveBreadth);

        log.debug("Exiting getWebCrawler() for URL [{}]", url);

        return ResponseEntity.ok(result);
    }


}
