import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JPasswordField txtpw;
	private Statement stmt;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 364, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("logo");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/logo237.png")));
		lblNewLabel.setBounds(64, 22, 237, 121);
		contentPanel.add(lblNewLabel);
		
		txtid = new JTextField();
		txtid.setText("tiger");
		txtid.setFont(new Font("굴림", Font.BOLD, 12));
		txtid.setColumns(10);
		txtid.setBounds(64, 153, 237, 34);
		contentPanel.add(txtid);
		
		JButton btnlogin = new JButton("로그인");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				String pw = txtpw.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String temp = "jdbc:mysql://localhost/show_ticket?user=root&password=1234";
				Connection con = null;
				try {
					con = DriverManager.getConnection(temp);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					stmt =  con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String sql = "select * from membertbl where ID ='" +id+ "'and PW='" +pw+"'";
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					if(rs.next() == true ) {
						JOptionPane.showMessageDialog(null, txtid.getText()+"님 안녕하세요");
						dispose();
						Home home;
						try {
							home = new Home();
							home.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "로그인실패");
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnlogin.setBounds(64, 268, 237, 34);
		contentPanel.add(btnlogin);
		
		txtpw = new JPasswordField("123456");
		txtpw.setFont(new Font("굴림", Font.BOLD, 12));
		txtpw.setBounds(64, 208, 237, 34);
		contentPanel.add(txtpw);
		
		JButton btnNewButton = new JButton("아이디찾기");
		btnNewButton.setBounds(64, 376, 115, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnjoin = new JButton("회원가입");
		btnjoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Join dlg = new Join();
				dlg.setVisible(true);
				dlg.setModal(true);
			}
		});
		btnjoin.setBounds(64, 320, 237, 34);
		contentPanel.add(btnjoin);
		
		JButton btnNewButton_2 = new JButton("비밀번호찾기");
		btnNewButton_2.setBounds(186, 376, 115, 23);
		contentPanel.add(btnNewButton_2);
	}
}
