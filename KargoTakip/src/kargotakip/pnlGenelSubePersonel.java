package kargotakip;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.ImageIcon;


public class pnlGenelSubePersonel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel pnl_sag;
	
	public pnlGenelSubePersonel(int _w, int _h) {
		setBackground(new Color(95, 158, 160));

		//setBounds((_w-507)/2, (_h-400)/2, 795, 497);
		setBounds(0, 0, 1366,668);
		setLayout(null);

		//------------------------sol panel-----------------------------------------------
		
		JPanel pnl_sol = new JPanel();
		pnl_sol.setBackground(new Color(245, 245, 220));
		pnl_sol.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnl_sol.setBounds(10, 11, 185, 646);
		add(pnl_sol);
		pnl_sol.setLayout(null);
		
		JButton btn_yeniKargo = new JButton("Yeni Kargo");
		btn_yeniKargo.setBackground(new Color(245, 222, 179));
		btn_yeniKargo.setIcon(new ImageIcon("img\\ekle.png"));
		btn_yeniKargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlKargoKayit _k=new pnlKargoKayit();
				_k.setBounds(105, 50, 881, 516);
				panelEkle(_k);
			}
		});
		btn_yeniKargo.setBounds(10, 22, 165, 34);
		pnl_sol.add(btn_yeniKargo);
		
		JButton btn_subedekiKargolar = new JButton("\u015Eubedeki Kargolar");
		btn_subedekiKargolar.setBackground(new Color(245, 222, 179));
		btn_subedekiKargolar.setIcon(new ImageIcon("img\\burada.png"));
		btn_subedekiKargolar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(new pnlListeleme(0));
			}
		});
		btn_subedekiKargolar.setBounds(10, 85, 165, 34);
		pnl_sol.add(btn_subedekiKargolar);
		
		JButton btn_gelecekKargolar = new JButton("Gelecek Kargolar");
		btn_gelecekKargolar.setBackground(new Color(245, 222, 179));
		btn_gelecekKargolar.setIcon(new ImageIcon("img\\yolda-gelecek.png"));
		btn_gelecekKargolar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(new pnlListeleme(1));
			}
		});
		btn_gelecekKargolar.setBounds(10, 145, 165, 34);
		pnl_sol.add(btn_gelecekKargolar);
		
		JButton btn_kargoAra = new JButton("Kargo Ara");
		btn_kargoAra.setBackground(new Color(245, 222, 179));
		btn_kargoAra.setIcon(new ImageIcon("img\\arama.png"));
		btn_kargoAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlKargoAra _a=new pnlKargoAra();
				_a.setBounds(90, 60, 940, 477);
				panelEkle(_a);
			}
		});
		btn_kargoAra.setBounds(10, 215, 165, 34);
		pnl_sol.add(btn_kargoAra);
		
		//----------------Sað Panl--------------------------------------------
		
		pnl_sag = new JPanel();
		pnl_sag.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnl_sag.setBounds(205, 11, 1147, 657);
		pnl_sag.setBackground(new Color(245, 245, 220));
		add(pnl_sag);
		pnl_sag.setLayout(null);
		panelEkle(new pnlListeleme(0));
		
		
	}
	
	public void panelEkle(JPanel _p){
		pnl_sag.removeAll();
		pnl_sag.add(_p);
		pnl_sag.invalidate();
		pnl_sag.revalidate();
		pnl_sag.repaint();
	}
	
}
