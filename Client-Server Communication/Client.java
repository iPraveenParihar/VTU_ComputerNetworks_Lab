import java.io.*;
import java.net.*;
import java.util.*;

public class Client{

	public static void main(String[] args) {
		
		DatagramSocket socket;
		DatagramPacket dataPacket;
		Scanner in;
		byte[] buffer;
		String receivedMsg = "";
		try{
			socket = new DatagramSocket(6000);
			
			while(!receivedMsg.equals("exit")){
				buffer = new byte[1024];

				dataPacket = new DatagramPacket(buffer, buffer.length);
				socket.receive(dataPacket);
				receivedMsg = new String(dataPacket.getData(),0,dataPacket.getLength());
				System.out.println("Received: "+receivedMsg);
			}
			socket.close();
		}
		catch(Exception e){
			System.out.print(e);
		}
	}
}