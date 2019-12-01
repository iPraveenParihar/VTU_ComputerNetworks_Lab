import java.io.*;
import java.net.*;


class Server {

	static Socket socket;
	static ServerSocket serverSocket;

	static DataInputStream din;
	static DataOutputStream dout;
	static InputStreamReader ip;
	static BufferedReader br;

	static File file;
	static FileInputStream fileReader;

	static String address;
	static String port;
	static String filename;

	public static void getConnection(int port) throws Exception{

		System.out.println("Listening on :"+port);
		serverSocket = new ServerSocket(port);
		System.out.println("Waiting for Request from Client....");
		socket = serverSocket.accept();
		System.out.println("Connected to "+socket.getInetAddress().toString());
		
	}

	public static void main(String[] args) {
		
		try{ 

			while(true) {

				System.out.println(InetAddress.getLocalHost().getHostAddress());
				getConnection(Integer.parseInt(args[0]));

				din = new DataInputStream(socket.getInputStream());
				dout = new DataOutputStream(socket.getOutputStream());

				filename = din.readUTF();

				file = new File(filename);

				if(!file.exists()){
					dout.flush();
					dout.writeUTF("Not Found");
					dout.close();
					din.close();
					socket.close();
					serverSocket.close();
					System.out.println("Requested File Not Found\n\n");
					continue;
				}

				fileReader = new FileInputStream(file);

				System.out.println("Sending file "+filename+" [Size : "+file.length()+" bytes]");
				
				byte[] buffer = new byte[4096];
			
				int read;
				
				while((read = fileReader.read(buffer)) != -1){
					dout.write(buffer, 0, read);
					dout.flush();
				}

				System.out.println("Completed.\n\n");
			
				fileReader.close();
				dout.close();
				din.close();
				socket.close();
				serverSocket.close();
			}
		}
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}
}