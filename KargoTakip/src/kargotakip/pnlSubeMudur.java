package kargotakip;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;


public class pnlSubeMudur extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel tmpPanel;
	
	
	public pnlSubeMudur(int _w,int _h) {
		setBounds(0, 0, 1366,668);
		setLayout(null);
		
		//Sürekli içi deðiþen en altaki panel
		tmpPanel = new JPanel();
		tmpPanel.setBounds(0, 0, 1366, 668);
		add(tmpPanel);
		tmpPanel.setLayout(null);
		
		//------Ana sayfa panelli--------------------------------------------------------------------------
				
		JPanel anaPanel = new JPanel();
		anaPanel.setBackground(new Color(51, 204, 153));
		anaPanel.setBounds(0, 0, 1366, 668);
		tmpPanel.add(anaPanel);
		anaPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 204, 153));
		panel.setBounds(0, 0, 750, 420);
		panel.setBounds((_w-750)/2, (_h-421)/2, 750, 421);
		anaPanel.add(panel);
		panel.setLayout(null);
		
		//------Personel Ýþlem alt menu sayfasý--------------------------------------------------------------------------
				
		JPanel personelIslem = new JPanel();
		personelIslem.setBackground(new Color(135, 206, 250));
		personelIslem.setBounds(0, 0, 1356,668);
		//tmpPanel.add(personelIslem);
		personelIslem.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(210, 180, 140));
		panel_1.setBorder(new TitledBorder(null, "Personel \u0130\u015Flem Alt Men\u00FC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 341, 646);
		personelIslem.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(210, 180, 140));
		panel_2.setBounds(361, 11, 995, 657);
		personelIslem.add(panel_2);
		sagpanelePanelEkle(panel_2,new pnlPersonelAra()); //
		panel_2.setLayout(null);
		
		JButton btn_pGeri = new JButton("Anasayfaya Geri D\u00F6n");
		btn_pGeri.setIcon(new ImageIcon("img\\geri.png"));
		btn_pGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(anaPanel);
			}
		});
		btn_pGeri.setBounds(68, 546, 182, 42);
		panel_1.add(btn_pGeri);
		
		
		JButton btn_pEkle = new JButton("Personel Ekle");
		btn_pEkle.setBackground(new Color(135, 206, 250));
		btn_pEkle.setIcon(new ImageIcon("img\\ekle.png"));
		btn_pEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_2, new pnlPersonelKayit());
			}
		});
		btn_pEkle.setBounds(68, 70, 188, 42);
		panel_1.add(btn_pEkle);
		
		JButton btn_pAra = new JButton("Personel Ara");
		btn_pAra.setBackground(new Color(135, 206, 250));
		btn_pAra.setIcon(new ImageIcon("img\\arama.png"));
		btn_pAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_2,new pnlPersonelAra());
			}
		});
		btn_pAra.setBounds(68, 146, 188, 42);
		panel_1.add(btn_pAra);
		
		//------Müþteri Ýþlem alt menu sayfasý--------------------------------------------------------------------------
			
		JPanel musteriIslem = new JPanel();
		musteriIslem.setBackground(new Color(176, 224, 230));
		musteriIslem.setBounds(0, 0, 1366,668);
		//tmpPanel.add(musteriIslem);
		musteriIslem.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(210, 180, 140));
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "M\u00FC\u015Fteri \u0130\u015Flem Alt Men\u00FC", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 11, 341, 646);
		musteriIslem.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(210, 180, 140));
		panel_9.setBounds(361, 11, 995, 646);
		musteriIslem.add(panel_9);
		sagpanelePanelEkle(panel_9,new pnlMusteriAra()); //
		panel_9.setLayout(null);
		
		JButton btn_sGeri = new JButton("Anasayfaya Geri D\u00F6n");
		btn_sGeri.setIcon(new ImageIcon("img\\geri.png"));
		btn_sGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(anaPanel);
			}
		});
		btn_sGeri.setBounds(68, 551, 182, 42);
		panel_3.add(btn_sGeri);
		
		JButton btn_mAra = new JButton("M\u00FC\u015Fteri Ara");
		btn_mAra.setBackground(new Color(135, 206, 235));
		btn_mAra.setIcon(new ImageIcon("img\\arama.png"));
		btn_mAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_9,new pnlMusteriAra());
			}
		});
		btn_mAra.setBounds(68, 85, 188, 42);
		panel_3.add(btn_mAra);
			
		
		//------Kargo Ýþlem alt menu sayfasý--------------------------------------------------------------------------
				
		JPanel kargoIslem = new JPanel();
		kargoIslem.setBackground(new Color(102, 205, 170));
		kargoIslem.setBounds(0, 0, 1366,668);
		//tmpPanel.add(kargoIslem);
		kargoIslem.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(210, 180, 140));
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kargo \u0130\u015Flem Alt Men\u00FC", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(10, 11, 341, 646);
		kargoIslem.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(210, 180, 140));
		panel_6.setBounds(361, 11, 995, 657);
		kargoIslem.add(panel_6);
		sagpanelePanelEkle(panel_6, new pnlKargoAra());
		panel_6.setLayout(null);
		
		JButton btn_kGeri = new JButton("Anasayfaya Geri D\u00F6n");
		btn_kGeri.setIcon(new ImageIcon("img\\geri.png"));
		btn_kGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(anaPanel);
			}
		});
		btn_kGeri.setBounds(68, 547, 182, 42);
		panel_5.add(btn_kGeri);
		
		JButton btn_kEkle = new JButton("Kargo Ekle");
		btn_kEkle.setBackground(new Color(102, 205, 170));
		btn_kEkle.setIcon(new ImageIcon("img\\ekle.png"));
		btn_kEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_6, new pnlKargoKayit());
			}
		});
		btn_kEkle.setBounds(76, 103, 188, 42);
		panel_5.add(btn_kEkle);
		
		JButton btn_kAra = new JButton("Kargo Ara");
		btn_kAra.setBackground(new Color(102, 205, 170));
		btn_kAra.setIcon(new ImageIcon("img\\arama.png"));
		btn_kAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_6, new pnlKargoAra());
			}
		});
		btn_kAra.setBounds(76, 179, 188, 42);
		panel_5.add(btn_kAra);
		
		
		//------Rapor Ýþlem alt menu sayfasý--------------------------------------------------------------------------
		
		JPanel raporIslem = new JPanel();
		raporIslem.setBackground(new Color(119, 136, 153));
		raporIslem .setBounds(0, 0, 1366,668);
		//tmpPanel.add(raporIslem );
		raporIslem.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(238, 232, 170));
		panel_7.setBorder(new TitledBorder(null, "Rapor \u0130\u015Flem Alt Men\u00FC", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_7.setBounds(10, 11, 341, 646);
		raporIslem.add(panel_7);
		panel_7.setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(238, 232, 170));
		panel_8.setBounds(361, 11, 995, 657);
		raporIslem.add(panel_8);
		panel_8.setLayout(null);
		
		JButton btn_rGeri = new JButton("Anasayfaya Geri D\u00F6n");
		btn_rGeri.setIcon(new ImageIcon("img\\geri.png"));
		btn_rGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(anaPanel);
			}
		});
		btn_rGeri.setBounds(74, 536, 182, 42);
		panel_7.add(btn_rGeri);
		
		
		JButton btn_smGunluk = new JButton("G\u00FCnl\u00FCk Raporlama");
		btn_smGunluk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_8, new pnlGunlukRaporlamaSM());
			}
		});
		btn_smGunluk.setIcon(new ImageIcon("img\\gunluk.png"));
		btn_smGunluk.setBackground(new Color(176, 224, 230));
		btn_smGunluk.setBounds(68, 89, 188, 42);
		panel_7.add(btn_smGunluk);
		sagpanelePanelEkle(panel_8, new pnlGunlukRaporlamaSM()); //rapora basýldýðýnda varsauýlan olarak açýlýr
		
		JButton btn_smAyik = new JButton("Ayl\u0131k Raporlama");
		btn_smAyik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sagpanelePanelEkle(panel_8, new pnlAylikRaporlamaSM());
			}
		});
		btn_smAyik.setIcon(new ImageIcon("img\\aylik.png"));
		btn_smAyik.setBackground(new Color(176, 224, 230));
		btn_smAyik.setBounds(68, 165, 188, 42);
		panel_7.add(btn_smAyik);
		
		
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
		
		JButton btn_subeIslem = new JButton("M\u00FC\u015Fteri \u0130\u015Flemleri");
		btn_subeIslem.setIcon(new ImageIcon("img\\m\u00FCsteri.png"));
		btn_subeIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEkle(musteriIslem);
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
