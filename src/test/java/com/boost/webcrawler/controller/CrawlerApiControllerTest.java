package com.boost.webcrawler.controller;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.boost.webcrawler.model.ApiModelCrawlerNode;
import com.boost.webcrawler.service.WebCrawlerProcessor;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

@SpringBootTest(classes = CrawlerApiControllerTest.class)
@RunWith(SpringRunner.class)
public class CrawlerApiControllerTest {
    private final String BASE_URL="/crawl";
    @InjectMocks
    private CrawlerApiController crawlerApiController;

    @Mock
    WebCrawlerProcessor webCrawlerService;

    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(crawlerApiController).build();
    }

    @Test
    public void getCrawlerPage_url_listNodes() throws Exception {
        Mockito.doReturn(getApiModelCrawlerNode()).when(webCrawlerService).getWebCrawlerResult("url",1,1);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"?url=https://monzo.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    private ApiModelCrawlerNode getApiModelCrawlerNode(){
        ApiModelCrawlerNode node = new ApiModelCrawlerNode();
        node.setTitle("test object");
        node.setUrl("https://test.com");
        return node;
    }
}
