package selenium.pageobject;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import selenium.core.BasePage;
import selenium.core.WebLocator;

public class CheckboxesPage extends BasePage {
  Page page;

  public CheckboxesPage() {
    page = new Page();
  }

  public CheckboxesPage verifyPageHeader() throws InterruptedException {
    page.lblPageHeader().waitUntilVisible().hightlight();
    return this;
  }

  // Click on checkbox will only change the checkbox between check and uncheck
  public CheckboxesPage clickOnCheckbox1() throws InterruptedException {
    page.chkCheckbox1().waitUntilClickable().hightlight().click();
    return this;
  }

  public CheckboxesPage verifyCheckbox1IsChecked() throws InterruptedException {
    Assertions.assertThat(page.chkCheckbox1().waitUntilVisible().isSelected())
        .describedAs("Checkbox 1 should be checked")
        .isTrue();
    return this;
  }

  public CheckboxesPage verifyCheckbox1IsUnchecked() throws InterruptedException {
    Assertions.assertThat(page.chkCheckbox1().waitUntilVisible().isSelected())
            .describedAs("Checkbox 1 should be unchecked")
            .isFalse();
    return this;
  }

  // Click on checkbox will only change the checkbox between check and uncheck
  public CheckboxesPage clickOnCheckbox2() throws InterruptedException {
    page.chkCheckbox2().waitUntilClickable().hightlight().click();
    return this;
  }

  public CheckboxesPage verifyCheckbox2IsChecked() throws InterruptedException {
    Assertions.assertThat(page.chkCheckbox1().waitUntilVisible().isSelected())
            .describedAs("Checkbox 2 should be checked")
            .isTrue();
    return this;
  }

  public CheckboxesPage verifyCheckbox2IsUnchecked() throws InterruptedException {
    Assertions.assertThat(page.chkCheckbox1().waitUntilVisible().isSelected())
            .describedAs("Checkbox 2 should be unchecked")
            .isFalse();
    return this;
  }

  class Page {
    public WebLocator lblPageHeader() {
      return new WebLocator(By.xpath("//h3[text()='Checkboxes']"));
    }

    public WebLocator chkCheckbox1() {
      return new WebLocator(By.xpath("//form[@id='checkboxes']/input[1]"));
    }

    public WebLocator chkCheckbox2() {
      return new WebLocator(By.xpath("//form[@id='checkboxes']/input[2]"));
    }
  }
}
