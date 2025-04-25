package appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import static appmanager.HelperBase.sleep;

public class ApplicationManager {
    public static WebDriver driver;
    private static String browser;
    public static String target = System.getProperty("target", "local");
    public static PropertyFileReader reader = new PropertyFileReader(String.format("local.properties", target));

    public ApplicationManager(String browser) {
        ApplicationManager.browser = browser;
    }

    public ApplicationManager() {
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            if (browser.equals(BrowserType.IE)) {
                //System.setProperty("webdriver.ie.driver","./drivers/IEDriverServer.exe");
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                driver.manage().deleteAllCookies();
            } else if (browser.equals(BrowserType.CHROME)) {
                  File file = new File(ExtentCucumberFormatter.outputDirectory + File.separator + "TestDocuments");
                        file.mkdir();
                        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                        chromePrefs.put("profile.default_content_settings.popups", 0);
                        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
                        chromePrefs.put("download.prompt_for_download", false);
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--headless");
                        options.addArguments("window-size=1200x600");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.setExperimentalOption("prefs", chromePrefs);
//                        WebDriverManager.chromedriver().setup();
                        driver = WebDriverManager.chromedriver().capabilities(options).clearResolutionCache().clearDriverCache().create();
  //                      driver = new ChromeDriver(options);
                        driver.manage().deleteAllCookies();
            } else if (browser.equals(BrowserType.EDGE)) {
                //System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
                driver = WebDriverManager.edgedriver().create();
//                driver = new ChromeDriver();
//                driver.manage().deleteAllCookies();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            return driver;
        } else {
            return driver;
        }

    }

    public void initUrll() {
        try {
            getWebDriver().get(reader.get("web.Url"));

            getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public void initUrl() {

        initUrll();
sleep(5000);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }


    public static void robotType(Robot robot, String characters) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(characters);
        clipboard.setContents(stringSelection, null);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
    }


}
