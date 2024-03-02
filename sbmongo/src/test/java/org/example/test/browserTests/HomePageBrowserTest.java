package org.example.test.browserTests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomePageBrowserTest {

    private static HtmlUnitDriver htmlUnitDriver;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        htmlUnitDriver = new HtmlUnitDriver();
        htmlUnitDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void teardown() {
        htmlUnitDriver.quit();
    }

    @Test
    void testHomePageHtml(){
        String homePageUrl = "http://localhost:" + port;
        htmlUnitDriver.get(homePageUrl);
        assertEquals("Taco Cloud",htmlUnitDriver.getTitle());
    }
}
