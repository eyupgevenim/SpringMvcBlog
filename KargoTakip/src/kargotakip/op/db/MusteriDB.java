package kargotakip.op.db;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import kargotakip.op.Musteri;

public class MusteriDB extends Baglanti<Musteri> {


	public int Ekle(Musteri _m) {
		String sql = "INSERT INTO musteri(adi,soyadi,tc,tel,email,adres_,sube_) values('" 
				+ _m.adi + "','"
				+ _m.soyadi + "','" 
				+ _m.tc + "','" 
				+ _m.tel + "','" 
				+ _m.email+ "'," 
				+ _m._adres.adres_ + "," 
				+ _m._sube.sube_ + ")";

		if(baglantiAc()){
			try {
				ps = (PreparedStatement) conn.prepareStatement(sql,
				        Statement.RETURN_GENERATED_KEYS); 
				ps.execute();
				rs = (ResultSet) ps.getGeneratedKeys();
				int generatedKey = 0;
				if (rs.next()) {
				    generatedKey = rs.getInt(1);
				}
				rsKapat();
				psKapat();
				connKapat();
				return generatedKey;
			} catch (Exception e) {
				rsKapat();
				psKapat();
				connKapat();
				return -1;
			}
		}else{
			return -1;
		}
	}

	@Override
	public boolean Sil(int id) {
		String sql = "UPDATE  musteri SET sil=1"+
				" WHERE musteri_="+id;
	
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
	public boolean Guncelle(Musteri _m) {
		String sql = "UPDATE  musteri SET "
				+"adi='"+_m.adi+"',"
				+"soyadi='"+_m.soyadi+"',"
				+"tc='"+_m.tc+"',"
				+"tel='"+_m.tel+"',"
				+"email='"+_m.email+"'"
				+" WHERE musteri_="+_m.musteri_;
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
	public Musteri Bilgisi(int id) {
		String sql = "SELECT * FROM musteri "
				+" WHERE sil=0 && musteri_="+id;
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				Musteri _m=new Musteri();
				while (rs.next()) {
					_m.musteri_ = rs.getInt("musteri_");
					_m.adi= rs.getString("adi");
					_m.soyadi = rs.getString("soyadi");
					_m.email = rs.getString("email");
					_m.tc=rs.getString("tc");
					_m.tel=rs.getString("tel");
					_m._adres=new AdresDB().Bilgisi(rs.getInt("adres_"));
					_m._sube=new SubeDB().Bilgisi(rs.getInt("sube_"));
					
				}
				rsKapat();
				connKapat();
				stmtKapat();
				return _m;
				
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
	public ArrayList<Musteri> Ara(Musteri _m) {
		String sql="SELECT * FROM musteri ";
		String where;
		if(_m.adi != null){
			where = " WHERE sube_="+_m._sube.sube_+" && sil=0 && adi LIKE '"+_m.adi+"%'";
		}else if(_m.soyadi != null){
			where = " WHERE sube_="+_m._sube.sube_+" && sil=0 && soyadi LIKE '"+_m.soyadi+"%'";
		}else if(_m.email != null){
			where = " WHERE sube_="+_m._sube.sube_+" && sil=0 && email LIKE '"+_m.email+"%'";
		}else if(_m.tel != null){
			where = " WHERE sube_="+_m._sube.sube_+" && sil=0 && tel LIKE '"+_m.tel+"%'";
		}else{
			return null;
		}
		sql+=where;
		
		if (baglantiAc()) {
			try {
				rs =  (com.mysql.jdbc.ResultSet) stmt.executeQuery(sql);
				ArrayList<Musteri> _musteriler=new ArrayList<Musteri>();
				while (rs.next()) {
					Musteri _mTmp=new Musteri();
					_mTmp.musteri_ = rs.getInt("musteri_");
					_mTmp.adi= rs.getString("adi");
					_mTmp.soyadi = rs.getString("soyadi");
					_mTmp.email = rs.getString("email");
					_mTmp.tc=rs.getString("tc");
					_mTmp.tel=rs.getString("tel");
					_mTmp._adres=new AdresDB().Bilgisi(rs.getInt("adres_"));
					_mTmp._sube=new SubeDB().Bilgisi(rs.getInt("sube_"));
					
					_musteriler.add(_mTmp);
				}
				rsKapat();
				connKapat();
				stmtKapat();

				return _musteriler;
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
	ArrayList<Musteri> Listele() {
		// TODO Auto-generated method stub
		return null;
	}

}
