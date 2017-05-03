package kargotakip.op;

import java.util.ArrayList;
import java.util.Vector;

import kargotakip.op.db.SubeDB;


public class Sube {
	
	public int sube_;
	public String subeAdi;
	public Adres _adres;
	public String koordinat;
	public int subeTip;
	
	
	public boolean subeEkle(Sube _s) {
		int ads_;
		if( (ads_=new Adres().adresEkle(_s._adres)) > 0){
			_s._adres.adres_=ads_;
			if(new SubeDB().Ekle(_s)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}
	
	public boolean subeGuncelle(Sube _s) {
		return new SubeDB().Guncelle(_s);
	}
	
	public boolean subeSil(int id) {
		return new SubeDB().Sil(id);
	}
	
	public Sube subeBilgisi(int id){
		return new SubeDB().Bilgisi(id);
	}
	
	public ArrayList<Sube> subeAra(Sube _s) {
		return new SubeDB().Ara(_s);
	}
	
	public Vector<Object> subelerVector(){
		
		ArrayList<Sube> subeler=new SubeDB().Listele();
		Vector<Object> _v=new Vector<>();
		for(int i=0;i<subeler.size();i++){
			_v.add(new Item(subeler.get(i).sube_, subeler.get(i).subeAdi));
		}
		return _v;
	}

	public Vector<Object> subeVector(int id){
		Sube sube=new SubeDB().Bilgisi(id);
		Vector<Object> _v=new Vector<>();
		_v.add(new Item(sube.sube_, sube.subeAdi));
		
		return _v;
	}
	
	public class Item {

	    private int id;
	    private String name;

	    public Item(int id, String name) {
	        this.id = id;
	        this.name = name;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String toString(){
	        return this.name;
	    }
	}

}
