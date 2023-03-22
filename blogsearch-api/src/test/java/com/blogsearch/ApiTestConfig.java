package com.blogsearch;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = "com.blogsearch.api")
public class ApiTestConfig {

}
