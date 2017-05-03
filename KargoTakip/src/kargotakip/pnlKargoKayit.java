package kargotakip;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import java.awt.Font;

import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;

import kargotakip.op.Adres;
import kargotakip.op.Kargo;
import kargotakip.op.Musteri;
import kargotakip.op.Personel;
import kargotakip.op.Sube;
import kargotakip.op.db.KargoDB;

import javax.swing.DefaultComboBoxModel;

public class pnlKargoKayit extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//kargo
	private JTextField txtF_kilo;
	private JTextField txtF_desi;
	private JTextField txtF_ucret;
	private JComboBox<Object> cBox_ucretTip ;
	
	//müþteri
	private JTextField txtF_mTc;
	private JTextField txtF_mSoyad;
	private JTextField txtF_mAd;
	private JTextField txtF_mEmail;
	private JTextField txtF_mTel;
	private JTextPane txtP_mAdres ;
	private JComboBox<Object> cBox_mIlce;
	private JComboBox<Object> cBox_mIl;
	
	
	//alýcý
	private JTextField txtF_aTel;
	private JTextField txtF_aSoyad;
	private JTextField txtF_aAd;
	private JComboBox<Object> cBox_aIlce;
	private JComboBox<Object> cBox_aIl;
	private JTextPane txtP_aAdres ;
	
	
	//savkiyat
	private JComboBox<Object> cBox_aliciSube ;
	private JComboBox<Object> cBox_subeArasiPersonel ;
	private JComboBox<Object> cBox_kargoDurumu;
	
	
	
	
	/**
	 * Create the panel.
	 */
	public pnlKargoKayit() {
		setBackground(new Color(143, 188, 143));
		setBounds(50, 70, 881, 516);
		setLayout(null);
		{
			// Kargo bilgileri
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 128, 0));
			panel.setBorder(new TitledBorder(null, "Kargo Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 273, 203);
			add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Kilo:");
				lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblNewLabel.setBounds(10, 21, 63, 25);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblDesi = new JLabel("Desi:");
				lblDesi.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblDesi.setBounds(10, 68, 63, 25);
				panel.add(lblDesi);
			}
			{
				JLabel lblcret = new JLabel("\u00DCcret:");
				lblcret.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblcret.setBounds(10, 109, 63, 25);
				panel.add(lblcret);
			}
			{
				JLabel lbldemeTipi = new JLabel("\u00D6deme Tipi:");
				lbldemeTipi.setFont(new Font("Dialog", Font.PLAIN, 14));
				lbldemeTipi.setBounds(10, 156, 86, 25);
				panel.add(lbldemeTipi);
			}
			{
				txtF_kilo = new JTextField();
				txtF_kilo.setBounds(94, 26, 144, 23);
				panel.add(txtF_kilo);
				txtF_kilo.setColumns(10);
			}
			{
				txtF_desi = new JTextField();
				txtF_desi.setColumns(10);
				txtF_desi.setBounds(94, 73, 144, 23);
				panel.add(txtF_desi);
			}
			{
				txtF_ucret = new JTextField();
				txtF_ucret.setColumns(10);
				txtF_ucret.setBounds(94, 114, 144, 23);
				panel.add(txtF_ucret);
			}
			{
				cBox_ucretTip = new JComboBox<Object>();
				cBox_ucretTip.setModel(new DefaultComboBoxModel<Object>(new String[] {"Pe\u015Fin \u00D6deme", "Kap\u0131da \u00D6deme"}));
				cBox_ucretTip.setBounds(94, 158, 144, 25);
				panel.add(cBox_ucretTip);
			}
		}
		{
			//Müþteri Bilgileri
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 191, 255));
			panel.setBorder(new TitledBorder(null, "M\u00FC\u015Fteri Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(293, 11, 289, 406);
			add(panel);
			panel.setLayout(null);
			{
				txtF_mTc = new JTextField();
				txtF_mTc.setColumns(10);
				txtF_mTc.setBounds(83, 113, 143, 23);
				panel.add(txtF_mTc);
			}
			{
				txtF_mSoyad = new JTextField();
				txtF_mSoyad.setColumns(10);
				txtF_mSoyad.setBounds(83, 72, 143, 23);
				panel.add(txtF_mSoyad);
			}
			{
				JLabel lblSoyad = new JLabel("Soyad\u0131:");
				lblSoyad.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblSoyad.setBounds(10, 70, 63, 25);
				panel.add(lblSoyad);
			}
			{
				JLabel lblTc = new JLabel("T.C:");
				lblTc.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblTc.setBounds(10, 111, 63, 25);
				panel.add(lblTc);
			}
			{
				txtF_mAd = new JTextField();
				txtF_mAd.setColumns(10);
				txtF_mAd.setBounds(83, 25, 143, 23);
				panel.add(txtF_mAd);
			}
			{
				JLabel lblAd = new JLabel("Ad\u0131:");
				lblAd.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblAd.setBounds(10, 23, 63, 25);
				panel.add(lblAd);
			}
			{
				txtF_mEmail = new JTextField();
				txtF_mEmail.setColumns(10);
				txtF_mEmail.setBounds(83, 200, 143, 23);
				panel.add(txtF_mEmail);
			}
			{
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblEmail.setBounds(10, 198, 63, 25);
				panel.add(lblEmail);
			}
			{
				JLabel lblTel = new JLabel("Tel:");
				lblTel.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblTel.setBounds(10, 157, 63, 25);
				panel.add(lblTel);
			}
			{
				txtF_mTel = new JTextField();
				txtF_mTel.setColumns(10);
				txtF_mTel.setBounds(83, 159, 143, 23);
				panel.add(txtF_mTel);
			}
			{
				txtP_mAdres = new JTextPane();
				txtP_mAdres.setBounds(83, 336, 149, 59);
				panel.add(txtP_mAdres);
			}
			{
				cBox_mIlce = new JComboBox<Object>();
				cBox_mIlce.setBounds(83, 294, 149, 23);
				panel.add(cBox_mIlce);
			}
			{
				cBox_mIl = new JComboBox<Object>(new Adres().illerVector());
				cBox_mIl.setBounds(83, 249, 149, 23);
				cBox_mIl.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				        
				    	panel.remove(cBox_mIlce);
				    	Adres.Item selected_item = (Adres.Item) cBox_mIl.getSelectedItem();
				    	Vector<Object> i_=new Adres().ilcelerVector(selected_item.getId());
				    	cBox_mIlce = new JComboBox<Object>(i_);
				    	cBox_mIlce.setBounds(83, 294, 149, 23);
						panel.add(cBox_mIlce);
						panel.invalidate();
						panel.revalidate();
						panel.repaint();
				    	
				    }
				});
				panel.add(cBox_mIl);
			}
			{
				JLabel label = new JLabel("\u0130l:");
				label.setFont(new Font("Dialog", Font.PLAIN, 14));
				label.setBounds(10, 248, 52, 20);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("\u0130l\u00E7e:");
				label.setFont(new Font("Dialog", Font.PLAIN, 14));
				label.setBounds(10, 293, 52, 20);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Adres:");
				label.setFont(new Font("Dialog", Font.PLAIN, 14));
				label.setBounds(10, 324, 52, 20);
				panel.add(label);
			}
		}
		{
			// Alýcý Bilgileri
			JPanel panel = new JPanel();
			panel.setBackground(new Color(144, 238, 144));
			panel.setBorder(new TitledBorder(null, "Al\u0131c\u0131 Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(592, 11, 282, 336);
			add(panel);
			panel.setLayout(null);
			{
				txtP_aAdres = new JTextPane();
				txtP_aAdres.setBounds(83, 260, 149, 59);
				panel.add(txtP_aAdres);
			}
			{
				JLabel label = new JLabel("Adres:");
				label.setFont(new Font("Dialog", Font.PLAIN, 14));
				label.setBounds(10, 248, 52, 20);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("\u0130l\u00E7e:");
				label.setFont(new Font("Dialog", Font.PLAIN, 14));
				label.setBounds(10, 217, 52, 20);
				panel.add(label);
			}
			{
				cBox_aIlce = new JComboBox<Object>();
				cBox_aIlce.setBounds(83, 218, 149, 23);
				panel.add(cBox_aIlce);
			}
			{
				cBox_aIl = new JComboBox<Object>(new Adres().illerVector());
				cBox_aIl.setBounds(83, 173, 149, 23);
				cBox_aIl.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				        
				    	panel.remove(cBox_aIlce);
				    	Adres.Item selected_item = (Adres.Item) cBox_aIl.getSelectedItem();
				    	Vector<Object> i_=new Adres().ilcelerVector(selected_item.getId());
				    	cBox_aIlce = new JComboBox<Object>(i_);
				    	cBox_aIlce.setBounds(83, 218, 149, 23);
						panel.add(cBox_aIlce);
						panel.invalidate();
						panel.revalidate();
						panel.repaint();
				    	
				    }
				});
				panel.add(cBox_aIl);
			}
			{
				JLabel label = new JLabel("\u0130l:");
				label.setFont(new Font("Dialog", Font.PLAIN, 14));
				label.setBounds(10, 172, 52, 20);
				panel.add(label);
			}
			{
				txtF_aTel = new JTextField();
				txtF_aTel.setColumns(10);
				txtF_aTel.setBounds(83, 119, 143, 23);
				panel.add(txtF_aTel);
			}
			{
				JLabel label = new JLabel("Tel:");
				label.setFont(new Font("Dialog", Font.PLAIN, 14));
				label.setBounds(10, 117, 63, 25);
				panel.add(label);
			}
			{
				txtF_aSoyad = new JTextField();
				txtF_aSoyad.setColumns(10);
				txtF_aSoyad.setBounds(83, 72, 143, 23);
				panel.add(txtF_aSoyad);
			}
			{
				JLabel label = new JLabel("Soyad\u0131:");
				label.setFont(new Font("Dialog", Font.PLAIN, 14));
				label.setBounds(10, 70, 63, 25);
				panel.add(label);
			}
			{
				JLabel label = new JLabel("Ad\u0131:");
				label.setFont(new Font("Dialog", Font.PLAIN, 14));
				label.setBounds(10, 23, 63, 25);
				panel.add(label);
			}
			{
				txtF_aAd = new JTextField();
				txtF_aAd.setColumns(10);
				txtF_aAd.setBounds(83, 25, 143, 23);
				panel.add(txtF_aAd);
			}
		}
		{
			//saykiyat bilgileri
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 128, 128));
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sevkiyat", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 225, 273, 192);
			add(panel);
			panel.setLayout(null);
			{
				cBox_aliciSube = new JComboBox<Object>(new Sube().subelerVector());
				cBox_aliciSube.setBounds(94, 23, 146, 28);
				panel.add(cBox_aliciSube);
			}
			{
				JLabel lblAlcSube = new JLabel("Al\u0131c\u0131 \u015Eube:");
				lblAlcSube.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblAlcSube.setBounds(10, 22, 84, 30);
				panel.add(lblAlcSube);
			}
			{
				cBox_subeArasiPersonel = new JComboBox<Object>(new Personel().sevkitatPersonelVektor());
				cBox_subeArasiPersonel.setBounds(94, 62, 146, 28);
				panel.add(cBox_subeArasiPersonel);
			}
			{
				JLabel lblubeye = new JLabel("\u015Eubeye:");
				lblubeye.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblubeye.setBounds(10, 61, 63, 25);
				panel.add(lblubeye);
			}
			{
				JLabel lblAdrese = new JLabel("Adrese:");
				lblAdrese.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblAdrese.setBounds(10, 97, 63, 30);
				panel.add(lblAdrese);
			}
			{
				JComboBox<Object> cBox_adresArasi = new JComboBox<Object>();
				cBox_adresArasi.setEnabled(false);
				cBox_adresArasi.setBounds(94, 98, 146, 28);
				panel.add(cBox_adresArasi);
			}
			{
				cBox_kargoDurumu = new JComboBox<Object>(new Kargo().kargoDurumu());
				cBox_kargoDurumu.setBounds(94, 139, 146, 28);
				panel.add(cBox_kargoDurumu);
			}
			{
				JLabel lblDurum = new JLabel("Durum:");
				lblDurum.setFont(new Font("Dialog", Font.PLAIN, 14));
				lblDurum.setBounds(10, 138, 63, 30);
				panel.add(lblDurum);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(210, 105, 30));
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 428, 861, 77);
			add(panel);
			panel.setLayout(null);
			{
				//Kaydet e basýldýðýnda
				JButton btn_kKayit = new JButton("Kaydet");
				btn_kKayit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(txtF_desi.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Desi'yi boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_kilo.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Kilo'yu boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_ucret.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Ücreti yi boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_aAd.equals("")){
							JOptionPane.showMessageDialog(null, 
									"Alýcý Adý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_aSoyad.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Alýcý soyadý geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_aTel.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Alýcý telefonunu boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtP_aAdres.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Alýcý adresi boþ  geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(cBox_aIlce.getSelectedItem() == null){
							JOptionPane.showMessageDialog(null, 
									"Alýcý ilçesini boþ býrakamazsýnýz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_mAd.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Müþteri adý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_mSoyad.equals("")){
							JOptionPane.showMessageDialog(null, 
									"Müþteri soyadý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_mTc.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Müþteri tc'sini boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_mTel.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Müþteri telefonunu boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_mEmail.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Müþteri emaili boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(cBox_mIlce.getSelectedItem() == null){
							JOptionPane.showMessageDialog(null, 
									"Müþteri ilçesini boþ býrakamazsýnýz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtP_mAdres.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Müþteri adresini boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else{
							
							Kargo k=new Kargo();
							
							k.aliciAdi=txtF_aAd.getText();
							k.aliciSoyadi=txtF_aAd.getText();
							k.aliciTel=txtF_aTel.getText();
							Adres aa=new Adres();
							aa.adres=txtP_aAdres.getText();
							Adres.Item aIlce = (Adres.Item) cBox_aIlce.getSelectedItem();
							aa.ilce_=aIlce.getId();
							k._aliciAdres=aa;
							
							Musteri m=new Musteri();
							m.adi=txtF_mAd.getText();
							m.soyadi=txtF_mSoyad.getText();
							m.tc=txtF_mTc.getText();
							m.tel=txtF_mTel.getText();
							m.email=txtF_mEmail.getText();
							Adres am=new Adres();
							am.adres=txtP_mAdres.getText();
							Adres.Item mIlce = (Adres.Item) cBox_aIlce.getSelectedItem();
							am.ilce_=mIlce.getId();
							m._adres=am;
							Sube ms=new Sube();
							ms.sube_=frmMain.SUBE_;
							m._sube=ms;
							k._musteri=m;
							
							k.kilo=Double.valueOf(txtF_kilo.getText());
							k.desi=Double.valueOf(txtF_desi.getText());
							k.ucret=Double.valueOf(txtF_ucret.getText());
							k.ucrettip_=cBox_ucretTip.getSelectedIndex();
							
							Sube as=new Sube();
							Sube.Item aSube = (Sube.Item) cBox_aliciSube.getSelectedItem();
							as.sube_=aSube.getId();
							k._aliciSube=as;
							
							Sube gs=new Sube();
							gs.sube_=frmMain.SUBE_;
							k._gondericiSube=gs;
							
							Personel gp=new Personel();
							gp.personel_=frmMain.PERSONEL_;
							k._gondericiPersonel=gp;
							
							Personel sa=new Personel();
							Personel.Item sArasi = (Personel.Item) cBox_subeArasiPersonel.getSelectedItem();
							sa.personel_=sArasi.getId();
							k._subelerArasiSevkiyat=sa;
							
							KargoDB.Item durum = (KargoDB.Item) cBox_kargoDurumu.getSelectedItem();
							k.durun_=durum.getId();
							
							k.kayitTarihi=new java.sql.Date(new java.util.Date().getTime());
							
							if(new Kargo().kargoEkle(k)){
								txtF_aAd.setText("");
								txtF_aSoyad.setText("");
								txtF_aTel.setText("");
								txtP_aAdres.setText("");
								txtF_mAd.setText("");
								txtF_mSoyad.setText("");
								txtF_mTc.setText("");
								txtF_mTel.setText("");
								txtF_mEmail.setText("");
								txtP_mAdres.setText("");
								txtF_kilo.setText("");
								txtF_desi.setText("");
								txtF_ucret.setText("");
								JOptionPane.showMessageDialog(null, 
										"Kargo baþarýyla kaydedildi.", "Kayýt Baþarlý !", 
										JOptionPane.ERROR_MESSAGE);
								
							}else{
								JOptionPane.showMessageDialog(null, 
										"Kargo kaydederken bir hatayla karþýlaþtý !!!", "Kayýt Hatasý !", 
										JOptionPane.ERROR_MESSAGE);
							}
							
						}
						
						
					}
				});
				btn_kKayit.setBackground(new Color(0, 255, 0));
				btn_kKayit.setIcon(new ImageIcon("img\\kaydet.png"));
				btn_kKayit.setBounds(710, 22, 141, 44);
				panel.add(btn_kKayit);
			}
		}
		
	}

}
