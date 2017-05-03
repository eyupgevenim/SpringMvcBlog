package kargotakip.op.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import kargotakip.frmMain;
import kargotakip.op.Personel;

public class PersonelDB extends Baglanti<Personel> {
	
	public int personel_;
	public String adi;
	public String soyadi;
	public String tc;
	public String tel;
	public String email;
	public int personelTipi;
	public int sube_;
	

	public boolean Login(String _e, String _s) {
		String sql = "SELECT * FROM personel WHERE email='"+_e+ "' && sifre='"+_s+"'";
		if (baglantiAc()) {
			try {
				ResultSet rs = stmt.executeQuery(sql);
				int kayitSayisi = 0;
				while (rs.next()) {
					personel_ = rs.getInt("personel_");
					personelTipi = rs.getInt("personeltip_");
					adi= rs.getString("adi");
					soyadi = rs.getString("soyadi");
					email = rs.getString("email");
					sube_ = rs.getInt("sube_");

					kayitSayisi++;
				}
				rs.close();
				connKapat();
				stmtKapat();

				if (kayitSayisi == 1) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean Ekle(Personel _p) {
		String sql = "INSERT INTO personel(adi,soyadi,tc,tel,email,adres_,"
				+"dogumtarihi,cinsiyet,personeltip_,sube_,sifre) values('" 
				+ _p.adi + "','"
				+ _p.soyadi + "','" 
				+ _p.tc + "','" 
				+ _p.tel + "','" 
				+ _p.email+ "'," 
				+ _p._adres.adres_ + ",'" 
				+ _p.dogumTarihi+"'," 
				+ _p.cinsiyet + "," 
				+ _p.personelTipi + "," 
				+ _p._sube.sube_+ ",'" 
				+ _p.parola + "')";

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
	public boolean Sil(int id) {
		String sql = "UPDATE  personel SET sil=1"+
					" WHERE personel_="+id;

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

	@Override
	public boolean Guncelle(Personel _p) {
		String sql = "UPDATE  personel SET "
				+" adi='"+_p.adi+"',"
				+"soyadi='"+_p.soyadi+"',"
				+"tc='"+_p.tc+"',"
				+"tel='"+_p.tel+"',"
				+"email='"+_p.email+"',"
				+"dogumtarihi='"+_p.dogumTarihi+"',"
				+"cinsiyet="+_p.cinsiyet+","
				+"personeltip_="+_p.personelTipi+","
				+"sube_="+_p._sube.sube_+","
				+"adres_="+_p._adres.adres_
				+" WHERE personel_="+_p.personel_;
		if (baglantiAc()) {
			try {
				stmt = (Statement) conn.createStatement();
				stmt.executeUpdate(sql);
				stmtKapat();
				connKapat();
				System.out.println("Buarada ");
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
	public Personel Bilgisi(int id) {
		String sql = "SELECT * FROM personel p "
				+" INNER JOIN adres a ON p.adres_=a.adres_ "
				+" INNER JOIN sube s ON p.sube_=s.sube "
				+" WHERE p.sil=0 && p.personel_="+id;
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				Personel _p=new Personel();
				while (rs.next()) {
					_p.personel_ = rs.getInt("personel_");
					_p.personelTipi = rs.getInt("personeltip_");
					_p.adi= rs.getString("adi");
					_p.soyadi = rs.getString("soyadi");
					_p.email = rs.getString("email");
					_p.tc=rs.getString("tc");
					_p.tel=rs.getString("tel");
					_p.cinsiyet=rs.getByte("cinsiyet");
					_p._adres=new AdresDB().Bilgisi(rs.getInt("adres_"));
					_p._sube=new SubeDB().Bilgisi(rs.getInt("sube_"));
				}
				rsKapat();
				connKapat();
				stmtKapat();
				return _p;
				
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
	public ArrayList<Personel> Ara(Personel _p) {
		String sql="SELECT * FROM personel "
				+"INNER JOIN adres ON personel.adres_=adres.adres_ "
				+"INNER JOIN sube ON personel.sube_=sube.sube ";
		String where;
		if(_p.adi != null){
			where = "WHERE personel.sil=0 && adi LIKE '"+_p.adi+"%'";
		}else if(_p.email != null){
			where = "WHERE personel.sil=0 && email LIKE '"+_p.email+"%'";
		}else if(_p._sube.subeAdi != null){
			where = "WHERE personel.sil=0 && sube.subeadi LIKE '"+_p._sube.subeAdi+"%'";
		}else{
			return null;
		}
		sql+=where;
		
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				ArrayList<Personel> _personeller=new ArrayList<Personel>();
				while (rs.next()) {
					Personel _pTmp=new Personel();
					_pTmp.personel_ = rs.getInt("personel_");
					_pTmp.personelTipi = rs.getInt("personeltip_");
					_pTmp.adi= rs.getString("adi");
					_pTmp.soyadi = rs.getString("soyadi");
					_pTmp.email = rs.getString("email");
					_pTmp.tc=rs.getString("tc");
					_pTmp.tel=rs.getString("tel");
					_pTmp.dogumTarihi=rs.getDate("dogumtarihi");
					_pTmp.cinsiyet=rs.getByte("cinsiyet");
					_pTmp._adres=new AdresDB().Bilgisi(rs.getInt("adres_"));
					_pTmp._sube=new SubeDB().Bilgisi(rs.getInt("sube"));
					if(frmMain.PERSONELTIP_ == 1 ){
						if(_pTmp.personelTipi != frmMain.PERSONELTIP_)
							_personeller.add(_pTmp);
					}else{
						if(frmMain.PERSONEL_ !=_pTmp.personel_ && frmMain.SUBE_ == _pTmp._sube.sube_ )
							_personeller.add(_pTmp);
					}
					
				}
				rsKapat();
				connKapat();
				stmtKapat();

				return _personeller;
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
	public ArrayList<Personel> Listele() {
		String sql = "SELECT * FROM personel WHERE sil=0 ";
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				ArrayList<Personel> _personeller=new ArrayList<Personel>();
				while (rs.next()) {
					Personel _p=new Personel();
					_p.personel_ = rs.getInt("personel_");
					_p.personelTipi = rs.getInt("personeltip_");
					_p.adi= rs.getString("adi");
					_p.soyadi = rs.getString("soyadi");
					_p.email = rs.getString("email");
					_p.tc=rs.getString("tc");
					//_p.dogumTarihi=rs.getDate("dogumtarihi");
					_p.cinsiyet=rs.getByte("cinsiyet");
					_p._sube.sube_ = rs.getInt("sube_");
					_p._adres.adres_=rs.getInt("adres_");

					_personeller.add(_p);
				}
				rsKapat();
				connKapat();
				stmtKapat();

				return _personeller;
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return null;
			}
		} else {
			return null;
		}
	}

	public ArrayList<Personel> sevkitatPersonel(){
		String sql="SELECT * FROM personel WHERE sil=0 && personeltip_=4";
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				ArrayList<Personel> _personeller=new ArrayList<Personel>();
				while (rs.next()) {
					Personel _pTmp=new Personel();
					_pTmp.personel_ = rs.getInt("personel_");
					_pTmp.personelTipi = rs.getInt("personeltip_");
					_pTmp.adi= rs.getString("adi");
					_pTmp.soyadi = rs.getString("soyadi");
					_pTmp.email = rs.getString("email");
					_pTmp.tc=rs.getString("tc");
					_pTmp.tel=rs.getString("tel");
					//_pTmp.dogumTarihi=rs.getDate("dogumtarihi");
					_pTmp.cinsiyet=rs.getByte("cinsiyet");
					_pTmp._adres=new AdresDB().Bilgisi(rs.getInt("adres_"));
					_pTmp._sube=new SubeDB().Bilgisi(rs.getInt("sube_"));
					_personeller.add(_pTmp);
				}
				rsKapat();
				connKapat();
				stmtKapat();

				return _personeller;
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public boolean sifreGuncelleme(String email,String yeniSifre){
		String sql = "UPDATE  personel SET sifre='"+yeniSifre+
				"' WHERE email='"+email+"'";
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
		}else {
			return false;
		}
	}
	
}
