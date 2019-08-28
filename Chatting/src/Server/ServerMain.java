package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain extends Thread {
	
	ServerSocket ss;
	Socket cs;
	int port;
	
	public ServerMain(int port) {
		this.port = port;
		
	}
	
	@Override
	public void run() {
		try {
			ss = new ServerSocket(port);
			System.out.println(ss.getLocalPort());
			System.out.println("��븦 ��ٸ��� ��...");
			
			cs = ss.accept();
			System.out.println("���� ����Ǿ����ϴ�" + cs.getInetAddress());
		} catch (IOException e) {
			System.err.println("error");
		}		
	}
	
	public static void main(String[] args) {
		//new ServerMain(8888).start();
	}
}
