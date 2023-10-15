package selenium.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.pageobject.CheckboxesPage;

public class CheckboxesSteps {
  CheckboxesPage page;

  public CheckboxesSteps() {
    page = new CheckboxesPage();
  }

  @When("User checks the \"checkbox 1\"")
  public void userChecksThe() throws InterruptedException {
    page.clickOnCheckbox1();
  }

  @When("User unchecks the \"checkbox 2\"")
  public void userUncheckCheckbox2() throws InterruptedException {
    page.clickOnCheckbox2();
  }

  @Then("\"checkbox 1\" is checked")
  public void checkbox1IsChecked() throws InterruptedException {
    page.verifyCheckbox1IsChecked();
  }

  @Then("\"checkbox 2\" is unchecked")
  public void checkbox2IsUnchecked() throws InterruptedException {
    page.verifyCheckbox2IsUnchecked();
  }
}
