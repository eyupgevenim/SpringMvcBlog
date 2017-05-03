package kargotakip;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import kargotakip.op.Adres;
import kargotakip.op.Personel;
import kargotakip.op.Sube;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

public class dlgPersonelGuncelle extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtF_email;
	private JTextField txtF_sayad;
	private JTextField txtF_ad;
	private JTextField txtF_tel;
	public boolean kayitBasarliMi=false;
	public Personel p;
	
	
	private JComboBox<Object> cBox_il;
	private JComboBox<Object> cBox_ilce;
	private JComboBox<Object> cBox_sube;
	private JTextField txtF_tc;
	
	public dlgPersonelGuncelle(frmMain f,int personel_) {
		super(f);
		setBackground(new Color(0, 128, 128));
		setTitle("Personel Güncelleme Formu");
		setBounds(100, 100, 874, 514);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 139, 139));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			//personel bilgilerini çekme
			p=new Personel().kisiBilgisi(personel_);
			
			
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Genel Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(33, 11, 384, 404);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Cinsiyet:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 340, 52, 25);
		panel.add(label);
		
		JComboBox<Object> cBox_cinsiyet = new JComboBox<Object>();
		cBox_cinsiyet.setBounds(139, 340, 193, 25);
		cBox_cinsiyet.addItem("Kadýn");
		cBox_cinsiyet.addItem("Erkek");
		panel.add(cBox_cinsiyet);
		
		JLabel label_1 = new JLabel("Do\u011Fum Tarihi:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(10, 281, 90, 25);
		panel.add(label_1);
		
		JLabel label_3 = new JLabel("T.C:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(10, 163, 52, 25);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Soyad\u0131:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(10, 104, 52, 25);
		panel.add(label_4);
		
		txtF_sayad = new JTextField(p.soyadi);
		txtF_sayad.setColumns(10);
		txtF_sayad.setBounds(139, 100, 193, 29);
		panel.add(txtF_sayad);
		
		txtF_ad = new JTextField(p.adi);
		txtF_ad.setColumns(10);
		txtF_ad.setBounds(139, 40, 193, 30);
		panel.add(txtF_ad);
		
		JLabel label_5 = new JLabel("Ad\u0131:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(10, 45, 52, 25);
		panel.add(label_5);
		
		//personel tip
		JComboBox<Object> cBox_peraonelTip = new JComboBox<Object>();
		cBox_peraonelTip.setModel(new DefaultComboBoxModel<Object>(new String[] {"\u015Eube m\u00FCd\u00FCr\u00FC", "Genel personel", "Sevkiyat personeli"}));
		if(frmMain.PERSONELTIP_ > 1){
			cBox_peraonelTip.removeAllItems();
			cBox_peraonelTip.addItem("Genel personel");
			cBox_peraonelTip.addItem("Sevkiyat personeli");
		}
		cBox_peraonelTip.setBounds(139, 218, 193, 28);
		panel.add(cBox_peraonelTip);
		
		JLabel lblPersonelTipi = new JLabel("Personel Tipi:");
		lblPersonelTipi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPersonelTipi.setBounds(10, 218, 90, 25);
		panel.add(lblPersonelTipi);
		
		JDateChooser dc_dogumTarihi = new JDateChooser();
		dc_dogumTarihi.setBounds(139, 281, 193, 28);
		panel.add(dc_dogumTarihi);
		
		//tc
		txtF_tc = new JTextField(p.tc);
		txtF_tc.setColumns(10);
		txtF_tc.setBounds(139, 159, 193, 29);
		panel.add(txtF_tc);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 205, 170));
		panel_1.setBorder(new TitledBorder(null, "\u0130leti\u015Fim Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(422, 11, 415, 404);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
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
		
		JTextPane txtP_adres = new JTextPane();
		txtP_adres.setText(p._adres.adres);
		txtP_adres.setBounds(112, 300, 193, 82);
		panel_1.add(txtP_adres);
		
		JLabel label_2 = new JLabel("Email:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(22, 40, 52, 20);
		panel_1.add(label_2);
		
		//email
		txtF_email = new JTextField(p.email);
		txtF_email.setBounds(112, 37, 193, 27);
		panel_1.add(txtF_email);
		txtF_email.setColumns(10);
		
		JLabel lblTel = new JLabel("Tel:");
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTel.setBounds(22, 100, 71, 20);
		panel_1.add(lblTel);
		
		//tel
		txtF_tel = new JTextField(p.tel);
		txtF_tel.setColumns(10);
		txtF_tel.setBounds(112, 97, 193, 26);
		panel_1.add(txtF_tel);
		
		JLabel lblSube = new JLabel("\u015Eube:");
		lblSube.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSube.setBounds(22, 159, 52, 20);
		panel_1.add(lblSube);
		
		//sube
		if(frmMain.PERSONELTIP_ >1){
			cBox_sube = new JComboBox<Object>(new Sube().subeVector(frmMain.SUBE_));
			cBox_sube.setEnabled(false);
		}else{
			if(frmMain.PERSONEL_ == p.personel_){
				cBox_sube = new JComboBox<Object>(new Sube().subeVector(frmMain.SUBE_));
				cBox_sube.setEnabled(false);
			}else{
				cBox_sube = new JComboBox<Object>(new Sube().subelerVector());
			}
				
		}
		cBox_sube.setBounds(112, 156, 193, 27);
		panel_1.add(cBox_sube);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(34, 139, 34));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// kaydet buttonuna basýldýðýnda
				JButton btn_kaydet = new JButton("Kaydet");
				btn_kaydet.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						if(txtF_ad.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Adý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_sayad.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Soyadý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_tc.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"TC yi boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(dc_dogumTarihi.getDate() == null){
							JOptionPane.showMessageDialog(null, 
									"Doðum tarihi boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_email.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Emaili boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtF_tel.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Telefonu boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(cBox_ilce.getSelectedItem() == null){
							JOptionPane.showMessageDialog(null, 
									"Ýlçeyi boþ býrakamazsýnýz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtP_adres.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Adresi boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else{
							
							Adres a=new Adres();
							a.adres_=p._adres.adres_;
							a.adres=txtP_adres.getText();
							Adres.Item selected_ilce = (Adres.Item) cBox_ilce.getSelectedItem();
							a.ilce_=selected_ilce.getId();
							a.ilce=cBox_ilce.getSelectedItem().toString();
							Adres.Item selected_il = (Adres.Item) cBox_il.getSelectedItem();
							a.il_=selected_il.getId();
							a.il=cBox_il.getSelectedItem().toString();
							
							
							p.adi=txtF_ad.getText();
							p.soyadi=txtF_sayad.getText();
							p.tc=txtF_tc.getText();
							p.email=txtF_email.getText();
							p.tel=txtF_tel.getText();
							p.cinsiyet=cBox_cinsiyet.getSelectedIndex();
							p.dogumTarihi=new java.sql.Date(dc_dogumTarihi.getDate().getTime());
							int pT=cBox_peraonelTip.getSelectedIndex();
							if(frmMain.PERSONELTIP_ > 1){
								pT+=3;
							}else{
								pT++;
							}
							p.personelTipi=pT;
							
							Sube s=new Sube();
							Sube.Item selected_sube = (Sube.Item) cBox_sube.getSelectedItem();
							s.sube_=selected_sube.getId();
							s.subeAdi=cBox_sube.getSelectedItem().toString();
							
							
							
							p._adres=a;
							p._sube=s;
							if(new Personel().kisiGuncelle(p)){
								kayitBasarliMi=true;
								dispose();
							}else{
								JOptionPane.showMessageDialog(null, 
										"Personeli Güncellerken bir hatayla karþýlaþtý !!!", "Kayýt hatasý !", 
										JOptionPane.ERROR_MESSAGE);
							}
							
						}
						
						
					}
				});
				btn_kaydet.setBackground(new Color(0, 255, 0));
				btn_kaydet.setIcon(new ImageIcon("img\\kaydet.png"));
				btn_kaydet.setActionCommand("OK");
				buttonPane.add(btn_kaydet);
				getRootPane().setDefaultButton(btn_kaydet);
			}
			{
				// iptal buttonuna basýldýðýnda
				JButton btn_iptal = new JButton("\u0130ptal");
				btn_iptal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//JOptionPane.showMessageDialog(null, " --- ");
						dispose();
						
					}
				});
				btn_iptal.setBackground(new Color(255, 140, 0));
				btn_iptal.setIcon(new ImageIcon("img\\iptal.png"));
				btn_iptal.setActionCommand("Cancel");
				buttonPane.add(btn_iptal);
			}
		}
	}
}