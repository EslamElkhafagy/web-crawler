package com.boost.webcrawler.service;

import com.boost.webcrawler.exception.BusinessExcption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = WebCrawlerProcessorTest.class)
@RunWith(SpringRunner.class)
public class DownloadPageContentServiceTest {

    @InjectMocks
    DownloadPageContentService downloadPageContentService;

    @Spy
    OutboundGatewayService outboundGatewayService;


    @Test
    public void gdownloadPageContent_Url_Content() throws BusinessExcption, URISyntaxException {

        downloadPageContentService.downloadPageContents("https://monzo.com");
    }
}
