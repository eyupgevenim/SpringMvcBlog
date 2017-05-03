package kargotakip;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import kargotakip.op.Kargo;
import kargotakip.op.Sube;

public class pnlKargoAra extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtF_kargoArama;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultTableModel dm;
	private dlgKargoGuncelle dialog=null;
	
	
	public pnlKargoAra() {
		setBackground(new Color(0, 128, 128));
		setBounds(0, 90, 940, 477);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(10, 11, 153, 452);
		add(panel);
		panel.setLayout(null);
		
		txtF_kargoArama = new JTextField();
		txtF_kargoArama.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtF_kargoArama.setBounds(6, 11, 140, 32);
		panel.add(txtF_kargoArama);
		txtF_kargoArama.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(100, 149, 237));
		panel_2.setBounds(6, 77, 140, 137);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtSube = new JRadioButton("\u015Eube");
		buttonGroup.add(rdbtSube);
		rdbtSube.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtSube.setBackground(new Color(100, 149, 237));
		rdbtSube.setBounds(42, 74, 59, 23);
		panel_2.add(rdbtSube);
		
		JRadioButton rdbtnTel = new JRadioButton("Tel");
		rdbtnTel.setSelected(true);
		buttonGroup.add(rdbtnTel);
		rdbtnTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnTel.setBackground(new Color(100, 149, 237));
		rdbtnTel.setBounds(42, 38, 59, 23);
		panel_2.add(rdbtnTel);
		
		//ara buttonu
		JButton btn_ara = new JButton("Ara");
		btn_ara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dm.getDataVector().removeAllElements();
			    revalidate();
				
				Kargo k_=new Kargo();
				Sube s=new Sube();
				if(rdbtnTel.isSelected()){
					k_.aliciTel=txtF_kargoArama.getText();
				}else if(rdbtSube.isSelected()){
					s.subeAdi=txtF_kargoArama.getText();
				}
				k_._gondericiSube=s;
				ArrayList<Kargo> _k=new Kargo().kargoAra(k_);
				if(_k != null){
					
				    for(Kargo k: _k){
				    	String oT=k.ucrettip_==0?"Peþin Ödeme":"Kapýda ödeme";
				    	//kargodurum tablosu deðiþtiðinde burasýda deðiþmeli...
				    	String kD=k.durun_==1?"Kabul":k.durun_==2?"Alýcý Þubeye Sevkiyat":
				    		k.durun_==3?"Alýcý Þubede":k.durun_==4?"Alýcý Adrese Sevkiyat":"Teslim Edildi";
				    	Object[] row={
				    			k.kargo_,kD,k.aliciAdi,k.aliciSoyadi,k.aliciTel,k._aliciAdres.adres,
				    			k._aliciSube.subeAdi,k._subelerArasiSevkiyat.adi+" "+k._subelerArasiSevkiyat.soyadi,
				    			k._subeAdreseSevkiyat.adi+" "+k._subeAdreseSevkiyat.soyadi,oT,
				    			k._musteri.adi,k._musteri.soyadi
				    	};
				    	dm.addRow(row);
				    }
					
				}else{
					JOptionPane.showMessageDialog(null, 
							"Kargo bulunamadý !", "Arama sonucu", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				k_=null;
				
			}
		});
		btn_ara.setIcon(new ImageIcon("img\\arama.png"));
		btn_ara.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_ara.setBounds(10, 260, 136, 32);
		panel.add(btn_ara);
		
		//temizle buttonu
		JButton btn_temizle = new JButton("Temizle");
		btn_temizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtF_kargoArama.setText("");
				dm.getDataVector().removeAllElements();
				table.invalidate();
				table.revalidate();
				table.repaint();
				
			}
		});
		btn_temizle.setIcon(new ImageIcon("img\\temizle.png"));
		btn_temizle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_temizle.setBounds(10, 319, 136, 32);
		panel.add(btn_temizle);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(205, 92, 92));
		panel_1.setBorder(new TitledBorder(null, "Kargo Sonuclar\u0131", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(173, 11, 757, 371);
		add(panel_1);
		panel_1.setLayout(null);
		
		//tablo
		table = new JTable();
		dm = new DefaultTableModel(0, 0);
		dm.setColumnIdentifiers(new String[] {
				"id","Durumu","Alýcý Adý", "Alýcý Soyadý", "Alýcý Tel", "Alýcý Adres", "Alýcý Þube", 
				"Þubeye S.", "Adrese S.", "Ödeme Tipi", "Müþteri Adý", "Müþteri Soyadý"
			});
		table.setModel(dm);
		table.getColumn("id").setPreferredWidth(0);
		table.getColumn("id").setMinWidth(0);
		table.getColumn("id").setWidth(0);
		table.getColumn("id").setMaxWidth(0);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 27, 737, 320);
		panel_1.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(205, 133, 63));
		panel_3.setBounds(173, 393, 757, 70);
		add(panel_3);
		panel_3.setLayout(null);
		
		//Güncelle buttonu
		JButton btn_guncelle = new JButton("G\u00FCncelle");
		btn_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i=table.getSelectedRow();
				if(i < 0){
					JOptionPane.showMessageDialog(null, 
							"Lütfen veri seçin !!!", "Veri Seçme hatasý", 
							JOptionPane.ERROR_MESSAGE);
				}else{
					//JOptionPane.showMessageDialog(null, personel_);
					int kargo_=Integer.valueOf(table.getValueAt(i,0).toString());
					dialog = new dlgKargoGuncelle(kargotakip.frmMain.frame,kargo_);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.addWindowListener(new WindowListener() {
						
						@Override
						public void windowClosed(WindowEvent arg0) {
							
							if(dialog.kayitBasarliMi){
								Kargo k=dialog.k;
								int i=table.getSelectedRow();
								if(k.durun_==5){
									dm.removeRow(i);
								}else{
									String kD=k.durun_==1?"Kabul":k.durun_==2?"Alýcý Þubeye Sevkiyat":
							    		k.durun_==3?"Alýcý Þubede":k.durun_==4?"Alýcý Adrese Sevkiyat":"Teslim Edildi";
									dm.setValueAt(kD, i, 1);
									dm.setValueAt(k._subeAdreseSevkiyat.adi, i, 8);
								}
								
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
		btn_guncelle.setBounds(614, 11, 133, 48);
		panel_3.add(btn_guncelle);
		btn_guncelle.setBackground(new Color(50, 205, 50));
		btn_guncelle.setIcon(new ImageIcon("img\\guncelleme.png"));
		
		// kargo numarasý al buttonu
		JButton btnKargoNosuAl = new JButton("Kargo No'su Al");
		btnKargoNosuAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i=table.getSelectedRow();
				if(i<0){
					JOptionPane.showMessageDialog(null, 
							"Lütfen veri seçin !!!", "Veri Seçme hatasý", 
							JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, 
							"Kargo No: "+table.getValueAt(i,0).toString(), "Kargo Numarasý", 
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});
		btnKargoNosuAl.setIcon(new ImageIcon("img\\numara.png"));
		btnKargoNosuAl.setBounds(385, 11, 167, 48);
		panel_3.add(btnKargoNosuAl);
		
	}
}
