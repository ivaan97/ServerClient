import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.net.Socket;

public class Client {

private static int PORT = 8080;

        public static void main(String[] args) throws IOException {
            
        	Link link = new Link();
        	
        	//ermöglicht das zeilenweise einlesen
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Verbinden mit (IP eingeben)\n '/quit' um zu beenden");
                
                //Serveradresse initialisieren mit 127.0.0.1 (=localhost)
                String serverAddress = "127.0.0.1";
                
                //eingabe in variable input, zeilenweise speichern
                String input = reader.readLine();
                
                //gibt der user "/quit" ein, so wird das Programm beendet
                if (input.equals("/quit")) {
                    System.out.println("Programm beendet");
                    System.exit(0);
                }else if(input!=null){
                serverAddress=input;
                }
                
            //boolean variable die zur Überprüfung dient 
            boolean quit = false;
            
            //quit ist am anfang immer false, also muss die while mindestens einmal abgearbeitet werden
            while(!quit) {

            	//eingabe des Links, der zu herunterladenden Website, es muss "http://" vorangestellt werden
                System.out.println("Link eingeben (mit http://)");
                input = reader.readLine();
                if (input.equals("/quit")) {
                    quit = true;
                } else if (input != null) {
                	
                	//link durch setter auf eingabe setzen
                    link.setMessage(input);
                    
                    //Verbindungsaufbau
                    Socket s = new Socket(serverAddress, PORT);                                        
                    
                    //Ausgabekanal 
                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                    
                    //in ausgabe schreiben (was? --> link den der user eingibt)
                    out.writeObject(link);
                    out.flush();
                    
                    //EingabeKanal
                    InputStream in = s.getInputStream();
                    FileOutputStream fileOut = new FileOutputStream("ClientDatei.html");

                    byte[] buffer = new byte[1024];
                    while (s.isConnected()) {
                        int bytesRead = in.read(buffer);
                        if (bytesRead == -1) break;
                        fileOut.write(buffer, 0, bytesRead);
                    }

                    fileOut.close();
                    quit=true;
                }
            }

            System.out.println("Beendet");


        }


}