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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import kargotakip.op.Musteri;
import kargotakip.op.Sube;

public class pnlMusteriAra extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtF_ara;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultTableModel dm;
	private dlgMusteriGuncelle dialog=null;
	
	private JRadioButton rAd;
	private JRadioButton rSoyad;
	private JRadioButton rEmail;
	private JRadioButton rTel;

	/**
	 * Create the panel.
	 */
	public pnlMusteriAra() {
		setBounds(40,80, 900, 483);
		setBackground(new Color(47, 79, 79));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 33, 247, 376);
		add(panel);
		
		txtF_ara = new JTextField();
		txtF_ara.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtF_ara.setColumns(10);
		txtF_ara.setBounds(13, 11, 223, 39);
		panel.add(txtF_ara);
		
		//ara buttonu
		JButton btn_ara = new JButton("Ara");
		btn_ara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Musteri m=new Musteri();
				Sube s=new Sube();
				s.sube_=frmMain.SUBE_;
				m._sube=s;
				
				if(rAd.isSelected()){
					m.adi=txtF_ara.getText();
				}else if(rSoyad.isSelected()){
					m.soyadi=txtF_ara.getText();
				}else if(rEmail.isSelected()){
					m.email=txtF_ara.getText();
				}else if(rTel.isSelected()){
					m.tel=txtF_ara.getText();
				}
				ArrayList<Musteri> _m=new Musteri().kisiAra(m);
				if(_m !=null){
					
					dm.getDataVector().removeAllElements();
				    revalidate();
					for(Musteri mler:_m){
						
						Object[] row = {
								mler.musteri_,
								mler.adi, 
								mler.soyadi, 
								mler.tc, 
								mler.tel, 
								mler.email, 
								mler._adres.adres, 
								mler._adres.ilce, 
								mler._adres.il,
								mler._sube.subeAdi
							};
						dm.addRow(row);
					}
				}else{
					JOptionPane.showMessageDialog(null, 
							"MÜÞTERÝ bulunamadý !", "Arama sonucu", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				_m=null;
				m=null;
			}
		});
		btn_ara.setIcon(new ImageIcon("img\\arama.png"));
		btn_ara.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_ara.setBounds(134, 309, 103, 39);
		panel.add(btn_ara);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Arama Kriterleri", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(25, 77, 188, 181);
		panel.add(panel_1);
		
		rTel = new JRadioButton("Tel");
		buttonGroup.add(rTel);
		rTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rTel.setBackground(SystemColor.inactiveCaption);
		rTel.setBounds(89, 72, 53, 23);
		panel_1.add(rTel);
		
		rEmail = new JRadioButton("Email");
		buttonGroup.add(rEmail);
		rEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rEmail.setBackground(SystemColor.inactiveCaption);
		rEmail.setBounds(85, 31, 71, 23);
		panel_1.add(rEmail);
		
		rSoyad = new JRadioButton("Soyad");
		buttonGroup.add(rSoyad);
		rSoyad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rSoyad.setBackground(SystemColor.inactiveCaption);
		rSoyad.setBounds(6, 72, 71, 23);
		panel_1.add(rSoyad);
		
		rAd = new JRadioButton(" Ad");
		rAd.setSelected(true);
		buttonGroup.add(rAd);
		rAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rAd.setBackground(SystemColor.inactiveCaption);
		rAd.setBounds(6, 31, 59, 23);
		panel_1.add(rAd);
		
		//temizle buttonu
		JButton btn_temizle = new JButton("Temizle");
		btn_temizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtF_ara.setText("");
				dm.getDataVector().removeAllElements();
				table.invalidate();
				table.revalidate();
				table.repaint();
				
			}
		});
		btn_temizle.setIcon(new ImageIcon("img\\temizle.png"));
		btn_temizle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_temizle.setBounds(10, 309, 118, 39);
		panel.add(btn_temizle);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Arama Sonu\u00E7lar\u0131", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(new Color(119, 136, 153));
		panel_2.setBounds(270, 33, 617, 376);
		add(panel_2);
		
		//tablo
		table = new JTable();
		dm = new DefaultTableModel(0, 0);
		dm.setColumnIdentifiers(new String[] {
				"id","Adý", "Soyadý", "Tc", "Tel", "Email", "Adres", "Ýlçe", "Ýl","Þube"
			});
		table.setModel(dm);
		table.getColumn("id").setPreferredWidth(0);
		table.getColumn("id").setMinWidth(0);
		table.getColumn("id").setWidth(0);
		table.getColumn("id").setMaxWidth(0);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 23, 575, 320);
		panel_2.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(119, 136, 153));
		panel_3.setBounds(10, 413, 877, 59);
		add(panel_3);
		panel_3.setLayout(null);
		
		//güncelleme buttonu
		JButton btn_guncelle = new JButton("G\u00FCncelle");
		btn_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i=table.getSelectedRow();
				if(i < 0){
					JOptionPane.showMessageDialog(null, 
							"Lütfen veri seçin !!!", "Veri Seçme hatasý", 
							JOptionPane.ERROR_MESSAGE);
				}else{
					int musteri_=Integer.valueOf(table.getValueAt(i,0).toString());
					//JOptionPane.showMessageDialog(null, personel_);
					dialog = new dlgMusteriGuncelle(kargotakip.frmMain.frame,musteri_);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
					dialog.addWindowListener(new WindowListener() {
						
						@Override
						public void windowClosed(WindowEvent arg0) {
							
							if(dialog.kayitBasarliMi){
								Musteri tmp=dialog.m;
								int i=table.getSelectedRow();
								
								dm.setValueAt(tmp.adi, i, 1);
								dm.setValueAt(tmp.soyadi, i, 2);
								dm.setValueAt(tmp.tc, i, 3);
								dm.setValueAt(tmp.tel, i, 4);
								dm.setValueAt(tmp.email, i, 5);
								dm.setValueAt(tmp._adres.adres, i, 6);
								dm.setValueAt(tmp._adres.ilce, i, 7);
								dm.setValueAt(tmp._adres.il, i, 8);
								dm.setValueAt(tmp._sube.subeAdi, i, 9);
								
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
		btn_guncelle.setBackground(new Color(72, 209, 204));
		btn_guncelle.setIcon(new ImageIcon("img\\guncelleme.png"));
		btn_guncelle.setBounds(717, 11, 135, 43);
		panel_3.add(btn_guncelle);
		
	}
}
