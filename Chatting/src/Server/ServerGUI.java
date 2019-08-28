package Server;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerGUI extends JFrame {
	
	public ServerGUI() {
		init();
		connect();
	}
	
	ServerSocket ss;
	Socket sc;
	JTextField port_tf;
	JButton port_btn;
	
	
	void init() {
		Container con;
		JPanel panel = new JPanel();
		JList list = new JList();
		JLabel port_label = new JLabel("포트번호");
		port_tf = new JTextField(10);
		port_btn = new JButton("접속");
		
		con = getContentPane();		
		con.add(panel);
		
		panel.add(list, "Center");
		list.setPreferredSize(new Dimension(250, 420));
		panel.add(port_label, "South");
		panel.add(port_tf);
		panel.add(port_btn);		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//닫으면 꺼지겠다.
		setSize(300,500);
		setVisible(true);		
	}
	
	void connect(){
		port_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(port_btn.getText());
				if(port_btn.getText().equals("접속")){
					port_btn.setText("종료");
					int port = Integer.parseInt(port_tf.getText());
					System.out.println(port);
					
					new ServerMain(port).start();
					
				} else if(port_btn.getText().equals("종료")) {
					port_btn.setText("접속");
					
				}				
			}
		});
	}
	
	public static void main(String[] args) {
		new ServerGUI();
	}
}
