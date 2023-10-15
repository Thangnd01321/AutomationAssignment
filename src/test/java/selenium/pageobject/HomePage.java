package selenium.pageobject;

import org.openqa.selenium.By;
import selenium.core.BasePage;
import selenium.core.WebLocator;

public class HomePage extends BasePage {
  Page page;

  public HomePage() {
    page = new Page();
  }

  public HomePage verifyPageHeader() throws InterruptedException {
    page.lblPageHeader().waitUntilVisible().hightlight();
    return this;
  }

  public HomePage clickOnFormAuthentication() throws InterruptedException {
    page.aFormAuthentication().waitUntilClickable().hightlight().click();
    return this;
  }

  public HomePage clickOnCheckboxes() throws InterruptedException {
    page.aCheckboxes().waitUntilClickable().hightlight().click();
    return this;
  }

  public HomePage clickOnDropdown() throws InterruptedException {
    page.aDropdown().waitUntilClickable().hightlight().click();
    return this;
  }

  public HomePage clickOnFileUpload() throws InterruptedException {
    page.aFileUpload().waitUntilClickable().hightlight().click();
    return this;
  }

  class Page {
    public WebLocator lblPageHeader() {
      return new WebLocator(By.xpath("//h1[text()='Welcome to the-internet']"));
    }

    public WebLocator aFormAuthentication() {
      return new WebLocator(By.partialLinkText("Form Authentication"));
    }

    public WebLocator aCheckboxes() {
      return new WebLocator(By.partialLinkText("Checkboxes"));
    }

    public WebLocator aDropdown() {
      return new WebLocator(By.partialLinkText("Dropdown"));
    }

    public WebLocator aFileUpload() {
      return new WebLocator(By.partialLinkText("File Upload"));
    }
  }
}
