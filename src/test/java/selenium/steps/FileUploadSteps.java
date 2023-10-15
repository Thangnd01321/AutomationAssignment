package selenium.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.pageobject.FileUploadPage;
import utils.settings.TestConfig;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadSteps {
  FileUploadPage page;

  public FileUploadSteps() {
    page = new FileUploadPage();
  }

  @When("User uploads file {string}")
  public void userUploadsFile(String fileName) throws InterruptedException {
    Path filePath = Paths.get(TestConfig.DATA_PATH, fileName);
    page.inputFileUpload(filePath.toFile().getAbsolutePath()).clickOnUploadButton();
  }

  @Then("File {string} is uploaded")
  public void fileIsUploaded(String fileName) throws InterruptedException {
    page.verifyFileUploadedHeader().verifyUploadedFile(fileName);
  }
}
