package selenium.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import selenium.pageobject.SecurePage;

import java.util.List;
import java.util.Map;

public class SecureSteps {
  SecurePage page;

  public SecureSteps() {
    page = new SecurePage();
  }

  @Then("User is navigated to secure page")
  public void userLoginUsingUsernameAndPassword(DataTable dataTable) throws InterruptedException {
    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    String message = list.get(0).get("message");
    page.verifyPageHeader().verifyPageMessage(message);
  }
}
