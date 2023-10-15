package selenium.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.manager.SeleniumManager;
import utils.enums.Browser;
import utils.testhelper.TestParams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
  // Driver instance
  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

  /**
   * Start new browser
   *
   * @return {@link WebDriver}
   */
  public static WebDriver startBrowser() {
    switch (Browser.getBrowser()) {
      case CHROME:
        driver.set(startChrome());
        break;
      case FIREFOX:
        driver.set(startFirefox());
      case EDGE:
        break;
      case IE:
        break;
      case OPERA:
        break;
      case SAFARI:
        break;
      default:
        break;
    }

    driver.get().manage().window().maximize();
    return driver.get();
  }

  public static WebDriver getDriver() {
    return driver.get();
  }

  public static void quitDriver() {
    if (driver.get() != null) {
      driver.get().quit();
      driver.set(null);
    }
  }

  public static void captureScreenshot(String ssPath, String ssName) throws IOException {
    File screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshot, new File(Paths.get(ssPath, ssName).toString()));
  }

  public static WebDriver startChrome() {
    // Chrome config
    ChromeOptions options = new ChromeOptions();
    Map<String, Object> prefs = new HashMap<String, Object>();
    prefs.put("safebrowsing.enabled", "false"); // accept file download
    prefs.put("profile.managed_default_content_settings.images", "2");
    prefs.put("disk-cache-size", "4096");
    options.setExperimentalOption("prefs", prefs);
    options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
    options.addArguments("--no-sandbox"); // https://stackoverflow.com/a/50725918/1689770
    options.addArguments("--disable-infobars"); // https://stackoverflow.com/a/43840128/1689770
    options.addArguments("--disable-dev-shm-usage"); // https://stackoverflow.com/a/50725918/1689770
    options.addArguments(
        "--disable-browser-side-navigation"); // https://stackoverflow.com/a/49123152/1689770
    // https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
    options.addArguments("--disable-web-security"); // Enable using JS on IFrame
    options.addArguments("--ignore-certificate-errors"); // Ignore SSL
    options.addArguments("--incognito"); // Private mode
    options.addArguments("--disable-software-rasterizer");
    options.addArguments("--ignore-gpu-blocklist");
    options.addArguments("--use-gl");
    options.addArguments("--enable-webgl-draft-extensions");
    // Headless
    if (TestParams.IS_HEADLESS) {
      options.addArguments("--headless");
      options.addArguments("--window-size=1920,1080");
    }

    // Since chrome 114, should clear driver cache first
    WebDriverManager.chromedriver().clearDriverCache().setup();

    return new ChromeDriver(options);
  }

  public static WebDriver startFirefox() {
    FirefoxOptions options = new FirefoxOptions();
    options.setCapability("marionette", true);
    // Headless
    if (TestParams.IS_HEADLESS) {
      options.addArguments("window-size=1920,1080");
    }

    WebDriverManager.firefoxdriver().setup();
    return new FirefoxDriver(options);
  }
}
