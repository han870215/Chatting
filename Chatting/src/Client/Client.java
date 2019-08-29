package Client;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {
	
	// Socket 관련
	Socket sc;
	String ip;
	int port;
	
	// GUI 관련
	JTextField ip_tf;
	JTextField port_tf;
	JButton connect_btn;
	
	JTextField msg_tf;
	
	public Client() {
		init();
		action();
	}
	
	void init(){
		Container con;
		JPanel panel = new JPanel();
		// North
		JLabel ip_label = new JLabel("IP");
		JLabel port_label = new JLabel("PORT");
		ip_tf = new JTextField(9);
		port_tf = new JTextField(3);
		connect_btn = new JButton("접속");
		
		panel.add(ip_label, "North");
		panel.add(ip_tf);
		panel.add(port_label);
		panel.add(port_tf);
		panel.add(connect_btn);
		
		ip_tf.setText("127.0.0.1");
		port_tf.setText("1111");
		// Center
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setPreferredSize(new Dimension(280, 400));
		textArea.setEnabled(false);
		textArea.setEditable(false);
		
		
		panel.add(scroll, "Center");
		// South
		msg_tf = new JTextField(25);
		
		panel.add(msg_tf, "South");
		
		con = getContentPane();
		con.add(panel);
		
		
		
		setTitle("클라이언트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	void action() {
		connect_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(connect_btn.getText().equals("접속")) {
					ip_tf.setEditable(false);
					ip_tf.setEnabled(false);
					port_tf.setEditable(false);
					port_tf.setEnabled(false);
					ip = ip_tf.getText();
					port = Integer.parseInt(port_tf.getText());
					
					connect_btn.setText("종료");
					/*
					try {
						sc = new Socket(ip, port);
					} catch (UnknownHostException e1) {
	
						e1.printStackTrace();
					} catch (IOException e1) {
	
						e1.printStackTrace();
					}
					*/
				} else if(connect_btn.getText().equals("종료")) {
					System.exit(0);
				}
			}
		});
	}
	public static void main(String[] args) {
		
		new Client();
		
	}

}
