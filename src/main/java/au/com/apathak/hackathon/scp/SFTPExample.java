package au.com.apathak.hackathon.scp;

import java.io.ByteArrayInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Logger;
import com.jcraft.jsch.Session;

public class SFTPExample {

  public static void main(String[] args) {
    final String remoteServer = "localhost";
    final int remotePort = 22;
    final String remoteDirectory = "/tmp";
    final String destinationFile = "test.txt";
    final String remoteUserName = "root";
    Session jSchSession = null;
    try{

    JSch jSch = new JSch();
    jSch.setLogger(new Logger() {
      @Override
      public boolean isEnabled(int i) {
        return true;
      }

      @Override
      public void log(int i, String s) {
        System.out.println(s);
      }
    });

    jSchSession = jSch.getSession(remoteUserName, remoteServer, remotePort);


    jSch.addIdentity("/opt/moa/registration/config/oft_private.key");
    jSch.setConfig("StrictHostKeyChecking", "no");

    System.out.println("SCP: Identity added ");
    // 10 seconds session timeout
    jSchSession.connect(30000);
    System.out.println("SCP: Opened Session.");
    Channel sftp = jSchSession.openChannel("sftp");
    System.out.println(" ++++++++++ sftp ");
    sftp.connect(30000);
    System.out.println("SCP: Connected. ");
    ChannelSftp channelSftp = (ChannelSftp) sftp;
    final String dst = String.format("%s/%s",remoteDirectory,destinationFile);
    System.out.println("SCP: Transfering file. " + dst);
    // transfer file from local to remote server
    channelSftp.cd(remoteDirectory);

    String payload = "a test message.a test message.";
    channelSftp.put(new ByteArrayInputStream(payload.getBytes()), dst);
    System.out.println("SFTP complete.");
    channelSftp.exit();
    System.out.println("SFTP exit ");

  } catch (Exception e) {
    e.printStackTrace();
  } finally {
    if (jSchSession != null) {
      System.out.println("Closing connection");
      jSchSession.disconnect();
    }
  }
  }

}
