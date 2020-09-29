package au.com.apathak.hackathon.encryption;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {

  /**
   * Encrypt using key
   *
   * @param key
   * @return
   */
  public static String encrypt(final String key, final byte[] iv, final String text) {
//    String k = "key0key0key0key0key0key0key0key0";
    String ivBase64Encoded = "+XdOGW+MCuf5VxE/wx4WlQ==";

    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

    Cipher cipher = null;
    try {
      SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

      cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
      byte[] encrypted = cipher.doFinal(text.getBytes());
      String encText = Base64.getEncoder().encodeToString(encrypted);
      return encText;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * Encrypt using key
   *
   * @param key
   * @return
   */
  public static String decrypt(final String key, final byte[] iv, final String encrypt) {
//    String k = "key0key0key0key0key0key0key0key0";
//    String ivBase64Encoded = "+XdOGW+MCuf5VxE/wx4WlQ==";

    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

    Cipher cipher = null;
    try {
      SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");

      cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
      byte[] plain = cipher.doFinal(Base64.getDecoder().decode(encrypt));
      return new String(plain);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public static void main(String[] args) {
    String encrypt = encrypt("key0key0key0key0", new byte[] { 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12 }, "Hello");
    System.out.printf("%n%s%n", encrypt);
    System.out.printf("%n%s%n",decrypt("key0key0key0key0",
        new byte[] { 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12, 0x10, 0x12 },
        encrypt));
  }

}
