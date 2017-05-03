package kargotakip.op;

import java.util.ArrayList;
import java.util.Vector;

import kargotakip.op.db.AdresDB;

public class Adres {

	public int adres_;
	public String adres;
	public int ilce_;
	public String ilce;
	public int il_;
	public String il;
	
	int adresEkle(Adres _a) {
		
		adres_=new AdresDB().Ekle(_a) ;
		return adres_ > 0 ? adres_:-1;	
	}
	
	boolean adresGuncelle(Adres _a) {
		return new AdresDB().Guncelle(_a);
	}
	
	Adres adresBilgisi(int id){
		return new AdresDB().Bilgisi(id);
	}
	
	ArrayList<Adres> adresAra(Adres _a) {
		return new AdresDB().Ara(_a);
	}
	
	public ArrayList<Adres> ilceAra(int il_) {
		return new AdresDB().ilceAra(il_);
	}
	
	public ArrayList<Adres> ilListele() {
		return new AdresDB().ilListele();
	}
	
	public Vector<Object> illerVector()	{
		
		ArrayList<Adres> iller=new AdresDB().ilListele();
		Vector<Object> _v=new Vector<>();
		for(int i=0;i<iller.size();i++){
			_v.add(new Item(iller.get(i).il_, iller.get(i).il));
		}
		return _v;
	}

	public Vector<Object> ilcelerVector(int il_){
		ArrayList<Adres> ilceler=new AdresDB().ilceAra(il_);
		Vector<Object> _v=new Vector<>();
		for(int i=0;i<ilceler.size();i++){
			_v.add(new Item(ilceler.get(i).ilce_, ilceler.get(i).ilce));
		}
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



