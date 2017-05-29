import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;


public class Client {
	public static void main(String[] args) {
		
		calcMsg msg = new calcMsg();
		
		String serverAddress = JOptionPane.showInputDialog(
	            "Enter IP Address of a machine that is\n" +
	            "running the date service on port 8080:");
		
		String calc = JOptionPane.showInputDialog(
	            "Enter calculation \n");
		
		msg.setMessage(calc);
		
//		System.out.println(msg.getMessage() + "test" + calc);
		
	        Socket s = null;
			try {
				s = new Socket(serverAddress, 8080);
			} catch (IOException e) {
				e.printStackTrace();
			}
	   
	        BufferedReader input = null;
			try {
				input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (IOException e2) {
				System.out.println("input empty");
				e2.printStackTrace();
			}
	        
	        
			
	        ObjectOutputStream sendCalc = null;
			try {
				sendCalc = new ObjectOutputStream(s.getOutputStream());
				sendCalc.writeObject(msg);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
           
		
				try {
					sendCalc.writeObject(calc);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			try {
					sendCalc.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.out.println(calc);
                String erg = null;
				try {
					erg = input.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
                System.out.println(erg);
                JOptionPane.showMessageDialog(null, "The solution is: " + erg);

                
                
	        try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	}
}
