package selenium.pageobject;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import selenium.core.BasePage;
import selenium.core.WebLocator;

public class FileUploadPage extends BasePage {
  Page page;

  public FileUploadPage() {
    page = new Page();
  }

  public FileUploadPage verifyPageHeader() throws InterruptedException {
    page.lblPageHeader().waitUntilVisible().hightlight();
    return this;
  }

  public FileUploadPage inputFileUpload(String filePath) throws InterruptedException {
    page.inputFileUpload().waitUntilClickable().sendKeys(filePath);
    return this;
  }

  public FileUploadPage clickOnUploadButton() throws InterruptedException {
    page.btnFileUpload().waitUntilClickable().hightlight().click();
    return this;
  }

  public FileUploadPage verifyFileUploadedHeader() throws InterruptedException {
    page.lblPageUploadedHeader().waitUntilVisible().hightlight();
    return this;
  }

  public FileUploadPage verifyUploadedFile(String fileName) {
    Assertions.assertThat(page.lblUploadedFile().waitUntilVisible().getText().trim())
        .as("Uploaded file should be: " + fileName)
        .isEqualTo(fileName);
    return this;
  }

  class Page {
    public WebLocator lblPageHeader() {
      return new WebLocator(By.xpath("//h3[normalize-space()='File Uploader']"));
    }

    public WebLocator lblPageUploadedHeader() {
      return new WebLocator(By.xpath("//h3[normalize-space()='File Uploaded!']"));
    }

    public WebLocator inputFileUpload() {
      return new WebLocator(By.id("file-upload"));
    }

    public WebLocator btnFileUpload() {
      return new WebLocator(By.id("file-submit"));
    }

    public WebLocator lblUploadedFile() {
      return new WebLocator(By.id("uploaded-files"));
    }
  }
}
