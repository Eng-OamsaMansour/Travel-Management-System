package TMSpack;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;
import java.util.*;

public class Hotel {
	private String hotel_ID;
	private int hotel_stars;
	private String hotel_name;
	private String hotel_address;
	
	public Hotel (String hotel_ID,int hotel_stars,String hotel_name,String hotel_address) {
		this.hotel_ID = hotel_ID;
		this.hotel_stars = hotel_stars;
		this.hotel_name = hotel_name;
		this.hotel_address = hotel_address;
	}
	
    public String getHotel_ID() {
		return hotel_ID;
	}

	public void setHotel_ID(String hotel_ID) {
		this.hotel_ID = hotel_ID;
	}

	public int getHotel_stars() {
		return hotel_stars;
	}

	public void setHotel_stars(int hotel_stars) {
		this.hotel_stars = hotel_stars;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getHotel_address() {
		return hotel_address;
	}

	public void setHotel_address(String hotel_address) {
		this.hotel_address = hotel_address;
	}

	public void add_hotel(Hotel H){

		ArrayList<String> ids= new ArrayList<String>();
    
		try{
			OracleDataSource o = new OracleDataSource();
			o.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			o.setUser("c##TMS");
			o.setPassword("123456");
			Connection c = o.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from LOCATIONS");
			while(rs.next()) {  
			ids.add( rs.getString(1));
			}
			c.close();
		}
		catch (Exception e){
			System.out.println( e.toString());
		}
		String g = ids.get(0);
		for(int i=0; i<ids.size();i++){
			int condition = g.compareTo(ids.get(i));
			if (condition<0){
				g = ids.get(i);
			}
		}
		String [] spilt_id = g.split("H", 2);
		String id_n = spilt_id[1];
		int id_int = Integer.parseInt(id_n);
		id_int++;
		String id ="0";
		id="H"+Integer.toString(id_int);
                
			 Driver driver = new oracle.jdbc.driver.OracleDriver();
	   try{
				DriverManager.registerDriver(driver);
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				Connection con = DriverManager.getConnection(url, "c##TMS", "123456"); 
				/*(LOCATION_ID,REQUIRED_DECUMENTS,CONTRY, CITY)*/
				PreparedStatement stmt = con.prepareStatement("insert into HOTEL values(?,?,?,?)");
				stmt.setString(1,id);
				stmt.setString(2, H.getHotel_name());
				stmt.setInt(3,H.getHotel_stars());
				stmt.setString(4, H.getHotel_address());
				stmt.executeUpdate();
				con.setAutoCommit(false);
				con.commit();
				con.close();
			}
		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	        
public void delete_hotel(String id){
    
	try{
		   OracleDataSource ods = new OracleDataSource();
		   ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		   ods.setUser("c##TMS");
		   ods.setPassword("123456");
		   Connection conn = ods.getConnection();
		   Statement stm = conn.createStatement();
		   stm.executeQuery("DELETE FROM TRIP WHERE HOTEL_ID ='" + id + "'");
		   conn.setAutoCommit(false);
		   conn.commit();
		   conn.close();
	  }
   catch (Exception e){
	   
	   System.out.println(e.toString());
   }
	
	
	  try{
		   OracleDataSource od = new OracleDataSource();
		   od.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		   od.setUser("c##TMS");
		   od.setPassword("123456");
		   Connection con = od.getConnection();
		   Statement stmt = con.createStatement();
		   stmt.executeQuery("DELETE FROM ROOM_KINDS WHERE HOTEL_ID ='" + id + "'");
		   con.setAutoCommit(false);
		   con.commit();
		   con.close();
	  }
   catch (Exception e){
	   
	   System.out.println(e.toString());
   }
	  
	  
	  try{
		   OracleDataSource od = new OracleDataSource();
		   od.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		   od.setUser("c##TMS");
		   od.setPassword("123456");
		   Connection con = od.getConnection();
		   Statement stmt = con.createStatement();
		   stmt.executeQuery("DELETE FROM HOTELS_LOCATION WHERE HOTEL_ID ='" + id + "'");
		   con.setAutoCommit(false);
		   con.commit();
		   con.close();
	  }
   catch (Exception e){
	   
	   System.out.println(e.toString());
   }
	  
	  
	  try{
		   OracleDataSource od = new OracleDataSource();
		   od.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		   od.setUser("c##TMS");
		   od.setPassword("123456");
		   Connection con = od.getConnection();
		   Statement stmt = con.createStatement();
		   stmt.executeQuery("DELETE FROM  HOTEL WHERE HOTEL_ID ='" + id + "'");
		   con.setAutoCommit(false);
		   con.commit();
		   con.close();
	  }
   catch (Exception e){
	   
	   System.out.println(e.toString());
   }
	 
   }
        
}
