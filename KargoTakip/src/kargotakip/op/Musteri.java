package kargotakip.op;

import java.util.ArrayList;

import kargotakip.op.db.MusteriDB;

public class Musteri extends Kisi<Musteri>{

	public int musteri_;
	

	@Override
	boolean kisiEkle(Musteri _m) {
		//
		return false;
	}
	
	public int musteriEkle(Musteri _m){
		if( (_m._adres.adres_= new Adres().adresEkle(_m._adres) )> 0){
			return new MusteriDB().Ekle(_m);
		}else{
			return -1;
		}
	}

	@Override
	public boolean kisiGuncelle(Musteri _m) {
		if(new Adres().adresGuncelle(_m._adres)){
			return new MusteriDB().Guncelle(_m);
		}else{
			return false;
		}
	}

	@Override
	boolean kisiSil(int id) {
		return new MusteriDB().Sil(id);
	}

	@Override
	public ArrayList<Musteri> kisiAra(Musteri _m) {
		return new MusteriDB().Ara(_m);
	}

	@Override
	ArrayList<Musteri> kisileriListele() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Musteri kisiBilgisi(int id) {
		return new MusteriDB().Bilgisi(id);
	}
}
