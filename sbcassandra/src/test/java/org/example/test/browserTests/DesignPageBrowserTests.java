package org.example.test.browserTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DesignPageBrowserTests {

    private static HtmlUnitDriver htmlUnitDriver;
    private String url;

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate rest;


    @BeforeEach
    void setUp(){
        htmlUnitDriver = new HtmlUnitDriver();
        htmlUnitDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        url = "http://localhost:" + port;
    }

    @AfterAll
    static void end(){
        htmlUnitDriver.quit();
    }

    @Test
    void testDesignPage(){
        htmlUnitDriver.get(url);
        assertEquals("Taco Cloud", htmlUnitDriver.getTitle());
    }
}
