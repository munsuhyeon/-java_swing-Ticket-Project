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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Join extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JTextField txtpw;
	private JTextField txtpw_check;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphone;
	private Statement stmt;
	private Connection con;
	
	private void SQLConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String temp = "jdbc:mysql://localhost/show_ticket?user=root&password=1234";
		con = DriverManager.getConnection(temp);
		stmt =  con.createStatement();
	}
	
	
	public Join() {
		setBounds(100, 100, 393, 730);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbllogo = new JLabel("");
		lbllogo.setIcon(new ImageIcon(Join.class.getResource("/img/logo237.png")));
		lbllogo.setBounds(78, 25, 237, 121);
		contentPanel.add(lbllogo);
		
		txtid = new JTextField();
		txtid.setColumns(10);
		txtid.setBounds(78, 187, 237, 23);
		contentPanel.add(txtid);
		
		JLabel lblid = new JLabel("아이디");
		lblid.setFont(new Font("굴림", Font.PLAIN, 14));
		lblid.setBounds(78, 162, 50, 25);
		contentPanel.add(lblid);
		
		JButton btncheck = new JButton("중복확인");
		btncheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SQLConnection();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql = "SELECT * FROM memberTBL where id='" + txtid.getText() + "'";
				ResultSet rs = null;
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(rs.next()) {
						JOptionPane.showMessageDialog(null,"이미 존재하는 아이디입니다");
						txtid.setText("");
						txtid.requestFocus();
					}else {
						JOptionPane.showMessageDialog(null,"사용가능한 아이디입니다");
						txtpw.requestFocus();
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btncheck.setBounds(224, 220, 91, 23);
		contentPanel.add(btncheck);
		
		JLabel lblpw = new JLabel("비밀번호");
		lblpw.setFont(new Font("굴림", Font.PLAIN, 14));
		lblpw.setBounds(78, 251, 79, 25);
		contentPanel.add(lblpw);
		
		txtpw = new JTextField();
		txtpw.setColumns(10);
		txtpw.setBounds(78, 284, 237, 23);
		contentPanel.add(txtpw);
		
		JLabel lblpw_check = new JLabel("비밀번호 확인");
		lblpw_check.setFont(new Font("굴림", Font.PLAIN, 14));
		lblpw_check.setBounds(78, 317, 123, 25);
		contentPanel.add(lblpw_check);
		
		txtpw_check = new JTextField();
		txtpw_check.setColumns(10);
		txtpw_check.setBounds(78, 352, 237, 23);
		contentPanel.add(txtpw_check);
		
		JLabel lblname = new JLabel("이름");
		lblname.setFont(new Font("굴림", Font.PLAIN, 14));
		lblname.setBounds(78, 390, 123, 25);
		contentPanel.add(lblname);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(78, 425, 237, 23);
		contentPanel.add(txtname);
		
		JLabel lblemail = new JLabel("이메일");
		lblemail.setFont(new Font("굴림", Font.PLAIN, 14));
		lblemail.setBounds(78, 458, 123, 25);
		contentPanel.add(lblemail);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(78, 493, 237, 23);
		contentPanel.add(txtemail);
		
		JLabel lblphone = new JLabel("전화번호");
		lblphone.setFont(new Font("굴림", Font.PLAIN, 14));
		lblphone.setBounds(78, 526, 123, 25);
		contentPanel.add(lblphone);
		
		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(78, 562, 237, 23);
		contentPanel.add(txtphone);
		
		JButton btnjoin = new JButton("가입하기");
		btnjoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtpw_check.getText().equals(txtpw.getText())) {
					
					try {
						SQLConnection();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String sql = "INSERT INTO membertbl VALUES(null,'";
					sql = sql + txtid.getText() + "','" + txtpw.getText() + "','";
					sql = sql + txtname.getText() + "','" + txtemail.getText() + "','";
					sql = sql + txtphone.getText() + "');";
					try {
						if(stmt.executeUpdate(sql) >= 1) {
							JOptionPane.showMessageDialog(null, "회원가입 완료");
							dispose();
						}else
							JOptionPane.showMessageDialog(null, "회원가입 실패");
					}catch (HeadlessException e1) {
						e1.printStackTrace();
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,"비밀번호가 일치하지않습니다");
					txtpw_check.setText("");
					txtpw_check.requestFocus();
				}
			}
		});
		btnjoin.setBounds(78, 612, 237, 34);
		contentPanel.add(btnjoin);
	}
	
}
