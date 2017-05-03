package kargotakip;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import kargotakip.op.Kargo;

public class pnlMusteriKargoBilgi extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Kargo k;
	
	public pnlMusteriKargoBilgi(int kargo_) {
		setBackground(new Color(64, 224, 208));
		setLayout(null);
		
		{
			k=new Kargo().kargoBilgisi(kargo_);
		}
		
		JLabel lblNewLabel = new JLabel("Al\u0131c\u0131:     "+k.aliciAdi+" "+k.aliciSoyadi);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(50, 129, 506, 23);
		add(lblNewLabel);
		
		JLabel lblGnderen = new JLabel("G\u00F6nderen:     "+k._musteri.adi+" "+k._musteri.soyadi);
		lblGnderen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGnderen.setBounds(50, 80, 522, 23);
		add(lblGnderen);
		
		JLabel lblAlcAdresi = new JLabel("Al\u0131c\u0131 Adresi:     "+k._aliciAdres.adres+" "+k._aliciAdres.ilce+"/"+k._aliciAdres.il);
		lblAlcAdresi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAlcAdresi.setBounds(50, 184, 506, 23);
		add(lblAlcAdresi);
		
		JLabel lblcret = new JLabel("\u00DCcret:     "+k.ucret);
		lblcret.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcret.setBounds(50, 236, 500, 23);
		add(lblcret);
		
		JLabel lblcretTipi;
		if(k.ucrettip_ == 0)
			lblcretTipi = new JLabel("Ödeme Tipi:     Peþin Ödeme");
		else
			lblcretTipi = new JLabel("Ödeme Tipi:     Kapýda ödeme");
		lblcretTipi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcretTipi.setBounds(50, 303, 500, 23);
		add(lblcretTipi);
		
		String kD=k.durun_==1?"Kabul":k.durun_==2?"Alýcý Þubeye Sevkiyat":
    		k.durun_==3?"Alýcý Þubede":k.durun_==4?"Alýcý Adrese Sevkiyat":"Teslim Edildi";
		JLabel lblKargoDurumu = new JLabel("Kargo Durumu:     "+kD);
		lblKargoDurumu.setForeground(new Color(0, 0, 102));
		lblKargoDurumu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKargoDurumu.setBounds(50, 367, 500, 23);
		add(lblKargoDurumu);
		
		JLabel lblKargoBilgisiVe = new JLabel("Kargo Bilgisi ve Durumu");
		lblKargoBilgisiVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKargoBilgisiVe.setBounds(151, 11, 179, 35);
		add(lblKargoBilgisiVe);

	}

}
