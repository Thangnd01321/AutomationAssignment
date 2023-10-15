package selenium.pageobject;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import selenium.core.BasePage;
import selenium.core.WebLocator;

public class DropdownPage extends BasePage {
  Page page;

  public DropdownPage() {
    page = new Page();
  }

  public DropdownPage verifyPageHeader() throws InterruptedException {
    page.lblPageHeader().waitUntilVisible().hightlight();
    return this;
  }

  public DropdownPage selectOption(String option) throws InterruptedException {
    page.sltDropdown().waitUntilClickable().hightlight().selectOptionByVisibleText(option);
    return this;
  }

  public DropdownPage verifySelectedOption(String option) {
    Assertions.assertThat(page.sltDropdown().getSelectedOption())
        .as("Selected option should be: " + option)
        .isEqualTo(option);
    return this;
  }

  public DropdownPage verifyOptionIsSelected(String option) throws InterruptedException {
    // Verify that that option has attribute selected = selected
    page.valDropdown(option).waitUntilElementAttributeContainsValue("selected", "selected");
    return this;
  }

  class Page {
    public WebLocator lblPageHeader() {
      return new WebLocator(By.xpath("//h3[normalize-space()='Dropdown List']"));
    }

    public WebLocator sltDropdown() {
      return new WebLocator(By.id("dropdown"));
    }

    public WebLocator valDropdown(String option) {
      String xpath =
          String.format("//select[@id='dropdown']/option[normalize-space()='%s']", option);
      return new WebLocator(By.xpath(xpath));
    }
  }
}
