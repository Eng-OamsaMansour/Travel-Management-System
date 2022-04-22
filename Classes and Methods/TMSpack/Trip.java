package TMSpack;
import java.time.LocalDateTime;

public class Trip {
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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

}