package api.steps;

import api.core.BaseAPI;
import factory.DataContainer;
import io.cucumber.java.*;
import org.assertj.core.api.Assertions;
import selenium.core.BasePage;
import selenium.core.DriverManager;
import utils.common.RandomString;
import utils.logging.Log;
import utils.settings.TestConfig;
import utils.testhelper.ScreenRecording;
import utils.testhelper.TestParams;

import java.awt.*;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ThreadLocalRandom;

public class SetupSteps {
  DataContainer data;
  BasePage page;
  ScreenRecording recording;
  RandomString random;

  public SetupSteps(DataContainer data) throws IOException {

    if (random == null) {
      random = new RandomString(8, ThreadLocalRandom.current());
    }
    if (recording == null) {
      recording = new ScreenRecording();
    }
  }

  @Before("@api")
  public void beforeScenario(Scenario scenario)
      throws IOException, AWTException, InterruptedException {
    // Test site for API
    BaseAPI.url = TestParams.API_URL;

    Log.details("	Scenario: " + scenario.getName());
    // set scenario for logging
    TestConfig.scenario.set(scenario);
  }

  @After("@api")
  public void afterScenario(Scenario scenario) throws IOException, InterruptedException {
    try {
      if (scenario.getStatus() == Status.PASSED) Log.details("Test Status: Passed!!!!");
      else if (scenario.getStatus() == Status.FAILED) {
        Log.details("Test Status: Failed!!!!");
      }

    } catch (Exception e) {
      Log.details(e.getMessage());
    } finally {
      // Quit web driver after each scenario
      DriverManager.quitDriver();
    }

    // set null after each scenario
    TestConfig.scenario.set(null);
  }

  @AfterStep("@api")
  public void afterStep(Scenario scenario) throws IOException {}

  // For blank datatable value
  @DataTableType(replaceWithEmptyString = "[blank]")
  public String stringType(String cell) {
    return cell;
  }
}
