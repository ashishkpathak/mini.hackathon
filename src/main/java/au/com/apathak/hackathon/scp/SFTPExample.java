package au.com.apathak.hackathon.scp;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Logger;

public class SFTPExample {

  public static void main(String[] args) {
    final String remoteServer = "localhost";
    final int remotePort = 22;
    final String remoteDirectory = "/tmp";
    final String destinationFile = "test.txt";

    JSch jSch = new JSch();
    JSch.setLogger(new Logger() {
      @Override
      public boolean isEnabled(int i) {
        return true;
      }

      @Override
      public void log(int i, String s) {
        System.out.println(s);
      }
    });
 

  }

}
