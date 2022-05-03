package TMSpack;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.pool.OracleDataSource;

public class Trip {
	private String trip_ID;
	private String Location_ID;
	private Hotel booked_hotel;
	private Date date;
	private int price;
	private String transportation_way;
	private String trip_path_discreption;
	
	public Trip(String ID,String Location_ID,Hotel booked_hotel,Date date,int price,String transportation_way,String trip_path_discreption) {
		this.trip_ID = ID;
		this.Location_ID = Location_ID;
		this.booked_hotel = booked_hotel;
		this.date = date;
		this.price = price;
		this.transportation_way = transportation_way;
		this.trip_path_discreption = trip_path_discreption;
	}

	public String getTrip_ID() {
		return trip_ID;
	}

	public void setTrip_ID(String trip_ID) {
		this.trip_ID = trip_ID;
	}

	public String getLocation_ID() {
		return Location_ID;
	}

	public void setLocation_ID(String location_ID) {
		Location_ID = location_ID;
	}

	public Hotel getBooked_hotel() {
		return booked_hotel;
	}

	public void setBooked_hotel(Hotel booked_hotel) {
		this.booked_hotel = booked_hotel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTransportation_way() {
		return transportation_way;
	}

	public void setTransportation_way(String transportation_way) {
		this.transportation_way = transportation_way;
	}

	public String getTrip_path_discreption() {
		return trip_path_discreption;
	}

	public void setTrip_path_discreption(String trip_path_discreption) {
		this.trip_path_discreption = trip_path_discreption;
	}

    public void Add_Trip(Trip newTRIP) {
        ArrayList<String> IDs = new ArrayList<String>();
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("c##TMS");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select * from TRIP");
            while (result.next()) {
                IDs.add(result.getString(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        String graterid = IDs.get(0);
        for (int i = 0; i < IDs.size(); i++) {
            int condition = graterid.compareTo(IDs.get(i));
            if (condition < 0) {
                graterid = IDs.get(i);
            }
        }
        String[] splited_id = graterid.split("T", 2);
        String id_num_part = splited_id[1];
        int INT_ID = Integer.parseInt(id_num_part);
        INT_ID++;
        String id = "0";
        id = "T"+Integer.toString(INT_ID);
         Driver driver = new oracle.jdbc.driver.OracleDriver();
        try {
            DriverManager.registerDriver(driver);
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Connection con = DriverManager.getConnection(url, "c##TMS", "123456");           
            /*(TRIP_ID, DATE_, PRICE, TRIP_LOCATION, TRIP_HOTEL, TRANSPORTATION_WAY, TRIP_PATH_DESCRIPTION, TRIP_KIND, MAX_BOOKING_NUM, CURRENT_BOOKING_NUMBER)*/          
            PreparedStatement stmt = con.prepareStatement("insert into TRIP values(?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, id);
            stmt.setDate(2, newTRIP.date);
            stmt.setInt(3, newTRIP.price);
            stmt.setString(4, newTRIP.Location_ID);
            stmt.setString(5, newTRIP.booked_hotel.getHotel_ID());
            stmt.setString(6, newTRIP.transportation_way);
            stmt.setString(7, newTRIP.trip_path_discreption);
            stmt.setString(8, newTRIP.Trip_Kind);
            stmt.setInt(9, newTRIP.max_booking_num);
            stmt.setInt(10, newTRIP.current_booking_num);
            stmt.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
        } catch (Exception ex) {            
           System.out.print(ex.toString());
        }            
    }
}