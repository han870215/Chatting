package Server;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame {

	// ���� ����
	ServerSocket ss;
	Socket sc;
	
	// Ŭ���̾�Ʈ ����
	static ArrayList<Socket> clientSocket = new ArrayList<>();
	static ArrayList<PrintWriter> clientWriter = new ArrayList<>();
	

	// GUI ����
	JTextArea textArea;
	JTextField port_tf;
	JButton port_btn;

	public Server() {
		init(); // GUI ����
		connect(); // ����/���� ��ư
	}

	void init() {
		Container con;
		JPanel panel = new JPanel();
		textArea = new JTextArea();
		textArea.setEditable(false);
		JLabel port_label = new JLabel("��Ʈ��ȣ");
		port_tf = new JTextField(10);
		port_btn = new JButton("����");
		JScrollPane scroll = new JScrollPane(textArea);

		con = getContentPane();
		con.add(panel);
		
		
		panel.add(scroll, "Center");
		scroll.setPreferredSize(new Dimension(280, 430));
		panel.add(port_label, "South");
		panel.add(port_tf);
		panel.add(port_btn);

		setTitle("ä�ü���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	void connect() {
		port_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (port_btn.getText().equals("����")) {
					port_btn.setText("����");
					int port = Integer.parseInt(port_tf.getText());
					try {
						ss = new ServerSocket(port);
						
						textArea.append("��Ʈ��ȣ : " + ss.getLocalPort() + "�� ���Ӽ���!!!\n");
						Thread th = new Thread(new Runnable() {

							@Override
							public void run() {
								while (true) {
									try {
										sc = ss.accept();
										textArea.append(sc.getInetAddress() + " �����Ͽ����ϴ�.\n");
										clientSocket.add(sc);
										PrintWriter p = new PrintWriter(sc.getOutputStream());
										clientWriter.add(p);
										textArea.append("�����ο��� [" + clientSocket.size() + "��] �Դϴ�.\n");
										new ChatManager(sc, p).start();										
									} catch (Exception e) {
										break;
									}
								}

							}
						});
						th.start();
					} catch (IOException e1) {
						System.err.println("���� ����!!");
					}

				} else if (port_btn.getText().equals("����")) {
					port_btn.setText("����");
					try {
						ss.close();
						textArea.append("��������!!\n");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
	}

	public static void main(String[] args) {
		new Server();
	}
}

class ChatManager extends Thread {
	
	Socket sc;
	PrintWriter p;


	public ChatManager(Socket sc, PrintWriter p) {
		this.sc = sc;
		this.p = p;
	}

	public void sendAll(String msg) {
		for (PrintWriter writer : Server.clientWriter) {
			/*
			if (writer.equals(p))
				continue;
			*/
			writer.println(msg);
			writer.flush();
		}
	}

	@Override
	public void run() {
		sendAll("'" + sc.getInetAddress() + "'���� �����ϼ̽��ϴ�.");

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(sc.getInputStream()));

			String receiveMsg;

			while (true) {
				receiveMsg = reader.readLine();

				if (receiveMsg == null) {
					sendAll("'" + sc.getInetAddress() + "'�� �������ϴ�.");
					break;
				}
				sendAll(receiveMsg);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (Server.clientSocket.contains(sc)) {
					Server.clientSocket.remove(sc);
					System.out.println("���� ��Ȳ : " + Server.clientSocket.toString());
				}
				if (Server.clientSocket.contains(p)) {
					Server.clientSocket.remove(p);
					System.out.println("PrintWriter ��Ȳ : " + Server.clientWriter.toString());
				}
				if (p != null) {
					p.close();
				}
				if (sc != null) {
					sc.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
