import java.io.*;
import java.net.*;
import java.util.*;

public class Server{

	public static void main(String[] args) {
		
		DatagramSocket socket;
		DatagramPacket dataPacket;
		Scanner in;

		String sendMsg = "";

		try{	
			socket = new DatagramSocket();
			in = new Scanner(System.in);
			InetAddress host = InetAddress.getByName("127.0.0.1");
			while(!sendMsg.equals("exit")){
				System.out.print("Message: ");
				sendMsg = in.nextLine();
				dataPacket = new DatagramPacket(sendMsg.getBytes(), sendMsg.length(), host, 6000);
				socket.send(dataPacket);
			}
			socket.close();

		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}