package com.migros.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features", 
    glue = "com.migros.steps",               
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    tags = "@Test"                     
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
