package selenium.pageobject;

import org.openqa.selenium.By;
import selenium.core.BasePage;
import selenium.core.WebLocator;

public class SecurePage extends BasePage {
  Page page;

  public SecurePage() {
    page = new Page();
  }

  public SecurePage verifyPageHeader() throws InterruptedException {
    page.lblPageHeader().waitUntilVisible().hightlight();
    return this;
  }

  public SecurePage clickOnLogoutButton() throws InterruptedException {
    page.btnLogout().waitUntilClickable().hightlight().click();
    return this;
  }
  public SecurePage verifyLogoutButtonIsVisible() throws InterruptedException {
    page.btnLogout().waitUntilVisible().hightlight();
    return this;
  }

  public SecurePage verifyPageMessage(String message) throws InterruptedException {
    page.lblMessage().waitUntilVisible().hightlight().waitUntilElementTextContains(message);
    return this;
  }

  class Page {
    public WebLocator lblPageHeader() {
      return new WebLocator(By.xpath("//h2[normalize-space()='Secure Area']"));
    }

    public WebLocator btnLogout() {
      return new WebLocator(By.xpath("//a[normalize-space()='Logout']"));
    }

    public WebLocator lblMessage() {
      return new WebLocator(By.id("flash-messages"));
    }
  }
}
