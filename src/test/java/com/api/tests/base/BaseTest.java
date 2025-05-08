package com.api.tests.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    @BeforeAll
    public static void setup() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("src/test/resources/config.properties"));
        RestAssured.baseURI = props.getProperty("base.url");
    }
}
