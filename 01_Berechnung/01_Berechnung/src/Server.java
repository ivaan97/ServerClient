import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public static void main(String[] args) {
		 ServerSocket listener = null;
		try {
			listener = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	        try {
	            while (true) {
	                Socket s = null;
					try {
						s = listener.accept();
					} catch (IOException e) {
						e.printStackTrace();
					}
	                try {
	                	  ObjectInputStream input = null;
						try {
							input = new ObjectInputStream(s.getInputStream());
						} catch (IOException e) {
							e.printStackTrace();
						}
						
//						System.out.println("eingabe: -> " + input);
						
						
	              	        String line = null;
							Message m = null;
							try {
								m = (Message) input.readObject();
							} catch (ClassNotFoundException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							line = m.getMessage();
	              	        
	              	    String[] parts1 = line.split("-");
	              	    String[] parts2 = line.split("\\+");
	              	    String[] parts3 = line.split("\\*");
	              	    String[] parts4 = line.split("/");
	              	    
	              	    int ergebnis = 0;
	              	    
	              	    System.out.println(parts1.length + "   " + line);
	              	    
	              	    if(parts1.length == 2){
	              	    	
	              	    	String part1 = parts1[0];
	              	    	String part2 = parts1[1];
	              	    	
	              	    	ergebnis = Integer.parseInt(parts1[0]) - Integer.parseInt(parts1[1]);
	         
	              	    	System.out.println("Das Ergebnis lautet: " + ergebnis + "!!!!");
	              	    	
	              	    	
	              	    }else if(parts2.length == 2){
	              	    	
	              	    	String part1 = parts2[0];
	              	    	String part2 = parts2[1];
	              	    	
	              	    	ergebnis = Integer.parseInt(parts2[0]) + Integer.parseInt(parts2[1]);
	         
	              	    	System.out.println("Das Ergebnis lautet: " + ergebnis + "!!!!");
	              	    	
	              	    }else if (parts3.length == 2){
	              	    	
	              	    	String part1 = parts3[0];
	              	    	String part2 = parts3[1];
	              	    	
	              	    	ergebnis = Integer.parseInt(parts3[0]) * Integer.parseInt(parts3[1]);
	         
	              	    	System.out.println("Das Ergebnis lautet: " + ergebnis + "!!!!");
	              	    	
	              	    }else if (parts4.length == 2){
	              	    	
	              	    	String part1 = parts4[0];
	              	    	String part2 = parts4[1];
	              	    	
	              	    	ergebnis = Integer.parseInt(parts4[0]) / Integer.parseInt(parts4[1]);
	         
	              	    	System.out.println("Das Ergebnis lautet: " + ergebnis + "!!!!");
	              	    	
	              	    	
	              	    	
	              	    }else{
	              	    	System.out.println("fail!");
	              	    }
	              	    
	              	    try {
							s.getOutputStream().write((ergebnis + "\n").getBytes("UTF-8"));
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	              	   
	              	    
	              	  
	              	        
	                } finally {
	                    try {
							s.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
	                }
	            }
	        }
	        finally {
	            try {
					listener.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	}
}
