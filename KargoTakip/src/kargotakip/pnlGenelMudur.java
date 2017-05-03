package kargotakip;


import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.ImageIcon;
import java.awt.SystemColor;


public class pnlGenelMudur extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel tmpPanel;
	
	public pnlGenelMudur(int _w,int _h) {
		setBounds(0, 0, 1366,668);
		setLayout(null);
		
		//Sürekli içi deðiþen en altaki panel
		tmpPanel = new JPanel();
		tmpPanel.setBounds(0, 0, 1366, 668);
		add(tmpPanel);
		tmpPanel.setLayout(null);
		
		//------Ana sayfa panelli--------------------------------------------------------------------------
				
		JPanel anaPanel = new JPanel();
		anaPanel.setBackground(new Color(135, 206, 235));
		anaPanel.setBounds(0, 0, 1366, 668);
		tmpPanel.add(anaPanel);
		anaPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		//panel.setBounds(0, 0, 750, 420);
		panel.setBounds((_w-750)/2, (_h-421)/2, 750, 421);
		panel.setBackground(new Color(135, 206, 235));
		anaPanel.add(panel);
		panel.setLayout(null);
		
		//------Personel Ýþlem alt menu sayfasý--------------------------------------------------------------------------
				
		JPanel personelIslem = new JPanel();
		personelIslem.setBackground(SystemColor.activeCaption);
		personelIslem.setBounds(0, 0, 1366,668);
		//tmpPanel.add(personelIslem);
		personelIslem.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 222, 179));
		panel_1.setBorder(new TitledBorder(null, "Personel \u0130\u015Flem Alt Men\u00FC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 24, 341, 633);
		personelIslem.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 222, 179));
		panel_2.setBounds(361, 23, 995, 645);
		personelIslem.add(panel_2);
		sagpanelePanelEkle(panel_2,new pnlPersonelAra()); //
		panel_2.setLayout(null);
		
		JButton btn_pGeri = new JButton("Anasayfaya Geri D\u00F6n");
		btn_pGeri.setBackground(new Color(46, 139, 87));
		btn_pGeri.setIcon(new ImageIcon("img\\geri.png"));
		btn_pGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(anaPanel);
			}
		});
		btn_pGeri.setBounds(68, 546, 182, 42);
		panel_1.add(btn_pGeri);
		
		
		JButton btn_pEkle = new JButton("Personel Ekle");
		btn_pEkle.setBackground(new Color(46, 139, 87));
		btn_pEkle.setIcon(new ImageIcon("img\\ekle.png"));
		btn_pEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_2, new pnlPersonelKayit());
			}
		});
		btn_pEkle.setBounds(68, 70, 188, 42);
		panel_1.add(btn_pEkle);
		
		JButton btn_pAra = new JButton("Personel Ara");
		btn_pAra.setBackground(new Color(46, 139, 87));
		btn_pAra.setIcon(new ImageIcon("img\\arama.png"));
		btn_pAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_2,new pnlPersonelAra());
			}
		});
		btn_pAra.setBounds(68, 146, 188, 42);
		panel_1.add(btn_pAra);
		
		//------Þube Ýþlem alt menu sayfasý--------------------------------------------------------------------------
				
		JPanel subeIslem = new JPanel();
		subeIslem.setBackground(SystemColor.activeCaption);
		subeIslem.setBounds(0, 0, 1366,668);
		//tmpPanel.add(subeIslem);
		subeIslem.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(245, 222, 179));
		panel_3.setBorder(new TitledBorder(null, "\u015Eube \u0130\u015Flem Alt Men\u00FC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 11, 341, 646);
		subeIslem.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(245, 222, 179));
		panel_4.setBounds(361, 23, 995, 713);
		personelIslem.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(245, 222, 179));
		panel_9.setBounds(361, 11, 995, 646);
		subeIslem.add(panel_9);
		sagpanelePanelEkle(panel_9,new pnlSubeAra());
		panel_9.setLayout(null);
		
		JButton btn_sGeri = new JButton("Anasayfaya Geri D\u00F6n");
		btn_sGeri.setIcon(new ImageIcon("img\\geri.png"));
		btn_sGeri.setBackground(new Color(46, 139, 87));
		btn_sGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(anaPanel);
			}
		});
		btn_sGeri.setBounds(68, 551, 182, 42);
		panel_3.add(btn_sGeri);
		
		JButton btn_sEkle = new JButton("Þube Ekle");
		btn_sEkle.setIcon(new ImageIcon("img\\ekle.png"));
		btn_sEkle.setBackground(new Color(46, 139, 87));
		btn_sEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_9,new pnlSubeKayit());
			}
		});
		btn_sEkle.setBounds(68, 84, 188, 42);
		panel_3.add(btn_sEkle);
		
		JButton btn_sAra = new JButton("Þube Ara");
		btn_sAra.setIcon(new ImageIcon("img\\arama.png"));
		btn_sAra.setBackground(new Color(46, 139, 87));
		btn_sAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_9,new pnlSubeAra());
			}
		});
		btn_sAra.setBounds(68, 160, 188, 42);
		panel_3.add(btn_sAra);
			
		
		//------Kargo Ýþlem alt menu sayfasý--------------------------------------------------------------------------
				
		JPanel kargoIslem = new JPanel();
		kargoIslem.setBackground(SystemColor.activeCaption);
		kargoIslem.setBounds(0, 0, 1366,668);
		//tmpPanel.add(kargoIslem);
		kargoIslem.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(245, 222, 179));
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kargo \u0130\u015Flem Alt Men\u00FC", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(10, 11, 341, 646);
		kargoIslem.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(245, 222, 179));
		panel_6.setBounds(361, 11, 995, 657);
		kargoIslem.add(panel_6);
		sagpanelePanelEkle(panel_6, new pnlKargoAra()); //
		panel_6.setLayout(null);
		
		JButton btn_kGeri = new JButton("Anasayfaya Geri D\u00F6n");
		btn_kGeri.setIcon(new ImageIcon("img\\geri.png"));
		btn_kGeri.setBackground(new Color(46, 139, 87));
		btn_kGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(anaPanel);
			}
		});
		btn_kGeri.setBounds(68, 547, 182, 42);
		panel_5.add(btn_kGeri);
		
		JButton btn_kEkle = new JButton("Kargo Ekle");
		btn_kEkle.setIcon(new ImageIcon("img\\ekle.png"));
		btn_kEkle.setBackground(new Color(46, 139, 87));
		btn_kEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_6, new pnlKargoKayit());
			}
		});
		btn_kEkle.setBounds(76, 103, 188, 42);
		panel_5.add(btn_kEkle);
		
		JButton btn_kAra = new JButton("Kargo Ara");
		btn_kAra.setIcon(new ImageIcon("img\\arama.png"));
		btn_kAra.setBackground(new Color(46, 139, 87));
		btn_kAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_6, new pnlKargoAra());
			}
		});
		btn_kAra.setBounds(76, 179, 188, 42);
		panel_5.add(btn_kAra);
		
		
		//------Rapor Ýþlem alt menu sayfasý--------------------------------------------------------------------------
		
		JPanel raporIslem = new JPanel();
		raporIslem.setBackground(SystemColor.activeCaption);
		raporIslem .setBounds(0, 0, 1366,668);
		//tmpPanel.add(raporIslem );
		raporIslem.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(245, 222, 179));
		panel_7.setBorder(new TitledBorder(null, "Rapor \u0130\u015Flem Alt Men\u00FC", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_7.setBounds(10, 23, 341, 645);
		raporIslem.add(panel_7);
		panel_7.setLayout(null);
		
		JButton btn_rGeri = new JButton("Anasayfaya Geri D\u00F6n");
		btn_rGeri.setIcon(new ImageIcon("img\\geri.png"));
		btn_rGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(anaPanel);
			}
		});
		btn_rGeri.setBounds(74, 536, 182, 42);
		btn_rGeri.setBackground(new Color(46, 139, 87));
		panel_7.add(btn_rGeri);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(245, 222, 179));
		panel_8.setBounds(361, 23, 995, 645);
		raporIslem.add(panel_8);
		panel_8.setLayout(null);
		
		JButton btn_gmGunluk = new JButton("G\u00FCnl\u00FCk Raporlama");
		btn_gmGunluk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sagpanelePanelEkle(panel_8, new pnlGunlukRaporlamaGM());
			}
		});
		btn_gmGunluk.setIcon(new ImageIcon("img\\gunluk.png"));
		btn_gmGunluk.setBackground(new Color(46, 139, 87));
		btn_gmGunluk.setBounds(68, 89, 188, 42);
		panel_7.add(btn_gmGunluk);
		sagpanelePanelEkle(panel_8, new pnlGunlukRaporlamaGM());//varsayýlan
		
		JButton btn_gmAylik = new JButton("Ayl\u0131k Raporlama");
		btn_gmAylik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_8, new pnlAylikRaporlamaGM());
			}
		});
		btn_gmAylik.setIcon(new ImageIcon("img\\aylik.png"));
		btn_gmAylik.setBackground(new Color(46, 139, 87));
		btn_gmAylik.setBounds(68, 165, 188, 42);
		panel_7.add(btn_gmAylik);
		
		//------Ana menü kart buttonlarý--------------------------------------------------------------------------
		
		JButton btn_personelIslem = new JButton("Personel \u0130\u015Flemleri");
		btn_personelIslem.setIcon(new ImageIcon("img\\personel-islem.png"));
		btn_personelIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelEkle(personelIslem);
			}
		});
		btn_personelIslem.setBounds(0, 0, 341, 176);
		panel.add(btn_personelIslem);
		
		JButton btn_subeIslem = new JButton("\u015Eube \u0130\u015Flemleri");
		btn_subeIslem.setIcon(new ImageIcon("img\\sube.png"));
		btn_subeIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(subeIslem);
			}
		});
		btn_subeIslem.setBounds(0, 245, 341, 176);
		panel.add(btn_subeIslem);
		
		JButton btn_kargoIslem = new JButton("Kargo \u0130\u015Flemleri");
		btn_kargoIslem.setIcon(new ImageIcon("img\\kargo.png"));
		btn_kargoIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(kargoIslem);
			}
		});
		btn_kargoIslem.setBounds(409, 0, 341, 176);
		panel.add(btn_kargoIslem);
		
		JButton btn_raporIslem = new JButton("Rapor I\u015Flemleri");
		btn_raporIslem.setIcon(new ImageIcon("img\\rapor.png"));
		btn_raporIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(raporIslem);
			}
		});
		btn_raporIslem.setBounds(409, 245, 341, 176);
		panel.add(btn_raporIslem);
		
//----------------------------------------------------------------------------
			
	}
	
	// tmpPanel in içine panel ekleme
	public void panelEkle(JPanel _p){
		tmpPanel.removeAll();
		tmpPanel.add(_p);
		tmpPanel.invalidate();
		tmpPanel.revalidate();
		tmpPanel.repaint();
	}
	
	// Birinci panelin içine ikinci paneli ekleme
	public void sagpanelePanelEkle(JPanel _ekleyen,JPanel _eklenen){
		_ekleyen.removeAll();
		_ekleyen.add(_eklenen);
		_ekleyen.invalidate();
		_ekleyen.revalidate();
		_ekleyen.repaint();
	}
}
