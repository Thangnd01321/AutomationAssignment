package selenium.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.pageobject.LoginPage;

import java.util.List;
import java.util.Map;

public class LoginSteps {
  LoginPage page;

  public LoginSteps() {
    page = new LoginPage();
  }

  @When("User login using username {string} and password {string}")
  public void userLoginUsingUsernameAndPassword(String username, String password)
      throws InterruptedException {
    page.enterUsername(username).enterPassword(password).clickOnLoginButton();
  }

  @Then("User should get error message {string}")
  public void userShouldGetErrorMessage(String errorMessage) throws InterruptedException {
    page.verifyErrorMessage(errorMessage);
  }

  @When("User login using following credentials")
  public void userLoginUsingFollowingCredentials(DataTable dataTable) throws InterruptedException {
    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    String username = list.get(0).get("username");
    String password = list.get(0).get("password");
    page.enterUsername(username).enterPassword(password).clickOnLoginButton();
  }
}
