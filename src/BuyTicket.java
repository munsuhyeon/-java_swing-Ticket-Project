import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class BuyTicket extends JDialog {
	private final JPanel toppanel = new JPanel();
	private final JLabel lbltitle = new JLabel("New label");
	private final JPanel panel = new JPanel();
	private JToggleButton[] seats = new JToggleButton[100];
	private final JPanel calendarpanel = new JPanel();
	private final JPanel seatpanel = new JPanel();
	private final JPanel infopanel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("STAGE");
	private final JPanel setas_panel = new JPanel();
	private final JPanel panel_4 = new JPanel();
	private final JPanel panelCal = new JPanel();
	private final JButton btnMonthPrevious = new JButton("<");
	private final JTextField txtYear = new JTextField();
	private final JLabel lblYear = new JLabel("년");
	private final JTextField txtMonth = new JTextField();
	private final JLabel lblMonth = new JLabel("월");
	private final JButton btnCreate = new JButton("생성");
	private final JButton btnMonthNext = new JButton(">");
	private boolean[] bSchedule = new boolean[31]; 
	String mDate = "";
	int year, month, day;
	private final JLabel lbldate = new JLabel("New label");
	private final JPanel infopanel_1 = new JPanel();
	private final JButton btnNewButton = new JButton("구매하기");
	private final JLabel lblNewLabel_1 = new JLabel("선택한 좌석");
	private final JLabel lblNewLabel_2 = new JLabel("선택한 날짜");
	private final JTextArea textArea = new JTextArea();
	private final JTextArea textArea_1 = new JTextArea();
	private Statement stmt;
	private ResultSet rs;
	
	public String getMDate() {
		return mDate;
	}
	public BuyTicket(String title, String date, String id) {
		setTitle(id+"님");
		setBounds(100, 100, 1300, 584);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		getContentPane().add(toppanel, BorderLayout.NORTH);
		
		toppanel.add(lbltitle);
		lbltitle.setText(title);
		lbltitle.setHorizontalAlignment(SwingConstants.LEFT);
		lbldate.setHorizontalAlignment(SwingConstants.CENTER);
		
		toppanel.add(lbldate);
		lbldate.setText(date);
		

		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		calendarpanel.setBounds(12, 25, 470, 470);
		
		panel.add(calendarpanel);
		calendarpanel.setLayout(new BorderLayout(0, 0));
		
		calendarpanel.add(panel_4, BorderLayout.NORTH);
		btnMonthPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = Integer.parseInt(txtMonth.getText());
				m--;
				if(m<1) {
					m=12;
					int y = Integer.parseInt(txtYear.getText());
					y--;
					txtYear.setText(Integer.toString(y));
				}
				txtMonth.setText(Integer.toString(m));

				setTitle(txtYear.getText() + "년도 달력");
				// 원래의 버튼들을 삭제한다.
				Component[] componentList = panelCal.getComponents();
				for(Component c: componentList) {
					if(c instanceof JButton)
						panelCal.remove(c);
				}
				panelCal.revalidate();
				panelCal.repaint();
				
				String week[]= {"일","월","화","수","목","금","토"};
				for(int i=0;i<7;i++) {
					JButton btnWeek = new JButton(week[i]);

					if(i%7==6) {  //토요일
						btnWeek.setBackground(Color.blue);
						btnWeek.setForeground(Color.white);
					}
					else if(i%7==0) {//일요일
						btnWeek.setBackground(Color.red);
						btnWeek.setForeground(Color.white);
					}
					else
						btnWeek.setBackground(Color.GREEN);
					panelCal.add(btnWeek);
					panelCal.revalidate();
				}
				int sum=0;
				int index=1; // 1922년 1월 1일의 위치
				int curYear = Integer.parseInt(txtYear.getText());
				int curMonth = Integer.parseInt(txtMonth.getText());
				int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
				for(int year=1922 ; year<curYear ; year++) {
					if(year%4==0 && year%100!=0 || year%400==0)
						sum = sum + 366;
					else
						sum = sum + 365;
				}
				for(int month=0;month<curMonth-1;month++) {
					if(curMonth==2 && (curYear%4==0 && curYear%100!=0 || curYear%400==0))
						sum = sum + ++Months[month];
					else
						sum = sum + Months[month];
				}				
				index = sum%7;
				for(int i=1; i<index+1 ;i++) {
					JButton btn = new JButton(" ");
					panelCal.add(btn);
					btn.setVisible(false);
				}
				// 월의 마지막 날짜에 맞춰 버튼을 생성
				for(int i=1;i<=Months[curMonth-1];i++) {
					JButton btn = null;
					btn = new JButton(Integer.toString(i));
					
					if((index+i)%7==0) {
						btn.setBackground(Color.blue);
						btn.setForeground(Color.white);
					}
					else if((index+i)%7==1) {
						btn.setBackground(Color.red);
						btn.setForeground(Color.white);
					}
					else if(day==i && Integer.toString(month).equals(txtMonth.getText().trim()) &&
							Integer.toString(year).equals(txtYear.getText().trim()))
						btn.setBackground(Color.yellow);
					else
						btn.setBackground(Color.white);					
										
					panelCal.add(btn);					
					panelCal.revalidate();	
					
					btn.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							JButton btn1 = (JButton) e.getSource();
							String year = txtYear.getText();
							String month = txtMonth.getText();
							String day = btn1.getText();							
							mDate = year + "-" + month + "-" + day;
							setVisible(false);
						}
					});
				}
			}
		});
		
		panel_4.add(btnMonthPrevious);
		txtYear.setText("2022");
		txtYear.setHorizontalAlignment(SwingConstants.RIGHT);
		txtYear.setColumns(10);
		
		panel_4.add(txtYear);
		
		panel_4.add(lblYear);
		txtMonth.setText("6");
		txtMonth.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMonth.setFont(new Font("굴림", Font.BOLD, 12));
		txtMonth.setColumns(10);
		
		panel_4.add(txtMonth);
		
		panel_4.add(lblMonth);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle(txtYear.getText() + "년도 달력");
				// 원래의 버튼들을 삭제한다.
				Component[] componentList = panelCal.getComponents();
				for(Component c: componentList) {
					if(c instanceof JButton)
						panelCal.remove(c);
				}
				panelCal.revalidate();
				panelCal.repaint();
				
				String week[]= {"일","월","화","수","목","금","토"};
				for(int i=0;i<7;i++) {
					JButton btnWeek = new JButton(week[i]);
					if(i%7==6) {  //토요일
						btnWeek.setBackground(Color.blue);
						btnWeek.setForeground(Color.white);
					}
					else if(i%7==0) {//일요일
						btnWeek.setBackground(Color.red);
						btnWeek.setForeground(Color.white);
					}
					else
						btnWeek.setBackground(Color.GREEN);
					
					panelCal.add(btnWeek);
					panelCal.revalidate();
				}
				int sum=0;
				int index=1; // 1922년 1월 1일의 위치
				int curYear = Integer.parseInt(txtYear.getText());
				int curMonth = Integer.parseInt(txtMonth.getText());
				int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
				for(int year=1922 ; year<curYear ; year++) {
					if(year%4==0 && year%100!=0 || year%400==0)
						sum = sum + 366;
					else
						sum = sum + 365;
				}
				for(int month=0;month<curMonth-1;month++) {
					if(curMonth==2 && (curYear%4==0 && curYear%100!=0 || curYear%400==0))
						sum = sum + ++Months[month];
					else
						sum = sum + Months[month];
				}				
				index = sum%7;
				for(int i=1; i<index+1 ;i++) {
					JButton btn = new JButton(" ");
					panelCal.add(btn);
					btn.setVisible(false);
				}
				// 월의 마지막 날짜에 맞춰 버튼을 생성
				for(int i=1;i<=Months[curMonth-1];i++) {
					JButton btn = null;
					btn = new JButton(Integer.toString(i));
					
					if((index+i)%7==0) {
						btn.setBackground(Color.blue);
						btn.setForeground(Color.white);
					}
					else if((index+i)%7==1) {
						btn.setBackground(Color.red);
						btn.setForeground(Color.white);
					}
					else if(day==i && Integer.toString(month).equals(txtMonth.getText().trim()) &&
							Integer.toString(year).equals(txtYear.getText().trim()))
						btn.setBackground(Color.yellow);
					else
						btn.setBackground(Color.white);					
					
					panelCal.add(btn);					
					panelCal.revalidate();	
					
					btn.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							JButton btn1 = (JButton) e.getSource();
							String year = txtYear.getText();
							String month = txtMonth.getText();
							String day = btn1.getText();							
							mDate = year + "-" + month + "-" + day;
							setVisible(false);
						}
					});
				}
			}
		});
		
		
		panel_4.add(btnCreate);
		btnMonthNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = Integer.parseInt(txtMonth.getText());
				m++;
				if(m>12) {
					m=1;
					int y = Integer.parseInt(txtYear.getText());
					y++;
					txtYear.setText(Integer.toString(y));
				}
				txtMonth.setText(Integer.toString(m));

				setTitle(txtYear.getText() + "년도 달력");
				// 원래의 버튼들을 삭제한다.
				Component[] componentList = panelCal.getComponents();
				for(Component c: componentList) {
					if(c instanceof JButton)
						panelCal.remove(c);
				}
				panelCal.revalidate();
				panelCal.repaint();
				
				String week[]= {"일","월","화","수","목","금","토"};
				for(int i=0;i<7;i++) {
					JButton btnWeek = new JButton(week[i]);

					if(i%7==6) {  //토요일
						btnWeek.setBackground(Color.blue);
						btnWeek.setForeground(Color.white);
					}
					else if(i%7==0) {//일요일
						btnWeek.setBackground(Color.red);
						btnWeek.setForeground(Color.white);
					}
					else
						btnWeek.setBackground(Color.GREEN);
					
					panelCal.add(btnWeek);
					panelCal.revalidate();
				}
				int sum=0;
				int index=1; // 1922년 1월 1일의 위치
				int curYear = Integer.parseInt(txtYear.getText());
				int curMonth = Integer.parseInt(txtMonth.getText());
				int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
				for(int year=1922 ; year<curYear ; year++) {
					if(year%4==0 && year%100!=0 || year%400==0)
						sum = sum + 366;
					else
						sum = sum + 365;
				}
				for(int month=0;month<curMonth-1;month++) {
					if(curMonth==2 && (curYear%4==0 && curYear%100!=0 || curYear%400==0))
						sum = sum + ++Months[month];
					else
						sum = sum + Months[month];
				}				
				index = sum%7;
				for(int i=1; i<index+1 ;i++) {
					JButton btn = new JButton(" ");						
					panelCal.add(btn);
					btn.setVisible(false);
				}
				// 월의 마지막 날짜에 맞춰 버튼을 생성
				for(int i=1;i<=Months[curMonth-1];i++) {
					JButton btn = null;
					btn = new JButton(Integer.toString(i));
					
					if((index+i)%7==0) {
						btn.setBackground(Color.blue);
						btn.setForeground(Color.white);
					}
					else if((index+i)%7==1) {
						btn.setBackground(Color.red);
						btn.setForeground(Color.white);
					}
					else if(day==i && Integer.toString(month).equals(txtMonth.getText().trim()) &&
							Integer.toString(year).equals(txtYear.getText().trim()))
						btn.setBackground(Color.yellow);					
					else
						btn.setBackground(Color.white);					
					
					panelCal.add(btn);					
					panelCal.revalidate();	
					
					btn.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							JButton btn1 = (JButton) e.getSource();
							String year = txtYear.getText();
							String month = txtMonth.getText();
							String day = btn1.getText();							
							mDate = year + "-" + month + "-" + day;
							setVisible(false);
						}
					});
				}
			}
		});
		ShowCalendar();
		
		panel_4.add(btnMonthNext);
		
		calendarpanel.add(panelCal, BorderLayout.CENTER);
		panelCal.setLayout(new GridLayout(0, 7, 5, 5));
		seatpanel.setBounds(494, 25, 642, 470);
		
		panel.add(seatpanel);
		seatpanel.setLayout(new BorderLayout(0, 0));
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		seatpanel.add(lblNewLabel, BorderLayout.NORTH);
		
		seatpanel.add(setas_panel, BorderLayout.CENTER);
		setas_panel.setLayout(new GridLayout(10, 10, 5, 5));
		////
		infopanel.setBounds(1148, 25, 129, 171);
		
		panel.add(infopanel);
		infopanel.setLayout(new BorderLayout(0, 0));
		
		infopanel.add(lblNewLabel_2, BorderLayout.NORTH);
		
		infopanel.add(textArea, BorderLayout.CENTER);
		infopanel_1.setBounds(1148, 222, 129, 171);
		
		panel.add(infopanel_1);
		infopanel_1.setLayout(new BorderLayout(0, 0));
		
		infopanel_1.add(lblNewLabel_1, BorderLayout.NORTH);
		
		infopanel_1.add(textArea_1, BorderLayout.CENTER);
		/*btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "선택한 날짜 : " + textArea.getText() +
						"\n" + textArea_1.getText() + "\n구매하시겠습니까?");
				String pdate = textArea.getText();
				String title = lbltitle.getText();
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
				String sql = "select * from purchaselist where TITLE ='" + title + 
						"'and PDATE='" +pdate+ "'and ID='" +  + '";
				System.out.println(sql);
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "구매가 완료되었습니다");
				dispose();
				Home home;
				try {
					home = new Home();
					home.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});*/
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> seatlist = new ArrayList<String>();
				String seat_num = (textArea_1.getText().replaceAll("[^0-9]",","));//숫자만 출력
				seat_num = seat_num.replace(",,,,,", " ");//콤마5개 1개로 바꾸기
				seat_num = seat_num.substring(0, seat_num.length()-4);//마지막콤마 제거
				seatlist.add(seat_num);
				for(int i = 0; i<seatlist.size(); i++) {
					String num = seatlist.get(i);
					System.out.println(num + "   ");
				}
				/*JOptionPane.showMessageDialog(null, "선택한 날짜 : " + textArea.getText() +
						"\n" + textArea_1.getText() + "\n구매하시겠습니까?");
				String pdate = textArea.getText();
				String title = lbltitle.getText();
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
				for(int i = 0; i<seatlist.size(); i++) {
					String sql = "select * from purchaselist where ID ='" + id + "'TITLE='" + title +
							 "'SEATS='" + seatlist.get(i) + "'and PDATE='" +pdate+"'";	
				System.out.println(sql);
				
				try {
					rs = stmt.executeQuery(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				JOptionPane.showMessageDialog(null, "구매가 완료되었습니다");
				dispose();
				Home home;
				try {
					home = new Home(id);
					home.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
            	
			}
		});
		btnNewButton.setBounds(1148, 403, 129, 92);
		
		panel.add(btnNewButton);
		
		ActionListener seatSelectionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showtextArea();
            }
		};
            for(int i=0; i<seats.length; i++) {
            	JToggleButton btn = new JToggleButton(Integer.toString(i+1));
            	btn.addActionListener(seatSelectionListener);
            	seats[i] = btn;
            	btn.setBackground(Color.PINK);
            	setas_panel.add(btn);
            }
			}//BuyTicket
		/*for(int i=0; i<100; i++) {
				btn = new JButton(Integer.toString(i+1));
				btn.setVisible(true);
				setas_panel.add(btn);	
		
		btn.addActionListener(new ActionListener() {						
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				String info = btn.getText();							
				lblinfo.setText(info+"번 좌석");
			}
		});
		}*/
		
	private void showtextArea() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<seats.length; i++) {
			JToggleButton btn = seats[i];
			if(btn.isSelected()) {
				sb.append(btn.getText());
				sb.append("번 좌석\n");
			}
		}
		String s = sb.toString();
		if(s.length()>0) {
			s = s.substring(0, s.length()-1);
		}
		textArea_1.setText(s);
	}	
	
	private void ShowCalendar() {
		 // 현재 년 월의 달력 보여주는 곳!!
		// 원래의 버튼들을 삭제한다.
		Component[] componentList = panelCal.getComponents();
		for(Component c: componentList) {
			if(c instanceof JButton)
				panelCal.remove(c);
		}
		panelCal.revalidate();
		panelCal.repaint();
		
		String week[]= {"일","월","화","수","목","금","토"};
		for(int i=0;i<7;i++) {
			JButton btnWeek = new JButton(week[i]);

			if(i%7==6) {  //토요일
				btnWeek.setBackground(Color.blue);
				btnWeek.setForeground(Color.white);
			}
			else if(i%7==0) {//일요일
				btnWeek.setBackground(Color.red);
				btnWeek.setForeground(Color.white);
			}
			else
				btnWeek.setBackground(Color.GREEN);
			
			panelCal.add(btnWeek);
			panelCal.revalidate();
		}
		int sum=0;
		int index=1; // 1922년 1월 1일의 위치
		int curYear = Integer.parseInt(txtYear.getText());
		int curMonth = Integer.parseInt(txtMonth.getText());
		int Months[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		for(int year=1922 ; year<curYear ; year++) {
			if(year%4==0 && year%100!=0 || year%400==0)
				sum = sum + 366;
			else
				sum = sum + 365;
		}
		for(int month=0;month<curMonth-1;month++) {
			if(curMonth==2 && (curYear%4==0 && curYear%100!=0 || curYear%400==0))
				sum = sum + ++Months[month];
			else
				sum = sum + Months[month];
		}				
		index = sum%7;
		for(int i=1; i<index+1 ;i++) {
			JButton btn = new JButton(" ");
			panelCal.add(btn);
			btn.setVisible(false);
		}
		// 월의 마지막 날짜에 맞춰 버튼을 생성
		for(int i=1;i<=Months[curMonth-1];i++) {
			JButton btn = null;
			btn = new JButton(Integer.toString(i));
			
			if((index+i)%7==0) {
				btn.setBackground(Color.blue);
				btn.setForeground(Color.white);
			}
			else if((index+i)%7==1) {
				btn.setBackground(Color.red);
				btn.setForeground(Color.white);
			}
			else if(day==i && Integer.toString(month).equals(txtMonth.getText().trim()) &&
					Integer.toString(year).equals(txtYear.getText().trim()))
				btn.setBackground(Color.yellow);
			else
				btn.setBackground(Color.white);		
			
			panelCal.add(btn);					
			panelCal.revalidate();	
			
			btn.addActionListener(new ActionListener() {						
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn1 = (JButton) e.getSource();
					String year = txtYear.getText();
					String month = txtMonth.getText();
					String day = btn1.getText();							
					mDate = year + "-" + month + "-" + day;
					setVisible(true);
					textArea.setText(mDate);
				}
			});
		}
		
	}	
	}
