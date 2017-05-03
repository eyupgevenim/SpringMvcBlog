package kargotakip.op.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public abstract class Baglanti<T> {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	   private static final String LOCALHOST="localhost";
	   private static final String PORT="3306";
	   private static final String DB_NAME="kargotakipdb";
	   private static final String USER = "root";
	   private static final String PASS = "";
	   private static final String ENCODING="?useUnicode=true&characterEncoding=UTF-8";
	   private static final String DB_URL = "jdbc:mysql://"+LOCALHOST+":"+PORT+"/"+DB_NAME+ENCODING;

	   protected Connection conn = null;
	   protected Statement stmt = null;
	   protected PreparedStatement ps=null;
	   protected ResultSet rs=null;


	protected boolean baglantiAc(){
		
		try {
			  Class.forName(JDBC_DRIVER);
		      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		      stmt=(Statement)conn.createStatement();
		      return true;
		} catch (Exception e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
	}
	
	protected void connKapat(){
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	
	protected void stmtKapat(){
		try {
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException e1){
		      }
		   }
	}
	
	protected void psKapat(){
		try {
			if(ps!=null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
		         if(ps!=null)
		            ps.close();
		      }catch(SQLException e1){
		      }
		   }
	}
	
	protected void rsKapat(){
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
		         if(rs!=null)
		            rs.close();
		      }catch(SQLException e1){
		      }
		   }
	}
	
	//abstract boolean Ekle(T _p);
	abstract boolean Sil(int id);
	abstract boolean Guncelle(T _p);
	abstract T Bilgisi(int id);
	abstract ArrayList<T> Ara(T _p);
	abstract ArrayList<T> Listele();
	
	
}
