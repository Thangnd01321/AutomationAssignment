package selenium.pageobject;

import org.openqa.selenium.By;
import selenium.core.BasePage;
import selenium.core.WebLocator;

public class LoginPage extends BasePage {
  Page page;

  public LoginPage() {
    page = new Page();
  }

  public LoginPage verifyPageHeader() throws InterruptedException {
    page.lblPageHeader().waitUntilVisible().hightlight();
    return this;
  }

  public LoginPage enterUsername(String userName) throws InterruptedException {
    page.inputUsername().waitUntilClickable().hightlight().sendKeys(userName);
    return this;
  }

  public LoginPage enterPassword(String password) throws InterruptedException {
    page.inputPassword().waitUntilClickable().hightlight().sendKeys(password);
    return this;
  }

  public LoginPage clickOnLoginButton() throws InterruptedException {
    page.btnLogin().waitUntilClickable().hightlight().click();
    return this;
  }

  public LoginPage verifyErrorMessage(String message) throws InterruptedException {
    page.lblMessage().waitUntilVisible().hightlight().waitUntilElementTextContains(message);
    return this;
  }

  class Page {
    public WebLocator lblPageHeader() {
      return new WebLocator(By.xpath("//h2[text()='Login Page']"));
    }

    public WebLocator inputUsername() {
      return new WebLocator(By.id("username"));
    }

    public WebLocator inputPassword() {
      return new WebLocator(By.id("password"));
    }

    public WebLocator btnLogin() {
      return new WebLocator(By.xpath("//button[normalize-space()='Login']"));
    }

    public WebLocator lblMessage() {
      return new WebLocator(By.id("flash-messages"));
    }
  }
}
