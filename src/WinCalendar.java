import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class WinCalendar extends JDialog {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JTextField txtYear = new JTextField();
	private final JLabel lblYear = new JLabel("년");
	private final JLabel lblMonth = new JLabel("월");
	private final JTextField txtMonth = new JTextField();
	private final JButton btnCreate = new JButton("생성");
	private final JPanel panelCal = new JPanel();
	private boolean[] bSchedule = new boolean[31]; 
	String mDate = "";
	private final JButton btnMonthNext = new JButton(">");
	private final JButton btnMonthPrevious = new JButton("<");
	int year, month, day;
	
	public String getMDate() {
		return mDate;
	}
	
	public WinCalendar() {
		setModal(true);
		txtYear.setHorizontalAlignment(SwingConstants.RIGHT);;
		txtYear.setColumns(10);
		initGUI();
		
		Calendar now = Calendar.getInstance();
		year = now.get(Calendar.YEAR);
		month = now.get(Calendar.MONTH)+1;
		day = now.get(Calendar.DAY_OF_MONTH);
		
		txtYear.setText(Integer.toString(year));
		txtMonth.setText(Integer.toString(month));
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
		
		panel.add(btnMonthNext);
		setTitle(txtYear.getText() + "년도 달력");
		
		ShowCalendar();
	}
	private void ShowCalendar() { // 현재 년 월의 달력 보여주는 곳!!

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

	private void initGUI() {		
		setBounds(100, 100, 558, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(panel, BorderLayout.NORTH);
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
		
		panel.add(btnMonthPrevious);
		
		panel.add(txtYear);
		
		panel.add(lblYear);
		txtMonth.setFont(new Font("굴림", Font.BOLD, 12));
		txtMonth.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMonth.setText("1");
		txtMonth.setColumns(10);
		
		panel.add(txtMonth);
		
		panel.add(lblMonth);
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
		
		panel.add(btnCreate);
		
		contentPane.add(panelCal, BorderLayout.CENTER);
		panelCal.setLayout(new GridLayout(0, 7, 5, 5));
	}

}
