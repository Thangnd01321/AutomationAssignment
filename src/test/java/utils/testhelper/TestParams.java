package utils.testhelper;

import constants.Constant;

public class TestParams {

  public static final String API_URL = getApiUrl();
  private static final String HEADLESS = "headless";
  public static final boolean IS_HEADLESS = isHeadless();
  private static final String LOGGING = "logging";
  public static final boolean DEBUGGING = isLogger();
  private static final String BROWSER = "browser";
  private static final String RECORDING = "recording";
  public static final boolean SCREEN_RECORDING = isRecording();
  private static final String URL = "url";
  public static final String WEB_URL = getWebUrl();
  private static final String LOGGING_API_RESPONSE = "loggingApiResponse";
  private static final String ALLOW_API_ERROR = "allowApiError";

  private static boolean isLogger() {
    if (System.getProperty(LOGGING) != null) {
      return System.getProperty(LOGGING).equalsIgnoreCase(Constant.API_PARAM_VALUE_TRUE);
    }
    return false;
  }

  private static boolean isRecording() {
    if (System.getProperty(RECORDING) != null) {
      return System.getProperty(RECORDING).equalsIgnoreCase(Constant.API_PARAM_VALUE_TRUE);
    }
    return false;
  }

  private static boolean isHeadless() {
    if (System.getProperty(HEADLESS) != null) {
      if (System.getProperty(HEADLESS).equalsIgnoreCase(Constant.API_PARAM_VALUE_TRUE)) {
        return true;
      }
    }
    return false;
  }

  private static String getWebUrl() {
    if (System.getProperty(URL) != null) {
      return System.getProperty(URL);
    }
    return Constant.WEB_URL;
  }

  private static String getApiUrl() {
    if (System.getProperty("apiUrl") != null) {
      return System.getProperty("apiUrl");
    }
    return Constant.API_URL;
  }

  private static boolean isLoggingApiResponse() {
    if (System.getProperty(LOGGING_API_RESPONSE) != null) {
      if (System.getProperty(LOGGING_API_RESPONSE)
          .equalsIgnoreCase(Constant.API_PARAM_VALUE_TRUE)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isAllowApiError() {
    if (System.getProperty(ALLOW_API_ERROR) != null) {
      if (System.getProperty(ALLOW_API_ERROR).equalsIgnoreCase(Constant.API_PARAM_VALUE_TRUE)) {
        return true;
      }
    }
    return false;
  }
}
