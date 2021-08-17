package au.com.apathak.hackathon.misc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileSplitterUtils {

  public static void main(String... args) throws Exception {
    File file = new File("/tmp/a.txt");
    if (!file.exists() || file.length() == 0 || !file.canRead()) {
      return;
    }

    int partition = 50000;
    byte[] buffer = new byte[0x1000];
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file), 1024);
    int read = bis.read(buffer);
    int tread = 0;
    int count = 0;
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("/tmp/a.txt." + count++));
    while (read != -1) {
      tread += read;
      if (tread >= partition) {
        bos.close();
        bos = new BufferedOutputStream(new FileOutputStream("/tmp/a.txt." + count++));
        bos.write(buffer, 0, read);
        read = bis.read(buffer);
        tread = 0;
      } else {
        bos.write(buffer, 0, read);
        read = bis.read(buffer);
      }
    }
    bos.close();

  }
}
