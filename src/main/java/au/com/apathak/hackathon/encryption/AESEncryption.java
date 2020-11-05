package au.com.apathak.hackathon.encryption;

import java.util.Base64;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.xml.internal.rngom.parse.host.Base;

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
    Random random = new Random();
    byte[] buffer = new byte[16];
//    "GW5jD9udqRPVjAHea7OkOhchSf8pxgyZ"
     random.nextBytes(buffer);
    System.out.printf("%s%n", Base64.getEncoder().encodeToString(buffer));
    final String plainText ="DATE_TIME,SERVICE_ID,CUSTOMER_EMAIL\n"
        + "13-Oct-2020 06:10:40,0428462969,kaur4@yopmail.com\n"
        + "T,1";


    String k = "G987999222929212312567j8j5dfg9yZ";
    String ivBase64Encoded = "Ntq78ytDZ5cwWk8zI6Fv4Q==";
    String encrypt = encrypt(k, Base64.getDecoder().decode(ivBase64Encoded),plainText);
    System.out.printf("%n%s%n", encrypt);
    System.out.printf("%n%s%n",decrypt(k, Base64.getDecoder().decode(ivBase64Encoded),encrypt));
  }

}
