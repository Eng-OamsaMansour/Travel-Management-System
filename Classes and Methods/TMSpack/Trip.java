package TMSpack;
import java.time.LocalDateTime;

public class Trip<Hotel> {
	private String trip_ID;
	private String Location_ID;
	private Hotel booked_hotel;
	private LocalDateTime date;
	private int price;
	private String transportation_way;
	private String trip_path_discreption;
	
	public Trip(String ID,String Location_ID,Hotel booked_hotel,LocalDateTime date,int price,String transportation_way,String trip_path_discreption) {
		this.trip_ID = ID;
		this.Location_ID = Location_ID;
		this.booked_hotel = booked_hotel;
		this.date = date;
		this.price = price;
		this.transportation_way = transportation_way;
		this.trip_path_discreption = trip_path_discreption;
	}

}