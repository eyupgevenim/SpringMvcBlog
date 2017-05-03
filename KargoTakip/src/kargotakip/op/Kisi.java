package kargotakip.op;

import java.util.ArrayList;



public abstract class Kisi<T>{
	
	public String adi;
	public String soyadi;
	public String tc;
	public String tel;
	public String email;
	public Adres _adres;
	public Sube _sube;
	
	
	abstract boolean kisiEkle(T _p);
	abstract boolean kisiGuncelle(T _p);
	abstract boolean kisiSil(int id);
	abstract T kisiBilgisi(int id);
	abstract ArrayList<T> kisiAra(T _p);
	abstract ArrayList<T> kisileriListele();
	

}
