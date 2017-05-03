package kargotakip;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import kargotakip.op.Kargo;

public class pnlMusteriGiris extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtF_aTel;
	private JTextField txtF_kargoNo;

	
	public pnlMusteriGiris(int _w,int _h) {
		setBounds((_w-450)/2, (_h-195)/2, 476, 195);
		setBackground(new Color(204, 204, 204));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 153));
		panel.setBounds(0, 0, 476, 55);
		add(panel);
		
		JLabel lblMteriKargoSorgulama = new JLabel("M\u00FC\u015Fteri Kargo Sorgulama");
		lblMteriKargoSorgulama.setForeground(new Color(255, 255, 255));
		panel.add(lblMteriKargoSorgulama);
		lblMteriKargoSorgulama.setBackground(new Color(0, 0, 153));
		lblMteriKargoSorgulama.setHorizontalAlignment(SwingConstants.CENTER);
		lblMteriKargoSorgulama.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		JLabel lblNewLabel = new JLabel("Al\u0131c\u0131 Tel:");
		lblNewLabel.setBounds(24, 80, 63, 31);
		add(lblNewLabel);
		
		JLabel lblKargoNo = new JLabel("Kargo No:");
		lblKargoNo.setBounds(238, 80, 63, 31);
		add(lblKargoNo);
		
		txtF_aTel = new JTextField();
		txtF_aTel.setBounds(89, 82, 139, 26);
		add(txtF_aTel);
		txtF_aTel.setColumns(10);
		
		txtF_kargoNo = new JTextField();
		txtF_kargoNo.setColumns(10);
		txtF_kargoNo.setBounds(311, 82, 155, 26);
		add(txtF_kargoNo);
		
		JButton btn_kargoSorgu = new JButton("Sorgula");
		btn_kargoSorgu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!txtF_aTel.getText().trim().equals("") && !txtF_kargoNo.getText().trim().equals("")){
					
					if(new Kargo().musteriKargobilgisi(txtF_aTel.getText().trim(), Integer.valueOf(txtF_kargoNo.getText().trim())) > 0 ){
						panelCiz(_w, _h,Integer.valueOf(txtF_kargoNo.getText().trim()));
					}else{
						JOptionPane.showMessageDialog(null, "Aradýðýnýz kargo bullunamadý !!!", "Arama Sonuçsuz !", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Alanlarý boþ geçemezsiniz !!!", "Boþ geçme hatasý !", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btn_kargoSorgu.setBackground(new Color(153, 153, 51));
		btn_kargoSorgu.setBounds(377, 141, 89, 31);
		add(btn_kargoSorgu);
		
	}
	
	public void panelCiz(int _w,int _h,int kargo_){
		frmMain.PANEL.removeAll();
		JPanel _pnl=new pnlMusteriKargoBilgi(kargo_);
		_pnl.setBounds((_w-582)/2, (_h-422)/2, 582, 422);
		frmMain.PANEL.add(_pnl);
		frmMain.PANEL.invalidate();
		frmMain.PANEL.revalidate();
		frmMain.PANEL.repaint();
	}
}
