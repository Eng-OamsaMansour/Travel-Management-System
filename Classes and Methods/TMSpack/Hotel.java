package TMSpack;

public class Hotel {
	private String hotel_ID;
	private int hotel_stars;
	private String hotel_name;
	private Locations hotel_address;
	
	public Hotel (String hotel_ID,int hotel_stars,String hotel_name,Locations hotel_address) {
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

	public Locations getHotel_address() {
		return hotel_address;
	}

	public void setHotel_address(Locations hotel_address) {
		this.hotel_address = hotel_address;
	}
}
