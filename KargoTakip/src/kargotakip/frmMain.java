package kargotakip;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;

public class frmMain extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int PERSONEL_ =-1;
	public static int PERSONELTIP_ =-1;
	public static String ADI =null;
	public static String SOYADI =null;
	public static String EMAIL=null;
	public static int SUBE_ =-1;

	public static JPanel pnlMENU;
	public static JPanel PANEL;
	public static JButton btn_girisDegistir;
	public static int w;
	public static int h;

	public static frmMain frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new frmMain();
					frame.setVisible(true);
					//frame.setResizable(false); // yeniden buyutlandýrýlamaz
					
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			        frame.addWindowListener(new WindowAdapter() {
			            public void windowClosing(WindowEvent ev) {
			            	int dialogButton = JOptionPane.YES_NO_OPTION;
			            	int EvetVeyeHayir= JOptionPane.showConfirmDialog (null, 
			                		"Tüm programý kapatmak istediðinize emin misiniz?",
			                		"Önemli Uyarý !!!",dialogButton);
			                if(EvetVeyeHayir == JOptionPane.YES_OPTION){ 
			                	frame.dispose();
			                }
			            }
			        });
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Kargo Takip Sistemi");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		ImageIcon img = new ImageIcon("img\\kargotakip.jpg");
		setIconImage(img.getImage());
		getContentPane().setLayout(null);
		
		w=this.getToolkit().getScreenSize().width;
		h=this.getToolkit().getScreenSize().height-80;
		
		pnlMENU = new JPanel();
		pnlMENU.setBackground(new Color(152, 251, 152));
		pnlMENU.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlMENU.setBounds(0, 0, 1366, 80);
		getContentPane().add(pnlMENU);
		pnlMENU.setLayout(null);
		
		PANEL = new JPanel();
		PANEL.setBackground(new Color(0, 191, 255));
		PANEL.setBounds(0, 80, 1366, 635);
		getContentPane().add(PANEL);
		PANEL.setLayout(null);
		
		PANEL.add(new pnlLogin(w,h));
		
		btnGirisDegistir();
		
	}
	
	public static void btnGirisDegistir(){
		
		pnlMENU.removeAll();
		btn_girisDegistir = new JButton("Müþteri Kargo Sorgulama");
		btn_girisDegistir.setIcon(new ImageIcon("img\\degistir.png"));
		btn_girisDegistir.setBackground(new Color(240, 248, 255));
		btn_girisDegistir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PANEL.removeAll();
				if(btn_girisDegistir.getText()=="Müþteri Kargo Sorgulama"){
					
					PANEL.add(new pnlMusteriGiris(w,h));
					btn_girisDegistir.setText("Personel Giriþi");
				}else{
					PANEL.add(new pnlLogin(w,h));
					btn_girisDegistir.setText("Müþteri Kargo Sorgulama");
				}
				PANEL.invalidate();
				PANEL.revalidate();
				PANEL.repaint();
			}	
		});
		btn_girisDegistir.setBounds(1157, 22, 195, 36);
		pnlMENU.add(btn_girisDegistir);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\1.png"));
		lblNewLabel.setBounds(10, 0, 103, 80);
		pnlMENU.add(lblNewLabel);
		pnlMENU.invalidate();
		pnlMENU.revalidate();
		pnlMENU.repaint();
	}

	public static void setMenu(String _kullanici){
		
		pnlMENU.removeAll();
		
		
		JMenuBar mnB_kullanici = new JMenuBar();
		mnB_kullanici.setBounds(1235, 5, 200, 70);
		pnlMENU.add(mnB_kullanici);
		
		JMenu mn_kullanici = new JMenu(_kullanici);
		mn_kullanici.setIcon(new ImageIcon("img\\user-email.png"));
		mn_kullanici.setBounds(1165, 5, 300, 70);
		mn_kullanici.setHorizontalAlignment(SwingConstants.CENTER);
		mnB_kullanici.add(mn_kullanici);
		
		JMenuItem mnI_guncelle = new JMenuItem("Bilgilerini G\u00FCncelle");
		mnI_guncelle.setIcon(new ImageIcon("img\\bilgi-guncelle.png"));
		mn_kullanici.add(mnI_guncelle);
		mnI_guncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dlgPersonelGuncelle dialog=new dlgPersonelGuncelle(frmMain.frame,frmMain.PERSONEL_);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
                
			}	
		});
		
		JMenuItem mnI_sifre = new JMenuItem("\u015Eifreni De\u011Fi\u015Ftir");
		mnI_sifre.setIcon(new ImageIcon("img\\sifre-degistir.png"));
		mn_kullanici.add(mnI_sifre);
		mnI_sifre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dlgSifreDegistirme dialog=new dlgSifreDegistirme(frmMain.frame);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
                
			}	
		});
		
		JMenuItem mnI_cikis = new JMenuItem("\u00C7\u0131k\u0131\u015F Yap");
		mnI_cikis.setIcon(new ImageIcon("D:img\\cikis.png"));
		mn_kullanici.add(mnI_cikis);
		mnI_cikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
            	int EvetVeyeHayir= JOptionPane.showConfirmDialog (null, 
                		"Hesabýnýzdan çýkmak istediðinize emin misiniz?",
                		"Uyarý !!!",dialogButton);
                if(EvetVeyeHayir == JOptionPane.YES_OPTION){ 
                	cikisYap();
                }
                
			}	
		});
		
		pnlMENU.invalidate();
		pnlMENU.revalidate();
		pnlMENU.repaint();
	}
	
	public static void cikisYap(){
		PERSONEL_ =-1;
		PERSONELTIP_ =-1;
		ADI =null;
		SOYADI =null;
		SUBE_ =-1;
		PANEL.removeAll();
		PANEL.add(new pnlLogin(w,h));
		btnGirisDegistir();
		PANEL.invalidate();
		PANEL.revalidate();
		PANEL.repaint();
	}
}
