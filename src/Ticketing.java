import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Ticketing extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTabbedPane pane = new JTabbedPane();
	private Vector data;
	private JTable table1 = new JTable();
	
	public Ticketing() throws Exception {
		setTitle("티켓팅");
		setBounds(100, 100, 758, 397);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane);
		
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("제목");
		columnNames.add("장소");
		columnNames.add("날짜");
		columnNames.add("관람시간");
		columnNames.add("연령");
		data = null;//new Vector<>();
		
		//ShowAllBooks();  //data를 가져온다.
		
		DefaultTableModel dtm = new DefaultTableModel(data,columnNames);
		table1 = new JTable(dtm);
		
		tabbedPane.add("ㅇ",table1);
		
		
	}

	private void ShowAllBooks() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String temp = "jdbc:mysql://localhost/show_ticket?user=root&password=1234";
		Connection con = DriverManager.getConnection(temp);
		Statement stmt = con.createStatement();
		String sql = "select * from concerttbl";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			Vector row = new Vector<>();
			for(int i=0;i<9;i++)
				row.add(rs.getString(i+1));
			data.add(row);
		}
		System.out.println(data);
		rs.close(); stmt.close(); con.close();
	}
}
