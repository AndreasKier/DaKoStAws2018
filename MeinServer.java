package auditLog;

import java.io.BufferedReader;  
import java.io.InputStreamReader;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException; 
import java.net.ServerSocket;
import java.net.Socket;
import edu.hm.dako.chat.common.ChatPDU;


public class MeinServer {
	
	public static void main (String[]args)throws IOException{
		
		PropertyConfigurator.configureAndWatch("log4j.test.properties");
		MeinServer server = new MeinServer(50001);
		
		log.debug(server.toString());
		server.startListening();
		
	}
	
	private static final Logger log = Logger.getLogger(MeinServer.class.getName());
	private int port;
	
	public MeinServer(int port) {
		this.port = port;
	}
	
	public void startListening() {
		new Thread(new Runnable() {
			
			public void run() {
				
				while(true) {
					
					try {
						System.out.println("[AUDIT] Server gestartet");
						ServerSocket serverSocket = new ServerSocket(port);
						System.out.println("[AUDIT] Warten auf Verbindung...");
						Socket remoteClientSocket = serverSocket.accept();
						System.out.println("[AUDIT] Client verbunden");
						
							Scanner s = new Scanner (new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream())));
						if(s.hasNextLine()) {
							System.out.println("[AUDIT] Neue Nachricht von der GUI \n" + s.nextLine());
						}
						s.close();
						remoteClientSocket.close();
						serverSocket.close();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
}
