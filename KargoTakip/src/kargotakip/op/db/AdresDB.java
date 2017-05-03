package kargotakip.op.db;

import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import kargotakip.op.Adres;

public class AdresDB extends Baglanti<Adres> {

	
	public int Ekle(Adres _a) {
		
		String sql="INSERT INTO adres"
				+ "( adres, ilce_) VALUES"
				+ "('"+_a.adres+"',"+_a.ilce_+")";
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
		String sql="DELETE adres WHERE adres_ ="+id;
		if(baglantiAc()){
			try {
				ps = (PreparedStatement) conn.prepareStatement(sql); 
				ps.execute();
				psKapat();
				connKapat();
				return true;
				
			} catch (Exception e) {
				psKapat();
				connKapat();
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public boolean Guncelle(Adres _a) {
		String sql="UPDATE adres SET "
				+"adres='"+_a.adres
				+"',"+" ilce_="+_a.ilce_
				+" WHERE adres_="+_a.adres_;
		if(baglantiAc()){
			try {
				stmt = (Statement) conn.createStatement();
				stmt.executeUpdate(sql);
				stmtKapat();
				connKapat();
				return true;
				
			} catch (Exception e) {
				stmtKapat();
				connKapat();
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public Adres Bilgisi(int id) {
		String sql="SELECT * FROM adres "
				+"INNER JOIN ilce ON adres.ilce_=ilce.ilce_ "
				+"INNER JOIN il ON ilce.il_=il.il_ "
				+"WHERE adres_="+id;
		if (baglantiAc()) {
			try {
				rs =  (ResultSet) stmt.executeQuery(sql);
				Adres _a=new Adres();
				while (rs.next()) {
					_a.adres_ = rs.getInt("adres_");
					_a.adres= rs.getString("adres");
					_a.ilce_ = rs.getInt("ilce_");
					_a.ilce= rs.getString("ilce");
					_a.il_ = rs.getInt("il_");
					_a.il= rs.getString("il");
				}
				rsKapat();
				connKapat();
				stmtKapat();
				return _a;
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
	public ArrayList<Adres> Ara(Adres _a) {
		String sql;
		if(_a.adres_ != 0){
			sql = "SELECT * FROM adres WHERE adres_="+_a.adres_;
		}else if(!_a.adres.equals("")){
			sql = "SELECT * FROM adres WHERE adres LIKE '"+_a.adres+"%'";
		}else if(_a.ilce_ != 0){
			sql = "SELECT * FROM adres WHERE ilce_="+_a.ilce_;
		}else{
			return null;
		}
		
		if (baglantiAc()) {
			try {
				rs =  (ResultSet) stmt.executeQuery(sql);
				ArrayList<Adres> _adresler=new ArrayList<Adres>();
				while (rs.next()) {
					Adres _aTmp=new Adres();
					_aTmp.adres_ = rs.getInt("adres_");
					_aTmp.adres= rs.getString("adres");
					_aTmp.ilce_ = rs.getInt("ilce_");
					_aTmp.ilce= rs.getString("ilce");
					_aTmp.il_ = rs.getInt("il_");
					_aTmp.il= rs.getString("il");
					
					_adresler.add(_aTmp);
				}
				rsKapat();
				connKapat();
				stmtKapat();
				return _adresler;
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
	ArrayList<Adres> Listele() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Adres> ilceAra(int il_) {
		String sql = "SELECT * FROM ilce WHERE il_="+il_;
		ArrayList<Adres> ilceler=new ArrayList<Adres>();
		if (baglantiAc()) {
			try {
				rs = (ResultSet) stmt.executeQuery(sql);
				while (rs.next()) {
					Adres _a =new Adres();
					_a.ilce_ = rs.getInt("ilce_");
					_a.ilce= rs.getString("ilce");
					ilceler.add(_a);
				}
				rs.close();
				connKapat();
				stmtKapat();
				return ilceler;
				
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public ArrayList<Adres> ilListele() {
		String sql = "SELECT * FROM il";
		ArrayList<Adres> iller=new ArrayList<Adres>();
		if (baglantiAc()) {
			try {
				rs = (ResultSet) stmt.executeQuery(sql);
				while (rs.next()) {
					Adres _a =new Adres();
					_a.il_ = rs.getInt("il_");
					_a.il= rs.getString("il");
					iller.add(_a);
				}
				rs.close();
				connKapat();
				stmtKapat();
				return iller;
			} catch (Exception e) {
				connKapat();
				stmtKapat();
				return null;
			}
		} else {
			return null;
		}
	}
	

}
