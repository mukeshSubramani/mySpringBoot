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
class OrderPageBrowserTests {

    private static HtmlUnitDriver htmlUnitDriver;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        htmlUnitDriver = new HtmlUnitDriver();
        htmlUnitDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    static void finish(){
        htmlUnitDriver.quit();
    }

    @Test
    void testOrderPage(){
        String url = "http://localhost:" + port;
        htmlUnitDriver.get(url);

        assertEquals("Taco Cloud", htmlUnitDriver.getTitle());
    }
}
