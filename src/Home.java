import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Home extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Vector data;
	private final JButton btnNewButton = new JButton("New button");
	private final JButton btnNewButton_1 = new JButton("New button");
	private final JButton btnNewButton_2 = new JButton("마이페이지");
	private final JButton btnNewButton_3 = new JButton("로그아웃");
	private final JTextField textField = new JTextField();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table;
	private final JLabel userid = new JLabel("userid");
	
	public Home(String id) throws Exception {
		textField.setText("검색어 입력");
		textField.setColumns(10);
		setBounds(100, 100, 793, 696);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		contentPanel.add(toolBar, BorderLayout.NORTH);
		
		toolBar.add(btnNewButton);
		
		toolBar.add(btnNewButton_1);
		
		toolBar.add(btnNewButton_2);
		
		toolBar.add(btnNewButton_3);
		userid.setForeground(Color.BLUE);
		
		toolBar.add(userid);
		userid.setText(id+"님 환영합니다");
		
		toolBar.add(textField);
		
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("공연번호");
		columnNames.add("제목");
		columnNames.add("장소");
		columnNames.add("날짜");
		columnNames.add("공연시간");
		columnNames.add("가격");
		columnNames.add("수량");
		columnNames.add("가격타입");
		columnNames.add("나이");
		columnNames.add("이미지");
		data = new Vector<>();
		
		ShowAllConcerts(); //data를 가져온다.
		
		DefaultTableModel dtm = new DefaultTableModel(data,columnNames);
		table = new JTable(dtm);
		
		JPopupMenu popup = new JPopupMenu();
		JMenuItem mnuBuy1 = new JMenuItem("예매하기");
		JMenuItem mnuCancel = new JMenuItem("취소하기");
		popup.add(mnuBuy1);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int column = table.columnAtPoint(e.getPoint());
				if(!table.isRowSelected(row)) {
					table.changeSelection(row, column, false, false);
				}
				int amount = Integer.parseInt(table.getValueAt(row, 4).toString());
				
				if(amount != 0) {
					mnuBuy1.setEnabled(true);
					popup.show(e.getComponent(), e.getX()+5, e.getY()-5);
				}else {
					mnuBuy1.setEnabled(false);
					popup.show(e.getComponent(), e.getX()+5, e.getY()-5);
				}
				
				String title = table.getValueAt(row, 0).toString();
			}
		});
		mnuBuy1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				TableModel data = table.getModel();
				
				String title = table.getValueAt(row,1).toString();
				String place = table.getValueAt(row,2).toString();
				String date = table.getValueAt(row,3).toString();
				String time = table.getValueAt(row,4).toString();
				String price = table.getValueAt(row,5).toString();
				String pic = table.getValueAt(row,9).toString();
				
				ShowInfo dlg = new ShowInfo(title,place,date,time,price,pic,id);
				dlg.setModal(true);
				dlg.setVisible(true);
			}
		});
		
		scrollPane.setViewportView(table);	
		
	}

	private void ShowAllConcerts() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String temp = "jdbc:mysql://localhost/show_ticket?user=root&password=1234";
		Connection con = DriverManager.getConnection(temp);
		Statement stmt = con.createStatement();
		String sql = "select * from concerttbl";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Vector row = new Vector<>();
			for(int i=0;i<10;i++)
				row.add(rs.getString(i+1));
			data.add(row);
		}
		rs.close(); stmt.close(); con.close();
	}	
}

