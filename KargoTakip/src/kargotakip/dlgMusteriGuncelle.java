package kargotakip;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.JComboBox;

import java.util.Vector;
import java.awt.SystemColor;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import kargotakip.op.Adres;
import kargotakip.op.Musteri;

public class dlgMusteriGuncelle extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textF_tc;
	private JTextField textF_soyad;
	private JTextField textF_ad;
	private JTextField textF_email;
	private JTextField textF_tel;

	public boolean kayitBasarliMi=false;
	public Musteri m;
	private JComboBox<Object> cBox_ilce;
	private JComboBox<Object> cBox_il;
	private JTextPane txtP_adres;
	
	public dlgMusteriGuncelle(frmMain f,int musteri_) {
		super(f);
		setTitle("M\u00FC\u015Fteri G\u00FCncelleme Formu");
		setBounds(450, 100, 346, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			
			m=new Musteri().kisiBilgisi(musteri_);
			
		}
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "M\u00FC\u015Fteri Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 0, 338, 428);
		contentPanel.add(panel);
		
		textF_tc = new JTextField(m.tc);
		textF_tc.setColumns(10);
		textF_tc.setBounds(83, 113, 176, 23);
		panel.add(textF_tc);
		
		textF_soyad = new JTextField(m.soyadi);
		textF_soyad.setColumns(10);
		textF_soyad.setBounds(83, 72, 176, 23);
		panel.add(textF_soyad);
		
		JLabel label = new JLabel("Soyad\u0131:");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		label.setBounds(10, 70, 63, 25);
		panel.add(label);
		
		JLabel label_1 = new JLabel("T.C:");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_1.setBounds(10, 111, 63, 25);
		panel.add(label_1);
		
		textF_ad = new JTextField(m.adi);
		textF_ad.setColumns(10);
		textF_ad.setBounds(83, 25, 176, 23);
		panel.add(textF_ad);
		
		JLabel label_2 = new JLabel("Ad\u0131:");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_2.setBounds(10, 23, 63, 25);
		panel.add(label_2);
		
		textF_email = new JTextField(m.email);
		textF_email.setColumns(10);
		textF_email.setBounds(83, 200, 176, 23);
		panel.add(textF_email);
		
		JLabel label_3 = new JLabel("Email:");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_3.setBounds(10, 198, 63, 25);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Tel:");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_4.setBounds(10, 157, 63, 25);
		panel.add(label_4);
		
		textF_tel = new JTextField(m.tel);
		textF_tel.setColumns(10);
		textF_tel.setBounds(83, 159, 176, 23);
		panel.add(textF_tel);
		
		txtP_adres = new JTextPane();
		txtP_adres.setText(m._adres.adres);
		txtP_adres.setBounds(83, 336, 176, 59);
		panel.add(txtP_adres);
		
		cBox_ilce = new JComboBox<Object>();
		cBox_ilce.setBounds(83, 294, 176, 23);
		panel.add(cBox_ilce);
		
		cBox_il = new JComboBox<Object>(new Adres().illerVector());
		cBox_il.setBounds(83, 249, 176, 23);
		cBox_il.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        
		    	panel.remove(cBox_ilce);
		    	Adres.Item selected_item = (Adres.Item) cBox_il.getSelectedItem();
		    	Vector<Object> i_=new Adres().ilcelerVector(selected_item.getId());
		    	cBox_ilce = new JComboBox<Object>(i_);
		    	cBox_ilce.setBounds(83, 294, 176, 23);
				panel.add(cBox_ilce);
				panel.invalidate();
				panel.revalidate();
				panel.repaint();
		    	
		    }
		});
		panel.add(cBox_il);
		
		JLabel label_5 = new JLabel("\u0130l:");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_5.setBounds(10, 248, 52, 20);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u0130l\u00E7e:");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_6.setBounds(10, 293, 52, 20);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Adres:");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 14));
		label_7.setBounds(10, 324, 52, 20);
		panel.add(label_7);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				//kaydet buttonu
				JButton btn_kaydet = new JButton("Kaydet");
				btn_kaydet.setBackground(Color.GREEN);
				btn_kaydet.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(textF_ad.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Adý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(textF_soyad.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Soyadý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(textF_tc.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"TC yi boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(textF_email.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Emaili boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(textF_tel.getText().equals("")){
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
							a.adres_=m._adres.adres_;
							a.adres=txtP_adres.getText();
							Adres.Item selected_ilce = (Adres.Item) cBox_ilce.getSelectedItem();
							a.ilce_=selected_ilce.getId();
							a.ilce=cBox_ilce.getSelectedItem().toString();
							Adres.Item selected_il = (Adres.Item) cBox_il.getSelectedItem();
							a.il_=selected_il.getId();
							a.il=cBox_il.getSelectedItem().toString();
							m._adres=a;
							
							m.adi=textF_ad.getText();
							m.soyadi=textF_soyad.getText();
							m.tc=textF_tc.getText();
							m.email=textF_email.getText();
							m.tel=textF_tel.getText();
							
							if(new Musteri().kisiGuncelle(m)){
								kayitBasarliMi=true;
								dispose();
							}else{
								JOptionPane.showMessageDialog(null, 
										"Müþteri Güncellerken bir hatayla karþýlaþtý !!!", "Kayýt hatasý !", 
										JOptionPane.ERROR_MESSAGE);
							}
							
							
						}
						
					}
				});
				btn_kaydet.setIcon(new ImageIcon("img\\kaydet.png"));
				btn_kaydet.setActionCommand("OK");
				buttonPane.add(btn_kaydet);
				getRootPane().setDefaultButton(btn_kaydet);
			}
			{
				//iptal buttonu
				JButton btn_iptal = new JButton("\u0130ptal");
				btn_iptal.setBackground(new Color(255, 140, 0));
				btn_iptal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btn_iptal.setIcon(new ImageIcon("img\\iptal.png"));
				btn_iptal.setActionCommand("Cancel");
				buttonPane.add(btn_iptal);
			}
		}
	}
}
