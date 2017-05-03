package kargotakip;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import kargotakip.op.Kargo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class pnlListeleme extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private DefaultTableModel dm;
	private dlgKargoGuncelle dialog=null;

	
	public pnlListeleme(int btn) {
		setBackground(new Color(107, 142, 35));
		setBounds(80, 80, 970, 461);
		setLayout(null);
		
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
		scrollPane.setBounds(10, 11, 952, 366);
		add(scrollPane);
		
		// þubedeki kargolar
		if(btn == 0){
			dm.getDataVector().removeAllElements();
		    revalidate();
		    ArrayList<Kargo> _k=new Kargo().subedekiKargolar(frmMain.SUBE_);
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
			    _k=null;
			}
		    
		 //gelecek kargolar 
		}else if(btn == 1){
			dm.getDataVector().removeAllElements();
		    revalidate();
		    
		    ArrayList<Kargo> _k=new Kargo().gelecekKargolar(frmMain.SUBE_);
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
			    _k=null;
			}
		    
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(10, 388, 952, 62);
		add(panel);
		panel.setLayout(null);
		
		// güncelle buttonu
		JButton btn_guncelle = new JButton("G\u00FCncelle");
		btn_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				int i=table.getSelectedRow();
				if(i < 0){
					JOptionPane.showMessageDialog(null, 
							"Lütfen veri seçin !!!", "Veri Seçme hatasý", 
							JOptionPane.ERROR_MESSAGE);
				}else{
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
		btn_guncelle.setBackground(new Color(0, 250, 154));
		btn_guncelle.setIcon(new ImageIcon("img\\guncelleme.png"));
		btn_guncelle.setBounds(825, 11, 117, 40);
		panel.add(btn_guncelle);
		
		//kargo numarasý al
		JButton btnKargoNosuAl = new JButton("Kargo No'su Al");
		btnKargoNosuAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		btnKargoNosuAl.setBounds(548, 11, 165, 40);
		panel.add(btnKargoNosuAl);

	}
}
