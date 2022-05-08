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

	public static boolean Reservation(String trip_id, String user_id) {
        boolean user_sucsses = false;
        boolean trip_sucsses = false;
        boolean can_add = false;
        ArrayList<String> user_IDs = new ArrayList<String>();
        ArrayList<String> Trip_IDs = new ArrayList<String>();
        try {
            OracleDataSource user_ods = new OracleDataSource();
            user_ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            user_ods.setUser("c##TMS");
            user_ods.setPassword("123456");
            Connection con = user_ods.getConnection();
            Statement stmt = con.createStatement();
            ResultSet user_result = stmt.executeQuery("select * from PIRSON");
            while (user_result.next()) {
                user_IDs.add(user_result.getString(3));
            }
            con.close();
            boolean user_found = false;
            for (int i = 0; i < user_IDs.size(); i++) {

                if (user_IDs.get(i).equals(user_id)) {

                    user_sucsses = true;
                    user_found = true;
                    break;
                }
            }
            if (user_found) {
                user_sucsses = true;
                OracleDataSource Trip_ods = new OracleDataSource();
                Trip_ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                Trip_ods.setUser("c##TMS");
                Trip_ods.setPassword("123456");
                Connection Tcon = Trip_ods.getConnection();
                Statement Tstmt = Tcon.createStatement();
                ResultSet Trip_result = Tstmt.executeQuery("select * from TRIP");
                while (Trip_result.next()) {
                    Trip_IDs.add(Trip_result.getString(1));
                }
                Tcon.close();
                boolean Trip_found = false;
                for (int i = 0; i < Trip_IDs.size(); i++) {
                    if (Trip_IDs.get(i).equals(trip_id)) {
                        trip_sucsses = true;
                        Trip_found = true;
                        break;
                    }
                }
                if (Trip_found) {
                    OracleDataSource Current_Book_num_data_ds = new OracleDataSource();
                    Current_Book_num_data_ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                    Current_Book_num_data_ds.setUser("c##TMS");
                    Current_Book_num_data_ds.setPassword("123456");
                    Connection book_num_data_con = Current_Book_num_data_ds.getConnection();
                    Statement book_num_data_stmt = book_num_data_con.createStatement();
                    ResultSet book_num_data_result = book_num_data_stmt.executeQuery("SELECT CURRENT_BOOKING_NUMBER FROM TRIP WHERE TRIP_ID = '"+trip_id+"'");
                    book_num_data_result.next();
                    OracleDataSource max_book_num_ds = new OracleDataSource();
                    max_book_num_ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                    max_book_num_ds.setUser("c##TMS");
                    max_book_num_ds.setPassword("123456");
                    Connection max_book_num_con = max_book_num_ds.getConnection();
                    Statement max_book_num_Statement = max_book_num_con.createStatement();
                    ResultSet max_book_num_result = max_book_num_Statement.executeQuery("SELECT MAX_BOOKING_NUM FROM TRIP WHERE TRIP_ID = '"+trip_id+"'");
                    max_book_num_result.next();
                    if (max_book_num_result.getBigDecimal(1).intValue() > book_num_data_result.getBigDecimal(1).intValue()) {                     
                        can_add = true;
                    }
                    max_book_num_con.close();
                    max_book_num_con.close();
                }
            }
        } catch (SQLException ex) {          
            
            Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);          
        }
        System.out.print(user_sucsses +"\n"
                + trip_sucsses + "\n"
                + can_add + "\n");

        if (user_sucsses == true && trip_sucsses == true && can_add == true) {
            Driver driver = new oracle.jdbc.driver.OracleDriver();
            try {

                OracleDataSource Current_Book_num_ds = new OracleDataSource();
                Current_Book_num_ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
                Current_Book_num_ds.setUser("c##TMS");
                Current_Book_num_ds.setPassword("123456");
                Connection book_num_con = Current_Book_num_ds.getConnection();
                Statement book_num_stmt = book_num_con.createStatement();
                ResultSet book_num_result = book_num_stmt.executeQuery("SELECT CURRENT_BOOKING_NUMBER FROM TRIP WHERE TRIP_ID = '"+trip_id+"' ");
                book_num_result.next();
                Driver book_num_driver = new oracle.jdbc.driver.OracleDriver();
                DriverManager.registerDriver(book_num_driver);
                String url1 = "jdbc:oracle:thin:@localhost:1521:xe";
                Connection book_num_con2 = DriverManager.getConnection(url1, "c##TMS", "123456");
                Statement stmt1 = book_num_con2.createStatement();
                String strstm = "UPDATE TRIP SET CURRENT_BOOKING_NUMBER = " + book_num_result.getBigDecimal(1).add(BigDecimal.ONE) + " WHERE TRIP_ID = '"+trip_id+"' ";
                stmt1.execute(strstm);
                book_num_con2.setAutoCommit(false);
                book_num_con2.commit();
                book_num_con2.close();
                book_num_con.close();
                DriverManager.registerDriver(driver);
                String url = "jdbc:oracle:thin:@localhost:1521:xe";
                Connection con = DriverManager.getConnection(url, "c##TMS", "123456");
                PreparedStatement stmt = con.prepareStatement("insert into BOOKTRIP values(?,?)");
                stmt.setString(1, user_id);
                stmt.setString(2, trip_id);
                stmt.executeQuery();
                con.setAutoCommit(false);
                con.commit();
                con.close();
            } catch (SQLException ex) {                
                Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

	public static void Delete_Trip(String ID) {
        try {
            OracleDataSource ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ods.setUser("c##TMS");
            ods.setPassword("123456");
            Connection con = ods.getConnection();
            Statement stmt = con.createStatement();
            stmt.executeQuery("DELETE FROM TRIP WHERE TRIP_ID ='" + ID + "'");
            con.setAutoCommit(false);
            con.commit();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void confirm_trip(String Trip_ID) {
        try {
            String confirm = "confirm";
            OracleDataSource ds = new OracleDataSource();
            ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ds.setUser("c##TMS");
            ds.setPassword("123456");
            Connection con = ds.getConnection();
            Statement book_num_stmt = con.createStatement();
            ResultSet book_num_result = book_num_stmt.executeQuery("UPDATE TRIP SET STATUS = '" + confirm + "' WHERE TRIP_ID ='" + Trip_ID + "' ");
            book_num_result.next();
        } catch (SQLException ex) {
            Logger.getLogger(Trip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<String> castumize_part1(String location) {
        ArrayList<String> hotels_avilabile = new ArrayList<String>();
        try {
            OracleDataSource ds = new OracleDataSource();
            ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ds.setUser("c##TMS");
            ds.setPassword("123456");
            Connection con = ds.getConnection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT HOTEL_ID FROM HOTELS_LOCATION WHERE LOCATION_ID = '" + location + "'");
            while (result.next()) {
                hotels_avilabile.add(result.getString(1));
            }
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(TripB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hotels_avilabile;
    }
}