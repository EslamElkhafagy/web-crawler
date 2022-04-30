package com.boost.webcrawler.api;


import com.boost.webcrawler.exception.BusinessExcption;
import com.boost.webcrawler.exception.ErrorResponse;
import com.boost.webcrawler.model.ApiModelCrawlerNode;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.*;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;

public interface CrawlerApi {

    @ApiOperation(value = "", nickname = "getWebCrawler", notes = "Get Web Crawler details by url", response = ApiModelCrawlerNode.class, tags={ "Crawler", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ApiModelCrawlerNode.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
    @RequestMapping(value = "/crawl",
            produces = { "application/json" },
            consumes = { "*/*" },
            method = RequestMethod.GET)
     ResponseEntity<ApiModelCrawlerNode> getWebCrawler(@NotNull @ApiParam(value = "Query param for 'url'", required = true)
                                                              @Validated @RequestParam(value = "url", required = true) String url,
                                                              @ApiParam(value = "Query param for crawling depth level") @Validated @RequestParam(value = "depth", required = false) Integer depth,@ApiParam(value = "Query param for crawling breadth level") @Validated @RequestParam(value = "breadth", required = false) Integer breadth) throws BusinessExcption, URISyntaxException;

}
