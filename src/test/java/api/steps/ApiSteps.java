package api.steps;

import api.request.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import constants.APIKeys;
import constants.Constant;
import factory.DataContainer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.*;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import utils.logging.Log;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiSteps {
  private ApiUsers apiUsers;
  private ApiCategories apiCategories;
  private ApiOrders apiOrders;
  private ApiProducts apiProducts;
  private ApiReviews apiReviews;
  // data
  private List<MUser> users;
  private List<MCategory> categories;
  private List<MProduct> products;
  private MOrder order;
  private MProduct product;
  private MUser user;
  private int numOfUsers = 0;
  private int numOfReviews = 0;
  private ObjectMapper mapper;

  public ApiSteps(DataContainer data)
      throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

    Assertions.setPrintAssertionsDescription(true);

    PrintStream myStream =
        new PrintStream(System.out) {
          @Override
          public void println(String x) {
            Log.details("Assertion: " + x);
            super.println(x);
          }
        };
    System.setOut(myStream);

    // API
    apiUsers = new ApiUsers();
    apiCategories = new ApiCategories();
    apiOrders = new ApiOrders();
    apiReviews = new ApiReviews();
    apiProducts = new ApiProducts();

    users = new ArrayList<>();
    categories = new ArrayList<>();
    products = new ArrayList<>();
    order = new MOrder();
    product = new MProduct();
    mapper = new ObjectMapper();
  }

  @Given("Following user does not exist")
  public void userDoesNotExist(DataTable dataTable)
      throws NoSuchAlgorithmException, KeyStoreException, IOException, URISyntaxException,
          KeyManagementException {
    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    String email = list.get(0).get("email");

    // Call API to get list users
    apiUsers.get();

    users = apiUsers.getUsers();

    // Delete all users with email
    for (MUser user : users) {
      if (user.getEmail() != null && user.getEmail().equalsIgnoreCase(email)) {
        String userId = user.getId();
        Log.details("\nDelete user with email: " + email + " and id: " + userId);
        apiUsers.delete(userId);
      }
    }
  }

  @When("Send POST to create a new user")
  public void sendPOSTToCreateANewUser(DataTable dataTable)
      throws IOException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException,
          KeyManagementException {
    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    String email = list.get(0).get("email");
    String username = list.get(0).get("username");
    String password = list.get(0).get("password");

    // Get total existing users before create new
    apiUsers.clearRequestBody();
    apiUsers.get();
    numOfUsers = apiUsers.getUsers().size();

    // Put json
    apiUsers
        .clearRequestBody()
        .put(APIKeys.USER_NAME, username)
        .put(APIKeys.EMAIL, email)
        .put(APIKeys.PASSWORD, password);
    // create user
    apiUsers.post();

    // Set user from create user response for using later
    this.user = apiUsers.getUser();
  }

  @Then("A new user is created")
  public void aNewUserIsCreated(DataTable dataTable)
      throws NoSuchAlgorithmException, KeyStoreException, IOException, URISyntaxException,
          KeyManagementException {
    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    String email = list.get(0).get("email");
    String username = list.get(0).get("username");
    String password = list.get(0).get("password");

    // Verify response success
    Assertions.assertThat(apiUsers.getResponseHttpStatus())
        .as("Http status should be: " + HttpStatus.CREATED)
        .isEqualTo(HttpStatus.CREATED);

    apiUsers.clearRequestBody();
    apiUsers.get();

    Log.details("Verify new creation user");
    try {
      Assertions.assertThat(user.getUsername())
          .as("User name should be: " + username)
          .isEqualTo(username);
      Assertions.assertThat(user.getEmail()).as("Email should be: " + email).isEqualTo(email);
      Assertions.assertThat(user.getPassword())
          .as("Password should be: " + password)
          .isEqualTo(password);
    } catch (AssertionError e) {
      Log.details(apiUsers.getResponsePayload().toString());
      throw e;
    }
  }

  @When("Send GET to get list of categories")
  public void sendGETToGetListOfCategories()
      throws NoSuchAlgorithmException, KeyStoreException, IOException, URISyntaxException,
          KeyManagementException {
    // Get list categories
    apiCategories.clearRequestBody();
    apiCategories.get();

    this.categories = apiCategories.getCategories();
  }

  @When("Send GET to get list of products")
  public void sendGETToGetListOfProducts()
      throws NoSuchAlgorithmException, KeyStoreException, IOException, URISyntaxException,
          KeyManagementException {
    // Get list products
    apiProducts.clearRequestBody();
    apiProducts.get();
  }

  @When("Send POST to place an order with a product")
  public void sendPOSTToPlaceAnOrderWithProduct(DataTable dataTable)
      throws IOException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException,
          KeyManagementException {
    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    String category = list.get(0).get("category");
    String quantity = list.get(0).get("quantity");

    String categoryId = "";
    for (MCategory mCategory : this.categories) {
      if (mCategory.getName().equals(category)) {
        categoryId = mCategory.getId();
      }
    }
    Assertions.assertThat(category).as("Should found category with name: " + category).isNotEmpty();

    // Get all product with category
    this.products = apiProducts.getProductsByCategory(categoryId);

    // Using first product with category to place order
    this.product = this.products.get(0);

    String userId = this.user.getId();
    String productId = product.getId();
    // create order with a product only

    apiOrders
        .clearRequestBody()
        .put(APIKeys.USER_ID, userId)
        .put(APIKeys.STATUS, Constant.ORDER_STATUS_PROCESSING)
        .putArray(APIKeys.ITEMS)
        .add(
            mapper
                .createObjectNode()
                .put(APIKeys.PRODUCT_ID, productId)
                .put(APIKeys.QUANTITY, quantity));
    apiOrders.post();
    this.order = apiOrders.getOrder();
  }

  @When("Send PUT to update order status: \"shipped\"")
  public void sendPUTToUpdateOrderStatus()
      throws IOException, URISyntaxException, NoSuchAlgorithmException, KeyStoreException,
          KeyManagementException {
    // Update status of the order
    this.order.setStatus(Constant.ORDER_STATUS_SHIPPED);
    ObjectNode node = mapper.valueToTree(this.order);

    apiOrders.setRequestBody(node);
    apiOrders.put(this.order.getId());
  }

  @When("Send POST to write a review")
  public void sendPOSTToWriteAReview(DataTable dataTable)
      throws NoSuchAlgorithmException, KeyStoreException, IOException, URISyntaxException,
          KeyManagementException {
    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    String rating = list.get(0).get("rating");
    String comment = list.get(0).get("comment");

    // Get list reviews first
    apiReviews.get();
    this.numOfReviews = apiReviews.getReviews().size();

    String userId = this.user.getId();
    String productId = this.product.getId();
    apiReviews
        .clearRequestBody()
        .put(APIKeys.USER_ID, userId)
        .put(APIKeys.PRODUCT_ID, productId)
        .put(APIKeys.RATING, rating)
        .put(APIKeys.COMMENT, comment);
    apiReviews.post();
  }

  @Then("A new review is recorded")
  public void aNewReviewIsRecorded(DataTable dataTable) throws IOException {
    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    String rating = list.get(0).get("rating");
    String comment = list.get(0).get("comment");

    String userId = this.user.getId();
    String productId = this.product.getId();
    // Verify status code is 201 Created
    Assertions.assertThat(apiReviews.getResponseHttpStatus())
        .as("Response Http Status should be: " + HttpStatus.CREATED)
        .isEqualTo(HttpStatus.CREATED);

    // Verify new creation review
    MReview review = apiReviews.getReview();
    Assertions.assertThat(review.getUserId()).as("User Id should be: " + userId).isEqualTo(userId);
    Assertions.assertThat(review.getProductId())
        .as("Product Id should be: " + productId)
        .isEqualTo(productId);
    Assertions.assertThat(review.getRating()).as("Rating should be: " + rating).isEqualTo(rating);
    Assertions.assertThat(review.getComment())
        .as("Comment should be: " + comment)
        .isEqualTo(comment);
  }
}
