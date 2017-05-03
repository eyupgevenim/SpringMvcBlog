package kargotakip;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import kargotakip.op.Personel;
import kargotakip.op.Sube;

public class pnlPersonelAra extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtF_personelAra;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultTableModel dm;
	private dlgPersonelGuncelle dialog=null;
	
	
	public pnlPersonelAra() {
		setBackground(new Color(105, 105, 105));
		setBounds(0, 60, 979, 486);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 23, 259, 452);
		add(panel);
		panel.setLayout(null);
		
		txtF_personelAra = new JTextField();
		txtF_personelAra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtF_personelAra.setBounds(10, 11, 239, 39);
		panel.add(txtF_personelAra);
		txtF_personelAra.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Arama Kriterleri", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(16, 91, 233, 197);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton rdbtn_email = new JRadioButton("Email");
		buttonGroup.add(rdbtn_email);
		rdbtn_email.setBounds(58, 87, 109, 23);
		panel_1.add(rdbtn_email);
		
		JRadioButton rdbtn_ad = new JRadioButton("Ad");
		buttonGroup.add(rdbtn_ad);
		rdbtn_ad.setSelected(true);
		rdbtn_ad.setBounds(58, 30, 109, 23);
		panel_1.add(rdbtn_ad);
		
		JRadioButton rdbtn_sube = new JRadioButton("\u015Eube");
		buttonGroup.add(rdbtn_sube);
		rdbtn_sube.setBounds(58, 142, 109, 23);
		panel_1.add(rdbtn_sube);
		
		// ara butununa basýldýðýnda
		JButton btn_ara = new JButton("Ara");
		btn_ara.setIcon(new ImageIcon("img\\arama.png"));
		btn_ara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Personel p=new Personel();
				Sube s=new Sube();
				
				if(rdbtn_ad.isSelected()){
					p.adi=txtF_personelAra.getText();
				}else if(rdbtn_email.isSelected()){
					p.email=txtF_personelAra.getText();
				}else if(rdbtn_sube.isSelected()){
					s.subeAdi=txtF_personelAra.getText();
				}
				p._sube=s;
				ArrayList<Personel> _p=new Personel().kisiAra(p);
				if(_p !=null){
					dm.getDataVector().removeAllElements();
				    revalidate();
					for(Personel pler:_p){
						String cinsiyet=pler.cinsiyet>0?"E":"K";
						String pT=pler.personelTipi==1?"Genel Müdür" :
							pler.personelTipi==2?"Þube Müdürü":
								pler.personelTipi==3?"Genel personel":"Sevkiyat Personeli";
						Object[] row = {
								pler.personel_,pler.adi, pler.soyadi, pler.tc, pler.tel, 
								pler.email, cinsiyet, pler.dogumTarihi, 
								pT, pler._sube.subeAdi, 
								pler._adres.adres,pler._adres.ilce,pler._adres.il
							};
						dm.addRow(row);
					}
				}else{
					JOptionPane.showMessageDialog(null, 
							"Personel bulunamadý !", "Arama sonucu", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				p=null;
				
				
			}
		});
		btn_ara.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_ara.setBounds(136, 299, 113, 39);
		panel.add(btn_ara);
		
		// tamizle buttonuna basýldýðýnda
		JButton btn_temizle = new JButton("Temizle");
		btn_temizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtF_personelAra.setText("");
				dm.getDataVector().removeAllElements();
				table.invalidate();
				table.revalidate();
				table.repaint();
			}
		});
		btn_temizle.setIcon(new ImageIcon("img\\temizle.png"));
		btn_temizle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_temizle.setBounds(10, 299, 116, 39);
		panel.add(btn_temizle);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.scrollbar);
		panel_2.setBorder(new TitledBorder(null, "Arama Sonu\u00E7lar\u0131", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(273, 24, 698, 376);
		add(panel_2);
		panel_2.setLayout(null);
		
		// tablo
		table = new JTable();
		dm = new DefaultTableModel(0, 0);
		dm.setColumnIdentifiers(new String[] {
				"id","Adý", "soyadý", "Tc", "Tel", "Email", "Cinsiyet", "Doðum Tarihi", 
				"Personel Tipi", "Þube", "Adres","Ýlçe","Ýl"
			});
		table.setModel(dm);
		table.getColumn("id").setPreferredWidth(0);
		table.getColumn("id").setMinWidth(0);
		table.getColumn("id").setWidth(0);
		table.getColumn("id").setMaxWidth(0);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 28, 674, 320);
		panel_2.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(211, 211, 211));
		panel_3.setBounds(279, 411, 690, 64);
		add(panel_3);
		panel_3.setLayout(null);
		
		// güncelle buttonuna basýldýðýnda 
		JButton btn_pGuncelle = new JButton("G\u00FCncelle");
		btn_pGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i=table.getSelectedRow();
				if(i < 0){
					JOptionPane.showMessageDialog(null, 
							"Lütfen veri seçin !!!", "Veri Seçme hatasý", 
							JOptionPane.ERROR_MESSAGE);
				}else{
					int personel_=Integer.valueOf(table.getValueAt(i,0).toString());
					//JOptionPane.showMessageDialog(null, personel_);
					dialog = new dlgPersonelGuncelle(kargotakip.frmMain.frame,personel_);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
					dialog.addWindowListener(new WindowListener() {
						
						@Override
						public void windowClosed(WindowEvent arg0) {
							
							if(dialog.kayitBasarliMi){
								Personel tmp=dialog.p;
								int i=table.getSelectedRow();
								
								String c=tmp.cinsiyet == 0?"K":"E";
								String pT=tmp.personelTipi==1?"Genel Müdür" :
									tmp.personelTipi==2?"Þube Müdürü":
										tmp.personelTipi==3?"Genel personel":"Sevkiyat Personeli";
								
								dm.setValueAt(tmp.adi, i, 1);
								dm.setValueAt(tmp.soyadi, i, 2);
								dm.setValueAt(tmp.tc, i, 3);
								dm.setValueAt(tmp.tel, i, 4);
								dm.setValueAt(tmp.email, i, 5);
								dm.setValueAt(c, i, 6);
								dm.setValueAt(tmp.dogumTarihi, i, 7);
								dm.setValueAt(pT, i, 8);
								dm.setValueAt(tmp._sube.subeAdi, i, 9);
								dm.setValueAt(tmp._adres.adres, i, 10);
								dm.setValueAt(tmp._adres.ilce, i, 11);
								dm.setValueAt(tmp._adres.il, i, 12);
								
							}
						}

						@Override
						public void windowActivated(WindowEvent e) {}
						@Override
						public void windowClosing(WindowEvent e) {}
						@Override
						public void windowDeactivated(WindowEvent e) {}
						@Override
						public void windowDeiconified(WindowEvent e) {}
						@Override
						public void windowIconified(WindowEvent e) {}
						@Override
						public void windowOpened(WindowEvent e) {}				
					});
					
				}
				
				
			}
		});
		btn_pGuncelle.setBackground(new Color(64, 224, 208));
		btn_pGuncelle.setIcon(new ImageIcon("img\\guncelleme.png"));
		btn_pGuncelle.setBounds(383, 11, 127, 42);
		panel_3.add(btn_pGuncelle);
		
		// sil buttonuna basýldýðýnda
		JButton btn_pSil = new JButton("Sil");
		btn_pSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i=table.getSelectedRow();
				if(i < 0){
					JOptionPane.showMessageDialog(null, 
							"Lütfen veri seçin !!!", "Veri Seçme hatasý", 
							JOptionPane.ERROR_MESSAGE);
				}else{
					int personel_=Integer.valueOf(table.getValueAt(i,0).toString());
					
	            	int EvetVeyeHayir= JOptionPane.showConfirmDialog (null, 
	                		"Personeli silmek istediðinize emin misiniz ?",
	                		"Önemli Uyarý !!!",JOptionPane.YES_NO_OPTION);
	                if(EvetVeyeHayir == JOptionPane.YES_OPTION){ 
	                	if(new Personel().kisiSil(personel_)){
	                		dm.removeRow(i);
	                	}else{
	                		JOptionPane.showMessageDialog(null, 
	    							"Personel silinemedi", "Bir hata oluþtu !", 
	    							JOptionPane.INFORMATION_MESSAGE);
	                	}
	                }
					
				}
				
			}
		});
		btn_pSil.setBackground(new Color(244, 164, 96));
		btn_pSil.setIcon(new ImageIcon("img\\sil_.png"));
		btn_pSil.setBounds(542, 11, 127, 42);
		panel_3.add(btn_pSil);
		
	}
}
