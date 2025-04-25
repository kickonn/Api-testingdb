package tests;

import appmanager.*;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


@CucumberOptions(features = {
        "classpath:features"
//        "classpath:features/AdactinApp.feature",
//        "classpath:features/Api.feature",


},
        glue = "tests",
        plugin = {"pretty", "io.qameta.allure.cucumberjvm.AllureCucumberJvm",
                "appmanager.ExtentCucumberFormatter"},
        monochrome = true,
        //
//             dryRun = true,
        tags={"@Smoke123"})
//        tags = {"@Adactin,@RestAPI"})


public class TestRunner extends AbstractTestNGCucumberTests {
    String sourceDir = "./src/test/resources/";

    public TestRunner() {
    }

    private static ExtentCucumberFormatter formatter;
    EmailSender email = new EmailSender();
    static ArrayList<String> listOfScenarios = new ArrayList<>();
    static ArrayList<String> results = new ArrayList<>();
    //private static final Regex myRegex = new Regex("[^\\\\p{Alpha}\\\\p{Digit}]+");
    String emailSenderSwitch = "";
    String[] emailAddresses = null;


    @BeforeSuite
    public void setUp() {
        final PropertyFileReader localreader = new PropertyFileReader("local.properties");
        emailSenderSwitch = localreader.get("send.email").replaceAll("\\s+", "");
        emailAddresses = localreader.get("send.emailAddress").replaceAll("\\s+", "").split(",");
//        WebDriverUpdater.updateChromeDriver();
        formatter = new ExtentCucumberFormatter();
        ExtentCucumberFormatter.initiateExtentCucumberFormatter();
        ExtentCucumberFormatter.loadConfig(new File(sourceDir + "extent-config.xml"));
        if (emailSenderSwitch.equalsIgnoreCase("On")) {
            for (String emailAddress : emailAddresses) {
                String message = email.buildMessage();
                email.sendHTMLmessage(emailAddress, "Started to run " + localreader.get("application.name") + " automation scripts.", message);
            }
        }

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        final PropertyFileReader localreader = new PropertyFileReader("local.properties");
        formatter.releaseMediaPlayer();
        ApplicationManager.stop();
        if (emailSenderSwitch.equalsIgnoreCase("On")) {
            Comparator<String> comparator = Comparator.<String, Boolean>comparing(s -> s.contains("FAILED")).reversed().thenComparing(Comparator.naturalOrder());
            for (String emailAddress : emailAddresses) {
                Collections.sort(listOfScenarios, comparator);
                String message = email.buildPostMessage(listOfScenarios);
                email.sendHTMLmessage(emailAddress, "Execution of " + localreader.get("application.name") + " Selenium scripts completed. ", message);
            }
        }
    }


    @Before
    public void startScenario(Scenario scenario) {
        final PropertyFileReader localreader = new PropertyFileReader("local.properties");
        String fileName = scenario.getName().split(" ")[0];
        String[] tagsToBeRun = localreader.get("tagsForVideoCapture").replaceAll("\\s+", "").split(",");
        for (String tag : scenario.getSourceTagNames()) {
            for (String tagToBeRun : tagsToBeRun) {
                if (tag.equalsIgnoreCase(tagToBeRun)) {
                    System.out.println("================================= " + tag + " ==========================================");
                    HelperBase.screenShotSwitch = formatter.startVideoRecording(fileName);
                } else {
                    HelperBase.screenShotSwitch = false;
                }
            }
        }

    }

    @After
    public void endScenario(Scenario scenario) {
        ApplicationManager.stop();
        listOfScenarios.add(scenario.getStatus().toUpperCase() + " - " + scenario.getName());
        formatter.stopVideoRecording();
    }
}

