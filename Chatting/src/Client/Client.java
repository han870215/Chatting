package Client;

import java.awt.Container;
import java.awt.Dimension;
import java.net.Socket;

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
	
	// GUI 관련
	JTextField ip_tf;
	JTextField port_tf;
	JButton connect_btn;
	
	JTextField msg_tf;
	
	public Client() {
		init();
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

	public static void main(String[] args) {
		
		new Client();
		
	}

}
