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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import kargotakip.op.Adres;
import kargotakip.op.Sube;

public class pnlSubeAra extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtF_karagoAra;
	public JTable tblSubeSonuc;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultTableModel dm;
	private dlgSubeGuncelle dialog=null;
	
	
	public pnlSubeAra() {
		setBackground(new Color(85, 107, 47));
		setBounds(100,100,800,450);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(160, 82, 45));
		panel.setBounds(10, 11, 286, 438);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(119, 136, 153));
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Arama", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(81, 96, 133, 165);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnAd = new JRadioButton("Ad");
		buttonGroup.add(rdbtnAd);
		rdbtnAd.setBackground(new Color(119, 136, 153));
		rdbtnAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnAd.setBounds(37, 30, 63, 23);
		rdbtnAd.setSelected(true);//
		panel_2.add(rdbtnAd);
		
		JRadioButton rdbtnIl = new JRadioButton("\u0130l");
		buttonGroup.add(rdbtnIl);
		rdbtnIl.setBackground(new Color(119, 136, 153));
		rdbtnIl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnIl.setBounds(37, 78, 51, 23);
		panel_2.add(rdbtnIl);
		
		JRadioButton rdbtnIlce = new JRadioButton("\u0130l\u00E7e");
		buttonGroup.add(rdbtnIlce);
		rdbtnIlce.setBackground(new Color(119, 136, 153));
		rdbtnIlce.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnIlce.setBounds(37, 123, 63, 23);
		panel_2.add(rdbtnIlce);
		
		// Aramayý temizleyen button
		JButton btnTemizle = new JButton("Temizle");
		btnTemizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtF_karagoAra.setText("");
				dm.getDataVector().removeAllElements();
				tblSubeSonuc.invalidate();
				tblSubeSonuc.revalidate();
				tblSubeSonuc.repaint();
			}
		});
		btnTemizle.setIcon(new ImageIcon("img\\temizle.png"));
		btnTemizle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTemizle.setBounds(27, 322, 113, 34);
		panel.add(btnTemizle);
		
		// Ara buttonuna basýldýðýnda
		JButton btnAra = new JButton("Ara");
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sube s=new Sube();
				Adres a=new Adres();
				
				if(rdbtnAd.isSelected()){
					s.subeAdi=txtF_karagoAra.getText();
				}else if(rdbtnIl.isSelected()){
					a.il=txtF_karagoAra.getText();
				}else if(rdbtnIlce.isSelected()){
					a.ilce=txtF_karagoAra.getText();
				}
				s._adres=a;
				
				ArrayList<Sube> _s=new Sube().subeAra(s);
				if(_s !=null){
					dm.getDataVector().removeAllElements();
				    revalidate();
					for(Sube sler:_s){
						
						Object[] row = {sler.sube_, sler.subeAdi, sler.subeTip, 
								sler._adres.il, sler._adres.ilce, sler._adres.adres};
						dm.addRow(row);
					}
				}else{
					JOptionPane.showMessageDialog(null, 
							"Þube bulunamadý !", "Arama sonucu", 
							JOptionPane.INFORMATION_MESSAGE);
				}
				s=null;
			}
		});
		btnAra.setIcon(new ImageIcon("D:\\JavaProject\\Odev\\KargoTakip\\img\\arama.png"));
		btnAra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAra.setBounds(150, 322, 104, 34);
		panel.add(btnAra);
		
		txtF_karagoAra = new JTextField();
		txtF_karagoAra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtF_karagoAra.setBounds(27, 30, 227, 34);
		panel.add(txtF_karagoAra);
		txtF_karagoAra.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u015Eube \u015Eonu\u00E7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(189, 183, 107));
		panel_1.setBounds(306, 11, 488, 438);
		add(panel_1);
		panel_1.setLayout(null);
		
		//Veri tablosu
		tblSubeSonuc = new JTable();
		dm = new DefaultTableModel(0, 0);
		 dm.setColumnIdentifiers(new String[] {"id","Adý", "Tipi", "Ýl", "Ýlçe", "Adres"});
		tblSubeSonuc.setModel(dm);
		tblSubeSonuc.getColumn("id").setPreferredWidth(0);
		tblSubeSonuc.getColumn("id").setMinWidth(0);
		tblSubeSonuc.getColumn("id").setWidth(0);
		tblSubeSonuc.getColumn("id").setMaxWidth(0);
		JScrollPane scrollPane = new JScrollPane(tblSubeSonuc);
		scrollPane.setBounds(22, 21, 456, 320);
		panel_1.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(119, 136, 153));
		panel_3.setLayout(null);
		panel_3.setBounds(22, 352, 456, 64);
		panel_1.add(panel_3);
		
		// silme buttonuna basýldýðýnda
		JButton btn_sSil = new JButton("Sil");
		btn_sSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i=tblSubeSonuc.getSelectedRow();
				if(i < 0){
					JOptionPane.showMessageDialog(null, 
							"Lütfen veri seçin !!!", "Veri Seçme hatasý", 
							JOptionPane.INFORMATION_MESSAGE);
				}else{
					int sube_=Integer.valueOf(tblSubeSonuc.getValueAt(i,0).toString());
					
	            	int EvetVeyeHayir= JOptionPane.showConfirmDialog (null, 
	                		"Þubeyi silmek istediðinize emin misiniz ?",
	                		"Önemli Uyarý !!!",JOptionPane.YES_NO_OPTION);
	                if(EvetVeyeHayir == JOptionPane.YES_OPTION){ 
	                	if(new Sube().subeSil(sube_)){
	                		dm.removeRow(i);
	                	}else{
	                		JOptionPane.showMessageDialog(null, 
	    							"Þube silinemedi", "Bir hata oluþtu !", 
	    							JOptionPane.INFORMATION_MESSAGE);
	                	}
	                }
					
				}
				
			}
		});
		btn_sSil.setBackground(new Color(220, 20, 60));
		btn_sSil.setIcon(new ImageIcon("D:\\JavaProject\\Odev\\KargoTakip\\img\\sil_.png"));
		btn_sSil.setBounds(319, 11, 127, 42);
		panel_3.add(btn_sSil);
		
		JButton btn_sGuncelle = new JButton("G\u00FCncelle");
		btn_sGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i=tblSubeSonuc.getSelectedRow();
				if(i < 0){
					JOptionPane.showMessageDialog(null, 
							"Lütfen veri seçin !!!", "Veri Seçme hatasý", 
							JOptionPane.INFORMATION_MESSAGE);
				}else{
					int sube_=Integer.valueOf(tblSubeSonuc.getValueAt(i,0).toString());
					dialog = new dlgSubeGuncelle(kargotakip.frmMain.frame,sube_);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.addWindowListener(new WindowListener() {
						
						
						@Override
						public void windowClosed(WindowEvent arg0) {
							
							if(dialog.kayitBasarliMi){
								Sube tmp=dialog._s;
								int i=tblSubeSonuc.getSelectedRow();
								dm.setValueAt(tmp.subeAdi, i, 1);
								dm.setValueAt(tmp.subeTip, i, 2);
								dm.setValueAt(tmp._adres.il, i, 3);
								dm.setValueAt(tmp._adres.ilce, i, 4);
								dm.setValueAt(tmp._adres.adres, i, 5);
								
							}
						}

						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						
					});
					
				}
				
			}
		});
		btn_sGuncelle.setBackground(new Color(144, 238, 144));
		btn_sGuncelle.setIcon(new ImageIcon("D:\\JavaProject\\Odev\\KargoTakip\\img\\guncelleme.png"));
		btn_sGuncelle.setBounds(160, 11, 127, 42);
		panel_3.add(btn_sGuncelle);
		
		
		
		tblSubeSonuc.addMouseListener(new MouseAdapter() {
	          public void mouseClicked(MouseEvent e) {  
	              tblSubeSonuc.setColumnSelectionAllowed(false);
	              //table.setRowSelectionAllowed(true);
	          }  
	    });
		
		/*
		tblSubeSonuc.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            //btn_sSil.enable(true);
	        }
	    });
		*/
		
		/*
		tblSubeSonuc.getModel().addTableModelListener(new TableModelListener() {

		      public void tableChanged(TableModelEvent e) {
		         // your code goes here;
		      }
		    });
		*/
		
	}

}
