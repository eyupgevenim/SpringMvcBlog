package kargotakip;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import kargotakip.op.Adres;
import kargotakip.op.Sube;

import javax.swing.DefaultComboBoxModel;


public class dlgSubeGuncelle extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtF_subeAd;
	private JTextPane txtP_adres ;
	private JComboBox<Object> cBox_il;
	private JComboBox<Object> cBox_ilce;
	public Sube _s;
	public boolean kayitBasarliMi=false;
	public JButton btn_sIptal;

	
	public dlgSubeGuncelle(frmMain f,int sube_) {
		super(f);
		setTitle("\u015Eube G\u00FCncelleme");
		setBounds((f.getWidth()-550)/2, (f.getHeight()-510)/2, 549, 510);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(105, 105, 105));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{
			// Bu sayfa yüklenirken
			_s=new Sube().subeBilgisi(sube_);
			
		}
		
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(46, 139, 87));
			panel.setBorder(new TitledBorder(null, "\u015Eube G\u00FCncelle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 476, 402);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("\u015Eube Ad:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel.setBounds(35, 53, 77, 26);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblubeTip = new JLabel("\u015Eube Tip:");
				lblubeTip.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblubeTip.setBounds(35, 90, 77, 26);
				panel.add(lblubeTip);
			}
			{
				JLabel lblIl = new JLabel("\u0130l:");
				lblIl.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblIl.setBounds(35, 160, 77, 26);
				panel.add(lblIl);
			}
			{
				JLabel lblIle = new JLabel("\u0130l\u00E7e:");
				lblIle.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblIle.setBounds(35, 200, 77, 26);
				panel.add(lblIle);
			}
			{
				JLabel lblAdres = new JLabel("Adres:");
				lblAdres.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblAdres.setBounds(35, 252, 77, 26);
				panel.add(lblAdres);
			}
			{
				JComboBox<Object> cBox_subeTip = new JComboBox<Object>();
				cBox_subeTip.setModel(new DefaultComboBoxModel<Object>(new String[] {"Genel \u015Eube"}));
				cBox_subeTip.setEnabled(false);
				cBox_subeTip.setBounds(122, 90, 190, 26);
				panel.add(cBox_subeTip);
			}
			{
				cBox_il = new JComboBox<Object>(new Adres().illerVector());
				cBox_il.setBounds(122, 165, 190, 26);
				panel.add(cBox_il);
				
				cBox_il.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				        
				    	panel.remove(cBox_ilce);
				    	Adres.Item selected_item = (Adres.Item) cBox_il.getSelectedItem();
				    	Vector<Object> i_=new Adres().ilcelerVector(selected_item.getId());
				    	cBox_ilce = new JComboBox<Object>(i_);
				    	cBox_ilce.setBounds(122, 205, 190, 26);
						panel.add(cBox_ilce);
						panel.invalidate();
						panel.revalidate();
						panel.repaint();
				    	
				    }
				});
				
			}
			{
				cBox_ilce = new JComboBox<Object>();
				cBox_ilce.setBounds(122, 205, 190, 26);
				panel.add(cBox_ilce);
			}
			{
				txtF_subeAd = new JTextField();
				txtF_subeAd.setText(_s.subeAdi);
				txtF_subeAd.setBounds(122, 53, 190, 25);
				panel.add(txtF_subeAd);
				txtF_subeAd.setColumns(10);
			}
			{
				txtP_adres = new JTextPane();
				txtP_adres.setText(_s._adres.adres);
				txtP_adres.setBounds(122, 252, 190, 123);
				panel.add(txtP_adres);
			}
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(112, 128, 144));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// Kaydet e basýldýðýnda
				JButton btn_sKaydet = new JButton("Kaydet");
				btn_sKaydet.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
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
							
							Adres a=new Adres();
							a.adres_=_s._adres.adres_;
							
							_s.subeAdi=txtF_subeAd.getText();
							a.adres=txtP_adres.getText();
							
							Adres.Item selected_ilce = (Adres.Item) cBox_ilce.getSelectedItem();
							a.ilce_=selected_ilce.getId();
							a.ilce=cBox_ilce.getSelectedItem().toString();
							
							Adres.Item selected_il = (Adres.Item) cBox_il.getSelectedItem();
							a.il_=selected_il.getId();
							a.il=cBox_il.getSelectedItem().toString();
							
							//JOptionPane.showMessageDialog(null, "ilce "+_s.sube_);
							
							_s._adres=a;
							
							if(new Sube().subeGuncelle(_s)){
								kayitBasarliMi=true;
								dispose();
							}else{
								JOptionPane.showMessageDialog(null, 
										"Þubeyi güncelerken bir hata ile karþýlaþtý !!!","Bir hata oluþtu !", 
										JOptionPane.ERROR_MESSAGE);
							}
							
						}
						
						
					}
				});
				btn_sKaydet.setBackground(new Color(0, 255, 0));
				btn_sKaydet.setIcon(new ImageIcon("img\\kaydet.png"));
				btn_sKaydet.setActionCommand("OK");
				buttonPane.add(btn_sKaydet);
				getRootPane().setDefaultButton(btn_sKaydet);
			}
			{
				// Ýptal buttonuna basýldýðýnda
				btn_sIptal = new JButton("\u0130ptal");
				btn_sIptal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
					}
				});
				btn_sIptal.setBackground(new Color(255, 140, 0));
				btn_sIptal.setIcon(new ImageIcon("img\\iptal.png"));
				btn_sIptal.setActionCommand("Cancel");
				buttonPane.add(btn_sIptal);
			}
		}
	}
	

}
