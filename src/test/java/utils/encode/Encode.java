package utils.encode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encode {
  public static String toHash512Base64(String text) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.update(text.getBytes());
    byte[] byteData = md.digest();

    String hashCodeBuffer = new Base64Encoder().encode(byteData);

    return hashCodeBuffer.replace("\n", "");
  }
}
