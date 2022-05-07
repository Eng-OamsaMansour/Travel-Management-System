package TMSpack;

import java.util.ArrayList;

public class Locations {
	private String country;
	private String city;
	private String req_dec;
	private ArrayList<Hotel> avilable_hotels = new ArrayList<Hotel>();

	public Locations(String country,String city,ArrayList<Hotel> avilable_hotels) {
		this.avilable_hotels = avilable_hotels;
		this.city = city;
		this.country = country;
	}
    
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getreq_dec() {
		return req_dec;
	}

	public void setreq_dec(String req_dec) {
		this.req_dec = req_dec;
	}

	public ArrayList<Hotel> getAvilable_hotels() {
		return avilable_hotels;
	}

	public void setAvilable_hotels(ArrayList<Hotel> avilable_hotels) {
		this.avilable_hotels = avilable_hotels;
	}
	
	public void add_location(Locations L){

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
		String [] spilt_id = g.split("L", 2);
		String id_n = spilt_id[1];
		int id_int = Integer.parseInt(id_n);
		id_int++;
		String id ="0";
		id="L"+Integer.toString(id_int);
		
			 Driver driver = new oracle.jdbc.driver.OracleDriver();
	   try{
				DriverManager.registerDriver(driver);
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				Connection con = DriverManager.getConnection(url, "c##TMS", "123456"); 
				/*(LOCATION_ID,REQUIRED_DECUMENTS,CONTRY, CITY)*/
				PreparedStatement stmt = con.prepareStatement("insert into LOCATIONS values(?,?,?,?)");
				stmt.setString(1,id);
				stmt.setString(2, L.getreq_dec());
				stmt.setString(3,L.getCountry());
				stmt.setString(4, L.getCity());
				stmt.executeUpdate();
				con.setAutoCommit(false);
				con.commit();
				con.close();
			}
		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	      
public void delete_location(String id){
    
	try{
		   OracleDataSource ods = new OracleDataSource();
		   ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		   ods.setUser("c##TMS");
		   ods.setPassword("123456");
		   Connection conn = ods.getConnection();
		   Statement stm = conn.createStatement();
		   stm.executeQuery("DELETE FROM HOTEL WHERE LOCATION_ID ='" + id + "'");
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
		   stmt.executeQuery("DELETE FROM TRANSPORTATION_WAYS WHERE LOCATION_ID ='" + id + "'");
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
		   stmt.executeQuery("DELETE FROM HOTELS_LOCATION WHERE LOCATION_ID ='" + id + "'");
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
		   stmt.executeQuery("DELETE FROM TRIP WHERE LOCATION_ID ='" + id + "'");
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
		   stmt.executeQuery("DELETE FROM LOCATIONS WHERE LOCATION_ID ='" + id + "'");
		   con.setAutoCommit(false);
		   con.commit();
		   con.close();
	  }
   catch (Exception e){
	   
	   System.out.println(e.toString());
   }
   }
        
	
}
