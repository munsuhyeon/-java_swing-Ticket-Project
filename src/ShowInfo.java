import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ShowInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JLabel lblpic = new JLabel("");
	private final JLabel lbltitle = new JLabel("제목");
	private final JLabel lblNewLabel = new JLabel("장소");
	private final JLabel lblplace = new JLabel("장소");
	private final JLabel lblNewLabel_2 = new JLabel("날짜");
	private final JLabel lbldate = new JLabel("날짜");
	private final JLabel lblNewLabel_3 = new JLabel("공연시간");
	private final JLabel lbltime = new JLabel("공연시간");
	private final JLabel lblNewLabel_4 = new JLabel("가격");
	private final JLabel lblprice = new JLabel("가격");
	private final JButton btnbuy = new JButton("예매하기");
	
	public ShowInfo(String title,String place,String date,String time,String price, String Pic) {
		setBounds(100, 100, 718, 549);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
			lblpic.setBackground(Color.WHITE);
		
			
			lblpic.setOpaque(true);
			lblpic.setForeground(Color.BLACK);
			lblpic.setBounds(12, 85, 300, 400);
			lblpic.setText("<html><img src='" + Pic + "'></html>");
			contentPanel.add(lblpic);
			
			
			
			lbltitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
			
			lbltitle.setBounds(12, 27, 725, 48);
			lbltitle.setText(title);
			contentPanel.add(lbltitle);
			lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblNewLabel.setBounds(345, 85, 50, 28);
			
			contentPanel.add(lblNewLabel);
			lblplace.setHorizontalAlignment(SwingConstants.CENTER);
			lblplace.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			lblplace.setBounds(431, 85, 255, 28);
			lblplace.setText(place);
			contentPanel.add(lblplace);
			lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblNewLabel_2.setBounds(345, 148, 74, 28);
			
			contentPanel.add(lblNewLabel_2);
			lbldate.setHorizontalAlignment(SwingConstants.CENTER);
			lbldate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			lbldate.setBounds(431, 148, 255, 28);
			lbldate.setText(date);
			contentPanel.add(lbldate);
			lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblNewLabel_3.setBounds(345, 216, 87, 28);
			
			contentPanel.add(lblNewLabel_3);
			lbltime.setHorizontalAlignment(SwingConstants.CENTER);
			lbltime.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			lbltime.setBounds(431, 216, 255, 28);
			lbltime.setText(time+"분");
			contentPanel.add(lbltime);
			lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblNewLabel_4.setBounds(345, 285, 50, 28);
			
			contentPanel.add(lblNewLabel_4);
			lblprice.setHorizontalAlignment(SwingConstants.CENTER);
			lblprice.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			lblprice.setBounds(431, 285, 255, 28);
			lblprice.setText(price+"원");
			contentPanel.add(lblprice);
			btnbuy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuyTicket dlg = new BuyTicket(title,date);
					dlg.setModal(true);
					dlg.setVisible(true);
					dispose();
				}
			});
			btnbuy.setBounds(431, 450, 255, 35);
			
			contentPanel.add(btnbuy);
		}
	}

