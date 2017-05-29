import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


/*
 * Der Client Thread.
 * Öffnet input und output streams für einen bestimmten client, fragt nach dem namen 
 * und informiert andere nutzer (Broadcast) die zum server verbunden sind,
 * über den neuen Clienten bzw über die nachrichten.
 * Verlässt ein Nutzer den chatroom, so informiert dieser Thread alle aktiven nutzer
 * 
 */


class clientThread extends Thread {

  private DataInputStream is = null;
  private PrintStream os = null;
  private Socket clientSocket = null;
  private final clientThread[] threads;
  private int maxClientsCount;

  public clientThread(Socket clientSocket, clientThread[] threads) {
    this.clientSocket = clientSocket;
    this.threads = threads;
    maxClientsCount = threads.length;
  }

  public void run() {
    int maxClientsCount = this.maxClientsCount;
    clientThread[] threads = this.threads;

    try {
      /*
       * input und output streams für  client
       */
      is = new DataInputStream(clientSocket.getInputStream());
      os = new PrintStream(clientSocket.getOutputStream());
      os.println("Enter your name.");
      String name = is.readLine().trim();
      os.println("Hello " + name
          + " to our chat room.\nTo leave enter /quit in a new line");
      for (int i = 0; i < maxClientsCount; i++) {
        if (threads[i] != null && threads[i] != this) {
          threads[i].os.println("*** A new user " + name
              + " entered the chat room !!! ***");
        }
      }
      while (true) {
        String line = is.readLine();
        if (line.startsWith("/quit")) {
          break;
        }
        for (int i = 0; i < maxClientsCount; i++) {
          if (threads[i] != null) {
            threads[i].os.println("<" + name + "&gr; " + line);
          }
        }
      }
      for (int i = 0; i < maxClientsCount; i++) {
        if (threads[i] != null && threads[i] != this) {
          threads[i].os.println("*** The user " + name
              + " is leaving the chat room !!! ***");
        }
      }
      os.println("*** Bye " + name + " ***");

      /*
       * aktuelle thread variable auf null setzen, damit ein neuer client vom server
       * akzeptiert werden kann
       */
      for (int i = 0; i < maxClientsCount; i++) {
        if (threads[i] == this) {
          threads[i] = null;
        }
      }

      /*
       * schließen
       */
      is.close();
      os.close();
      clientSocket.close();
    } catch (IOException e) {
    }
  }
}
