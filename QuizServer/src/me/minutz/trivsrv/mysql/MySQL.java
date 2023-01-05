package me.minutz.trivsrv.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQL
{
  private String HOST = null;
  private String DB = null;
  private String USER = null;
  private String PASS = null;
  private boolean connected = false;

  private Statement st = null;
  private Connection con = null;
  private MySQLFunc MySQL;
  private String conName;
  private String tname;
  public List<String> lista;


  public MySQL(String name,String tname)
  {
	this.tname = tname;
    this.conName = name;
    this.connected = false;
  }

  public Boolean Connect(String host, String db, String user, String pass) {
    this.HOST = host;
    this.DB = db;
    this.USER = user;
    this.PASS = pass;
    this.MySQL = new MySQLFunc(host, db, user, pass);
    this.con = this.MySQL.open();
    try {
      this.st = this.con.createStatement();
      this.connected = true;
      if(!existaTabel()){
      createT();
      }
      countRows();

  	System.out.println("[" + this.conName + "] Connected to the database.");
    } catch (SQLException e) {
      this.connected = false;
  	System.out.println("[" + this.conName + "] Could not connect to the database.");
    }
    this.MySQL.close(this.con);
    return Boolean.valueOf(this.connected);
  }

  public void countRows()
  {
    List<String> al = new ArrayList<String>();
    ResultSet set = query(String.format("SELECT * FROM %s", new Object[] { tname }));
    try {
      while (set.next()){
    	  String uuid = set.getString("uuid");
    	  al.add(uuid);
      }
    }
    catch (SQLException e) {
    	System.out.println("Could not select all rows from table: " + tname + ", error: " + e.getErrorCode());
    }
    lista=al;
  }

  
  public void updateCont(String uuid,int i,int j,int k){
	  String q = "UPDATE "+tname+" SET "
			  	+"scut='%sct',"
			  	+"resurse='%res',"
			  	+"oameni='%oam'"
			  	+" WHERE `uuid`='%uuid'";
	  q=q.replaceAll("%uuid", uuid);
	  q=q.replaceAll("%sct", ""+i);
	  q=q.replaceAll("%res", ""+j);
	  q=q.replaceAll("%oam", ""+k);
	  execute(q);
  }

  public void addCont(String uuid,String nume,int scut,int resurse,int oameni){
	  String q = "INSERT INTO "+tname+" (uuid, nume, scut, resurse, oameni) VALUES ('"+uuid+"' ,'"+nume+"' ,'"+scut+"' ,'"+resurse+"' ,'"+oameni+"');";
	  execute(q);
  }  
  
  public void delCont(String uuid){
	  String q = "DELETE FROM "+tname+" WHERE `uuid`="+'"'+uuid+'"';
	  execute(q);
  }
  
  public void loadUseri(){
		String query = "select * from "+tname;
		ResultSet rs = query(query);
		try{
		while(rs.next()){

		}
		}catch(SQLException e){
  	    	System.out.println("[" + this.conName + "] Error loading conturi: " + e.getMessage());
		}
  }
  
  public boolean existaTabel(){
		try {
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, tname, null);
			if (!tables.next()) {
				return false;
			}else{
				return true;
			}
		} catch (SQLException e) {
	    	System.out.println("[" + this.conName + "] Error executing tabel check: " + e.getMessage());
		}
		return true;
	}

  
  public void createT(){
	  String q = "CREATE TABLE "+tname+"("
			  + "uuid varchar(100) NOT NULL,"
			  + "nume varchar(13) NOT NULL,"
			  + "scut int(3) NOT NULL,"
			  + "resurse int(3) NOT NULL,"
			  + "oameni int(3) NOT NULL"
			  + ");";
	  execute(q);
  }
  
  public void execute(String query) {
    this.MySQL = new MySQLFunc(this.HOST, this.DB, this.USER, this.PASS);
    this.con = this.MySQL.open();
    try {
      this.st = this.con.createStatement();
      this.st.execute(query);
    } catch (SQLException e) {
    	System.out.println("[" + this.conName + "] Error executing statement: " + e.getMessage());
    }
    this.MySQL.close(this.con);
  }

  public ResultSet query(String query) {
    this.MySQL = new MySQLFunc(this.HOST, this.DB, this.USER, this.PASS);
    this.con = this.MySQL.open();
    ResultSet rs = null;
    try {
      this.st = this.con.createStatement();
      rs = this.st.executeQuery(query);
    } catch (SQLException e) {
    	System.out.println("[" + this.conName + "] Error executing query: " + e.getMessage());
    }
    return rs;
  }
}