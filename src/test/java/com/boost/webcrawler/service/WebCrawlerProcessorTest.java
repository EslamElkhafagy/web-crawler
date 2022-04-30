package com.boost.webcrawler.service;

import com.boost.webcrawler.exception.BusinessExcption;
import com.boost.webcrawler.model.ApiModelCrawlerNode;
import com.boost.webcrawler.model.URLContentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = WebCrawlerProcessorTest.class)
@RunWith(SpringRunner.class)
public class WebCrawlerProcessorTest {

    @InjectMocks
    WebCrawlerProcessor webCrawlerProcessor;

    @Mock
    DownloadPageContentService downloadPageContentService;

    @Test
    public void getWebCrawlerResult_UrlAndDepthAndBreadth_AllPages() throws BusinessExcption, URISyntaxException {

        Set<String> setUrls= new HashSet<>();
        setUrls.add("demo url");
        Mockito.doReturn(getURLContentInfo()).when(downloadPageContentService).downloadPageContents("url");
        Mockito.doReturn(setUrls).when(downloadPageContentService).applyTitleAndReturnLinks(getURLContentInfo(), getApiModelCrawlerNode(),2);

        webCrawlerProcessor.getWebCrawlerResult("https://monzo.com",2,2);
    }

private URLContentInfo getURLContentInfo(){
    URLContentInfo contentInfo= new URLContentInfo();
    contentInfo.setUrl("http");
    contentInfo.setTitle("title");
    Set<String> nodes= new HashSet<>();
    nodes.add("safa");
    contentInfo.setLinkNodes(nodes);
    return contentInfo;
}

    private ApiModelCrawlerNode getApiModelCrawlerNode(){
        ApiModelCrawlerNode node = new ApiModelCrawlerNode();
        node.setTitle("test object");
        node.setUrl("https://test.com");
        return node;
    }
}
