package kargotakip;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Font;

import kargotakip.op.Kargo;
import kargotakip.op.Personel;
import kargotakip.op.db.KargoDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dlgKargoGuncelle extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtF_mTc;
	private JTextField txtF_mSoyad;
	private JTextField txtF_mAd;
	private JTextField txtF_mEmail;
	private JTextField txtF_mTel;
	private JTextField txtF_aTel;
	private JTextField txtF_aSoyad;
	private JTextField txtF_aAd;
	private JTextField txtF_kilo;
	private JTextField txtF_desi;
	private JTextField txtF_ucret;
	
	public boolean kayitBasarliMi=false;
	public Kargo k;


	
	public dlgKargoGuncelle(frmMain f,int kargo_) {
		super(f);
		setBackground(new Color(210, 180, 140));
		setTitle("Kargo G\u00FCncelleme Formu");
		setBounds(100, 100, 897, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 139, 139));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			
			k=new Kargo().kargoBilgisi(kargo_);
			
		}
		
		//sevkiyat bilgileri
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 0));
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sevkiyat", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 273, 192);
		contentPanel.add(panel);
		
		JComboBox<Object> cBox_aliciSube = new JComboBox<Object>();
		cBox_aliciSube.setEnabled(false);
		cBox_aliciSube.addItem(k._aliciSube.subeAdi);
		cBox_aliciSube.setBounds(94, 23, 146, 28);
		panel.add(cBox_aliciSube);
		
		JLabel label = new JLabel("Al\u0131c\u0131 \u015Eube:");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(10, 22, 85, 30);
		panel.add(label);
		
		JComboBox<Object> cBox_subeye = new JComboBox<Object>();
		cBox_subeye.setEnabled(false);
		cBox_subeye.addItem(k._subelerArasiSevkiyat.adi+" "+k._subelerArasiSevkiyat.soyadi);
		cBox_subeye.setBounds(94, 62, 146, 28);
		panel.add(cBox_subeye);
		
		JLabel label_1 = new JLabel("\u015Eubeye:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_1.setBounds(10, 61, 63, 25);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Adrese:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_2.setBounds(10, 97, 63, 30);
		panel.add(label_2);
		
		JComboBox<Object> cBox_adrese;
		if(k._subeAdreseSevkiyat.personel_ < 1){
			cBox_adrese = new JComboBox<Object>(new Personel().sevkitatPersonelVektor());
			cBox_adrese.setSelectedItem("Yok");
		}else{
			cBox_adrese = new JComboBox<Object>(new Personel().sevkitatPersonelVektor(k._subeAdreseSevkiyat.personel_));
			cBox_adrese.setEnabled(false);
		}
		
		cBox_adrese.setBounds(94, 98, 146, 28);
		panel.add(cBox_adrese);
		
		JComboBox<Object> cBox_kargoDurumu = new JComboBox<Object>(new Kargo().kargoDurumu());
		cBox_kargoDurumu.setBounds(94, 139, 146, 28);
		panel.add(cBox_kargoDurumu);
		
		JLabel label_3 = new JLabel("Durum:");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_3.setBounds(10, 138, 63, 30);
		panel.add(label_3);
		
		//müþteri bilgileri
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "M\u00FC\u015Fteri Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(293, 11, 289, 394);
		contentPanel.add(panel_1);
		
		txtF_mTc = new JTextField(k._musteri.tc);
		txtF_mTc.setEnabled(false);
		txtF_mTc.setColumns(10);
		txtF_mTc.setBounds(83, 113, 143, 23);
		panel_1.add(txtF_mTc);
		
		txtF_mSoyad = new JTextField(k._musteri.soyadi);
		txtF_mSoyad.setEnabled(false);
		txtF_mSoyad.setColumns(10);
		txtF_mSoyad.setBounds(83, 72, 143, 23);
		panel_1.add(txtF_mSoyad);
		
		JLabel label_4 = new JLabel("Soyad\u0131:");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_4.setBounds(10, 70, 63, 25);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("T.C:");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_5.setBounds(10, 111, 63, 25);
		panel_1.add(label_5);
		
		txtF_mAd = new JTextField(k._musteri.adi);
		txtF_mAd.setEnabled(false);
		txtF_mAd.setColumns(10);
		txtF_mAd.setBounds(83, 25, 143, 23);
		panel_1.add(txtF_mAd);
		
		JLabel label_6 = new JLabel("Ad\u0131:");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_6.setBounds(10, 23, 63, 25);
		panel_1.add(label_6);
		
		txtF_mEmail = new JTextField(k._musteri.email);
		txtF_mEmail.setEnabled(false);
		txtF_mEmail.setColumns(10);
		txtF_mEmail.setBounds(83, 200, 143, 23);
		panel_1.add(txtF_mEmail);
		
		JLabel label_7 = new JLabel("Email:");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_7.setBounds(10, 198, 63, 25);
		panel_1.add(label_7);
		
		JLabel label_8 = new JLabel("Tel:");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_8.setBounds(10, 157, 63, 25);
		panel_1.add(label_8);
		
		txtF_mTel = new JTextField(k._musteri.tel);
		txtF_mTel.setEnabled(false);
		txtF_mTel.setColumns(10);
		txtF_mTel.setBounds(83, 159, 143, 23);
		panel_1.add(txtF_mTel);
		
		JTextPane txtP_mAdres = new JTextPane();
		txtP_mAdres.setEnabled(false);
		txtP_mAdres.setText(k._musteri._adres.adres);
		txtP_mAdres.setBounds(83, 328, 149, 59);
		panel_1.add(txtP_mAdres);
		
		JComboBox<Object> cBox_mIlce = new JComboBox<Object>();
		cBox_mIlce.setEnabled(false);
		cBox_mIlce.addItem(k._musteri._adres.ilce);
		cBox_mIlce.setBounds(83, 294, 149, 23);
		panel_1.add(cBox_mIlce);
		
		JComboBox<Object> cBox_mIl = new JComboBox<Object>();
		cBox_mIl.setEnabled(false);
		cBox_mIl.addItem(k._musteri._adres.il);
		cBox_mIl.setBounds(83, 249, 149, 23);
		panel_1.add(cBox_mIl);
		
		JLabel label_9 = new JLabel("\u0130l:");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_9.setBounds(10, 248, 52, 20);
		panel_1.add(label_9);
		
		JLabel label_10 = new JLabel("\u0130l\u00E7e:");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_10.setBounds(10, 293, 52, 20);
		panel_1.add(label_10);
		
		JLabel label_11 = new JLabel("Adres:");
		label_11.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_11.setBounds(10, 324, 52, 20);
		panel_1.add(label_11);
		
		//alici bilgileri
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(173, 216, 230));
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Al\u0131c\u0131 Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(592, 11, 282, 336);
		contentPanel.add(panel_2);
		
		JTextPane txtP_aAdres = new JTextPane();
		txtP_aAdres.setEnabled(false);
		txtP_aAdres.setText(k._aliciAdres.adres);
		txtP_aAdres.setBounds(83, 260, 149, 59);
		panel_2.add(txtP_aAdres);
		
		JLabel label_12 = new JLabel("Adres:");
		label_12.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_12.setBounds(10, 248, 52, 20);
		panel_2.add(label_12);
		
		JLabel label_13 = new JLabel("\u0130l\u00E7e:");
		label_13.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_13.setBounds(10, 217, 52, 20);
		panel_2.add(label_13);
		
		JComboBox<Object> cBox_aIlce = new JComboBox<Object>();
		cBox_aIlce.setEnabled(false);
		cBox_aIlce.addItem(k._aliciAdres.ilce);
		cBox_aIlce.setBounds(83, 218, 149, 23);
		panel_2.add(cBox_aIlce);
		
		JComboBox<Object> cBox_aIl = new JComboBox<Object>();
		cBox_aIl.setEnabled(false);
		cBox_aIl.addItem(k._aliciAdres.il);
		cBox_aIl.setBounds(83, 173, 149, 23);
		panel_2.add(cBox_aIl);
		
		JLabel label_14 = new JLabel("\u0130l:");
		label_14.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_14.setBounds(10, 172, 52, 20);
		panel_2.add(label_14);
		
		txtF_aTel = new JTextField(k.aliciTel);
		txtF_aTel.setEnabled(false);
		txtF_aTel.setColumns(10);
		txtF_aTel.setBounds(83, 119, 143, 23);
		panel_2.add(txtF_aTel);
		
		JLabel label_15 = new JLabel("Tel:");
		label_15.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_15.setBounds(10, 117, 63, 25);
		panel_2.add(label_15);
		
		txtF_aSoyad = new JTextField(k.aliciSoyadi);
		txtF_aSoyad.setEnabled(false);
		txtF_aSoyad.setColumns(10);
		txtF_aSoyad.setBounds(83, 72, 143, 23);
		panel_2.add(txtF_aSoyad);
		
		JLabel label_16 = new JLabel("Soyad\u0131:");
		label_16.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_16.setBounds(10, 70, 63, 25);
		panel_2.add(label_16);
		
		JLabel label_17 = new JLabel("Ad\u0131:");
		label_17.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_17.setBounds(10, 23, 63, 25);
		panel_2.add(label_17);
		
		txtF_aAd = new JTextField(k.aliciAdi);
		txtF_aAd.setEnabled(false);
		txtF_aAd.setColumns(10);
		txtF_aAd.setBounds(83, 25, 143, 23);
		panel_2.add(txtF_aAd);
		
		//kargo bilgileri
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(173, 216, 230));
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Kargo Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 214, 273, 191);
		contentPanel.add(panel_3);
		
		JLabel label_18 = new JLabel("Kilo:");
		label_18.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_18.setBounds(10, 21, 63, 25);
		panel_3.add(label_18);
		
		JLabel label_19 = new JLabel("Desi:");
		label_19.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_19.setBounds(10, 68, 63, 25);
		panel_3.add(label_19);
		
		JLabel label_20 = new JLabel("\u00DCcret:");
		label_20.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_20.setBounds(10, 109, 63, 25);
		panel_3.add(label_20);
		
		JLabel label_21 = new JLabel("\u00D6deme Tipi:");
		label_21.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_21.setBounds(10, 156, 88, 25);
		panel_3.add(label_21);
		
		txtF_kilo = new JTextField();
		txtF_kilo.setEnabled(false);
		txtF_kilo.setText(String.valueOf(k.kilo));
		//txtF_kilo.setColumns(10);
		txtF_kilo.setBounds(96, 21, 142, 23);
		panel_3.add(txtF_kilo);
		
		txtF_desi = new JTextField();
		txtF_desi.setEnabled(false);
		txtF_desi.setText(String.valueOf(k.desi));
		//txtF_desi.setColumns(10);
		txtF_desi.setBounds(96, 68, 142, 23);
		panel_3.add(txtF_desi);
		
		txtF_ucret = new JTextField();
		txtF_ucret.setEnabled(false);
		txtF_ucret.setText(String.valueOf(k.ucret));
		//txtF_ucret.setColumns(10);
		txtF_ucret.setBounds(96, 109, 142, 23);
		panel_3.add(txtF_ucret);
		
		JComboBox<Object> cBox_odemeTip = new JComboBox<Object>();
		cBox_odemeTip.setEnabled(false);
		if(k.ucrettip_ == 0){
			cBox_odemeTip.addItem("Peþin Ödeme");
		}else{
			cBox_odemeTip.addItem("Kapýda Ödeme");
		}
		cBox_odemeTip.setBounds(96, 155, 142, 31);
		panel_3.add(cBox_odemeTip);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(173, 216, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// kaydet buttonu
				JButton btn_kKaydet = new JButton("Kaydet");
				btn_kKaydet.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						KargoDB.Item durum = (KargoDB.Item) cBox_kargoDurumu.getSelectedItem();
						k.durun_=durum.getId();
						
						Personel as=new Personel();
						if(cBox_adrese.getSelectedItem().equals("Yok"))
						{
							as.personel_=0;
						}else{
							Personel.Item sAdrese = (Personel.Item) cBox_adrese.getSelectedItem();
							as.personel_=sAdrese.getId();
							as.adi=sAdrese.toString();
						}
						
						k._subeAdreseSevkiyat=as;
						
						if(new Kargo().durumGuncelle(k)){
							kayitBasarliMi=true;
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, 
									"Kargo güncellenirken bir hatayla karþýlaþtý !!!", "Güncelleme Hatasý !", 
									JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				btn_kKaydet.setBackground(new Color(0, 255, 0));
				btn_kKaydet.setIcon(new ImageIcon("img\\kaydet.png"));
				btn_kKaydet.setActionCommand("OK");
				buttonPane.add(btn_kKaydet);
				getRootPane().setDefaultButton(btn_kKaydet);
			}
			{
				//iptal buttonu
				JButton btn_iptal = new JButton("\u0130ptal");
				btn_iptal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btn_iptal.setBackground(new Color(255, 127, 80));
				btn_iptal.setIcon(new ImageIcon("img\\iptal.png"));
				btn_iptal.setActionCommand("Cancel");
				buttonPane.add(btn_iptal);
			}
		}
	}
}
