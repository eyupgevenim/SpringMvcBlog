package kargotakip;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;

import com.toedter.calendar.JDateChooser;

import kargotakip.op.Adres;
import kargotakip.op.Personel;
import kargotakip.op.Sube;

public class pnlPersonelKayit extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDateChooser dcDogumTarihi;
	private JTextField txtTc;
	private JTextField txtSoyad;
	private JTextField txtAd;
	private JTextField txtEmail;
	private JTextField txtTel;
	private JComboBox<Object> cBox_ilce ;
	private JComboBox<Object> cBox_il;
	private JComboBox<Object> cBox_sube ;
	private JComboBox<Object> cBoxPersonelTip ;
	private JComboBox<Object> cBoxCinsiyet ;
	private JTextPane txtAdres;
	
	
	public pnlPersonelKayit() {
		setBackground(new Color(0, 128, 128));
		setBounds(80, 50, 874, 480);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Genel Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(new Color(102, 205, 170));
		panel.setBounds(25, 11, 384, 404);
		add(panel);
		
		JLabel label = new JLabel("Cinsiyet:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 340, 52, 25);
		panel.add(label);
		
		//cinsiyet
		cBoxCinsiyet = new JComboBox<Object>();
		cBoxCinsiyet.setModel(new DefaultComboBoxModel<Object>(new String[] {"Kad\u0131n", "Erkek"}));
		cBoxCinsiyet.setBounds(139, 340, 193, 25);
		panel.add(cBoxCinsiyet);
		
		//dogüm tarihi
		dcDogumTarihi = new JDateChooser();
		dcDogumTarihi.setBounds(139, 280, 193, 28);
		panel.add(dcDogumTarihi);
		
		JLabel label_1 = new JLabel("Do\u011Fum Tarihi:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(10, 281, 90, 25);
		panel.add(label_1);
		
		//tc		
		txtTc = new JTextField();
		txtTc.setColumns(10);
		txtTc.setBounds(139, 160, 193, 28);
		panel.add(txtTc);
		
		JLabel label_2 = new JLabel("T.C:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(10, 163, 52, 25);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Soyad\u0131:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(10, 104, 52, 25);
		panel.add(label_3);
		
		//soyadý
		txtSoyad = new JTextField();
		txtSoyad.setColumns(10);
		txtSoyad.setBounds(139, 100, 193, 29);
		panel.add(txtSoyad);
		
		//adý
		txtAd = new JTextField();
		txtAd.setColumns(10);
		txtAd.setBounds(139, 40, 193, 30);
		panel.add(txtAd);
		
		JLabel label_4 = new JLabel("Ad\u0131:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(10, 45, 52, 25);
		panel.add(label_4);
		
		//personel tipi
		cBoxPersonelTip = new JComboBox<Object>();
		cBoxPersonelTip.setModel(new DefaultComboBoxModel<Object>(new String[] {"\u015Eube m\u00FCd\u00FCr\u00FC", "Genel personel", "Sevkiyat personeli"}));
		if(frmMain.PERSONELTIP_ > 1){
			cBoxPersonelTip.removeAllItems();
			cBoxPersonelTip.addItem("Genel personel");
			cBoxPersonelTip.addItem("Sevkiyat personeli");
		}
		cBoxPersonelTip.setBounds(139, 218, 193, 28);
		panel.add(cBoxPersonelTip);
		
		JLabel label_5 = new JLabel("Personel Tipi:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(10, 218, 90, 25);
		panel.add(label_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "\u0130leti\u015Fim Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(102, 205, 170));
		panel_1.setBounds(434, 11, 415, 404);
		add(panel_1);
		
		//ilçe
		cBox_ilce = new JComboBox<Object>();
		cBox_ilce.setBounds(112, 252, 193, 23);
		panel_1.add(cBox_ilce);
		
		//il
		cBox_il = new JComboBox<Object>(new Adres().illerVector());
		cBox_il.setBounds(112, 204, 193, 27);
		cBox_il.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        
		    	panel_1.remove(cBox_ilce);
		    	Adres.Item selected_item = (Adres.Item) cBox_il.getSelectedItem();
		    	Vector<Object> i_=new Adres().ilcelerVector(selected_item.getId());
		    	cBox_ilce = new JComboBox<Object>(i_);
		    	cBox_ilce.setBounds(112, 252, 193, 23);
				panel_1.add(cBox_ilce);
				panel_1.invalidate();
				panel_1.revalidate();
				panel_1.repaint();
		    	
		    }
		});
		panel_1.add(cBox_il);
		
		JLabel label_6 = new JLabel("\u0130l:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_6.setBounds(22, 207, 52, 20);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel("\u0130l\u00E7e:");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_7.setBounds(22, 253, 52, 20);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel("Adres:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_8.setBounds(22, 300, 52, 20);
		panel_1.add(label_8);
		
		//adres
		txtAdres = new JTextPane();
		txtAdres.setBounds(112, 300, 193, 82);
		panel_1.add(txtAdres);
		
		JLabel label_9 = new JLabel("Email:");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_9.setBounds(22, 40, 52, 20);
		panel_1.add(label_9);
		
		//email
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(112, 37, 193, 27);
		panel_1.add(txtEmail);
		
		JLabel label_10 = new JLabel("Tel:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_10.setBounds(22, 100, 71, 20);
		panel_1.add(label_10);
		
		//tel
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(112, 97, 193, 26);
		panel_1.add(txtTel);
		
		JLabel label_11 = new JLabel("\u015Eube:");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_11.setBounds(22, 159, 52, 20);
		panel_1.add(label_11);
		
		// þube
		if(frmMain.PERSONELTIP_ >1){
			cBox_sube = new JComboBox<Object>(new Sube().subeVector(frmMain.SUBE_));
			cBox_sube.setBounds(112, 156, 193, 27);
			cBox_sube.setEnabled(false);
		}else{
			cBox_sube = new JComboBox<Object>(new Sube().subelerVector());
			cBox_sube.setBounds(112, 156, 193, 27);
		}
		panel_1.add(cBox_sube);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(34, 139, 34));
		panel_2.setBounds(25, 422, 824, 51);
		add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//kaydete basýldýðýnda
		JButton btn_kayit = new JButton("Kaydet");
		btn_kayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtAd.getText().equals("")){
					JOptionPane.showMessageDialog(null, 
							"Adý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
							JOptionPane.ERROR_MESSAGE);
				}else if(txtSoyad.getText().equals("")){
					JOptionPane.showMessageDialog(null, 
							"Soyadý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
							JOptionPane.ERROR_MESSAGE);
				}else if(txtTc.getText().equals("")){
					JOptionPane.showMessageDialog(null, 
							"TC yi boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
							JOptionPane.ERROR_MESSAGE);
				}else if(dcDogumTarihi.getDate() == null){
					JOptionPane.showMessageDialog(null, 
							"Doðum tarihi boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
							JOptionPane.ERROR_MESSAGE);
				}else if(txtEmail.getText().equals("")){
					JOptionPane.showMessageDialog(null, 
							"Emaili boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
							JOptionPane.ERROR_MESSAGE);
				}else if(txtTel.getText().equals("")){
					JOptionPane.showMessageDialog(null, 
							"Telefonu boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
							JOptionPane.ERROR_MESSAGE);
				}else if(cBox_ilce.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, 
							"Ýlçeyi boþ býrakamazsýnýz !!!", "Boþ býrakma hatasý!", 
							JOptionPane.ERROR_MESSAGE);
				}else if(txtAdres.getText().equals("")){
					JOptionPane.showMessageDialog(null, 
							"Adresi boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
							JOptionPane.ERROR_MESSAGE);
				}else{
					
					Personel p=new Personel();
					p.adi=txtAd.getText();
					p.soyadi=txtSoyad.getText();
					p.tc=txtTc.getText();
					p.email=txtEmail.getText();
					p.tel=txtTel.getText();
					p.cinsiyet=cBoxCinsiyet.getSelectedIndex();
					p.dogumTarihi=new java.sql.Date(dcDogumTarihi.getDate().getTime());
					
					
					int pT=cBoxPersonelTip.getSelectedIndex();
					if(frmMain.PERSONELTIP_ > 1){
						pT+=3;
					}else{
						pT++;
					}
					p.personelTipi=pT;
					p.parola=pT==4?"":"202cb962ac59075b964b07152d234b70";//varsayýlan þifre 123
					
					Sube s=new Sube();
					Sube.Item selected_sube = (Sube.Item) cBox_sube.getSelectedItem();
					s.sube_=selected_sube.getId();
					
					Adres a=new Adres();
					Adres.Item selected_ilce = (Adres.Item) cBox_ilce.getSelectedItem();
					a.ilce_=selected_ilce.getId();
					a.adres=txtAdres.getText();
					
					p._adres=a;
					p._sube=s;
					if(new Personel().kisiEkle(p)){
						txtAd.setText("");
						txtSoyad.setText("");
						txtTc.setText("");
						txtEmail.setText("");
						txtTel.setText("");
						txtAdres.setText("");
						JOptionPane.showMessageDialog(null, 
								"Personeli baþarýyla kaydetiniz.", "Kayýt Baþarlý", 
								JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, 
								"Personeli kaydederken bir hatayla karþýlaþtý !!!", "Kayýt hatasý !", 
								JOptionPane.ERROR_MESSAGE);
					}
					
					
					
				}
				
				
			}
		});
		btn_kayit.setIcon(new ImageIcon("img\\kaydet.png"));
		btn_kayit.setBackground(Color.GREEN);
		btn_kayit.setActionCommand("OK");
		panel_2.add(btn_kayit);
	}
}
