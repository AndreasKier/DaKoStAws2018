package auditLog;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MeinClient {

	public static void main(String[]args) {
		MeinClient client = new MeinClient("localhost", 50001);
		client.sendMessage("[CLIENT] Hallo?");
	}
	
	private InetSocketAddress adress;
	
	public MeinClient (String hostname, int port) {
		adress = new InetSocketAddress(hostname, port);
	}
	
	public void sendMessage(String msg) {
		
		try {
			System.out.println("[Client] Verbindet zu Server");
			Socket socket = new Socket();
			socket.connect(adress, 50001);
			System.out.println("[Client] Verbunden :)");
			
			System.out.println("[Client] Sende Nachricht....");
			PrintWriter pw = new PrintWriter (new OutputStreamWriter(socket.getOutputStream()));
			pw.println(msg);
			pw.flush();
			System.out.println("[Client] Nachricht gesendet!!!");
			
			pw.close();
			socket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
