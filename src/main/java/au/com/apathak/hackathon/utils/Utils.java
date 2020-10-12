package au.com.apathak.hackathon.utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class Utils {

  public static String formatKeyResourceFile(InputStream resource) throws Exception {
    return IOUtils.toString(resource, StandardCharsets.UTF_8).replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "")
        .replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replace("\n", "").replaceAll("\\s+", "");
  }
}
