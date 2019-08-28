package Server;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame {

	// 서버 관련
	ServerSocket ss;
	Socket sc;

	// GUI 관련
	JTextArea textArea;
	JTextField port_tf;
	JButton port_btn;

	public Server() {
		init();
		connect();
	}

	void init() {
		Container con;
		JPanel panel = new JPanel();
		textArea = new JTextArea();
		textArea.setEditable(false);
		JLabel port_label = new JLabel("포트번호");
		port_tf = new JTextField(10);
		port_btn = new JButton("접속");

		con = getContentPane();
		con.add(panel);

		panel.add(textArea, "Center");
		textArea.setPreferredSize(new Dimension(250, 420));
		panel.add(port_label, "South");
		panel.add(port_tf);
		panel.add(port_btn);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 닫으면 꺼지겠다.
		setSize(300, 500);
		setVisible(true);
	}

	void connect() {
		port_btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (port_btn.getText().equals("접속")) {
					port_btn.setText("종료");
					int port = Integer.parseInt(port_tf.getText());
					try {
						ss = new ServerSocket(port);
						
						textArea.append("포트번호 : " + ss.getLocalPort() + "로 접속성공!!!\n");
						Thread th = new Thread(new Runnable() {

							@Override
							public void run() {
								while (true) {
									try {
										sc = ss.accept();
										System.out.println("상대와 연결되었습니다" + sc.getInetAddress());
									} catch (Exception e) {
										break;
									}
								}

							}
						});
						th.start();
					} catch (IOException e1) {
						System.err.println("서버 종료!!");
					}

				} else if (port_btn.getText().equals("종료")) {
					port_btn.setText("접속");
					try {
						ss.close();
						System.out.println("종료 성공!!");
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
