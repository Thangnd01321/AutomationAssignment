package selenium.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.pageobject.CheckboxesPage;
import selenium.pageobject.DropdownPage;

public class DropdownSteps {
  DropdownPage page;

  public DropdownSteps() {
    page = new DropdownPage();
  }

  @When("User select option {string}")
  public void userSelectOption(String option) throws InterruptedException {
    page.selectOption(option);
  }

  @Then("{string} is selected")
  public void isSelected(String option) throws InterruptedException {
    page.verifySelectedOption(option);
  }
}
