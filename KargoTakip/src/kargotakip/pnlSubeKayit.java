package kargotakip;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import kargotakip.op.Adres;
import kargotakip.op.Sube;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

public class pnlSubeKayit extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtF_subeAd;
	private JComboBox<Object> cBox_il;
	private JComboBox<Object> cBox_ilce;
	ArrayList<Adres> ilceler;
	private JPanel panel;
	private JTextPane txtP_adres;
	
	public pnlSubeKayit() {
		setBounds(100, 40, 484, 527);
		setBackground(new Color(139, 69, 19));
		setLayout(null);
		{
			panel = new JPanel();
			panel.setBackground(new Color(160, 82, 45));
			panel.setBorder(new TitledBorder(null, "\u015Eube Ekleme", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(29, 11, 409, 421);
			add(panel);
			panel.setLayout(null);
			{
				JLabel lblubeAd = new JLabel("\u015Eube Ad :");
				lblubeAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblubeAd.setBounds(31, 44, 71, 39);
				panel.add(lblubeAd);
			}
			{
				JLabel lblubeTipi = new JLabel("\u015Eube Tip :");
				lblubeTipi.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblubeTipi.setBounds(31, 96, 71, 34);
				panel.add(lblubeTipi);
			}
			
			txtF_subeAd = new JTextField();
			txtF_subeAd.setBounds(130, 53, 219, 25);
			panel.add(txtF_subeAd);
			txtF_subeAd.setColumns(10);
			
			JComboBox<Object> cBox_subeTip = new JComboBox<Object>();
			cBox_subeTip.setModel(new DefaultComboBoxModel<Object>(new String[] {"Genel \u015Eube"}));
			cBox_subeTip.addItem("Alt Þube");
			cBox_subeTip.setEnabled(false);
			cBox_subeTip.setBounds(130, 103, 219, 25);
			panel.add(cBox_subeTip);
			{
				JLabel lblNewLabel = new JLabel("\u0130l:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel.setBounds(62, 162, 40, 25);
				panel.add(lblNewLabel);
			}
			{
				
				
				cBox_il = new JComboBox<Object>(new Adres().illerVector());
				cBox_il.setBounds(130, 164, 219, 25);
				panel.add(cBox_il);
				
				cBox_il.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				        
				    	panel.remove(cBox_ilce);
				    	Adres.Item selected_item = (Adres.Item) cBox_il.getSelectedItem();
				    	Vector<Object> i_=new Adres().ilcelerVector(selected_item.getId());
				    	cBox_ilce = new JComboBox<Object>(i_);
						cBox_ilce.setBounds(130, 217, 219, 25);
						panel.add(cBox_ilce);
						panel.invalidate();
						panel.revalidate();
						panel.repaint();
				    	
				    }
				});
				
			}
			{
				cBox_ilce = new JComboBox<Object>();
				cBox_ilce.setBounds(130, 217, 219, 25);
				panel.add(cBox_ilce);
				
			}
			{
				JLabel lblIle = new JLabel("\u0130l\u00E7e:");
				lblIle.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblIle.setBounds(62, 215, 40, 25);
				panel.add(lblIle);
			}
			{
				txtP_adres = new JTextPane();
				txtP_adres.setForeground(Color.BLACK);
				txtP_adres.setBackground(Color.WHITE);
				txtP_adres.setBounds(130, 278, 219, 113);
				panel.add(txtP_adres);
			}
			{
				JLabel lblAdres = new JLabel("Adres:");
				lblAdres.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblAdres.setBounds(62, 278, 40, 25);
				panel.add(lblAdres);
			}
		}
		{      //kaydet buttonu
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, ".", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setBounds(29, 434, 410, 82);
			add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
			{
				JButton btn_sKaydet = new JButton("Kaydet");
				btn_sKaydet.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(txtF_subeAd.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Þube adý boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(cBox_ilce.getSelectedItem() == null){
							JOptionPane.showMessageDialog(null, 
									"Ýlçeyi boþ býrakamazsýnýz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else if(txtP_adres.getText().equals("")){
							JOptionPane.showMessageDialog(null, 
									"Þube adresini boþ geçemezsiniz !!!", "Boþ býrakma hatasý!", 
									JOptionPane.ERROR_MESSAGE);
						}else{
							Sube s=new Sube();
							Adres a=new Adres();
							s.subeAdi=txtF_subeAd.getText();
							Adres.Item selected_item = (Adres.Item) cBox_ilce.getSelectedItem();
							a.ilce_=selected_item.getId();
							a.adres=txtP_adres.getText();
							s._adres=a;
							if(new Sube().subeEkle(s)){
								txtF_subeAd.setText("");
								txtP_adres.setText("");
								JOptionPane.showMessageDialog(null, 
										"Þube baþarýyla kayýt edildi.", "Baþarlý", 
										JOptionPane.INFORMATION_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null, 
										"Þube kayd edilmedi !!!", "Baþarsýz !", 
										JOptionPane.ERROR_MESSAGE);
							}
						}
						
					}
				});
				btn_sKaydet.setIcon(new ImageIcon("img\\kaydet.png"));
				btn_sKaydet.setActionCommand("OK");
				btn_sKaydet.setBounds(271, 23, 129, 48);
				buttonPane.add(btn_sKaydet);
			}
		}
	}

}
