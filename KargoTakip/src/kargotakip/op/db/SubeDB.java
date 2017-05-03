package kargotakip.op.db;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import kargotakip.op.Sube;

public class SubeDB extends Baglanti<Sube>{


	public boolean Ekle(Sube _s) {
		String sql="INSERT INTO sube"
				+ "( subeadi, adres_,koordinat,subetip_) VALUES"
				+ "('"+_s.subeAdi+"',"+_s._adres.adres_+",'"+_s.koordinat+"',"+_s.subeTip+")";
		
		if (baglantiAc()) {
			try {
				stmt = (Statement) conn.createStatement();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmtKapat();
			connKapat();
			return true;
		} else {
			stmtKapat();
			connKapat();
			return false;
		}
	}

	@Override
	public boolean Sil(int id) {
		String sql = "UPDATE  sube SET sil=1"+
				" WHERE sube="+id;
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
	public boolean Guncelle(Sube _s) {
		String sql = "UPDATE  sube SET "
				+"subeadi='"
				+_s.subeAdi+"',"
				+"koordinat='"
				+_s.koordinat+"',"
				+"subetip_="
				+_s.subeTip
				+" WHERE sube="+_s.sube_;
		if(new AdresDB().Guncelle(_s._adres)){
			
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
			
		}else{
			return false;
		}
		
	}

	@Override
	public Sube Bilgisi(int id) {
		String sql = "SELECT * FROM sube WHERE sil=0 && sube="+id;
		if (baglantiAc()) {
			try {
				rs =  (ResultSet) stmt.executeQuery(sql);
				Sube _s=new Sube();
				while (rs.next()) {
					_s.sube_=rs.getInt("sube");
					_s.subeAdi= rs.getString("subeadi");
					_s.koordinat = rs.getString("koordinat");
					_s.subeTip = rs.getInt("subetip_");
					_s._adres=new AdresDB().Bilgisi(rs.getInt("adres_"));
				}
				rsKapat();
				connKapat();
				stmtKapat();
				return _s;
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
	public ArrayList<Sube> Ara(Sube _s) {
		String sql="SELECT * FROM sube s ";
		if(_s.subeAdi != null){
			
			sql += " WHERE sil=0 && subeadi LIKE '"+_s.subeAdi+"%' ";
		}else if(_s._adres.il != null){
			
			sql+=" INNER JOIN adres a ON s.adres_=a.adres_ ";
			sql+=" INNER JOIN ilce ic ON a.ilce_=ic.ilce_ ";
			sql+=" INNER JOIN il i ON ic.il_=i.il_ ";
			sql += " WHERE s.sil=0 && i.il LIKE '"+_s._adres.il+"%'";
		}else if(_s._adres.ilce != null){
			
			sql+=" INNER JOIN adres a ON s.adres_=a.adres_ ";
			sql+=" INNER JOIN ilce ic ON a.ilce_=ic.ilce_ ";
			sql+=" INNER JOIN il i ON ic.il_=i.il_ ";
			sql += " WHERE s.sil=0 && ic.ilce LIKE '"+_s._adres.ilce+"%' ";
		}else{
			return null;
		}
		
		if (baglantiAc()) {
			try {
				rs =  (ResultSet)stmt.executeQuery(sql);
				ArrayList<Sube> _subeler=new ArrayList<Sube>();
				while (rs.next()) {
					Sube _sTmp=new Sube();
					_sTmp.sube_ = rs.getInt("sube");
					_sTmp.subeTip = rs.getInt("subetip_");
					_sTmp.subeAdi= rs.getString("subeadi");
					_sTmp.koordinat= rs.getString("koordinat");
					_sTmp._adres=new AdresDB().Bilgisi(rs.getInt("adres_"));
					_subeler.add(_sTmp);
				}
				rsKapat();
				connKapat();
				stmtKapat();
				return _subeler;
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
	public ArrayList<Sube> Listele() {
		String sql = "SELECT * FROM sube WHERE sil=0";
		ArrayList<Sube> subeler=new ArrayList<Sube>();
		if (baglantiAc()) {
			try {
				rs = (ResultSet) stmt.executeQuery(sql);
				while (rs.next()) {
					Sube _s =new Sube();
					_s.sube_ = rs.getInt("sube");
					_s.subeAdi= rs.getString("subeadi");
					_s.koordinat=rs.getString("koordinat");
					_s.subeTip=rs.getInt("subetip_");
					_s.subeTip=rs.getInt("adres_");
					subeler.add(_s);
				}
				rsKapat();
				connKapat();
				return subeler;
			} catch (Exception e) {
				rsKapat();
				connKapat();
				return null;
			}
		} else {
			return null;
		}
	}

}
