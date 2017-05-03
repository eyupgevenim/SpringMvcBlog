package kargotakip.op;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import kargotakip.frmMain;
import kargotakip.op.db.PersonelDB;

public class Personel extends Kisi<Personel> {
	
	public int personel_;
	public Date dogumTarihi;
	public int cinsiyet;
	public int personelTipi;
	public String parola;
	
	
	public boolean girisYap(String _email,String _parola){
		PersonelDB _pdb=new PersonelDB();
		if( _pdb.Login(_email, _parola) ){
			
			frmMain.PERSONEL_ = _pdb.personel_;
			frmMain.PERSONELTIP_ = _pdb.personelTipi;
			frmMain.ADI = _pdb.adi;
			frmMain.SOYADI = _pdb.soyadi;
			frmMain.EMAIL=_pdb.email;
			frmMain.SUBE_ = _pdb.sube_;
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean kisiEkle(Personel _p) {
		Adres a=new Adres();
		if( (a.adres_= new Adres().adresEkle(_p._adres) ) > 0){
			_p._adres=a;
			if(new PersonelDB().Ekle(_p)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	@Override
	public boolean kisiGuncelle(Personel _p) {
		if(new Adres().adresGuncelle(_p._adres)){
			return new PersonelDB().Guncelle(_p);
		}else{
			return false;
		}
	}
	
	@Override
	public boolean kisiSil(int id) {
		return new PersonelDB().Sil(id);
	}
	
	@Override
	public ArrayList<Personel> kisiAra(Personel _p) {
		return new PersonelDB().Ara(_p);
	}
	
	@Override
	public ArrayList<Personel> kisileriListele() {
		ArrayList<Personel> _psnl=new Personel().kisileriListele();
		if(_psnl != null){
			for(int i=0;i<_psnl.size();i++){
				_psnl.get(i)._adres=new Adres().adresBilgisi(_psnl.get(i)._adres.adres_);
				_psnl.get(i)._sube=new Sube().subeBilgisi(_psnl.get(i)._sube.sube_);
			}
			return _psnl;
		}else{
			return null;
		}
	}

	@Override
	public Personel kisiBilgisi(int id) {
		return new PersonelDB().Bilgisi(id);
	}

	public Vector<Object> sevkitatPersonelVektor()	{
		
		ArrayList<Personel> s=new PersonelDB().sevkitatPersonel();
		Vector<Object> _v=new Vector<>();
		
		if(s != null)
		for(int i=0;i<s.size();i++){
			_v.add(new Item(s.get(i).personel_,s.get(i).adi+" "+s.get(i).soyadi));
		}
		return _v;
	}
	
	public Vector<Object> sevkitatPersonelVektor(int id)	{
		
		Personel s=new PersonelDB().Bilgisi(id);
		Vector<Object> _v=new Vector<>();
		_v.add(new Item(s.personel_,s.adi+" "+s.soyadi));
		return _v;
	}
	
	
	//---------------------yardýmcý fonksiyonlar--------------------------------------
	
	
	public boolean sifreGuncelleme(String email_,String eskiSifre_,String yeniSifre_){
		if(new PersonelDB().Login(email_, eskiSifre_)){
			return new PersonelDB().sifreGuncelleme(email_, yeniSifre_);
		}else{
			return false;
		}
	}
	
	public String MD5(String _psw){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        md.update(_psw.getBytes());
        
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
	}
	
	public void kullaniciBilgisi(){
		frmMain.PERSONEL_ = personel_;
		frmMain.PERSONELTIP_ = personelTipi;
		frmMain.ADI = adi;
		frmMain.SOYADI =soyadi;
		frmMain.SUBE_ = _sube.sube_;
		frmMain.setMenu(frmMain.ADI+" "+frmMain.SOYADI);
	}
	
	
	//comboBoxlar içi model sýnýfý
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
