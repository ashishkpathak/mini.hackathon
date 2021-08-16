package au.com.apathak.hackathon.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileSplitter {

  public static void main(String[] args) throws Exception {

    if(args.length != 2){
      System.out.print("Usage: java FileSplitter fileName 10");
      System.out.print("Splits into 10 parts");

    }
    final String fileName = args[0];
    final int parts = Integer.parseInt(args[1]);

    byte[] buffer = new byte[0x1000];
    File file = new File(fileName);
    long length = file.length();
    long part = Math.round(Math.ceil(length/parts));


    boolean exists = true;
    int count = 0;
    BufferedInputStream bufferedReader = new BufferedInputStream(new FileInputStream(file));
    File ofile = new File(String.format("%s.%d", fileName, count++));
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ofile));
    int read = bufferedReader.read(buffer);
    int tRead = read;
    while (exists) {

      while (read != -1 && tRead <= part) {
        bos.write(buffer, 0, read);
        tRead += read;
        read = bufferedReader.read(buffer);
      }
      if (read == -1) {
        bos.close();
        exists = false;
      } else {
        bos.close();
        ofile = new File(String.format("%s.%d", fileName, count++));
        bos = new BufferedOutputStream(new FileOutputStream(ofile));
        tRead = 0;
      }
    }
  }
}
