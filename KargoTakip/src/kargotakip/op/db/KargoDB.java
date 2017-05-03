package kargotakip.op.db;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import kargotakip.op.Kargo;
import kargotakip.op.Sube;

public class KargoDB extends Baglanti<Kargo> {


	public boolean Ekle(Kargo _k) {
		String sql = "INSERT INTO kargo(aliciadi,alicisoyadi,alicitel,aliciadres_,"
				+"kilo,desi,ucret,ucrettip_,musteri_,gonderensube_,alicisube_,gonderenpersonel_,"
				+"subelerarasisevkiyat_,kargodurum_) values('" 
				+ _k.aliciAdi + "','" 
				+ _k.aliciSoyadi + "','" 
				+ _k.aliciTel + "'," 
				+ _k._aliciAdres.adres_+ "," 
				+ _k.kilo + "," 
				+ _k.desi + ","
				+ _k.ucret + "," 
				+ _k.ucrettip_ + "," 
				+ _k._musteri.musteri_+ "," 
				+ _k._gondericiSube.sube_ + ","
				+ _k._aliciSube.sube_+ "," 
				+ _k._gondericiPersonel.personel_ + "," 
				+ _k._subelerArasiSevkiyat.personel_+ ","  
				+ _k.durun_ + ")";

		if (baglantiAc()) {
			try {
				stmt = (Statement) conn.createStatement();
				stmt.executeUpdate(sql);
				stmtKapat();
				connKapat();
				return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				stmtKapat();
				connKapat();
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	boolean Sil(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Guncelle(Kargo _k) {
		String sql = "UPDATE  kargo SET "
				+"aliciadi='"+_k.aliciAdi+"',"
				+"alicisoyadi='"+_k.aliciSoyadi+"',"
				+"alicitel='"+_k.aliciTel+"',"
				+"aliciadres_="+_k._aliciAdres.adres_+","
				+"kilo="+_k.kilo+","
				+"desi="+_k.desi+","
				+"ucret="+_k.ucret+","
				+"ucrettip_="+_k.ucrettip_+","
				+"musteri_="+_k._musteri.musteri_+","
				+"gonderensube_="+_k._gondericiSube.sube_+","
				+"alicisube_="+_k._aliciSube.sube_+","
				+"gonderenpersonel_="+_k._gondericiPersonel.personel_+","
				+"alicipersonel_="+_k._aliciPersonel.personel_+","
				+"subelerarasisevkiyat_="+_k._subelerArasiSevkiyat.personel_+","
				+"subeadressevkiyat_="+_k._subeAdreseSevkiyat.personel_+","
				+"kargodurum_="+_k.durun_+","
				+"kayittarihi="+_k.kayitTarihi+","
				+" WHERE kargo_="+_k.kargo_;
		if (baglantiAc()) {
			try {
				stmt = (Statement) conn.createStatement();
				stmt.executeUpdate(sql);
				stmtKapat();
				connKapat();
				return true;
			} catch (SQLException e) {
				stmtKapat();
				connKapat();
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public Kargo Bilgisi(int id) {
		String sql = "SELECT * FROM kargo WHERE kargo_="+id;
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				Kargo _k=new Kargo();
				while (rs.next()) {
					_k.kargo_ = rs.getInt("kargo_");
					_k.aliciAdi = rs.getString("aliciadi");
					_k.aliciSoyadi = rs.getString("alicisoyadi");
					_k.aliciTel = rs.getString("alicitel");
					_k.kilo = rs.getDouble("kilo");
					_k.desi = rs.getDouble("desi");
					_k.ucret = rs.getDouble("ucret");
					_k.durun_ = rs.getInt("kargodurum_");
					//_k.kayitTarihi= rs.getDate("kayittarihi");
					_k._aliciAdres=new AdresDB().Bilgisi(rs.getInt("aliciadres_"));
					_k._aliciSube=new SubeDB().Bilgisi(rs.getInt("alicisube_"));
					_k._musteri=new MusteriDB().Bilgisi(rs.getInt("musteri_"));
					_k._subelerArasiSevkiyat=new PersonelDB().Bilgisi(rs.getInt("subelerarasisevkiyat_"));
					_k._subeAdreseSevkiyat=new PersonelDB().Bilgisi(rs.getInt("subeadressevkiyat_"));
					
				}
				rsKapat();
				connKapat();
				stmtKapat();
				return _k;
				
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<Kargo> Ara(Kargo _k) {
		String sql;
		if(_k.aliciTel != null){
			sql = "SELECT * FROM kargo WHERE kargodurum_<5 && alicitel LIKE '"+_k.aliciTel+"%'";
		}else if(_k._gondericiSube.subeAdi !=null){
			sql = "SELECT * FROM kargo k "
					+" INNER JOIN sube s ON k.gonderensube_=s.sube "
					+" WHERE k.kargodurum_<5 && s.subeadi LIKE '"+_k._gondericiSube.subeAdi+"%'";
		}else{
			return null;
		}
		
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				ArrayList<Kargo> _kargolar=new ArrayList<Kargo>();
				while (rs.next()) {
					Kargo _kTmp=new Kargo();
					_kTmp.kargo_ = rs.getInt("kargo_");
					_kTmp.aliciAdi = rs.getString("aliciadi");
					_kTmp.aliciSoyadi = rs.getString("alicisoyadi");
					_kTmp.aliciTel = rs.getString("alicitel");
					_kTmp.kilo = rs.getDouble("kilo");
					_kTmp.desi = rs.getDouble("desi");
					_kTmp.ucret = rs.getDouble("ucret");
					_kTmp.ucrettip_ = rs.getInt("ucrettip_");
					_kTmp._aliciAdres=new AdresDB().Bilgisi(rs.getInt("aliciadres_"));
					_kTmp._aliciSube=new SubeDB().Bilgisi(rs.getInt("alicisube_"));
					_kTmp._musteri=new MusteriDB().Bilgisi(rs.getInt("musteri_"));
					_kTmp._subelerArasiSevkiyat=new PersonelDB().Bilgisi(rs.getInt("subelerarasisevkiyat_"));
					_kTmp._subeAdreseSevkiyat=new PersonelDB().Bilgisi(rs.getInt("subeadressevkiyat_"));	
					_kTmp.durun_ = rs.getInt("kargodurum_");
					_kargolar.add(_kTmp);
				}
				rsKapat();
				connKapat();
				stmtKapat();

				return _kargolar;
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	ArrayList<Kargo> Listele() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean durumGuncelle(Kargo _k){
		String sql = "UPDATE  kargo SET kargodurum_="+_k.durun_
				+" ,subeadressevkiyat_="+_k._subeAdreseSevkiyat.personel_+"  WHERE kargo_="+_k.kargo_;
		if (baglantiAc()) {
			try {
				stmt = (Statement) conn.createStatement();
				stmt.executeUpdate(sql);
				stmtKapat();
				connKapat();
				return true;
			} catch (SQLException e) {
				stmtKapat();
				connKapat();
				return false;
			}
		} else {
			return false;
		}
	}
		
	public Vector<Object> kargoDurumu(){
		String sql = "SELECT * FROM kargodurum";
		Vector<Object> _v=new Vector<>();
		if (baglantiAc()) {
			try {
				rs = (ResultSet) stmt.executeQuery(sql);
				while (rs.next()) {
					_v.add(new Item(rs.getInt("kargodurum_"), rs.getString("durumu")));
				}
				rsKapat();
				connKapat();
				return _v;
			} catch (Exception e) {
				rsKapat();
				connKapat();
				return null;
			}
		} else {
			return null;
		}
		
	}
	
	public Vector<Object> kargoDurumu(int id){
		
		String sql = "SELECT * FROM kargodurum WHERE kargodurum_="+id;
		Vector<Object> _v=new Vector<>();
		if (baglantiAc()) {
			try {
				rs = (ResultSet) stmt.executeQuery(sql);
				while (rs.next()) {
					_v.add(new Item(rs.getInt("kargodurum_"), rs.getString("durumu")));
				}
				rsKapat();
				connKapat();
				return _v;
			} catch (Exception e) {
				rsKapat();
				connKapat();
				return null;
			}
		} else {
			return null;
		}
		
	}
	
	public ArrayList<Kargo> subedekiKargolar(int sube_){
		String sql = "SELECT * FROM kargo WHERE ((kargodurum_ =1 OR kargodurum_ =2) AND gonderensube_="
				+sube_+" ) OR ((kargodurum_ =3 OR kargodurum_ =4) AND alicisube_="+sube_+" )";
		
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				ArrayList<Kargo> _kargolar=new ArrayList<Kargo>();
				while (rs.next()) {
					Kargo _kTmp=new Kargo();
					_kTmp.kargo_ = rs.getInt("kargo_");
					_kTmp.aliciAdi = rs.getString("aliciadi");
					_kTmp.aliciSoyadi = rs.getString("alicisoyadi");
					_kTmp.aliciTel = rs.getString("alicitel");
					_kTmp.kilo = rs.getDouble("kilo");
					_kTmp.desi = rs.getDouble("desi");
					_kTmp.ucret = rs.getDouble("ucret");
					_kTmp.ucrettip_ = rs.getInt("ucrettip_");
					_kTmp._aliciAdres=new AdresDB().Bilgisi(rs.getInt("aliciadres_"));
					_kTmp._aliciSube=new SubeDB().Bilgisi(rs.getInt("alicisube_"));
					_kTmp._musteri=new MusteriDB().Bilgisi(rs.getInt("musteri_"));
					_kTmp._subelerArasiSevkiyat=new PersonelDB().Bilgisi(rs.getInt("subelerarasisevkiyat_"));
					_kTmp._subeAdreseSevkiyat=new PersonelDB().Bilgisi(rs.getInt("subeadressevkiyat_"));
					_kTmp.durun_ = rs.getInt("kargodurum_");
					_kargolar.add(_kTmp);
				}
				rsKapat();
				connKapat();
				stmtKapat();

				return _kargolar;
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public ArrayList<Kargo> gelecekKargolar(int sube_){
		String sql = "SELECT * FROM kargo WHERE (kargodurum_ =1 OR kargodurum_ =2) AND  alicisube_="+sube_;
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				ArrayList<Kargo> _kargolar=new ArrayList<Kargo>();
				while (rs.next()) {
					Kargo _kTmp=new Kargo();
					_kTmp.kargo_ = rs.getInt("kargo_");
					_kTmp.aliciAdi = rs.getString("aliciadi");
					_kTmp.aliciSoyadi = rs.getString("alicisoyadi");
					_kTmp.aliciTel = rs.getString("alicitel");
					_kTmp.kilo = rs.getDouble("kilo");
					_kTmp.desi = rs.getDouble("desi");
					_kTmp.ucret = rs.getDouble("ucret");
					_kTmp.ucrettip_ = rs.getInt("ucrettip_");
					_kTmp._aliciAdres=new AdresDB().Bilgisi(rs.getInt("aliciadres_"));
					_kTmp._aliciSube=new SubeDB().Bilgisi(rs.getInt("alicisube_"));
					_kTmp._musteri=new MusteriDB().Bilgisi(rs.getInt("musteri_"));
					_kTmp._subelerArasiSevkiyat=new PersonelDB().Bilgisi(rs.getInt("subelerarasisevkiyat_"));
					_kTmp._subeAdreseSevkiyat=new PersonelDB().Bilgisi(rs.getInt("subeadressevkiyat_"));
					
					
					_kTmp.durun_ = rs.getInt("kargodurum_");
					_kargolar.add(_kTmp);
				}
				rsKapat();
				connKapat();
				stmtKapat();

				return _kargolar;
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public int musteriKargoBilgisi(String aTel, int kKodu){
		String sql = "SELECT * FROM kargo WHERE kargodurum_<5 AND alicitel='"+aTel+"' AND kargo_="+kKodu;
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				int sayi=0;
				while (rs.next()) {
					sayi++;
				}
				rsKapat();
				connKapat();
				stmtKapat();
				return sayi;
				
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return -1;
			}
		} else {
			return -1;
		}
	}
	
	//-------------raporlama fonk.-----------------
	
	public ArrayList<Kargo> subelerGunluk(Date tarih){
	
		String sql = "SELECT s.subeadi AS subeAdi,COUNT(k.kargo_) AS kargoSayisi,SUM(k.ucret) AS toplamUcret  FROM kargo k "
				+" INNER JOIN sube s ON k.gonderensube_=s.sube "
				+" WHERE k.kayittarihi LIKE '"+tarih+"%'"
				+" GROUP BY s.subeadi "
				+" ORDER BY toplamUcret DESC";
		if (baglantiAc()) {
			try {
				rs =(ResultSet) stmt.executeQuery(sql);
				ArrayList<Kargo> _k=new ArrayList<Kargo>();
				while(rs.next()){
					Kargo k=new Kargo();
					Sube s=new Sube();
					s.subeAdi=rs.getString("SubeAdi");
					k._gondericiSube=s;
					k.kargo_=rs.getInt("kargoSayisi");
					k.ucret=rs.getDouble("toplamUcret");
					_k.add(k);
				}
				rsKapat();
				stmtKapat();
				connKapat();
				return _k;
			} catch (SQLException e) {
				rsKapat();
				stmtKapat();
				connKapat();
				return null;
			}
		} else {
			return null;
		}
		
	}
	
	public ArrayList<Kargo> subelerAylik(String tarih){
		String sql = "SELECT s.subeadi AS subeAdi,COUNT(k.kargo_) AS kargoSayisi,SUM(k.ucret) AS toplamUcret  FROM kargo k "
				+" INNER JOIN sube s ON k.gonderensube_=s.sube "
				+" WHERE k.kayittarihi LIKE '"+tarih+"%'"
				+" GROUP BY s.subeadi "
				+" ORDER BY toplamUcret DESC";
		if (baglantiAc()) {
			try {
				rs =(ResultSet) stmt.executeQuery(sql);
				ArrayList<Kargo> _k=new ArrayList<Kargo>();
				while(rs.next()){
					Kargo k=new Kargo();
					Sube s=new Sube();
					s.subeAdi=rs.getString("SubeAdi");
					k._gondericiSube=s;
					k.kargo_=rs.getInt("kargoSayisi");
					k.ucret=rs.getDouble("toplamUcret");
					_k.add(k);
				}
				rsKapat();
				stmtKapat();
				connKapat();
				return _k;
			} catch (SQLException e) {
				rsKapat();
				stmtKapat();
				connKapat();
				return null;
			}
		} else {
			return null;
		}
	}

	public ArrayList<Kargo> subeGunluk(int sube_,Date tarih){
		
		String sql = "SELECT *  FROM kargo "
				+" WHERE gonderensube_="+sube_+" AND kayittarihi LIKE '"+tarih+"%' "
				+" ORDER BY ucret DESC";
		if (baglantiAc()) {
			try {
				rs =(ResultSet) stmt.executeQuery(sql);
				ArrayList<Kargo> _k=new ArrayList<Kargo>();
				while(rs.next()){
					Kargo k=new Kargo();
					k._gondericiPersonel=new PersonelDB().Bilgisi(rs.getInt("gonderenpersonel_"));
					k.kilo=rs.getDouble("kilo");
					k.desi=rs.getDouble("desi");
					k.ucret=rs.getDouble("ucret");
					_k.add(k);
				}
				rsKapat();
				stmtKapat();
				connKapat();
				return _k;
			} catch (SQLException e) {
				rsKapat();
				stmtKapat();
				connKapat();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public ArrayList<Kargo> subeAylik(int sube_,String tarih){
		
		String sql = "SELECT DATE_FORMAT(kayittarihi,'%Y-%m-%d') AS kt,COUNT(kargo_) AS ks,SUM(ucret) AS tu  FROM kargo "
				+" WHERE gonderensube_="+sube_+" AND kayittarihi LIKE '"+tarih+"%' "
				+" GROUP BY DATE_FORMAT(kayittarihi,'%Y-%m-%d') "
				+" ORDER BY DATE_FORMAT(kayittarihi,'%Y-%m-%d') ASC";
		if (baglantiAc()) {
			try {
				rs =(ResultSet) stmt.executeQuery(sql);
				ArrayList<Kargo> _k=new ArrayList<Kargo>();
				while(rs.next()){
					Kargo k=new Kargo();
					k.kayitTarihi=rs.getDate("kt");
					k.kargo_=rs.getInt("ks");
					k.ucret=rs.getDouble("tu");
					_k.add(k);
				}
				rsKapat();
				stmtKapat();
				connKapat();
				return _k;
			} catch (SQLException e) {
				rsKapat();
				stmtKapat();
				connKapat();
				return null;
			}
		} else {
			return null;
		}
	}
	
	
	
	//comboBoxlar için model sýnýfý
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
