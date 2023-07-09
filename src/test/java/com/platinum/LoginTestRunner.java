package com.platinum;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:login.feature",
        glue = "com.platinum"
)
public class LoginTestRunner {
}
