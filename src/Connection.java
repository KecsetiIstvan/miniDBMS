import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection {

	private ServerSocket server;
	private Socket socket;
	
	public Connection(int port) throws IOException {
		server=new ServerSocket(port);
	}
	
	public void acceptConnection() throws IOException {
		socket=server.accept();
	}
	
	public void sendToClient(String toSend) throws IOException {
		byte[] send=toSend.getBytes(StandardCharsets.UTF_8);
		DataOutputStream oos=new DataOutputStream(socket.getOutputStream());
		oos.write(send);
		oos.close();
	}
	
	public String receiveFromClient() throws IOException {
		byte[] fogadott=new byte[10240];
		DataInputStream ois=new DataInputStream(socket.getInputStream());
		String received=new String(fogadott,"UTF-8");
		ois.read(fogadott);
		return received;
	}
	
}
