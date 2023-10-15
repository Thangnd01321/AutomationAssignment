package selenium.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.testhelper.TestParams;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WebLocator {

  private final WebDriver driver = DriverManager.getDriver();
  private final By locator;
  private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

  public WebLocator(By locator) {
    this.locator = locator;
  }

  /**
   * Wait until element is visible on browser using configuration timeout For setting timeout,
   * please type in command line with argument browsertimeout e.g.: -Dbrowsertimeout=30
   *
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilVisible() {
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return this;
  }

  /**
   * Wait until element is visible on browser using specific timeout
   *
   * @param timeout
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilVisible(int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return this;
  }

  /**
   * Wait until element text contains specific value
   *
   * @param text
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilElementTextContains(String text) {
    wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    return this;
  }

  /**
   * Wait until element text
   *
   * @param timeout, text
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilElementTextContains(int timeout, String text) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    return this;
  }

  /**
   * Wait until element has attribute contains value
   *
   * <p>Mostly wait for Ajax to load data into element.
   *
   * <p>E.g.: Css class is changing, Text field value e.t.c...
   *
   * @param attribute
   * @param value
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilElementAttributeContainsValue(String attribute, String value) {
    wait.until(
        CustomWait.waitUntilElementAttributeValueContains(driver, locator, attribute, value));
    return this;
  }

  /**
   * Wait until element has attribute contains value using specific timeout
   *
   * <p>Mostly wait for Ajax to load data into element.
   *
   * <p>E.g.: Css class is changing, Text field value e.t.c...
   *
   * @param timeout
   * @param attribute
   * @param value
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilElementAttributeContainsValue(
      int timeout, String attribute, String value) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    wait.until(
        CustomWait.waitUntilElementAttributeValueContains(driver, locator, attribute, value));
    return this;
  }

  /**
   * Wait until element has attribute value
   *
   * <p>Mostly wait for Ajax to load data into element.
   *
   * <p>E.g.: Css class is changing, Text field value e.t.c...
   *
   * @param attribute
   * @param value
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilElementAttributeValue(String attribute, String value) {
    wait.until(CustomWait.waitUntilElementAttributeValue(driver, locator, attribute, value));
    return this;
  }

  /**
   * Wait until element has attribute value using specific timeout
   *
   * <p>Mostly wait for Ajax to load data into element.
   *
   * <p>E.g.: Css class is changing, Text field value e.t.c...
   *
   * @param timeout
   * @param attribute
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilElementAttributeValue(int timeout, String attribute, String value) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    wait.until(CustomWait.waitUntilElementAttributeValue(driver, locator, attribute, value));
    return this;
  }

  /**
   * Wait until element is clickable on browser using configuration timeout For setting timeout,
   * please type in command line with argument browsertimeout e.g.: -Dbrowsertimeout=30
   *
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilClickable() {
    wait.until(ExpectedConditions.elementToBeClickable(locator));
    return this;
  }

  /**
   * Wait until element is clickable on browser using specific timeout
   *
   * @param timeout
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilClickable(int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    wait.until(ExpectedConditions.elementToBeClickable(locator));
    return this;
  }

  /**
   * Wait until element is not visible or not present on the DOM using configuration timeout For
   * setting timeout, please type in command line with argument browsertimeout e.g.:
   * -Dbrowsertimeout=30
   *
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilInvisible() {
    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    return this;
  }

  /**
   * Wait until element is not visible or not present on the DOM using specific timeout
   *
   * @param timeout
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilInvisible(int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    return this;
  }

  /**
   * Wait until element is present on the DOM using configuration timeout For setting timeout,
   * please type in command line with argument browsertimeout e.g.: -Dbrowsertimeout=30
   *
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilPresent() {
    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    return this;
  }

  /**
   * Wait until element is present on the DOM using specific timeout
   *
   * @param timeout
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilPresent(int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    return this;
  }

  /**
   * Wait until element has text value
   *
   * @param text
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilElementText(String text) {
    wait.until(ExpectedConditions.textToBe(locator, text));
    return this;
  }

  /**
   * Wait until element has text value
   *
   * @param timeout
   * @param text
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilElementText(int timeout, String text) {
    wait.until(ExpectedConditions.textToBe(locator, text));
    return this;
  }

  /**
   * Move to element using {@link Actions}
   *
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator moveToElement() {
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(locator)).perform();
    return this;
  }

  public WebLocator scrollToView() {
    JavascriptSupport.moveToElement(driver, findElement());
    return this;
  }

  public WebLocator delay(int timeout) throws InterruptedException {
    Thread.sleep(timeout * 1000);
    return this;
  }

  /**
   * Move to element using {@link Actions}
   *
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator moveToElement(WebElement element) {
    Actions action = new Actions(driver);
    action.moveToElement(element).perform();
    return this;
  }

  /**
   * Typing on the element, or set its value
   *
   * @param value character sequence to send to the element
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator sendKeys(CharSequence value) {
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(value);

    return this;
  }

  /**
   * Clear text value
   *
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator clear() {
    driver.findElement(locator).clear();
    return this;
  }

  /**
   * Click on element
   *
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator click() {
    WebElement element = driver.findElement(locator);
    element.click();

    return this;
  }

  public WebLocator clickByJs() {
    // Try to find element and click on it up to 3 times to prevent case page
    // refresh element
    for (int i = 0; i < 3; i++) {
      try {
        WebElement element = driver.findElement(locator);
        JavascriptSupport.clickElement(driver, element);
        break;
      } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
        if (i < 2) {
          continue;
        } else {
          throw e;
        }
      }
    }
    return this;
  }

  /**
   * Return Text value of element This method will not return Text value of Text field
   *
   * @return The innerText of this element.
   * @author thangnguyen
   */
  public String getText() {
    return driver.findElement(locator).getText();
  }

  /**
   * Return Text values of multiple element
   *
   * @return List<String> elements text values
   * @author thangnguyen
   */
  public List<String> getTexts() {
    List<WebElement> elems = driver.findElements(locator);
    List<String> textValues = new ArrayList<String>();
    for (WebElement elem : elems) {
      textValues.add(elem.getText());
    }
    return textValues;
  }

  /**
   * Get Attribute value of element
   *
   * @return The attribute/property's current value or null if the value is not set.
   * @author thangnguyen
   */
  public String getAttributeValue(String attributeName) {
    return driver.findElement(locator).getAttribute(attributeName);
  }

  /**
   * Find element
   *
   * @return The first matching {@link WebElement} on the current page
   * @author thangnguyen
   */
  public WebElement findElement() {
    return driver.findElement(locator);
  }

  /**
   * Find list of elements
   *
   * @return A list of all {@link WebElement}, or an empty list if nothing matches
   * @author thangnguyen
   */
  public List<WebElement> findElements() {
    return driver.findElements(locator);
  }

  /**
   * Get Css value of element
   *
   * @param propName
   * @return The current, computed value of the property.
   * @author thangnguyen
   */
  public String getCssValue(String propName) {
    return driver.findElement(locator).getCssValue(propName);
  }

  /**
   * Check whether element is selected or not For check box and radio button e.t.c...
   *
   * @return True if the element is currently selected or checked, false otherwise.
   * @author thangnguyen
   */
  public boolean isSelected() {
    return driver.findElement(locator).isSelected();
  }

  /**
   * Move to and highlight element for easier tracing Be careful, this method might break the
   * element
   *
   * @return {@link WebLocator}
   * @throws InterruptedException
   * @author thangnguyen
   */
  public WebLocator hightlight() throws InterruptedException {
    // Element not displayed then return
    try {
      if (!this.findElement().isDisplayed()) {
        return this;
      }
      // move to element before change style
      moveToElement();

      // Not recording or debugging then does not need highlight
      if (!TestParams.SCREEN_RECORDING && !TestParams.DEBUGGING) {
        return this;
      }
      if (TestParams.IS_HEADLESS) {
        return this;
      }

      // Change style
      String originalStyle = this.getAttributeValue("style");
      String newStyle = "background: yellow; border: 2px solid red;";
      // change to new style
      JavascriptSupport.applyStyle(driver, this.findElement(), newStyle);
      Thread.sleep(300);
      // change back
      JavascriptSupport.applyStyle(driver, this.findElement(), originalStyle);
      return this;
    } catch (NoSuchElementException e) {
      // Element not existed then return
      return this;
    } catch (StaleElementReferenceException e) {
      // Stale element
      return this;
    }
  }

  public WebLocator changeAttribute(String attribute, String value) {
    JavascriptSupport.applyStyle(driver, this.findElement(), attribute, value);
    return this;
  }

  /**
   * Clear text field by action
   *
   * @return {@link WebLocator}
   * @throws InterruptedException
   * @author thangnguyen
   */
  public WebLocator clearTextFieldAction() throws InterruptedException {
    WebElement element = driver.findElement(locator);
    element.clear();
    Actions action = new Actions(driver);
    action.sendKeys(Keys.SPACE).sendKeys(Keys.BACK_SPACE).perform();
    Thread.sleep(1000);
    return this;
  }

  /**
   * Set value of text field by action
   *
   * @return {@link WebLocator}
   * @throws InterruptedException
   * @author thangnguyen
   */
  public WebLocator setValuesAction(CharSequence value) {
    Actions action = new Actions(driver);
    if (System.getProperty("os.name").startsWith("Mac")) {
      action
          .moveToElement(driver.findElement(locator))
          .click()
          .sendKeys(Keys.chord(Keys.COMMAND, "a"), value)
          .perform();
    } else {
      action
          .moveToElement(driver.findElement(locator))
          .click()
          .sendKeys(Keys.chord(Keys.CONTROL, "a"), value)
          .perform();
    }
    return this;
  }

  /**
   * Send keys action
   *
   * @return {@link WebLocator}
   * @throws InterruptedException
   * @author thangnguyen
   */
  public WebLocator sendKeysAction(CharSequence value) {
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(locator)).click().sendKeys(value).perform();
    return this;
  }

  /**
   * Wait until number of elements present on web page
   *
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilNumberOfElements(int num) {
    wait.until(CustomWait.waitUntilNumberOfElement(driver, locator, num));
    return this;
  }

  /**
   * Wait until number of elements present on web page
   *
   * @param timeout
   * @return {@link WebLocator}
   * @author thangnguyen
   */
  public WebLocator waitUntilNumberOfElements(int num, int timeout) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout))
        .until(CustomWait.waitUntilNumberOfElement(driver, locator, num));
    return this;
  }

  public WebLocator verifyCheckbox(boolean isSelected) {
    assertEquals(
        "Element: " + locator + " is not selected", this.findElement().isSelected(), isSelected);
    return this;
  }

  public WebElement findChildElement(WebElement element) {
    return element.findElement(this.locator);
  }

  public WebLocator waitUntilChildElementVisible(WebElement element) {
    wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(element, this.locator));
    return this;
  }

  /**
   * Check that is element displayed
   *
   * @return boolean
   */
  public boolean isDisplayed() {
    List<WebElement> elements = driver.findElements(locator);
    return elements.size() != 0;
  }

  public WebLocator hightlightChildElement(WebElement element) throws InterruptedException {
    // Element not displayed then return
    try {
      if (!this.findChildElement(element).isDisplayed()) {
        return this;
      }
      // move to element before change style
      moveToElement(this.findChildElement(element));

      // Not recording or debugging then does not need highlight
      if (!TestParams.SCREEN_RECORDING && !TestParams.DEBUGGING) {
        return this;
      }

      // Change style
      String originalStyle = this.findChildElement(element).getAttribute("style");
      String newStyle = "background: yellow; border: 2px solid red;";
      // change to new style
      JavascriptSupport.applyStyle(driver, this.findChildElement(element), newStyle);
      Thread.sleep(300);
      // change back
      JavascriptSupport.applyStyle(driver, this.findChildElement(element), originalStyle);
      return this;
    } catch (NoSuchElementException e) {
      // Element not existed then return
      return this;
    } catch (StaleElementReferenceException e) {
      // Stale element
      return this;
    }
  }

  public void removeElement() {
    JavascriptSupport.removeElement(driver, findElement());
  }

  public WebLocator captureScreenshot() throws IOException {
    File file = findElement().getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("screenshots.png"));
    return this;
  }

  public WebLocator waitUntilElementIsSelected() {
    wait.until(ExpectedConditions.elementToBeSelected(this.locator));
    return this;
  }

  public WebLocator selectOptionByVisibleText(String option) {
    Select select = new Select(driver.findElement(this.locator));
    select.selectByVisibleText(option);
    return this;
  }

  public String getSelectedOption(){
    Select select = new Select(driver.findElement(this.locator));
    return select.getFirstSelectedOption().getText();
  }
}
