package kargotakip.op;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import kargotakip.op.db.KargoDB;


public class Kargo {
	
	public int kargo_;
	public Musteri _musteri;
	public String aliciAdi;
	public String aliciSoyadi;
	public String aliciTel;
	public Adres _aliciAdres;
	public double kilo;
	public double desi;
	public double ucret;
	public int ucrettip_;
	public Sube _gondericiSube;
	public Sube _aliciSube;
	public Personel _gondericiPersonel;
	public Personel _aliciPersonel;
	public Personel _subelerArasiSevkiyat;
	public Personel _subeAdreseSevkiyat;
	public int durun_;
	public Date kayitTarihi;
	//public Date subeyeUlasmaTarihi;
	
	
	
	public boolean kargoEkle(Kargo _k) {
		if( (_k._aliciAdres.adres_= new Adres().adresEkle(_k._aliciAdres) )> 0 
				&& (_k._musteri.musteri_ = new Musteri().musteriEkle(_k._musteri)) > 0 ){
			return new KargoDB().Ekle(_k);
		}else{
			return false;
		}
	}
	
	public boolean kargoGuncelle(Kargo _k) {
		if(new Adres().adresGuncelle(_k._aliciAdres) && new Adres().adresGuncelle(_k._musteri._adres)){
			return new KargoDB().Guncelle(_k);
		}else{
			return false;
		}
	}
	
	public Kargo kargoBilgisi(int id){
		
		return new KargoDB().Bilgisi(id);
	}
	
	public ArrayList<Kargo> kargoAra(Kargo _k) {
		return new KargoDB().Ara(_k);
	}
	
	public boolean durumGuncelle(Kargo _k){
		return new KargoDB().durumGuncelle(_k);
		
	}
	
 	public Vector<Object> kargoDurumu(){
		
		return new KargoDB().kargoDurumu();
	}

 	public ArrayList<Kargo> subedekiKargolar(int sube_){
 		return new KargoDB().subedekiKargolar(sube_);
 	}
 	
 	public ArrayList<Kargo> gelecekKargolar(int sube_){
 		return new KargoDB().gelecekKargolar(sube_);
 	}
 	
 	public int musteriKargobilgisi(String aTel,int kKodu){
 		return new KargoDB().musteriKargoBilgisi(aTel, kKodu);
 	}
 	
 	
 	public ArrayList<Kargo> subelerGunluk(Date tarih){
 		return new KargoDB().subelerGunluk(tarih);
 	}
 	
 	public ArrayList<Kargo> subelerAylik(String tarih){
 		return new KargoDB().subelerAylik(tarih);
 	}
 	
 	public ArrayList<Kargo> subeGunluk(int sube_,Date tarih){
 		return new KargoDB().subeGunluk(sube_,tarih);
 	}
 	
 	public ArrayList<Kargo> subeAylik(int sube_,String tarih){
 		return new KargoDB().subeAylik(sube_,tarih);
 	}
 	
 	
}
