/*
 * Class Name: MyRunner
 * Author: Amit Singh
 * Description: Starting point of execution. Contains runners and cucumber annotations
 * Date Created: 26-Feb-2020
 */
package framework;

import com.vimalselvam.cucumber.listener.ExtentProperties;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"com.cucumber.listener.ExtentCucumberFormatter:"},
        features = "src\\test\\resources\\features",
        glue={"StepDefinitions"},
        strict = true,
        monochrome = true
)

public class MyRunner   {

    public static HashMap<String, ArrayList<String>> allObjects;
    public static WebDriver webDriver;
    protected static String sReportPath;
    private TestNGCucumberRunner testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

    @BeforeClass
    public static void setUp()  {
        //Load config variables
        Config config = new Config();

        //Fetch all objects from repository
        allObjects = Support.getAllObjects();

        //Initialize Webdriver
        webDriver= Support.setBrowser(Config.getProperty("browser"));

        //Set report path
        sReportPath = Report.createReportFolder();

        //Initialize ExtentReports
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath(sReportPath + "\\executionReport.html");
        extentProperties.setProjectName("Swedbank Test Task");
    }

    @Test(dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass
    public static void tearDown()   {

        try {
            Driver.quit();

            Reporter.loadXMLConfig(new File(Config.getReportConfigPath()));
            Reporter.setSystemInfo("Tester Name", System.getProperty("user.name"));
            Reporter.setSystemInfo("OS", "Mac OS");
            Reporter.assignAuthor(System.getProperty("user.name"));
        }
        catch (NoSuchMethodError ex)    {
            System.out.println(ex);
        }
    }
}