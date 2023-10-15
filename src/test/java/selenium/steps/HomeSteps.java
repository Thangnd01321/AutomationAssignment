package selenium.steps;

import constants.Constant;
import io.cucumber.java.en.Given;
import selenium.pageobject.*;

public class HomeSteps {
  HomePage page;
  LoginPage login;
  CheckboxesPage checkboxes;
  DropdownPage dropdown;
  FileUploadPage fileUpload;

  public HomeSteps() {
    page = new HomePage();
    login = new LoginPage();
    checkboxes = new CheckboxesPage();
    dropdown = new DropdownPage();
    fileUpload = new FileUploadPage();
  }

  @Given("User is on login page")
  public void userOnLoginPage() throws InterruptedException {
    page.openPage(Constant.WEB_URL);
    page.verifyPageHeader().clickOnFormAuthentication();
    login.verifyPageHeader();
  }

  @Given("User is on checkboxes page")
  public void userIsOnCheckboxesPage() throws InterruptedException {
    page.openPage(Constant.WEB_URL);
    page.verifyPageHeader().clickOnCheckboxes();
    checkboxes.verifyPageHeader();
  }

  @Given("User is on dropdown page")
  public void userIsOnDropdownPage() throws InterruptedException {
    page.openPage(Constant.WEB_URL);
    page.verifyPageHeader().clickOnDropdown();
    dropdown.verifyPageHeader();
  }

  @Given("User is on upload page")
  public void userIsOnUploadPage() throws InterruptedException {
    page.openPage(Constant.WEB_URL);
    page.verifyPageHeader().clickOnFileUpload();
    fileUpload.verifyPageHeader();
  }
}
