package selenium.steps;

import cucumber.runner.core.TestRunner;
import factory.DataContainer;
import io.cucumber.java.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.core.BasePage;
import selenium.core.DriverManager;
import utils.common.RandomString;
import utils.logging.Log;
import utils.settings.TestConfig;
import utils.testhelper.ScreenRecording;
import utils.testhelper.TestParams;
import utils.testhelper.TestResult;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class SetupSteps {
  DataContainer data;
  BasePage page;
  ScreenRecording recording;
  RandomString random;

  public SetupSteps(DataContainer data) throws IOException {
    this.data = data;

    if (random == null) {
      random = new RandomString(8, ThreadLocalRandom.current());
    }
    if (recording == null) {
      recording = new ScreenRecording();
    }
  }

  @Before("@web")
  public void beforeScenario(Scenario scenario)
      throws IOException, AWTException, InterruptedException {
    // Start screen recording
    recording.startRecording(TestConfig.SCREEN_RECORDING_PATH.toString(), random.nextString());

    // Test site and API
    data.site = TestParams.WEB_URL;

    Log.details("	Scenario: " + scenario.getName());
    // set scenario for logging
    TestConfig.scenario.set(scenario);

    // Start browser before each scenario
    page = new BasePage();
  }

  @After("@web")
  public void afterScenario(Scenario scenario) throws IOException, InterruptedException {
    try {
      // Capture screenshot into cucumber report
      if (page.getDriver() != null) {
        String ssName = random.nextString() + ".png";
        TestResult.embedScreenCapture(scenario, ssName);
      }

      if (scenario.getStatus() == Status.PASSED) Log.details("Test Status: Passed!!!!");
      else if (scenario.getStatus() == Status.FAILED) {
        Log.details("Test Status: Failed!!!!");
      }

      // Upload anyway
      TestResult.embedScreenRecording(scenario, recording.stopRecording(), "text/plain");
    } catch (Exception e) {
      Log.details(e.getMessage());
    } finally {
      // Quit web driver after each scenario
      DriverManager.quitDriver();
    }

    // set null after each scenario
    TestConfig.scenario.set(null);
  }

  @AfterStep("@web")
  public void afterStep(Scenario scenario) throws IOException {}

  // For blank datatable value
  @DataTableType(replaceWithEmptyString = "[blank]")
  public String stringType(String cell) {
    return cell;
  }
}
