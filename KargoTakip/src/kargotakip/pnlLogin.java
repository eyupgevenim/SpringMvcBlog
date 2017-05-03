package kargotakip;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import kargotakip.op.Personel;

public class pnlLogin extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtF_email;
	private JPasswordField pswF_sifre;

	
	public pnlLogin(int _w,int _h) {
		setBounds((_w-507)/2, (_h-400)/2, 507, 400);
		setBackground(new Color(102, 255, 204));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(337, 5, 1, 1);
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel);
		
		JLabel label = new JLabel("Email:");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(93, 55, 86, 31);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Parola:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(93, 111, 86, 31);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(189, 55, 153, 31);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(189, 114, 153, 31);
		panel.add(textField_1);
		
		JButton button = new JButton("Giri\u015F Yap");
		button.setFont(new Font("Tahoma", Font.BOLD, 16));
		button.setBounds(189, 188, 154, 40);
		panel.add(button);
		
		txtF_email = new JTextField();
		txtF_email.setBounds(191, 155, 185, 31);
		add(txtF_email);
		txtF_email.setColumns(10);
		
		JLabel label_2 = new JLabel("Email:");
		label_2.setBounds(95, 155, 86, 31);
		add(label_2);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel label_3 = new JLabel("Parola:");
		label_3.setBounds(95, 215, 86, 31);
		add(label_3);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		// Giriþ Yap buttonu ve kontrolleri
		JButton btn_girisYap = new JButton("Giri\u015F Yap");
		btn_girisYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email=txtF_email.getText().trim();
				@SuppressWarnings("deprecation")
				String sifre=pswF_sifre.getText().trim();
				
				if(!email.equals("") && !sifre.equals("")){
					Personel _p=new Personel();
					if( _p.girisYap(email,new Personel().MD5(sifre) ) ){
						int pT=frmMain.PERSONELTIP_;
						frmMain.setMenu(frmMain.ADI+" "+frmMain.SOYADI);
						frmMain.PANEL.removeAll();
						if(pT==1){
							frmMain.PANEL.add(new pnlGenelMudur(_w,_h));
						}else if(pT==2){
							frmMain.PANEL.add(new pnlSubeMudur(_w,_h));
						}else if(pT==3){
							frmMain.PANEL.add(new pnlGenelSubePersonel(_w,_h));
						}
						frmMain.PANEL.invalidate();
						frmMain.PANEL.revalidate();
						frmMain.PANEL.repaint();
						
					}else{
						JOptionPane.showMessageDialog(null, 
								"Email veya Þifreyi yanlýþ girdiniz !!!", "Yanlýþ Email vaya þifre hatasý !", 
								JOptionPane.ERROR_MESSAGE);
						
					}
					
				}else{
					JOptionPane.showMessageDialog(null, 
							"Email veya Þifreyi boþ geçemezsiniz !!!", "Boþ alan býrakma hatasý !", 
							JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});
		btn_girisYap.setBackground(new Color(51, 255, 102));
		btn_girisYap.setIcon(new ImageIcon("img\\giris-yap.png"));
		btn_girisYap.setBounds(191, 289, 185, 51);
		add(btn_girisYap);
		btn_girisYap.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 204, 102));
		panel_1.setBounds(0, 0, 506, 73);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Giri\u015F Yap");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblNewLabel.setBounds(0, 0, 506, 73);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img\\user-email.png"));
		lblNewLabel_1.setBounds(378, 155, 37, 31);
		add(lblNewLabel_1);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("img\\sifre.png"));
		label_4.setBounds(378, 218, 37, 31);
		add(label_4);
		
		pswF_sifre = new JPasswordField();
		pswF_sifre.setBounds(191, 218, 185, 31);
		add(pswF_sifre);

		
	}
	
}
