package kargotakip;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import kargotakip.op.Personel;

public class dlgSifreDegistirme extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField pswF_eski;
	private JPasswordField pswF_yeni;
	private JPasswordField pswF_yeniTekrar;

	/**
	 * Create the dialog.
	 */
	public dlgSifreDegistirme(frmMain f) {
		super(f);
		setTitle("\u015Eifre De\u011Fi\u015Ftirme Formu");
		setBounds(400, 100, 501, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(60, 179, 113));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Eski \u015Eifreni Gir:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(43, 62, 140, 34);
		contentPanel.add(lblNewLabel);
		
		JLabel lblYeniifreniGir = new JLabel("Yeni \u015Eifreni Gir:");
		lblYeniifreniGir.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYeniifreniGir.setBounds(43, 159, 140, 34);
		contentPanel.add(lblYeniifreniGir);
		
		JLabel lblYeniifreTekrar = new JLabel("Yeni \u015Eifre Tekrar\u0131:");
		lblYeniifreTekrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblYeniifreTekrar.setBounds(43, 256, 140, 34);
		contentPanel.add(lblYeniifreTekrar);
		
		pswF_eski = new JPasswordField();
		pswF_eski.setEchoChar('*');
		pswF_eski.setBounds(194, 62, 222, 34);
		contentPanel.add(pswF_eski);
		
		pswF_yeni = new JPasswordField();
		pswF_yeni.setEchoChar('*');
		pswF_yeni.setBounds(194, 159, 222, 34);
		contentPanel.add(pswF_yeni);
		
		pswF_yeniTekrar = new JPasswordField();
		pswF_yeniTekrar.setEchoChar('*');
		pswF_yeniTekrar.setBounds(194, 254, 222, 34);
		contentPanel.add(pswF_yeniTekrar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(46, 139, 87));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// Kaydet buttonu
				JButton btn_kayit = new JButton("Kaydet");
				btn_kayit.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						String eS;
						String yS;
						String ySt;
						if(!( eS = pswF_eski.getText().trim() ).equals("") 
								&& !( yS = pswF_yeni.getText().trim() ).equals("") 
								&& !( ySt = pswF_yeniTekrar.getText().trim() ).equals("")){
							
							if(yS.equals(ySt)){
								if(new Personel().sifreGuncelleme(frmMain.EMAIL, new Personel().MD5(eS), new Personel().MD5(yS)) ){
									JOptionPane.showMessageDialog(null, 
											"Baþarlý bir þekilde þifreniz deðiþtirildi.", "Baþrlý ", 
											JOptionPane.ERROR_MESSAGE);
									dispose();
								}else{
									JOptionPane.showMessageDialog(null, 
											"Eski þifrenizi tekrar deneyiniz !!!", "Eski þifre hatasý !", 
											JOptionPane.ERROR_MESSAGE);
								}
							}else{
								JOptionPane.showMessageDialog(null, 
										"Yeni þifre ile tekrarý eþleþmiyor !!!", "Yeni þifreler eþleþmeme hatasý !", 
										JOptionPane.ERROR_MESSAGE);
							}
							
						}else{
							JOptionPane.showMessageDialog(null, 
									"Herhangi bir alaný boþ geçemezsiniz !!!", "Boþ Býrakma hatasý !", 
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btn_kayit.setBackground(new Color(50, 205, 50));
				btn_kayit.setIcon(new ImageIcon("img\\kaydet.png"));
				btn_kayit.setActionCommand("OK");
				buttonPane.add(btn_kayit);
				getRootPane().setDefaultButton(btn_kayit);
			}
			{
				// iptal buttonu
				JButton btn_iptal = new JButton("\u0130ptal Et");
				btn_iptal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
						
					}
				});
				btn_iptal.setBackground(new Color(255, 69, 0));
				btn_iptal.setIcon(new ImageIcon("img\\iptal.png"));
				btn_iptal.setActionCommand("Cancel");
				buttonPane.add(btn_iptal);
			}
		}
	}
}
