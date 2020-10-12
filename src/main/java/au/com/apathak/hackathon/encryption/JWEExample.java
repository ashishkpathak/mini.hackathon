package au.com.apathak.hackathon.encryption;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class JWEExample {


  public static void main (String ... args) throws Exception{

    BufferedRead
    InputStream resourceAsStream =
    JWEExample.class.getResourceAsStream("src/main/resources/private_key_jwe.pem");
    byte[] buffer = new byte[0x1000];
    StringBuffer sb = new StringBuffer();
    int read = resourceAsStream.read(buffer);
    while(read != -1){
      sb.append(new String(buffer,0,read));
      read = resourceAsStream.read(buffer);
    }

    System.out.printf("%s",sb.toString());
  }
}
