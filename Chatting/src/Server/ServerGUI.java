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
		JLabel port_label = new JLabel("��Ʈ��ȣ");
		port_tf = new JTextField(10);
		port_btn = new JButton("����");
		
		con = getContentPane();		
		con.add(panel);
		
		panel.add(list, "Center");
		list.setPreferredSize(new Dimension(250, 420));
		panel.add(port_label, "South");
		panel.add(port_tf);
		panel.add(port_btn);		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//������ �����ڴ�.
		setSize(300,500);
		setVisible(true);		
	}
	
	void connect(){
		port_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(port_btn.getText());
				if(port_btn.getText().equals("����")){
					port_btn.setText("����");
					int port = Integer.parseInt(port_tf.getText());
					System.out.println(port);
					
					new ServerMain(port).start();
					
				} else if(port_btn.getText().equals("����")) {
					port_btn.setText("����");
					
				}				
			}
		});
	}
	
	public static void main(String[] args) {
		new ServerGUI();
	}
}
