import java.io.*;
import java.net.*;


class Client {

	static Socket socket;
	static ServerSocket serverSocket;

	static DataInputStream din;
	static DataOutputStream dout;
	static InputStreamReader ip;
	static BufferedReader br;

	static File file;
	static FileOutputStream fileWriter;
	static PrintWriter pfileWriter;

	static String address;
	static String port;
	static String filename;
	static String response;

	public static void getConnection() throws Exception{

		socket = new Socket(address,Integer.parseInt(port));
	}

	public static void main(String[] args) {
		
		try {
			ip = new InputStreamReader(System.in);
			br = new BufferedReader(ip);

			address = "127.0.0.1";
			port = args[0];

			getConnection();

			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());

			System.out.print("Enter filename to request:");
			filename = br.readLine();

			dout.writeUTF(filename);

			
			if((response = din.readUTF()).equals("Not Found")){
				System.out.println("\nRequested File Not Found\n");
				return;
			}

			file = new File("Client_copy_"+filename);
			fileWriter = new FileOutputStream(file,false);

			byte[] buffer = new byte[4096];
			int read;

			while((read = din.read(buffer, 0, buffer.length)) > 0) {
				fileWriter.write(buffer, 0, read);
				System.out.write(buffer);
			}

			System.out.println("\nRecevied:"+file.length()+" bytes");

			fileWriter.close();
			dout.close();
			din.close();
			br.close();
			ip.close();

			socket.close();
		}
		catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}

	}
}